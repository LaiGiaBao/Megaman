package com.myTeam.status;

import com.myTeam.effect.CacheDataLoader;
import com.myTeam.effect.FrameImage;
import com.myTeam.game_object.BackGround;
import com.myTeam.game_object.BulletManager;
import com.myTeam.game_object.Camera;
import com.myTeam.game_object.Boss;
import com.myTeam.game_object.MegaMan;
import com.myTeam.game_object.Object;
import com.myTeam.game_object.ObjectManager;
import com.myTeam.game_object.PhysicMap;
import com.myTeam.game_object.RedBot;
import com.myTeam.user_interface.Frame;
import com.myTeam.user_interface.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public class GameWorld extends MainStatus {
	
    private BufferedImage bufferedImage;
    private int lastState;

    public ObjectManager objectManager;
    public BulletManager bulletManager;

    public MegaMan megaMan;
   
    public PhysicMap physicMap;
    public BackGround backGround;
    public Camera camera;

    public static final int finalBossX = 3600;
    
    public static final int START = 0;
    public static final int TUTORIAL = 1;
    public static final int GAMEPLAY = 2;
    public static final int GAMEOVER = 3;
    public static final int GAMEWIN = 4;
    public static final int PAUSE = 5;
    
    public static final int FINALBOSS = 0;
    
    public int openIntroGameY = 0;
    public int state = START;
    public int previousState = state;
    public int tutorialState = FINALBOSS;
    
    public int storyTutorial = 0;
    public int currentSize = 1;
    
    private boolean finalbossTrigger = true;
    Object boss;
    
    FrameImage avatar = CacheDataLoader.getInstance().getFrameImage("avatar");
    
    
    private int numOfLife = 3;
    
    
    public GameWorld(GamePanel gamePanel){
            super(gamePanel);

        
        bufferedImage = new BufferedImage(Frame.SCREEN_WIDTH, Frame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        //megaMan = new MegaMan(, , );
        physicMap = new PhysicMap(0, 0, this);
        backGround = new BackGround(0, 0, this);
        camera = new Camera(0, 50, Frame.SCREEN_WIDTH, Frame.SCREEN_HEIGHT, this);
        bulletManager = new BulletManager(this);
        
        objectManager = new ObjectManager(this);
        objectManager.addObject(megaMan);
        
        initEnemies();
        
    }
    
    private void initEnemies(){
        Object redbot = new RedBot(1300, 440, this);
        redbot.setDirection(Object.LEFTDIR);
        redbot.setTeamType(Object.ENEMYTEAM);
        objectManager.addObject(redbot);
        
        // se tao them nhieu con sau 
        
        

     //
    }

    public void switchState(int state){
        previousState = this.state;
        this.state = state;
    }
    
    private void TutorialUpdate(){
        switch(tutorialState){
                
            case FINALBOSS:
                if(storyTutorial == 0){
                    if(openIntroGameY >= 450) {
                        openIntroGameY-=1;
                    }
                    if(camera.getPosX() < finalBossX){
                        camera.setPosX(camera.getPosX() + 2);
                    }
                    
                    if(megaMan.getPosX() < finalBossX + 150){
                        megaMan.setDirection(Object.RIGHTDIR);
                        megaMan.run();
                        megaMan.Update();
                    }
                    
                    else
                    {
                        megaMan.stopRun();
                    }
                    
                    if(openIntroGameY < 450 && camera.getPosX() >= finalBossX && megaMan.getPosX() >= finalBossX + 150){ 
                        camera.lock();
                        storyTutorial++;
                        megaMan.stopRun();
                        physicMap.physmap[14][120] = 1;
                        physicMap.physmap[15][120] = 1;
                        physicMap.physmap[16][120] = 1;
                        physicMap.physmap[17][120] = 1;
                        
                        backGround.map[14][120] = 17;
                        backGround.map[15][120] = 17;
                        backGround.map[16][120] = 17;
                        backGround.map[17][120] = 17;
                    }
                    
                }
                break;
        }
    }
    
    private void drawString(Graphics2D g2, String text, int x, int y){
        for(String str : text.split("\n"))
            g2.drawString(str, x, y+=g2.getFontMetrics().getHeight());
    }
    
    private void TutorialRender(Graphics2D g2){
        switch(tutorialState){
            
            case FINALBOSS:
            	int Pos_y_Mid = Frame.SCREEN_HEIGHT/2 - 15;
                int Pos_y1 = Pos_y_Mid - Frame.SCREEN_HEIGHT/2 - openIntroGameY/2;
                int Pos_y2 = Pos_y_Mid + openIntroGameY/2;
                
            	Pos_y_Mid = Frame.SCREEN_HEIGHT/2 - 15;
            	Pos_y1 = Pos_y_Mid - Frame.SCREEN_HEIGHT/2 - openIntroGameY/2;
            	Pos_y2 = Pos_y_Mid + openIntroGameY/2;

                g2.setColor(Color.BLACK);
                g2.fillRect(0, Pos_y1, Frame.SCREEN_WIDTH, Frame.SCREEN_HEIGHT/2);
                g2.fillRect(0, Pos_y2, Frame.SCREEN_WIDTH, Frame.SCREEN_HEIGHT/2);
                break;
        }
    }
    
    public void Update(){
        
        switch(state){
            case START:
                
                break;
            case TUTORIAL:
                TutorialUpdate();
                
                break;
            case GAMEPLAY:
                objectManager.UpdateObjects();
                bulletManager.UpdateObjects();
        
                physicMap.Update();
                camera.Update();
                
                
                if(megaMan.getPosX() > finalBossX && finalbossTrigger){
                    finalbossTrigger = false;
                    switchState(TUTORIAL);
                    tutorialState = FINALBOSS;
                    storyTutorial = 0;
                    openIntroGameY = 650;
                    
                    //boss = new Boss(); // khi nao boss xong thi bo vao 
                    //boss.setTeamType(Object.ENEMYTEAM);
                    //boss.setDirection(Object.LEFTDIR);
                    //objectManager.addObject(boss);

                }
                
                if(megaMan.getState() == Object.DEATH){
                    numOfLife = numOfLife - 1 ;
                    if(numOfLife >= 0){
                        megaMan.setBlood(100);
                        megaMan.setPosY(megaMan.getPosY() - 50);
                        megaMan.setState(Object.NOBEHURT);
                        objectManager.addObject(megaMan);
                    }
                    else{
                        switchState(GAMEOVER);
                        //bgMusic.stop(); them nhac thi them 
                    }
                }
                if(!finalbossTrigger && boss.getState() == Object.DEATH)
                    switchState(GAMEWIN);
                
                break;
            case GAMEOVER:
                
                break;
            case GAMEWIN:
                
                break;
        }
        

    }

    public void Render(){

        Graphics2D g2 = (Graphics2D) bufferedImage.getGraphics();

        if(g2!=null){
            
            switch(state){
                case START:
                    g2.setColor(Color.BLACK);
                    g2.fillRect(0, 0, Frame.SCREEN_WIDTH, Frame.SCREEN_HEIGHT);
                    g2.setColor(Color.WHITE);
                    g2.drawString("PRESS ENTER TO CONTINUE", 400, 300);
                    break;
                    
                case PAUSE:
                    g2.setColor(Color.BLACK);
                    g2.fillRect(300, 260, 500, 70);
                    g2.setColor(Color.WHITE);
                    g2.drawString("PRESS ENTER TO CONTINUE", 400, 300);
                    break;
                    
                case TUTORIAL:
                    backGround.draw(g2);
                    if(tutorialState == FINALBOSS){
                        objectManager.draw(g2);
                    }
                    TutorialRender(g2);
                    
                    break;
                    
                case GAMEWIN:
                	
                case GAMEPLAY:
                    backGround.draw(g2);
                    objectManager.draw(g2);  
                    bulletManager.draw(g2);
                    
                    g2.setColor(Color.GRAY);
                    g2.fillRect(19, 59, 102, 22);
                    g2.setColor(Color.red);
                    g2.fillRect(20, 60, megaMan.getBlood(), 20);
                    
                    for(int i = 0; i < numOfLife; i++){
                        g2.drawImage(CacheDataLoader.getInstance().getFrameImage("hearth").getImage(), 20 + i*40, 18, null);
                    }
                    
                    
                    if(state == GAMEWIN){
                        g2.drawImage(CacheDataLoader.getInstance().getFrameImage("gamewin").getImage(), 300, 300, null);
                    }
                    
                    break;
                    
                case GAMEOVER:
                    g2.setColor(Color.BLACK);
                    g2.fillRect(0, 0, Frame.SCREEN_WIDTH, Frame.SCREEN_HEIGHT);
                    g2.setColor(Color.WHITE);
                    g2.drawString("GAME OVER!", 450, 300);
                    break;

            }
            

        }

    }

    public BufferedImage getBufferedImage(){
        return bufferedImage;
    }

    @Override
    public void processedKeyPress(int keyCode) {
       switch(keyCode){
            
            case KeyEvent.VK_DOWN:
                megaMan.dick();
                break;
                
            case KeyEvent.VK_RIGHT:
                megaMan.setDirection(megaMan.RIGHTDIR);
                megaMan.run();
                break;
                
            case KeyEvent.VK_LEFT:
                megaMan.setDirection(megaMan.LEFTDIR);
                megaMan.run();
                break;
                
            case KeyEvent.VK_ENTER:
                if(state == GameWorld.START){
                    if(previousState == GameWorld.GAMEPLAY)
                        switchState(GameWorld.GAMEPLAY);
                    else switchState(GameWorld.TUTORIAL);
                    
                }
                if(state == GameWorld.TUTORIAL && storyTutorial >= 1)
                {
                    
                    {
                        switchState(GameWorld.GAMEPLAY);
                    }
                    
                    // gap boss
                    if(tutorialState == GameWorld.FINALBOSS){
                        switchState(GameWorld.GAMEPLAY);
                    }
                }
                break;
                
            case KeyEvent.VK_SPACE:
                megaMan.jump();
                break;
                
            case KeyEvent.VK_C:
                megaMan.attack();
                break;
                
        }
    }

    @Override
    public void processedKeyRelease(int keyCode) {
        switch(keyCode){
            
            case KeyEvent.VK_UP:
                
                break;
                
            case KeyEvent.VK_DOWN:
                megaMan.standUp();
                break;
                
            case KeyEvent.VK_RIGHT:
                if(megaMan.getSpeedX() > 0)
                    megaMan.stopRun();
                break;
                
            case KeyEvent.VK_LEFT:
                if(megaMan.getSpeedX() < 0)
                    megaMan.stopRun();
                break;
                
            case KeyEvent.VK_ENTER:
                if(state == GAMEOVER || state == GAMEWIN) {
                    panel.setState(new Menu(panel));
                } 
                
                else if(state == PAUSE) {
                    state = lastState;
                }
                
                break;
                
            case KeyEvent.VK_SPACE:
                
                break;
                
            case KeyEvent.VK_C:
                
                break;
                
            case KeyEvent.VK_ESCAPE:
                lastState = state;
                state = PAUSE;
                break;
                
        }
        
        }

	
}
