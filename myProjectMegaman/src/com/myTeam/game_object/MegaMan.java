package com.myTeam.game_object;

import com.myTeam.status.*;
import com.myTeam.effect.Animation;
import com.myTeam.effect.CacheDataLoader;

import java.applet.AudioClip;
import java.awt.*;

public class MegaMan extends Character {


    public static final int RUNSPEED = 3;

    private Animation runForwardAnim, runBackAnim, runShootingForwarAnim, runShootingBackAnim;
    private Animation idleForwardAnim, idleBackAnim, idleShootingForwardAnim, idleShootingBackAnim;
    private Animation dickForwardAnim, dickBackAnim;
    private Animation flyForwardAnim, flyBackAnim, flyShootingForwardAnim, flyShootingBackAnim;
    private Animation landingForwardAnim, landingBackAnim;

    private Animation climWallForward, climWallBack;

    private long lastShootingTime;
    private boolean isShooting = false;

    // private AudioClip hurtingSound;
    private AudioClip shooting1;


    public MegaMan(float x, float y, float width, float height, float mass, int damge, int blood, GameWorld gameWorld) {
        super(x, y, width, height, mass, damge, blood, gameWorld);

        //  super(x, y, 70, 90, 0.1f, 100, 500, gameWorld);

        //  shooting1 = CacheDataLoader.getInstance().getSound("bluefireshooting");
        // hurtingSound = CacheDataLoader.getInstance().getSound("megamanhurt");

        setTeamType(LEAGUETEAM);

        setTimeForNoBeHurt(2000 * 1000000);

        runForwardAnim = CacheDataLoader.getInstance().getAnimation("run");
        runBackAnim = CacheDataLoader.getInstance().getAnimation("run");
        runBackAnim.flipAllImage();

        idleForwardAnim = CacheDataLoader.getInstance().getAnimation("idle");
        idleBackAnim = CacheDataLoader.getInstance().getAnimation("idle");
        idleBackAnim.flipAllImage();

        dickForwardAnim = CacheDataLoader.getInstance().getAnimation("dick");
        dickBackAnim = CacheDataLoader.getInstance().getAnimation("dick");
        dickBackAnim.flipAllImage();

        flyForwardAnim = CacheDataLoader.getInstance().getAnimation("flyingup");
        flyForwardAnim.setIsRepeated(false);
        flyBackAnim = CacheDataLoader.getInstance().getAnimation("flyingup");
        flyBackAnim.setIsRepeated(false);
        flyBackAnim.flipAllImage();

        landingForwardAnim = CacheDataLoader.getInstance().getAnimation("landing");
        landingBackAnim = CacheDataLoader.getInstance().getAnimation("landing");
        landingBackAnim.flipAllImage();

        climWallBack = CacheDataLoader.getInstance().getAnimation("clim_wall");
        climWallForward = CacheDataLoader.getInstance().getAnimation("clim_wall");
        climWallForward.flipAllImage();

        behurtForwardAnim = CacheDataLoader.getInstance().getAnimation("hurting");
        behurtBackAnim = CacheDataLoader.getInstance().getAnimation("hurting");
        behurtBackAnim.flipAllImage();

        idleShootingForwardAnim = CacheDataLoader.getInstance().getAnimation("idleshoot");
        idleShootingBackAnim = CacheDataLoader.getInstance().getAnimation("idleshoot");
        idleShootingBackAnim.flipAllImage();

        runShootingForwarAnim = CacheDataLoader.getInstance().getAnimation("runshoot");
        runShootingBackAnim = CacheDataLoader.getInstance().getAnimation("runshoot");
        runShootingBackAnim.flipAllImage();

        flyShootingForwardAnim = CacheDataLoader.getInstance().getAnimation("flyingupshoot");
        flyShootingBackAnim = CacheDataLoader.getInstance().getAnimation("flyingupshoot");
        flyShootingBackAnim.flipAllImage();
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rectangle = getBoundsofmegaman();

        if (isDick()) {
            rectangle.x=(int) getPosX() -20;
            rectangle.y=(int) getPosY() - 20;
            rectangle.width = 44;
            rectangle.height = 65;
        }
        else {
            rectangle.x=(int) getPosX() -22;
            rectangle.y=(int) getPosY() - 40;
            rectangle.width = 44;
            rectangle.height = 65;
        }
        return  rectangle;
    }

