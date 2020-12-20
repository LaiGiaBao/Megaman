package com.myTeam.game_object;

import com.myTeam.status.GameWorld;
import com.myTeam.game_object.ObjectManager;

public class BulletManager extends ObjectManager{
	public BulletManager(GameWorld gameWorld) {
        super(gameWorld);
    }

    @Override
    public void UpdateObjects() {
        super.UpdateObjects(); 
        synchronized(listOfObject){
            for(int i = 0; i < listOfObject.size(); i++){
                
                Object object = listOfObject.get(i);
                
                if(object.isObjectOutOfCameraView() || object.getState() == Object.DEATH){
                	listOfObject.remove(i);
                }
            }
        }
    }
}
