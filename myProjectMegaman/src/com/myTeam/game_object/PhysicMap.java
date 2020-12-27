package com.myTeam.game_object;

import com.myTeam.effect.CacheDataLoader;
import com.myTeam.status.GameWorld;

import org.w3c.dom.css.Rect;

import java.awt.*;

public class PhysicMap extends GameObject {
    public int [][] physmap;
    private int tileSize;

    public PhysicMap(float posX,float posY,GameWorld gameWorld){
        super(posX,posY,gameWorld);
        this.tileSize= 30;
        physmap = CacheDataLoader.getInstance().getPhysmap();
    }

    @Override
    public void Update() {

    }

    public void draw(Graphics2D g2){
        Camera camera = getGameWorld().camera;

        g2.setColor(Color.GRAY);
        for(int i = 0;i< physmap.length;i++)
            for(int j = 0 ; j<physmap[0].length;j++)
                if(physmap[i][j]!=0) g2.fillRect((int) getPosX() + j*tileSize - (int) camera.getPosX(),
                        (int) getPosY() + i*tileSize - (int) camera.getPosY(), tileSize, tileSize);

    }

    public Rectangle collisionWithLand(Rectangle rect){
        int posLeft = rect.x/tileSize;
        posLeft -=2;
        int posRight = (rect.x + rect.width)/tileSize;
        posRight +=2;
        int posBot = (rect.y+rect.height)/tileSize;
        if(posLeft<0) posLeft =0;

        if(posRight>=physmap[0].length) posRight = physmap[0].length -1;
        for(int y = posBot;y< physmap.length;y++){
            for(int x =posLeft;x<=posRight;x++){
                if(physmap[y][x]==1){
                    Rectangle r = new Rectangle((int) getPosX()+x*tileSize,(int)getPosY()+y*tileSize,tileSize,tileSize);
                    if(rect.intersects(r))
                        return r;
                }
            }
        }
        return null;
    }
    //va cham phai
    public Rectangle collisionWithRight(Rectangle rect){
        int posY1 = rect.y/tileSize;
        posY1-=2;
        int posY2 = (rect.y + rect.height)/tileSize;
        posY2+=2;

        int posX1 = (rect.x + rect.width)/tileSize;
        int posX2 = posX1 + 3;
        if(posX2 >= physmap[0].length) posX2 = physmap[0].length - 1;

        if(posY1 < 0) posY1 = 0;
        if(posY2 >= physmap.length) posY2 = physmap.length - 1;


        for(int x = posX1; x <= posX2; x++){
            for(int y = posY1; y <= posY2;y++){
                if(physmap[y][x] == 1){
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if(r.y < rect.y + rect.height - 1 && rect.intersects(r))
                        return r;
                }
            }
        }
        return null;

    }

    public Rectangle collisionWithLeft(Rectangle rect){
        int posY1 = rect.y/tileSize;
        posY1-=2;
        int posY2 = (rect.y + rect.height)/tileSize;
        posY2+=2;

        int posX1 = (rect.x + rect.width)/tileSize;
        int posX2 = posX1 - 3;
        if(posX2 < 0) posX2 = 0;

        if(posY1 < 0) posY1 = 0;
        if(posY2 >= physmap.length) posY2 = physmap.length - 1;


        for(int x = posX1; x >= posX2; x--){
            for(int y = posY1; y <= posY2;y++){
                if(physmap[y][x] == 1){
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if(r.y < rect.y + rect.height - 1 && rect.intersects(r))
                        return r;
                }
            }
        }
        return null;

    }

    public Rectangle collisionWithTop(Rectangle rect){
        int posX1 = rect.x/tileSize;
        posX1 -= 2;
        int posX2 = (rect.x + rect.width)/tileSize;
        posX2 += 2;

        //int posY = (rect.y + rect.height)/tileSize;
        int posY = rect.y/tileSize;

        if(posX1 < 0) posX1 = 0;

        if(posX2 >= physmap[0].length) posX2 = physmap[0].length - 1;

        for(int y = posY; y >= 0; y--){
            for(int x = posX1; x <= posX2; x++){

                if(physmap[y][x] == 1){
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if(rect.intersects(r))
                        return r;
                }
            }
        }
        return null;
    }

    public int getTileSize(){
        return tileSize;
    }


}
