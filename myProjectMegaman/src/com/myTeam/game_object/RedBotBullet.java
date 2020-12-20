package com.myTeam.game_object;

import com.myTeam.status.GameWorld;
import com.myTeam.effect.Animation;
import com.myTeam.effect.CacheDataLoader;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class RedBotBullet extends Bullet{
	
    private Animation forwardBulletAnim, backBulletAnim;
    
    public RedBotBullet(float x, float y, GameWorld gameWorld) {
            super(x, y, 30, 30, 1.0f, 10, gameWorld);
            forwardBulletAnim = CacheDataLoader.getInstance().getAnimation("redeyebullet");
            backBulletAnim = CacheDataLoader.getInstance().getAnimation("redeyebullet");
            backBulletAnim.flipAllImage();
    }

    
    
    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
            
            return getBoundForCollisionWithMap();
    }

    @Override
    public void draw(Graphics2D g2) {
        if(getSpeedX() > 0){          
            forwardBulletAnim.Update(System.nanoTime());
            forwardBulletAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
        }else{
            backBulletAnim.Update(System.nanoTime());
            backBulletAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
        }
    }

    @Override
    public void Update() {
        super.Update();
    }

    @Override
    public void attack() {}

}
