package com.myTeam.game_object;

import com.myTeam.status.GameWorld;
import java.awt.Rectangle;

public abstract class Character extends Object{
    // trang thai dang nhay
    private  boolean isJump;
    // dang quy
    private boolean isDick;

    //hanh dong luc tiep dat ( lam' them cho nhin dep) chay 1 Animation
    private  boolean land;
    public Character(float x, float y, float width, float height, float mass, int blood, GameWorld gameWorld) {
        super(x,y,width,height,mass,blood,gameWorld);
        setState(ALIVE);
    }
    public abstract void run();
    public abstract void jump();
    public abstract void dick();
    public abstract void standUp();
    public abstract void standRun();
    public abstract void stopRun();
    public boolean getisJump() {
        return  isJump;
    }

    public boolean isLand() {
        return land;
    }

    public boolean isJump() {
        return isJump;
    }

    public boolean isDick() {
        return isDick;
    }

    public void setLand(boolean land) {
        this.land = land;
    }

    public void setJump(boolean jump) {
        isJump = jump;
    }

    public void setDick(boolean dick) {
        isDick = dick;
    }
    public void Update() {
        super.Update();
        if (getState() == ALIVE || getState()==NOBEHURT) {
            if (!land) {
                setPosX(getPosX()+getSpeedX());
                // check xem no co' dung vao' tuong ben trai ko
                if (getDirection() == LEFTDIR && getGameWorld().getPhysicMap().collisionlefl(getBoundsofmegaman())!=null) {
                    Rectangle lefwall = getGameWorld().getPhysicMap().collisionlefl(getBoundsofmegaman());
                    setPosX(lefwall.x-getWidth()/2);
                }
                if  (getDirection() == RIGHTDIR && getGameWorld().getPhysicMap().collisionright(getBoundsofmegaman())!=null) {
                    Rectangle rightwall = getGameWorld().getPhysicMap().collisionright(getBoundsofmegaman());
                    setPosX(rightwall.x-getWidth()/2);
                }
                Rectangle boundForCollisionWithMapFuture = getBoundsofmegaman();
                boundForCollisionWithMapFuture.y += (getSpeedY()!=0?getSpeedY(): 2);
                Rectangle rectLand = getGameWorld().getPhysicMap().collisionland(boundForCollisionWithMapFuture);

                Rectangle rectTop = getGameWorld().getPhysicMap().collistiontop(boundForCollisionWithMapFuture);

                if(rectTop !=null){

                    setSpeedY(0);
                    setPosY(rectTop.y + getGameWorld().getPhysicMap().getSizeofsquares() + getHeight()/2);

                }else if(rectLand != null){
                    setJump(false);
                    if(getSpeedY() > 0) setLand(true);
                    setSpeedY(0);
                    setPosY(rectLand.y - getHeight()/2 - 1);
                }else{
                    setJump(true);
                    setSpeedY(getSpeedY() + getMass());
                    setPosY(getPosY() + getSpeedY());
                }
            }
        }
    }

}
