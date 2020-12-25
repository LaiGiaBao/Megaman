package com.myTeam.game_object;

import java.awt.*;
public class GameWorld {
    public PhysMap phympa ;
    public GameObject megaman ;
    public GameWorld() {
        phympa = new PhysMap(0,0);
        megaman = new GameObject(300,300,100,100,0.1f);
    }
    public void Update() {
        megaman.update();
    }
    public void Render(Graphics2D g) {
        megaman.draw(g);
        phympa.draw(g);
    }

    /*public PhysMap getPhympa() {
        return phympa;
    }

    public GameObject getMegaman() {
        return megaman;
    }

    public void setPhympa(PhysMap phympa) {
        this.phympa = phympa;
    }

    public void setMegaman(GameObject megaman) {
        this.megaman = megaman;
    }*/
}
