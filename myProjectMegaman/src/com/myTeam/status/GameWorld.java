package com.myTeam.status;

import com.myTeam.game_object.Camera;
import com.myTeam.game_object.MegaMan;
import com.myTeam.game_object.PhysicMap;
import com.myTeam.game_object.*;
import com.myTeam.user_interface.GamePanel;
import com.myTeam.effect.CacheDataLoader;
import com.myTeam.game_object.BulletManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.Object;
import com.myTeam.effect.*;
import com.myTeam.*;
public class GameWorld  extends  MainStatus {
    public MegaMan megaMan = new MegaMan(400.0F, 400.0F, this);
    private PhysicMap physicMap = new PhysicMap(0.0F, 0.0F, this);
    public Camera camera = new Camera(0.0F, 50.0F, 1.0F, 2.0F, this);
    private ObjectManager objectManager;
    private BulletManager bulletManager;

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

    FrameImage avatar = CacheDataLoader.getInstance().getFrameImage("avatar");
    private int numberLife = 3;

    public GameWorld(GamePanel gamePanel) {
        super(gamePanel);
    }

    public void Reset() {
    }

    public BufferedImage getBufferedImage() {
        return null;
    }

    public void setPressedButton(int key) {
    }

    public void setReleasedButton(int key) {
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

    }

    public void Update() {
        megaMan.Update();
        camera.Update();
        physicMap.Update();
    }
    public void Render() {
       /*megaMan.draw(g2);
       physicMap.draw(g2);

    }

    public PhysicMap getPhysicMap() {
        return physicMap;
    }

    public MegaMan getMegaMan() {
        return megaMan;
    }

    public void setPhysicMap(PhysicMap physicMap) {
        this.physicMap = physicMap;
    }

    public void setMegaMan(MegaMan megaMan) {
        this.megaMan = megaMan;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }*/
}
