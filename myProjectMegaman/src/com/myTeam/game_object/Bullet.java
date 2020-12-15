package com.myTeam.game_object;

import com.myTeam.status.GameWorld;
import java.awt.Graphics2D;

public abstract class Bullet extends Object {

    public Bullet(float x, float y, float width, float height, float mass, int damage, GameWorld gameWorld) {
            super(x, y, width, height, mass, 1, gameWorld);
            setDamage(damage);
    }
    
    public abstract void draw(Graphics2D g2d);

    public void Update(){
        super.Update();
        setPosX(getPosX() + getSpeedX());
        setPosY(getPosY() + getSpeedY());
        Object object = getGameWorld().ObjectManager.getCollisionWidthEnemy(this);
        if(object!=null && object.getState() == ALIVE){
            setBlood(0);
            object.beAttacked(getDamage());
            System.out.println("Set getting damage for enemy");
        }
    }
    
}

