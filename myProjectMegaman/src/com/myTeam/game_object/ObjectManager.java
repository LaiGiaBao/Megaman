package com.myTeam.game_object;

import com.myTeam.status.GameWorld;

import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List; 

public class ObjectManager {
	protected List<ObjectO> listOfObjects;
	private GameWorld gameWorld;
	public ObjectManager(GameWorld gameWorld){
		listOfObjects = Collections.synchronizedList(new LinkedList<ObjectO>());
		this.gameWorld = gameWorld;
	}

	public GameWorld getGameWorld() {
		return gameWorld;
	}

	public void setGameWorld(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}

	public void addObject(ObjectO objectO){
		synchronized (listOfObjects){
			listOfObjects.add(objectO);
		}
	}

	public void removeObject(ObjectO objectO){
		synchronized (listOfObjects){
			for(int id =0;id <listOfObjects.size();id++){
				ObjectO object = listOfObjects.get(id);
				if(object == objectO){
					listOfObjects.remove(id);
				}
			}
		}
	}

	public ObjectO getCollisionWithEnemy(ObjectO particularObject){
		synchronized (listOfObjects){
			for(int id =0; id <listOfObjects.size();id++){
				ObjectO object = listOfObjects.get(id);

				if(particularObject.getTeamType()!= object.getTeamType() &&
						particularObject.getBoundForCollisionWithEnemy().intersects(object.getBoundForCollisionWithEnemy())){
					return object;
				}
			}
		}
		return null;
	}


	public void UpdateObjects(){
		synchronized (listOfObjects){
			for(int id =0; id<listOfObjects.size();id++){
				ObjectO object = listOfObjects.get(id);

				if(!object.isObjectOutOfCameraView()) object.Update();

				if(object.getState()== ObjectO.DEATH){
					listOfObjects.remove(id);
				}
			}
		}
	}

	public void draw(Graphics2D g2){
		synchronized (listOfObjects){
			for (ObjectO object: listOfObjects)
				if(!object.isObjectOutOfCameraView()) object.draw(g2);
		}
	}
}

