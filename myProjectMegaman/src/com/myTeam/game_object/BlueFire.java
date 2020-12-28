package com.myTeam.game_object;
import com.myTeam.effect.Animation;
import com.myTeam.effect.CacheDataLoader;

import java.awt.*;

public class BlueFire extends Bullet {
    private Animation forwarBulletAnim, backBulletAnim;
    public BlueFire (float x, float y, GameWorld gameWorld) {
        super(x,y,60,30,1.0f,1,gameWorld);
        forwarBulletAnim = CacheDataLoader.getInstance().getAnimation("bluefire");
        backBulletAnim = CacheDataLoader.getInstance().getAnimation("bluefire");
        backBulletAnim.flipAllImage();
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return getBoundForCollisionWithEnemy();
    }

    @Override
    public void draw(Graphics2D g) {
        if (getSpeedX() > 0 ) {
            if (!forwarBulletAnim.isIgnoreFrame(0) && forwarBulletAnim.getCurrentFrame() == 3) {
            forwarBulletAnim.setIgnoreFrame(0);
            forwarBulletAnim.setIgnoreFrame(1);
            forwarBulletAnim.setIgnoreFrame(2);

            }
            forwarBulletAnim.Update(System.nanoTime());
            forwarBulletAnim.draw((int) (getPosX()-getGameWorld().camera.getPosX()),(int) (getPosY()-getGameWorld().camera.getPosY()),g);

        }
        else {
            if (!backBulletAnim.isIgnoreFrame(0)&& backBulletAnim .getCurrentFrame() == 3) {
                backBulletAnim.setIgnoreFrame(0);
                backBulletAnim.setIgnoreFrame(1);
                backBulletAnim.setIgnoreFrame(2);

            }
        }
    }
    @Override
    public void Update() {
        if (forwarBulletAnim.isIgnoreFrame(0) || backBulletAnim.isIgnoreFrame(0)) {
            setPosX(getPosX() + getSpeedX());
        }
        ObjectO objectO = getGameWorld().getObjectManager().getCollisionWithEnemy(this);
        if (objectO!=null && objectO.getState() == ALIVE) {
            setBlood(0);
            objectO.setBlood(objectO.getBlood()- getDamage());
            objectO.setState(BEHURT);
            System.out.println("gay damge len enemy");
        }

    }
    @Override
    public void attack() {

    }
}
