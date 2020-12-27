package com.myTeam.game_object;

import com.myTeam.status.*;

public class Camera extends GameObject{

    private float widthView;
    private float heightView;

    private boolean isLocked = false;

    public Camera(float x,float y,float widthView,float heightView,GameWorld gameWorld) {
        super(x, y, gameWorld);
        this.widthView = widthView;
        this.heightView = heightView;
    }



	public void lock(){
        isLocked = true;
    }

    public void unlock(){
        isLocked = false;
    }

    @Override
    public void Update() {

        // NOTE: WHEN SEE FINAL BOSS, THE CAMERA WON'T CHANGE THE POSITION,
        // AFTER THE TUTORIAL, CAMERA WILL SET THE NEW POS

        if(!isLocked){

            MegaMan mainCharacter = getGameWorld().megaman();

            if(mainCharacter.getPosX() - getPosX() > 400) setPosX(mainCharacter.getPosX() - 400);
            if(mainCharacter.getPosX() - getPosX() < 200) setPosX(mainCharacter.getPosX() - 200);

            if(mainCharacter.getPosY() - getPosY() > 400) setPosY(mainCharacter.getPosY() - 400); // bottom
            else if(mainCharacter.getPosY() - getPosY() < 250) setPosY(mainCharacter.getPosY() - 250);// top
        }

    }

    public float getWidthView() {
        return widthView;
    }

    public void setWidthView(float widthView) {
        this.widthView = widthView;
    }

    public float getHeightView() {
        return heightView;
    }

    public void setHeightView(float heightView) {
        this.heightView = heightView;
    }

	private float widthCam; // camera nhan vat theo chieu ngang screen
	private float heightCam; // camera nhan vat theo chieu doc man hinh 
	private boolean checkLock= false; // check de co the de camera di chuyen hoac khoa lai khi dau voi boss 
	


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
