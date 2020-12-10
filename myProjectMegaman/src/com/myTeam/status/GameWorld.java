package com.myTeam.status;

import com.myTeam.game_object.MegaMan;
import com.myTeam.game_object.PhysicMap;

import java.awt.*;

public class GameWorld {
    private MegaMan megaMan ;
    private PhysicMap physicMap ;

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
