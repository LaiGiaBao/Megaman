package com.myTeam.game_object;

import java.awt.*;

public class GameObject {
    private float posX;
    private float posY;
    private float width;
    private float height;
    private float mass;//de tinh toc do roi nhanh hay chap
    private float speedX;
//<<<<<<< HEAD
    private float speddY;
    private int DIR_LEFT;// xác định hướng megaman bắn đạn khi đứnng yên
//=======
    private float speedY;
   // private int DIR_LEFT;
//>>>>>>> 8583ca9107abd5105c1030bdec86831aa0ef1036
    private int DIR_RIGHT;
    private int dicrection;// len xuong;
    public GameObject(float posX,float posY,float width,float height,float mass) {
        this.posX=posX;
        this.posY=posY;
        this.width=width;
        this.height=height;
        this.mass=mass;
    }
    //update la posX va speedX để xác hướng bắn đạn
    public void update() {
        setPosX(getPosX()+getSpeedX());
        setPosY(getPosY()+getSpeedY());
        setSpeedY(getSpeedY()+getMass());// gia tốc rơi của mọi GameObject khi ko nhảy ( mass cang lon' roi cang nhanh)
        if (getPosY() > 400 ) setPosY(400);
        else setPosY(getPosY()+getSpeedY());
    }
    public void draw(Graphics2D g) {
        g.setColor(Color.ORANGE);
        g.fillRect((int) posX, (int) posY, (int) width, (int) height);
    }

    public float getHeight() {
        return height;
    }

    public float getMass() {
        return mass;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public float getWidth() {
        return width;
    }

    public int getDirection() {
        return dicrection;
    }

    public float getSpeedY() {
        return speedY;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setDirection(int dicrection) {
        this.dicrection = dicrection;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public void setWidth(float width) {
        this.width = width;
    }
    
   //  public abstract void Refresh(); //them method lam moi
}

