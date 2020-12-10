package com.myTeam.game_object;

import com.myTeam.status.GameWorld;
import com.myTeam.effect.Animation;
import java.awt.*;

public abstract class Object extends GameObject {
    //cung team (ko bi mat mau khi megaman tan cong
    public static final int LEAGUETEAM = 1;
    // team dich tan cong lan nhau
    public static final int ENEMYTEAM = 2;
    // huong nhan vat
    public static final int LEFTDIR = 0;
    public static final int RIGHTDIR = 1;
    // con song'
    public static final int ALIVE = 0;
    // bi trung chieu
    public static final int BEHURT = 1;
    // gan chet'
    public static final int FEY = 2;
    public static final int DEATH = 3;
    // bat tu sau khi hoi sinh
    public static final int NOBEHURT = 4;
    // nhan vat dang trong trang thai nao ?? 5 state
    private int state = ALIVE;

    private float width;
    private float height;
    private float mass;
    private float speedX;
    private float speedY;
    private int blood;
    // damge gay ra
    private int damage;
    // huong tan cong khi ko tan cong
    private int direction;

    protected Animation behurtForwardAnim, behurtBackAnim;
    // trang thai team minh
    private int teamType;
    // thoi gian bat tu sau khi hoi sinh
    private long startTimeNoBeHurt;
    private long timeForNoBeHurt;
    public Object(float x,float y,float width,float height,float mass,int blood,GameWorld gameWorld) {
        super(x,y,gameWorld);
        setWidth(width);
        setHeight(height);
        setMass(mass);
        setBlood(blood);

        direction = RIGHTDIR;
    }

    public long getTimeForNoBeHurt() {
        return timeForNoBeHurt;
    }

    public long getStartTimeNoBeHurt() {
        return startTimeNoBeHurt;
    }

    public int getTeamType() {
        return teamType;
    }

    public int getState() {
        return state;
    }

    public int getDirection() {
        return direction;
    }

    public int getDamage() {
        return damage;
    }

    public int getBlood() {
        return blood;
    }

    public Animation getBehurtForwardAnim() {
        return behurtForwardAnim;
    }

    public Animation getBehurtBackAnim() {
        return behurtBackAnim;
    }

    @Override
    public GameWorld getGameWorld() {
        return super.getGameWorld();
    }

    @Override
    public float getPosY() {
        return super.getPosY();
    }

    @Override
    public float getPosX() {
        return super.getPosX();
    }

    public float getWidth() {
        return width;
    }

    public float getMass() {
        return mass;
    }

    public float getHeight() {
        return height;
    }

    public float getSpeedX() {
        return speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setBehurtForwardAnim(Animation behurtForwardAnim) {
        this.behurtForwardAnim = behurtForwardAnim;
    }

    public void setBehurtBackAnim(Animation behurtBackAnim) {
        this.behurtBackAnim = behurtBackAnim;
    }

    public void setTimeForNoBeHurt(long timeForNoBeHurt) {
        this.timeForNoBeHurt = timeForNoBeHurt;
    }

    public void setTeamType(int teamType) {
        this.teamType = teamType;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setStartTimeNoBeHurt(long startTimeNoBeHurt) {
        this.startTimeNoBeHurt = startTimeNoBeHurt;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    @Override
    public void setPosY(float posY) {
        super.setPosY(posY);
    }

    @Override
    public void setPosX(float posX) {
        super.setPosX(posX);
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }
    public void attach() {}
    // ve 1 rectangle xung quanh doi tuong giong megaman
    public Rectangle getBoundsofmegaman() {
        Rectangle rectangle = new Rectangle();
        rectangle.x=(int) (getPosX()-getWidth()/2);
        rectangle.y=(int) (getPosY()-getHeight()/2);
        rectangle.width=(int) getWidth();
        rectangle.height=(int) getHeight();
        return  rectangle;
    }
    public void beAttached(int damage) {
        setBlood(getBlood()-damage);
        state = BEHURT;
        AnimationofbeAttached();
    }
    //hieu ung khi nhan damge tuy vao Object
    public void AnimationofbeAttached() {}
    public void Update() {
        switch (state) {
            case ALIVE:

                break;
            case BEHURT:
                if (behurtBackAnim == null) {
                    state = BEHURT;
                    startTimeNoBeHurt = System.nanoTime();
                    if (getBlood() == 0 ) {
                        state = FEY;
                    } else {
                        behurtForwardAnim.Update(System.nanoTime());
                        if (behurtForwardAnim.isLastFrame()) {
                            behurtForwardAnim.reset();
                            state = NOBEHURT;
                            if (getBlood() == 0 ) {
                                state = FEY;
                                startTimeNoBeHurt = System.nanoTime();
                            }
                        }
                    }
                }
                break;
            case FEY:
                state = DEATH;
                break;
            case  DEATH:
                break;
            case NOBEHURT:
                if (System.nanoTime()-startTimeNoBeHurt> timeForNoBeHurt)
                    state = ALIVE;
                break;
        }
    }
    public abstract  Rectangle boundCollisionEnemy;
    public abstract  void draw(Graphics2D g);
}
