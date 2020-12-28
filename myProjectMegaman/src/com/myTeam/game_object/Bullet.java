package com.myTeam.game_object;

import java.awt.Graphics2D;

public abstract class Bullet extends ObjectO {

    public Bullet(float x, float y, float width, float height, float mass, int damage, GameWorld gameWorld) {
            super(x, y, width, height, mass, 1, gameWorld);
            setDamage(damage);
    }
    
    public abstract void draw(Graphics2D g2d);

    public void Update(){
        super.Update();
        setPosX(getPosX() + getSpeedX());
        setPosY(getPosY() + getSpeedY());
        ObjectO object = getGameWorld().objectManager.getCollisionWithEnemy(this);
        if(object!=null && object.getState() == ALIVE){
            setBlood(0);
            object.beHurt(getDamage());
            System.out.println("Setting hurt for enemy");
        }
    }
    
}
