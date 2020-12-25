package com.myTeam.game_object;
import com.myTeam.effect.CacheDataLoader;
import java.awt.*;
import java.util.*;
public class PhysMap {
    public int [][] physmap;
    private int sideofsquares;
    public float posX,posY;
    public PhysMap(float posX,float posY) {
        this.posX=posX;
        this.posY=posY;
        this.sideofsquares=20;
        physmap= CacheDataLoader.getInstance().getPhysmap();
    }
    public void setSideofsquares(int sideofsquares) {
        this.sideofsquares = sideofsquares;
    }
    public int getSideofsquares() {
        return sideofsquares;
    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        for (int i=0;i<physmap.length;i++) {
            for (int j=0;j<physmap[0].length;j++) {
                if (physmap[i][j] != 0 ) {
                    g2.fillRect((int) posX+j*sideofsquares,(int) posY+i*sideofsquares,sideofsquares,sideofsquares);
                }
                }
        }
    }
    public void colliseMap() {

    }
}