    @Override
    public void Update() {

        super.Update();

        if (isShooting) {
            if (System.nanoTime() - lastShootingTime > 500 * 1000000) {
                isShooting = false;
            }
        }

        if (isLand()) {
            landingBackAnim.Update(System.nanoTime());
            if (landingBackAnim.isLastFrame()) {
                setLand(false);
                landingBackAnim.reset();
                runForwardAnim.reset();
                runBackAnim.reset();
            }
        }

    }

    /* private float posX;
     private float posY;
     private float speedX;
     private float mass;
     private float DIR_LEF;
     private float DIR_RIGHT;
     private float direction;
     private float width;
     private float height;
     GameWorld gameWorld;
     private float speedY;
 public MegaMan( float x, float y, GameWorld gameWorld){
         super(x, y, 70, 90, 0.1f, 100, gameWorld);
     }*/
    // tao 1 hinh chu nhat bao quanh nhan vat de xu ly' va cham vs Map vs creeps
    public Rectangle getBoundsofmegaman() {
        Rectangle rectangle = new Rectangle();
        rectangle.x = (int) (getPosX() - getWidth() / 2);
        rectangle.y = (int) (getPosY() - getHeight() / 2);
        rectangle.width = (int) getWidth();
        rectangle.height = (int) getHeight();
        return rectangle;

    }


