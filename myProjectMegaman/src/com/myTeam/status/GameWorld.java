package com.myTeam.status;

import com.myTeam.game_object.MegaMan;
import com.myTeam.game_object.PhysicMap;
import com.myTeam.effect.CacheDataLoader;
import com.myTeam.effect.FrameImage;
import com.myTeam.game_object.Camera;
import com.myTeam.game_object.BackGround;
import com.myTeam.game_object.Object;
import com.myTeam.game_object.ObjectManager;
import java.awt.*;

public class GameWorld {
    public MegaMan megaMan ;
    public PhysicMap physicMap ;
    public BackGround background;
    public Camera camera;
    public ObjectManager ObjectManager;
    
    public GameWorld() {
        megaMan= new MegaMan(300,300,100,100,0.1f,this);
        physicMap = new PhysicMap(0,0,this);
    }

    public void Update() {
        megaMan.Update();
    }
    public void Render(Graphics2D graphics2D) {
       // megaMan.draw(g2);
        //physicMap.draw(g2);
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
}
