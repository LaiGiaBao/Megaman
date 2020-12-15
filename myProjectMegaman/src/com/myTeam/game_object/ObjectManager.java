package com.myTeam.game_object;

import com.myTeam.status.GameWorld;
import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List; 

public class ObjectManager {
	protected List<Object> listOfObject;
	public GameWorld gameWorld;
	public GameWorld getGameWorld(){
	        return gameWorld;
	    }
	

    public ObjectManager(GameWorld gameWorld){
        
        List<Object> listOfObject = Collections.synchronizedList(new LinkedList<Object>());
        this.gameWorld = gameWorld;
        
    }
    
	public Object getCollisionWidthEnemy(Object object){
	      synchronized(listOfObject){
	            for(int i = 0; i < listOfObject.size(); i++){
	                
	               Object objectList = listOfObject.get(i);

	                if(object.getTeamType() != objectList.getTeamType() && 
	                        object.getBoundForCollisionWithEnemy().intersects(objectList.getBoundForCollisionWithEnemy())){
	                    return objectList;
	                }
	            }
	        }
	        return null;
	    }
	    
	    
		
	}

