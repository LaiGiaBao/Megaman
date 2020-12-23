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
	private GameWorld world;
	
	public GameObject(float x, float y, GameWorld world){
		posX=x;
		posY=y;
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
	
}
