package com.myTeam.game_object;

import com.myTeam.effect.CacheDataLoader;
import com.myTeam.effect.FrameImage;
import com.myTeam.user_interface.Frame;
import com.myTeam.game_object.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameWorld {
    private BufferedImage bufferedImage;
    public PhysicMap physicalMap;
    public MegaMan megaman;

    public Camera camera;

    public BackGround backgroundMap;

    public BulletManager bulletManager;

    public ObjectManager objectManager;

    private boolean finalbossTrigger = true;

    public static final int INIT_GAME = 0;
    public static final int TUTORIAL = 1;
    public static final int GAMEPLAY = 2;
    public static final int GAMEOVER = 3;
    public static final int GAMEWIN = 4;
    public static final int PAUSEGAME = 5;

    public static final int INTROGAME = 0;
    public static final int MEETFINALBOSS = 1;

    public static final int finalBossX = 3600;

    public int openIntroGameY = 0;
    public int state = INIT_GAME;
    public int previousState = state;
    public int tutorialState = INTROGAME;

    public int storyTutorial = 0;
    public String[] texts1 = new String[4];

    public String textTutorial;
    public int currentSize = 1;
    ObjectO boss;
    FrameImage avatar = CacheDataLoader.getInstance().getFrameImage("avatar");


    private int numberOfLife = 3;
    public GameWorld(){

        texts1[0] = "We are heros, and our mission is protecting our Home\nEarth....";
        texts1[1] = "There was a Monster from University on Earth in 10 years\n"
                + "and we lived in the scare in that 10 years....";
        texts1[2] = "Now is the time for us, kill it and get freedom!....";
        texts1[3] = "      LET'S GO!.....";
        textTutorial = texts1[0];
        megaman = new MegaMan(300,300,this);
        megaman.setTeamType(ObjectO.LEAGUETEAM);
        physicalMap = new PhysicMap(0,0,this);
        camera = new Camera(0,0, Frame.SCREEN_WIDTH,Frame.SCREEN_HEIGHT, this);

        bulletManager = new BulletManager(this);
        backgroundMap = new BackGround(0,0,this);

        objectManager = new ObjectManager(this);
        objectManager.addObject(megaman);
        bufferedImage = new BufferedImage(Frame.SCREEN_WIDTH, Frame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);

        initEnemies();
    }

    private void initEnemies(){
        ObjectO redbot = new RedBot(1250,410,this);
        redbot.setDirection(ObjectO.LEFTDIR);
        redbot.setTeamType(ObjectO.ENEMYTEAM);
        objectManager.addObject(redbot);
    }
    public void switchState(int state){
        previousState = this.state;
        this.state = state;
    }

    private void TutorialUpdate(){
        switch(tutorialState){
            case INTROGAME:

                if(storyTutorial == 0){
                    if(openIntroGameY < 450) {
                        openIntroGameY+=4;
                    }else storyTutorial ++;

                }else{

                    if(currentSize < textTutorial.length()) currentSize++;
                }
                break;
            case MEETFINALBOSS:
                if(storyTutorial == 0){
                    if(openIntroGameY >= 450) {
                        openIntroGameY-=1;
                    }
                    if(camera.getPosX() < finalBossX){
                        camera.setPosX(camera.getPosX() + 2);
                    }

                    if(megaman.getPosX() < finalBossX + 150){
                        megaman.setDirection(ObjectO.RIGHTDIR);
                        megaman.run();
                        megaman.Update();
                    }else{
                        megaman.stopRun();
                    }

                    if(openIntroGameY < 450 && camera.getPosX() >= finalBossX && megaman.getPosX() >= finalBossX + 150){
                        camera.lock();
                        storyTutorial++;
                        megaman.stopRun();
                        physicalMap.physmap[14][120] = 1;
                        physicalMap.physmap[15][120] = 1;
                        physicalMap.physmap[16][120] = 1;
                        physicalMap.physmap[17][120] = 1;

                        backgroundMap.map[14][120] = 17;
                        backgroundMap.map[15][120] = 17;
                        backgroundMap.map[16][120] = 17;
                        backgroundMap.map[17][120] = 17;
                    }

                }else{

                    if(currentSize < textTutorial.length()) currentSize++;
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
            case INTROGAME:
                int yMid = Frame.SCREEN_HEIGHT/2 - 15;
                int y1 = yMid - Frame.SCREEN_HEIGHT/2 - openIntroGameY/2;
                int y2 = yMid + openIntroGameY/2;

                g2.setColor(Color.BLACK);
                g2.fillRect(0, y1, Frame.SCREEN_WIDTH, Frame.SCREEN_HEIGHT/2);
                g2.fillRect(0, y2, Frame.SCREEN_WIDTH, Frame.SCREEN_HEIGHT/2);

                if(storyTutorial >= 1){
                    g2.drawImage(avatar.getImage(), 600, 350, null);
                    g2.setColor(Color.BLUE);
                    g2.fillRect(280, 450, 350, 80);
                    g2.setColor(Color.WHITE);
                    String text = textTutorial.substring(0, currentSize - 1);
                    drawString(g2, text, 290, 480);
                }

                break;
            case MEETFINALBOSS:
                yMid = Frame.SCREEN_HEIGHT/2 - 15;
                y1 = yMid - Frame.SCREEN_HEIGHT/2 - openIntroGameY/2;
                y2 = yMid + openIntroGameY/2;

                g2.setColor(Color.BLACK);
                g2.fillRect(0, y1, Frame.SCREEN_WIDTH, Frame.SCREEN_HEIGHT/2);
                g2.fillRect(0, y2, Frame.SCREEN_WIDTH, Frame.SCREEN_HEIGHT/2);
                break;
        }
    }
    public void Update(){
        switch(state){
            case INIT_GAME:

                break;
            case TUTORIAL:
                TutorialUpdate();

                break;
            case GAMEPLAY:
                objectManager.UpdateObjects();
                bulletManager.UpdateObjects();

                physicalMap.Update();
                camera.Update();


                if(megaman.getPosX() > finalBossX && finalbossTrigger){
                    finalbossTrigger = false;
                    switchState(TUTORIAL);
                    tutorialState = MEETFINALBOSS;
                    storyTutorial = 0;
                    openIntroGameY = 550;

                    boss = new Boss(finalBossX + 700, 460, this);
                    boss.setTeamType(ObjectO.ENEMYTEAM);
                    boss.setDirection(ObjectO.LEFTDIR);
                    objectManager.addObject(boss);

                }

                if(megaman.getState() == ObjectO.DEATH){
                    numberOfLife --;
                    if(numberOfLife >= 0){
                        megaman.setBlood(100);
                        megaman.setPosY(megaman.getPosY() - 50);
                        megaman.setState(ObjectO.NOBEHURT);
                        objectManager.addObject(megaman);
                    }else{
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

    public void Render(Graphics2D g2){
        backgroundMap.draw(g2);
        objectManager.draw(g2);
        bulletManager.draw(g2);

    }

    public ObjectManager getObjectManager() {
        return objectManager;
    }

    public void setObjectManager(ObjectManager objectManager) {
        this.objectManager = objectManager;
    }
}
