package com.myTeam.game_object;

import java.awt.Rectangle;

public abstract class Character extends ObjectO {

    private boolean isJumping;
    private boolean isDicking;

    private boolean isLanding;

    public Character(float x, float y, float width, float height, float mass, int blood, GameWorld gameWorld) {
        super(x, y, width, height, mass, blood, gameWorld);
        setState(ALIVE);
    }

    public abstract void run();

    public abstract void jump();

    public abstract void dick();

    public abstract void standUp();

    public abstract void stopRun();

    public boolean getIsJumping() {
        return isJumping;
    }

    public void setIsLanding(boolean b){
        isLanding = b;
    }

    public boolean getIsLanding(){
        return isLanding;
    }

    public void setIsJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

    public boolean getIsDicking() {
        return isDicking;
    }

    public void setIsDicking(boolean isDicking) {
        this.isDicking = isDicking;
    }

    @Override
    public void Update(){

        super.Update();

        if(getState() == ALIVE || getState() == NOBEHURT){

            if(!isLanding){

                setPosX(getPosX() + getSpeedX());


                if(getDirection() == LEFTDIR &&
                        getGameWorld().physicalMap.collisionWithLeft(getBoundForCollisionWithMap())!=null){

                    Rectangle rectLeftWall = getGameWorld().physicalMap.collisionWithLeft(getBoundForCollisionWithMap());
                    setPosX(rectLeftWall.x + rectLeftWall.width + getWidth()/2);

                }
                if(getDirection() == RIGHTDIR &&
                        getGameWorld().physicalMap.collisionWithRight(getBoundForCollisionWithMap())!=null){

                    Rectangle rectRightWall = getGameWorld().physicalMap.collisionWithRight(getBoundForCollisionWithMap());
                    setPosX(rectRightWall.x - getWidth()/2);

                }



              

                Rectangle boundForCollisionWithMapFuture = getBoundForCollisionWithMap();
                boundForCollisionWithMapFuture.y += (getSpeedY()!=0?getSpeedY(): 2);
                Rectangle rectLand = getGameWorld().physicalMap.collisionWithLand(boundForCollisionWithMapFuture);

                Rectangle rectTop = getGameWorld().physicalMap.collisionWithTop(boundForCollisionWithMapFuture);

                if(rectTop !=null){

                    setSpeedY(0);
                    setPosY(rectTop.y + getGameWorld().physicalMap.getTileSize() + getHeight()/2);

                }else if(rectLand != null){
                    setIsJumping(false);
                    if(getSpeedY() > 0) setIsLanding(true);
                    setSpeedY(0);
                    setPosY(rectLand.y - getHeight()/2 - 1);
                }else{
                    setIsJumping(true);
                    setSpeedY(getSpeedY() + getMass());
                    setPosY(getPosY() + getSpeedY());
                }
            }
        }
    }

}
