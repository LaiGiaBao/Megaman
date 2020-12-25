package com.myTeam.game_object;

import com.myTeam.effect.Animation;
import com.myTeam.effect.CacheDataLoader;
import com.myTeam.status.GameWorld;
import static com.myTeam.game_object.ObjectO.LEFTDIR;
import static com.myTeam.game_object.ObjectO.NOBEHURT;
import java.awt.Graphics2D;
import java.awt.Rectangle;
public class moster1 extends  ObjectO {
    private Animation forwarAnim, backAnim;
    private long startTimetoShoot;
    private float x1,x2;
    public moster1(float x, float y, GameWorld gameWorld) {
        super(x,y,127,89,0,100,gameWorld);
        backAnim = CacheDataLoader.getInstance().getAnimation("darkraise");
        forwarAnim = CacheDataLoader.getInstance().getAnimation("darkraise");
        forwarAnim.flipAllImage();
        startTimetoShoot = 0;
        setStartTimeNoBeHurt(3000);
        x1  =x-100;
        x2 = x+100;
        setSpeedX(1);
        setSpeedY(10);
    }
    @Override
    public void attack() {
      /*  float megamanX = getGameWorld().getMegaMan().getPosX();
        float megamanY = getGameWorld().getMegaMan().getPosY();
        float deltaX = megamanX - getPosX();
        float deltaY = megamanY - getPosY();

        float speed = 3;
        float a = Math.abs(deltaX / deltaY);

        float speedX = (float) Math.sqrt(speed * speed * a * a / (a * a + 1));
        float speedY = (float) Math.sqrt(speed * speed / (a * a + 1));
        // them cai dan m vo
        Bullet bullet = new RedBotBullet(getPosX(), getPosY(), getGameWorld());

        if(deltaX < 0)
            bullet.setSpeedX(-speedX);
        else bullet.setSpeedX(speedX);
        bullet.setSpeedY(speedY);
        bullet.setTeamType(getTeamType());
        getGameWorld().getObjectManager().addobject(bullet);*/
        Bullet bullet = new RedBotBullet(getPosX(), getPosY(), getGameWorld());
        if(getDirection() == LEFTDIR) bullet.setSpeedX(-8);
        else bullet.setSpeedX(8);
        bullet.setTeamType(getTeamType());
        getGameWorld().getObjectManager().addobject(bullet);
    }


    public void Update(){
        super.Update();
        if(getPosX() < x1)
            setSpeedX(1);
        else if(getPosX() > x2)
            setSpeedX(-1);
        setPosX(getPosX() + getSpeedX());

        if(System.nanoTime() - startTimetoShoot > 1000*10000000*1.5){
            attack();
            startTimetoShoot = System.nanoTime();
        }
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundsofmegaman();
        rect.x += 20;
        rect.width -= 40;

        return rect;
    }

    @Override
    public void draw(Graphics2D g2) {
        if(!isoutofcameraView()){
            if(getState() == NOBEHURT && (System.nanoTime()/10000000)%2!=1){
                // plash...
            }else{
                if(getDirection() == LEFTDIR){
                    backAnim.Update(System.nanoTime());
                    backAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()),
                            (int)(getPosY() - getGameWorld().camera.getPosY()), g2);
                }else{
                    forwarAnim.Update(System.nanoTime());
                    forwarAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()),
                            (int)(getPosY() - getGameWorld().camera.getPosY()), g2);
                }
            }
        }
        //drawBoundForCollisionWithEnemy(g2);
    }
}
