package com.myTeam.game_object;

import java.awt.*;
import com.myTeam.status.GameWorld;

public abstract class GameObject {
    private float posX;
    private float posY;
	private GameWorld world;
	
	public GameObject(float posX, float posY, GameWorld world){
		this.posX=posX;
		this.posY=posY;
		this.world=world;
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
		return world;
	}
	
	public abstract void Update();
	public abstract Rectangle getBoundForCollisionWithEnemy();
	
}
