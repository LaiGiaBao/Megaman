package com.myTeam.game_object;

import com.myTeam.effect.Animation;
import com.myTeam.effect.CacheDataLoader;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class RedBot extends ObjectO {

    private Animation forwardAnim, backAnim;
    
    private long startTimeToShoot;
    
    
    public RedBot(float x, float y, GameWorld gameWorld) {
        super(x, y, 127, 89, 0, 100, gameWorld);
        backAnim = CacheDataLoader.getInstance().getAnimation("redeye");
        forwardAnim = CacheDataLoader.getInstance().getAnimation("redeye");
        forwardAnim.flipAllImage();
        startTimeToShoot = 0;
        setDamage(10);
        setTimeForNoBehurt(300000000);
    }

    @Override
    public void attack() {
    
        Bullet bullet = new RedBotBullet(getPosX(), getPosY(), getGameWorld());
        if(getDirection() == LEFTDIR) bullet.setSpeedX(-8);
        else bullet.setSpeedX(8);
        bullet.setTeamType(getTeamType());
        getGameWorld().bulletManager.addObject(bullet);
    
    }

    
    public void Update(){
        super.Update();
        if(System.nanoTime() - startTimeToShoot > 1000*10000000){
            attack();
            System.out.println("Red Eye attack");
            startTimeToShoot = System.nanoTime();
        }
    }
    
    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();
        rect.x += 20;
        rect.width -= 40;
        
        return rect;
    }

    @Override
    public void draw(Graphics2D g2) {
        if(!isObjectOutOfCameraView()){
            if(getState() == NOBEHURT && (System.nanoTime()/10000000)%2!=1){
       
            }else{
                if(getDirection() == LEFTDIR){
                    backAnim.Update(System.nanoTime());
                    backAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
                            (int)(getPosY() - getGameWorld().camera.getPosY()), g2);
                }else{
                    forwardAnim.Update(System.nanoTime());
                    forwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
                            (int)(getPosY() - getGameWorld().camera.getPosY()), g2);
                }
            }
        }
    }
    
}