    public void draw(Graphics2D g2) {

        switch (getState()) {
            // con song or ko nhan damge
            case ALIVE:
            case NOBEHURT:
                // tao ra su kien luc co luc ko ( nhap'  nhay' hinh anh)
                if (getState() == NOBEHURT && (System.nanoTime() / 10000000) % 2 != 1) {
                    System.out.println("Plash...");
                } else {
                    // luc tiep dat' ( X khuyu goi khi tiep dat
                    if (isLand()) {
                        // update landingfor....
                        if (getDirection() == RIGHTDIR) {
                            landingForwardAnim.setCurrentFrame(landingBackAnim.getCurrentFrame());
                            landingForwardAnim.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()),
                                    (int) getPosY() - (int) getGameWorld().getCamera().getPosY() + (getBoundsofmegaman().height / 2 - landingForwardAnim.getCurrentImage().getHeight() / 2),
                                    g2);
                        } else {
                            landingBackAnim.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()),
                                    (int) getPosY() - (int) getGameWorld().getCamera().getPosY() + (getBoundsofmegaman().height / 2 - landingBackAnim.getCurrentImage().getHeight() / 2),
                                    g2);
                        }

                    } else if (isJump()) {
                        // huong khi dang nhat
                        if (getDirection() == RIGHTDIR) {
                            flyForwardAnim.Update(System.nanoTime());
                            // vua nhay vua ban
                            // chi update shoooting khi player ban' thi' ngay khi ban update animation shooting tu luc' do' cho toi' khi cham dat'
                            if (isShooting) {
                                // flyShooting vua bay vua ban
                                flyShootingForwardAnim.setCurrentFrame(flyForwardAnim.getCurrentFrame());
                                flyShootingForwardAnim.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()) + 10, (int) getPosY() - (int) getGameWorld().getCamera().getPosY(), g2);
                            } else
                                flyForwardAnim.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()), (int) getPosY() - (int) getGameWorld().getCamera().getPosY(), g2);
                        } else {
                            flyBackAnim.Update(System.nanoTime());
                            if (isShooting) {
                                flyShootingBackAnim.setCurrentFrame(flyBackAnim.getCurrentFrame());
                                flyShootingBackAnim.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()) - 10, (int) getPosY() - (int) getGameWorld().getCamera().getPosY(), g2);
                            } else
                                flyBackAnim.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()), (int) getPosY() - (int) getGameWorld().getCamera().getPosY(), g2);
                        }

                    } else if (isDick()) {

                        if (getDirection() == RIGHTDIR) {
                            dickForwardAnim.Update(System.nanoTime());
                            dickForwardAnim.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()),
                                    (int) getPosY() - (int) getGameWorld().getCamera().getPosY() + (getBoundsofmegaman().height / 2 - dickForwardAnim.getCurrentImage().getHeight() / 2),
                                    g2);
                        } else {
                            dickBackAnim.Update(System.nanoTime());
                            dickBackAnim.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()),
                                    (int) getPosY() - (int) getGameWorld().getCamera().getPosY() + (getBoundsofmegaman().height / 2 - dickBackAnim.getCurrentImage().getHeight() / 2),
                                    g2);
                        }

                    } else {
                        if (getSpeedX() > 0) {
                            runForwardAnim.Update(System.nanoTime());
                            if (isShooting) {
                                runShootingForwarAnim.setCurrentFrame(runForwardAnim.getCurrentFrame() - 1);
                                runShootingForwarAnim.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()), (int) getPosY() - (int) getGameWorld().getCamera().getPosY(), g2);
                            } else
                                runForwardAnim.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()), (int) getPosY() - (int) getGameWorld().getCamera().getPosY(), g2);
                            if (runForwardAnim.getCurrentFrame() == 1) runForwardAnim.setIgnoreFrame(0);
                        } else if (getSpeedX() < 0) {
                            runBackAnim.Update(System.nanoTime());
                            if (isShooting) {
                                runShootingBackAnim.setCurrentFrame(runBackAnim.getCurrentFrame() - 1);
                                runShootingBackAnim.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()), (int) getPosY() - (int) getGameWorld().getCamera().getPosY(), g2);
                            } else
                                runBackAnim.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()), (int) getPosY() - (int) getGameWorld().getCamera().getPosY(), g2);
                            if (runBackAnim.getCurrentFrame() == 1) runBackAnim.setIgnoreFrame(0);
                        } else {
                            if (getDirection() == RIGHTDIR) {
                                if (isShooting) {
                                    idleShootingForwardAnim.Update(System.nanoTime());
                                    idleShootingForwardAnim.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()), (int) getPosY() - (int) getGameWorld().getCamera().getPosY(), g2);
                                } else {
                                    idleForwardAnim.Update(System.nanoTime());
                                    idleForwardAnim.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()), (int) getPosY() - (int) getGameWorld().getCamera().getPosY(), g2);
                                }
                            } else {
                                if (isShooting) {
                                    idleShootingBackAnim.Update(System.nanoTime());
                                    idleShootingBackAnim.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()), (int) getPosY() - (int) getGameWorld().getCamera().getPosY(), g2);
                                } else {
                                    idleBackAnim.Update(System.nanoTime());
                                    idleBackAnim.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()), (int) getPosY() - (int) getGameWorld().getCamera().getPosY(), g2);
                                }
                            }
                        }
                    }
                }

                break;

            case BEHURT:
                if (getDirection() == RIGHTDIR) {
                    behurtForwardAnim.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()), (int) getPosY() - (int) getGameWorld().getCamera().getPosY(), g2);
                } else {
                    behurtBackAnim.setCurrentFrame(behurtForwardAnim.getCurrentFrame());
                    behurtBackAnim.draw((int) (getPosX() - getGameWorld().getCamera().getPosX()), (int) getPosY() - (int) getGameWorld().getCamera().getPosY(), g2);
                }
                break;

            case FEY:

                break;

        }

        drawBoundForCollisionWithMap(g2);

    }

    public void run() {
        if (getDirection() == LEFTDIR)
            setSpeedX(-3);
        else setSpeedX(3);
    }

    @Override
    public void jump() {
        // neu ko trong tran thai dang nhay  thi moi dc nhay ( ko  cho nhay lien tuc)
        if (!isJump()) {
            setJump(true);
            setSpeedY(-5.0f);
            //dong tac nhay voi roi
            flyBackAnim.reset();

            flyForwardAnim.reset();
        }
        // for clim wall gio han cho viec vua nhay vua
        else {
            Rectangle rectRightWall = getBoundsofmegaman();
            rectRightWall.x += 1;
            Rectangle rectLeftWall = getBoundsofmegaman();
            rectLeftWall.x -= 1;

            if (getGameWorld().getPhysicMap().collisionright(rectRightWall) != null && getSpeedX() > 0) {
                setSpeedY(-5.0f);
                //setSpeedX(-1);
                flyBackAnim.reset();
                flyForwardAnim.reset();
                //setDirection(LEFTDIR);
            } else if (getGameWorld().getPhysicMap().collisionlefl(rectLeftWall) != null && getSpeedX() < 0) {
                setSpeedY(-5.0f);
                setSpeedX(1);
                flyBackAnim.reset();
                flyForwardAnim.reset();
                setDirection(RIGHTDIR);
            }

        }
    }

    @Override
    public void dick() {
        if (!isJump())
            setDick(true);
    }

    @Override
    public void standUp() {
        setDick(false);
        idleForwardAnim.reset();
        idleBackAnim.reset();
        dickForwardAnim.reset();
        dickBackAnim.reset();
    }

    @Override
    public void standRun() {

    }

    @Override
    public void stopRun() {
        setSpeedX(0);
        runForwardAnim.reset();
        runBackAnim.reset();
        runForwardAnim.unIgnoreFrame(0);
        runBackAnim.unIgnoreFrame(0);
    }

    // lam dum bullet cho
   /* @Override
    public void attack() {
        // quy ko dc ban ( test )
        if(!isShooting && !isDick()){
            shooting1.play();
           Bullet bullet = new BlueFire(getPosX(), getPosY(), getGameWorld());
            if(getDirection() == LEFTDIR) {
                bullet.setSpeedX(-10);
                bullet.setPosX(bullet.getPosX() - 40);
                if(getSpeedX() != 0 && getSpeedY() == 0){
                    bullet.setPosX(bullet.getPosX() - 10);
                    bullet.setPosY(bullet.getPosY() - 5);
                }
            }else {
                bullet.setSpeedX(10);
                bullet.setPosX(bullet.getPosX() + 40);
                if(getSpeedX() != 0 && getSpeedY() == 0){
                    bullet.setPosX(bullet.getPosX() + 10);
                    bullet.setPosY(bullet.getPosY() - 5);
                }
            }
            if(isJump()) {
                bullet.setPosY(bullet.getPosY() - 20);
            bullet.setTeamType(getTeamType());
            ObjectManager.addobject(bullet);
            lastShootingTime = System.nanoTime();
            isShooting = true;
        }
        }
    }*/
    @Override
    public void attack() {

        if (!isShooting && !isDick()) {

            shooting1.play();

            Bullet bullet = new BlueFire(getPosX(), getPosY(), getGameWorld());
            if (getDirection() == LEFTDIR) {
                bullet.setSpeedX(-10);
                bullet.setPosX(bullet.getPosX() - 40);
                if (getSpeedX() != 0 && getSpeedY() == 0) {
                    bullet.setPosX(bullet.getPosX() - 10);
                    bullet.setPosY(bullet.getPosY() - 5);
                }
            } else {
                bullet.setSpeedX(10);
                bullet.setPosX(bullet.getPosX() + 40);
                if (getSpeedX() != 0 && getSpeedY() == 0) {
                    bullet.setPosX(bullet.getPosX() + 10);
                    bullet.setPosY(bullet.getPosY() - 5);
                }
            }
            if (isJump())
                bullet.setPosY(bullet.getPosY() - 20);

            bullet.setTeamType(getTeamType());
            getGameWorld().getObjectManager().addobject(bullet);

            lastShootingTime = System.nanoTime();
            isShooting = true;

        }

    }
    /*     @Override
        public void hurtingCallback () {
            System.out.println("Call back hurting");
            // hurtingSound.play();
        }

         @Override
        public void run () {
            // TODO Auto-generated method stub

        }
        @Override
        public void jump () {
            // TODO Auto-generated method stub

        }
        @Override
        public void dick () {
            // TODO Auto-generated method stub

        }
        @Override
        public void standUp () {
            // TODO Auto-generated method stub

        }
        @Override
        public void standRun () {
            // TODO Auto-generated method stub

        }
        @Override
        public void stopRun () {
            // TODO Auto-generated method stub

        }

        @Override
        public void draw (Graphics2D g){
            // TODO Auto-generated method stub

        }
        @Override
        public Rectangle getBoundForCollisionWithEnemy () {
            // TODO Auto-generated method stub
            return null;
        }*/
}


