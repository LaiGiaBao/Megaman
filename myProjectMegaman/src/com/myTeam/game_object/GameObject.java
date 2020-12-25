package com.myTeam.game_object;

import java.awt.*;
import com.myTeam.status.*;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
public abstract class GameObject {
    private float posX;
    private float posY;
<<<<<<< Updated upstream
	private GameWorld gameWorld;

	public GameObject(float posX, float posY, GameWorld gameWorld){
		this.posX=posX;
		this.posY=posY;
		this.gameWorld=gameWorld;

	}
	
	public void setPosX(float posX){
		this.posX=posX;
	}
	
	public float getPosX(){
		return posX;
	}
	
	public void setPosY(float posY){
		this.posY=posY;
	}
	
	public float getPosY(){
		return posY;
	}

	public GameWorld getGameWorld(){
		return gameWorld;
	}



	public abstract void Update();
	
	
}
=======
    private float width;
    private float height;
    private float mass;
    private float speedX;

    private float speddY;
    private int DIR_LEFT;

    private float speedY;
    GameWorld gameWorld;
    private int DIR_RIGHT;
    private int dicrection;// len xuong;
    public GameObject(float posX,float posY,float width,float height,float mass) {
        this.posX=posX;
        this.posY=posY;
        this.width=width;
        this.height=height;
        this.mass=mass;
    }
    // xu ly' va cham giua megaman voi map
    public Rectangle MegamanCollisewithMap() {
        Rectangle bound = new Rectangle();
        bound.x=(int) (getPosX() - (getWidth()/2));// tam nhan vat trung voi tam hinh chu nhat
        bound.y=(int) (getPosY() - (getHeight()/2));//
        bound.width = (int) getWidth();
        bound.height= (int) getHeight();
        return bound;
    }
    public void update() {
        setPosX(getPosX()+getSpeedX());
        setPosY(getPosY()+getSpeedY());

        setSpeedY(getSpeedY()+getMass());
       // if (gameWorld.getPosY() > 400) setPosY(400);

        //else setPosY(getPosY()+getSpeedY());
    }
    public void draw(Graphics2D g) {

        g.setColor(Color.RED);
        g.fillRect((int) (posX-getWidth()), (int) (posY-getHeight()), (int) width, (int) height);
        g.setColor(Color.YELLOW);
        g.fillRect((int) posX,(int) posY,2,2);
    }

    public float getHeight() {
        return height;
    }

    public float getMass() {
        return mass;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public float getWidth() {
        return width;
    }

    public int getDirection() {
        return dicrection;
    }

    public float getSpeedY() {
        return speedY;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setDirection(int dicrection) {
        this.dicrection = dicrection;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public void setWidth(float width) {
        this.width = width;
    }
    

   //  public abstract void Refresh(); //them method lam moi

    public void Reset(){}; //them method lam moi

}

    
>>>>>>> Stashed changes
