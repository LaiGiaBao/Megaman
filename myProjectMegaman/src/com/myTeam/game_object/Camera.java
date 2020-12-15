package com.myTeam.game_object;

import com.myTeam.status.GameWorld;

public class Camera extends GameObject{
	private float widthCam; // camera nhan vat theo chieu ngang screen
	private float heightCam; // camera nhan vat theo chieu doc man hinh 
	private boolean checkLock= false; // check de co the de camera di chuyen hoac khoa lai khi dau voi boss 
	
	public Camera(float x, float y, float widthView, float heightView, GameWorld gameWorld) {
	        super(x, y, gameWorld);
	        this.widthCam = widthCam;
	        this.heightCam = heightCam;
	    }
	
	public void lock(){
		checkLock = true;
				}
	
	public void unlock(){
	        checkLock = false;
	    }
	    
	    @Override
	    public void Update() {
	    	//Gap BOSS thi khoa man hinh de solo vs boss
	    	//Di chuyen se chuyen boi canh cua nhan vat 
	        
	        if(!checkLock){
	        
	            MegaMan mainPlayer = getGameWorld().megaMan;

	            if(mainPlayer.getPosX() - getPosX() > 500) 
	            	setPosX(mainPlayer.getPosX() - 500);
	            if(mainPlayer.getPosX() - getPosX() < 300) 
	            	setPosX(mainPlayer.getPosX() - 300);

	            if(mainPlayer.getPosY() - getPosY() > 500) 
	            	setPosY(mainPlayer.getPosY() - 500); // view o duoi nhan vat 
	            else if(mainPlayer.getPosY() - getPosY() < 350) 
	            	setPosY(mainPlayer.getPosY() - 350);// view o tren nhan vat
	        }
	    
	    }

	    public float getWidthCam() {
	        return widthCam;
	    }

	    public void setWidthCam(float widthCam) {
	        this.widthCam = widthCam;
	    }

	    public float getHeightCam() {
	        return heightCam;
	    }

	    public void setHeightCam(float heightCam) {
	        this.heightCam = heightCam;
	    }
	    
	}
