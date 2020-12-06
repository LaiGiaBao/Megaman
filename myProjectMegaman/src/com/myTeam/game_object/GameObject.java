package com.myTeam.game_object;

import java.awt.*;

public abstract class GameObject {
    private float posX;
    private float posY;
    private float width;
    private float height;
    private float mass;//de tinh toc do roi nhanh hay chap
    private float speedX;
    private float speedY;
    private int DIR_LEFT;
    private int DIR_RIGHT;
    private int dicrection;// len xuong;
    public void Megaman(float posX,float posY,float width,float height,float mass) {
        this.posX=posX;
        this.posY=posY;
        this.width=width;
        this.height=height;
        this.mass=mass;
    }
    public void draw(Graphics2D g) {
        g.setColor(Color.ORANGE);
        //g.fill((int) posX, (int) posY, (int) width, (int) height);
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
    
    public abstract void Refresh(); //them method lam moi
}

