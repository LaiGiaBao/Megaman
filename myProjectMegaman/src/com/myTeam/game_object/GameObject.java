package com.myTeam.game_object;

import java.awt.*;
import com.myTeam.status.GameWorld;

public abstract class GameObject {
    private float posX;
    private float posY;
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
