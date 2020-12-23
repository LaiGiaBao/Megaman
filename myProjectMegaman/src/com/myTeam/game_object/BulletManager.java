package com.myTeam.game_object;

import com.myTeam.status.GameWorld;

public class BulletManager extends ObjectManager{
	public BulletManager(GameWorld gameWorld) {
        super(gameWorld);
    }

    @Override
    public void UpdateObjects() {
        super.UpdateObjects(); 
        synchronized(listOfObjects){
            for(int i = 0; i < listOfObjects.size(); i++){
                
                ObjectO objectO = listOfObjects.get(i);
                
                if(objectO.isObjectOutOfCameraView() || objectO.getState() == ObjectO.DEATH){
                	listOfObjects.remove(i);
                }
            }
        }
    }
}
