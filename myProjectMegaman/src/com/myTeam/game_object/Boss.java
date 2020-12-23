package com.myTeam.game_object;

import com.myTeam.status.*;
import com.myTeam.effect.Animation;
import com.myTeam.effect.CacheDataLoader;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Hashtable; //trong animation can nen t import vo luon

//public class monster1
public class Boss extends Character {
    private Animation idleforward, idleback;


    private Animation shootingforward, shootingback;
    private Animation slideforward, slideback;

    private long startTimeForAttacked;
    // time thuc hien 1 chieu attack cua boss gom tem va time xai chieu
    private Hashtable<String, Long> timeAttack = new Hashtable<String, Long>();
    // loai attack(delay cx tinh trong attackType
    private String[] attackType = new String[4];
    private int attackIndex = 0;
    private long lastAttackTime;

    public Boss(float x, float y, GameWorld gameWorld) {
        super(x, y, 110, 150, 0.1f, 100,1000, gameWorld);
        idleback = CacheDataLoader.getInstance().getAnimation("boss_idle");
        idleforward = CacheDataLoader.getInstance().getAnimation("boss_idle");
        idleforward.flipAllImage();

        shootingback = CacheDataLoader.getInstance().getAnimation("boss_shooting");
        shootingforward = CacheDataLoader.getInstance().getAnimation("boss_shooting");
        shootingforward.flipAllImage();

        slideback = CacheDataLoader.getInstance().getAnimation("boss_slide");
        slideforward = CacheDataLoader.getInstance().getAnimation("boss_slide");
        slideforward.flipAllImage();

        getStartTimeNoBeHurt(500*1000000);
        setDamage(10);

        attackType[0] = "NONE";
        attackType[1] = "shooting";
        attackType[2] = "NONE";
        attackType[3] = "slide";

        timeAttack.put("NONE", new Long(2000));
        timeAttack.put("shooting", new Long(500));
        timeAttack.put("slide", new Long(5000));

    }

    public void Update(){
        super.Update();

        if(getGameWorld().getMegaMan().getPosX() > getPosX())
            setDirection(RIGHTDIR);
        else setDirection(LEFTDIR);

        if(startTimeForAttacked == 0)
            startTimeForAttacked = System.currentTimeMillis();
        else if(System.currentTimeMillis() - startTimeForAttacked > 300){
            attack();
            startTimeForAttacked = System.currentTimeMillis();
        }

        if(!attackType[attackIndex].equals("NONE")){
            if(attackType[attackIndex].equals("shooting")){
            //chen cai code bullet cho boss vao
                /*Bullet bullet = new RocketBullet(getPosX(), getPosY() - 50, getGameWorld());
                if(getDirection() == LEFTDIR) bullet.setSpeedX(-4);
                else bullet.setSpeedX(4);
                bullet.setTeamType(getTeamType());
                getGameWorld().bulletManager.addObject(bullet);*/

            }else if(attackType[attackIndex].equals("slide")){

                if(getGameWorld().getPhysicMap().collisionlefl(getBoundsofmegaman())!=null)
                    setSpeedX(5);
                if(getGameWorld().getPhysicMap().collisionright(getBoundsofmegaman())!=null)
                    setSpeedX(-5);
                setPosX(getPosX() + getSpeedX());
            }
        }else{
            // stop attack
            setSpeedX(0);
        }

    }

    @Override
    public void run() {

    }

    @Override
    public void jump() {
        setSpeedY(-5);
    }

    @Override
    public void dick() {


    }

    @Override
    public void standUp() {


    }

    @Override
    public void standRun() {

    }

    @Override
    public void stopRun() {


    }

    @Override
    public void attack() {

        // only switch state attack

        if(System.currentTimeMillis() - lastAttackTime > timeAttack.get(attackType[attackIndex])){
            lastAttackTime = System.currentTimeMillis();

            attackIndex ++;
            if(attackIndex >= attackType.length) attackIndex = 0;

            if(attackType[attackIndex].equals("slide")){
                if(getPosX() < getGameWorld().getMegaMan().getPosX())  {
                    setSpeedX(5);
                }
                else setSpeedX(-5);
            }

        }

    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        if(attackType[attackIndex].equals("slide")){
            Rectangle rect = getBoundsofmegaman();
            rect.y += 100;
            rect.height -= 100;
            return rect;
        }else
            return getBoundsofmegaman();
    }

    @Override
    public void draw(Graphics2D g2) {

        if(getState() == NOBEHURT && (System.nanoTime()/10000000)%2!=1)
        {
            System.out.println("Plash...");
        }else{

            if(attackType[attackIndex].equals("NONE")){
                if(getDirection() == RIGHTDIR){
                    idleforward.Update(System.nanoTime());
                    idleforward.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()), (int) getPosY() - (int) getGameWorld().getCamera().getPosY(), g2);
                }else{
                    idleback.Update(System.nanoTime());
                    idleback.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()), (int) getPosY() - (int) getGameWorld().getCamera().getPosY(), g2);
                }
            }else if(attackType[attackIndex].equals("shooting")){

                if(getDirection() == RIGHTDIR){
                    shootingforward.Update(System.nanoTime());
                    shootingforward.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()), (int) getPosY() - (int) getGameWorld().getCamera().getPosY(), g2);
                }else{
                    shootingback.Update(System.nanoTime());
                    shootingback.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()), (int) getPosY() - (int) getGameWorld().getCamera().getPosY(), g2);
                }

            }else if(attackType[attackIndex].equals("slide")){
                if(getSpeedX() > 0){
                    slideforward.Update(System.nanoTime());
                    slideforward.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()), (int) getPosY() - (int) getGameWorld().getCamera().getPosY() + 50, g2);
                }else{
                    slideback.Update(System.nanoTime());
                    slideback.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()), (int) getPosY() - (int) getGameWorld().getCamera().getPosY() + 50, g2);
                }
            }
        }
        // drawBoundForCollisionWithEnemy(g2);
    }
}