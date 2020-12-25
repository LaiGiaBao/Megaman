package com.myTeam.status;


import com.myTeam.effect.CacheDataLoader;
import com.myTeam.effect.FrameImage;
import com.myTeam.game_object.BackGround;
import com.myTeam.game_object.BulletManager;
 import com.myTeam.game_object.Camera;
import com.myTeam.game_object.MegaMan;
import com.myTeam.game_object.ObjectO;
import com.myTeam.game_object.ObjectManager;
import com.myTeam.game_object.PhysicMap;
 import com.myTeam.game_object.*;
import com.myTeam.user_interface.GamePanel;
import com.myTeam.effect.CacheDataLoader;
import com.myTeam.game_object.BulletManager;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.lang.Object;
import com.myTeam.effect.*;
import com.myTeam.*;
public class GameWorld  extends  MainStatus {


    private final Object ObjectO;
    public MegaMan megaMan = new MegaMan(400.0F, 400.0F, 100.0F, 100.0F, 100.0F, 100, 50, this);
    private PhysicMap physicMap = new PhysicMap(0.0F, 0.0F, this);
    //public Camera camera1 = new Camera(0.0F,50.0F,1.0F,2.0F,this);
    public Camera camera = new Camera(0.0F, 50.0F, 1.0F, 2.0F, this);
    //public Camera camera1 = new Camera(1,2,3,4,5,this);
    private ObjectManager objectManager;
    private BulletManager bulletManager;
    //public int state = INIT_GAME;
    private BufferedImage bufferedImage = new BufferedImage(1, 2, 2);
    private int lastState;
    public static final int finalBossX = 3600;
    public static final int GAMEPLAY = 2;
    public static final int GAMEOVER = 3;
    public static final int GAMEWIN = 4;
    public static final int PAUSEGAME = 5;
    public static final int MEETFINALBOSS = 1;
    private int numberOfLife = 3;
    public GameWorld gameWorld;
    private Object boss;
    public PhysicMap physicalMap;
    public BackGround backGround;
    public static final int INIT_GAME = 0;
    public static final int TUTORIAL = 1;
    ;

    public static final int INTROGAME = 0;

    FrameImage avatar = CacheDataLoader.getInstance().getFrameImage("avatar");
    private int numberLife = 3;

    public GameWorld(GamePanel gamePanel) {

        super(gamePanel);

        bufferedImage = new BufferedImage(Frame.WIDTH,Frame.HEIGHT, BufferedImage.TYPE_INT_ARGB);
        megaMan = new MegaMan(400, 400,100,100,100,100,100, this);
        physicalMap = new PhysicMap(0,0,this);
        backGround = new BackGround(0,0,this);
        camera = new Camera(0, 50,Frame.WIDTH, Frame.HEIGHT, this);
        bulletManager = new BulletManager(this);

        ObjectO = new ObjectManager(this);
        ObjectManager.addobject(megaMan);

        initEnemies();

     //   bgMusic = CacheDataLoader.getInstance().getSound("bgmusic");
    }
    public void switchState(int state) {
      //  previousState = this.state
    }
    private void initEnemies() {

    }
    public void Reset() {
    }

    public BufferedImage getBufferedImage() {
        return null;
    }

    @Override
    public void processedKeyPress(int keyCode) {

    }

