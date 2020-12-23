package com.myTeam.game_object;

import com.myTeam.status.*;
import java.awt.Graphics2D;
// Bullet is subclass of Object

public abstract class Bullet extends ObjectO {
    public Bullet(float x, float y, float width, float height, float mass, int blood, int damage, GameWorld gameWorld) {

        super(x, y, width, height, mass, 1, blood, gameWorld);
        setDamage(damage);
    }


    public void Update() {
        super.Update();
        setPosX(getPosX() + getSpeedX());
        setPosY(getPosY() + getSpeedY());
        ObjectO object = getGameWorld().getObjectManager().collisionwithenemy(this);
        // object!=null bullet chi co gay damge len enemy, chua tao cac dia hinh co the pha' vo ( luoi thiet ke lai map )
        if (object != null && object.getState() == ALIVE) {
            setBlood(0);
            object.beHurt(getDamage());
            System.out.println("Bullet set behurt for enemy");
        }
    }

    public abstract void draw(Graphics2D g);
}

