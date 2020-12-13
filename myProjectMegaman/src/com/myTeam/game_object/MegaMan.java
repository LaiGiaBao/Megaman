package com.myTeam.game_object;

import com.myTeam.status.GameWorld;
import com.myTeam.effect.Animation;
import com.myTeam.effect.CacheDataLoader;

import java.awt.*;

public class MegaMan extends Character {
    private float posX;
    private float posY;
    private float speedX;
    private  float mass;
    private float DIR_LEF;
    private float DIR_RIGHT;
    private float direction;
    private float width;
    private float height;
    GameWorld gameWorld;
    private float speedY;
    public MegaMan(float posX,float posY,float width,float height,float mass,GameWorld gameWorld) {
        this.posX=posX;
        this.posY=posY;
        this.width=width;
        this.height=height;
        this.mass=mass;
        this.gameWorld=gameWorld;
    }
    // tao 1 hinh chu nhat bao quanh nhan vat de xu ly' va cham vs Map vs creeps
    public Rectangle getBoundsofmegaman() {
        Rectangle rectangle = new Rectangle();
        rectangle.x=(int) (getPosX()-getWidth()/2);
        rectangle.y=(int) (getPosY()-getHeight()/2);
        rectangle.width=(int) getWidth();
        rectangle.height=(int) getHeight();
        return  rectangle;
    }
    public void Update() {
        setPosX(getPosX()+getSpeedX());
        // vi tri' moi cua nhan vat
        Rectangle rec1 = getBoundsofmegaman();
        rec1.y += getPosY();
        // hinh chu nhat dai dien cho lane
        Rectangle rec1land = gameWorld.getPhysicMap().collisionland(rec1);
        if (rec1land!=null) {
        //
            setPosY(rec1land.y-getHeight()/2);
        }
        else {
            // neu ko cham dat thi roi
            setPosY(getSpeedY()+speedY);
            setSpeedY(getSpeedY()+mass);
        }
    }
   /* public void draw(Graphics2D g) {
        g.setColor(Color.ORANGE);
        g.fillRect((i));
    }*/
    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public void setHeight(
            float height) {
        this.height = height;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    @Override
    public void setPosX(float posX) {
        this.posX = posX;
    }

    @Override
    public void setPosY(float posY) {
        this.posY = posY;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getSpeedX() {
        return speedX;
    }

    public float getHeight() {
        return height;
    }

    public float getMass() {
        return mass;
    }

    @Override
    public float getPosX() {
        return posX;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public float getSpeedY() {
        return speedY;
    }

    @Override
    public float getPosY() {
        return posY;
    }

    public float getWidth() {
        return width;
    }
}