    /*
        @Override
        public void processedKeyPress(int keyCode) {

        }

        @Override
        public void processedKeyRelease(int keyCode) {

        }
    */
    @Override
    public void processedKeyRelease(int keyCode) {
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
            //cai nay tui bay lam lai dum

            case KeyEvent.VK_ENTER:
                /*if(state == GameWorld.INIT_GAME){
                    if(previousState == gameWorld.GAMEPLAY)
                        switchState(gameWorld.GAMEPLAY);
                    else switchState(gameWorld.TUTORIAL);

                    //bgMusic.loop();
                    //bgMusic.play();
                }
                if(state == gameWorld.TUTORIAL && storyTutorial >= 1){
                    if(storyTutorial<=3){
                        storyTutorial ++;
                        currentSize = 1;
                        textTutorial = texts1[storyTutorial-1];
                    }else{
                        switchState(GameWorldState.GAMEPLAY);
                    }

                    // for meeting boss tutorial
                    if(tutorialState == GameWorldState.MEETFINALBOSS){
                        switchState(GameWorldState.GAMEPLAY);
                    }
                }
                break;
*/
            case KeyEvent.VK_SPACE:
                megaMan.jump();
                break;

            case KeyEvent.VK_A:
                megaMan.attack();
                break;

        }
    }


    public void Update() {
        this.megaMan.Update();
        this.camera.Update();
        this.physicMap.Update();
    }

    public void Render() {
    }

    public PhysicMap getPhysicMap() {
        return this.physicMap;
    }

    public MegaMan getMegaMan() {
        return this.megaMan;
    }

    public void setPhysicMap(PhysicMap physicMap) {
        this.physicMap = physicMap;
    }

    public void setMegaMan(MegaMan megaMan) {
        this.megaMan = megaMan;
    }

    public void setObjectManager(ObjectManager objectManager) {
        this.objectManager = objectManager;
    }

    public ObjectManager getObjectManager() {
        return objectManager;
    }

    public Camera getCamera() {
        return this.camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
   /* public MegaMan megaMan ;
    private PhysicMap physicMap ;
    public Camera camera;
    private ObjectManager objectManager;
    private BulletManager bulletManager;
    private BufferedImage bufferedImage;
    // cac thai trc
    private int lastState;
    public static final int finalBossX = 3600;
    //cac trang thai cho gameworld
    //public static final int INIT_GAME = 0;
    //public static final int TUTORIAL = 1;
    public static final int GAMEPLAY = 2;
    public static final int GAMEOVER = 3;
    public static final int GAMEWIN = 4;
    public static final int PAUSEGAME = 5;

    //public static final int INTROGAME = 0;
    public static final int MEETFINALBOSS = 1;
    private int numberOfLife = 3;
    public GameWorld gameWorld;
    //Boss
    private Object boss;
    FrameImage avatar = CacheDataLoader.getInstance().getFrameImage("avatar");

    private int numberLife = 3;

    public GameWorld(GamePanel gamePanel) {
        super(gamePanel);
    //    gameWorld = new GameWorld(this);
    //    megaMan= new MegaMan(300,300,this);
    //    physicMap = new PhysicMap(0,0,this);
     //   camera = new Camera(0,50,Frame.WIDTH,Frame.HEIGHT,this);
        bufferedImage = new BufferedImage(Frame.WIDTH,Frame.HEIGHT,BufferedImage.TYPE_INT_ARGB);
        megaMan = new MegaMan(400,400, this);
        physicMap= new PhysicMap(0,0,this);
        camera = new Camera(0,50,Frame.WIDTH,Frame.HEIGHT,this);
    }

    @Override
    
    public void Reset() {

    }




    @Override
    public BufferedImage getBufferedImage() {
        return null;
    }

    @Override
    public void setPressedButton(int key) {

    }

    @Override
    public void setReleasedButton(int key) {

=======
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
    ObjectO boss;
    
    FrameImage avatar = CacheDataLoader.getInstance().getFrameImage("avatar");
    
    
    private int numOfLife = 3;
    
    
    public GameWorld(GamePanel gamePanel){
            super(gamePanel);

        
        bufferedImage = new BufferedImage(Frame.SCREEN_WIDTH, Frame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        megaMan = new MegaMan(300,300 ,this );
        physicMap = new PhysicMap(0, 0, this);
        backGround = new BackGround(0, 0, this);
        camera = new Camera(0, 50, Frame.SCREEN_WIDTH, Frame.SCREEN_HEIGHT, this);
        bulletManager = new BulletManager(this);
        
        objectManager = new ObjectManager(this);
        objectManager.addObject(megaMan);
        
        initEnemies();
        
    }
    
    private void initEnemies(){
        ObjectO redbot = new RedBot(1300, 440, this);
        redbot.setDirection(ObjectO.LEFTDIR);
        redbot.setTeamType(ObjectO.ENEMYTEAM);
        objectManager.addObject(redbot);
        
        // se tao them nhieu con sau 
        
        


    public void Update() {
        megaMan.Update();
        camera.Update();
        physicMap.Update();
    }
    public void Render() {
       /*megaMan.draw(g2);
       physicMap.draw(g2);

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
                        megaMan.setDirection(ObjectO.RIGHTDIR);
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
                
                if(megaMan.getState() == ObjectO.DEATH){
                    numOfLife = numOfLife - 1 ;
                    if(numOfLife >= 0){
                        megaMan.setBlood(100);
                        megaMan.setPosY(megaMan.getPosY() - 50);
                        megaMan.setState(ObjectO.NOBEHURT);
                        objectManager.addObject(megaMan);
                    }
                    else{
                        switchState(GAMEOVER);

                    }
                }
                if(!finalbossTrigger && boss.getState() == ObjectO.DEATH)
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

    public PhysicMap getPhysicMap() {
        return physicMap;
    }

    public void setPhysicMap(PhysicMap physicMap) {
        this.physicMap = physicMap;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}


    */
}