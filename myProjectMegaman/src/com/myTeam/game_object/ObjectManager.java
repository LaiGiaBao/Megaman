package com.myTeam.game_object;

import com.myTeam.status.GameWorld;
import com.myTeam.game_object.Object;

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
	 public void UpdateObjects(){
        
        synchronized(listOfObject){
            for(int id = 0; id < listOfObject.size(); id++){
                
                Object object = listOfObject.get(id);
                
                
                if(!object.isObjectOutOfCameraView()) object.Update();
                
                if(object.getState() == Object.DEATH){
                	listOfObject.remove(id);
                }
            }
        }
        
    }
	
	 public void addObject(Object object){
	        synchronized(listOfObject){
	        	listOfObject.add(object);
	        }
	        
	    }
	 
	 public void removeObject(Object object){
	        synchronized(listOfObject){
	        
	            for(int i = 0; i < listOfObject.size(); i++){
	                
	                Object object1 = listOfObject.get(i);
	                if(object1 == object)
	                	listOfObject.remove(i);

	            }
	        }
	    }
	 
	 public void draw(Graphics2D g2){
	        synchronized(listOfObject){
	            for(Object object: listOfObject)
	                if(!object.isObjectOutOfCameraView()) object.draw(g2);
	        }
	    }
	}

