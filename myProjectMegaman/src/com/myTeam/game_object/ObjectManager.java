package com.myTeam.game_object;

import com.myTeam.status.GameWorld;


import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List; 

public class ObjectManager {

	// danh sach cach cac Object0 ton tai
	protected static List<ObjectO> listOfObject;
	private GameWorld gameWorld;

	public ObjectManager(GameWorld gameWorld) {
		listOfObject =  Collections.synchronizedList(new LinkedList<ObjectO>());
		this.gameWorld=gameWorld;
	}

	public GameWorld getGameWorld() {
		return gameWorld;
	}
	
	public static void addObject(ObjectO object) {
		synchronized (listOfObject) {
			listOfObject.add(object);
		}
	}

	public void removeObject(ObjectO object) {
		synchronized (listOfObject) {
			for (int i = 0; i < listOfObject.size(); i++) {
				ObjectO object1 = listOfObject.get(i);
				if (object1 == object ) {
					listOfObject.remove(i);
				}
			}

		}
	}
	public void setGameWorld(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}

	public ObjectO getCollisionWithEnemy(ObjectO object){
						synchronized (listOfObject){
							for(int id =0; id <listOfObject.size();id++){
								ObjectO object1 = listOfObject.get(id);
								if(object.getTeamType()!= object1.getTeamType() &&
										object.getBoundForCollisionWithEnemy().intersects(object1.getBoundForCollisionWithEnemy())){
									return object1;
								}
							}
						}
						return null;
					}
	
	public void UpdateObject() {
		synchronized (listOfObject) {
			for (int i = 0; i < listOfObject.size(); i++) {
				ObjectO objectO = listOfObject.get(i);
				if (!objectO.isObjectOutOfCameraView()) {
					objectO.Update();
					;
				}
				if (objectO.getState() == ObjectO.DEATH) {
					listOfObject.remove(i);

				}
			}
		}
	}
	public void UpdateObjects(){
		synchronized (listOfObject){
			for(int i =0; i<listOfObject.size();i++){
				ObjectO object = listOfObject.get(i);

				if(!object.isObjectOutOfCameraView()) 
					object.Update();

				if(object.getState()== ObjectO.DEATH){
					listOfObject.remove(i);

				}
			}
		}
	}

		public void draw (Graphics2D g2){
			synchronized (listOfObject) {
				for (ObjectO object : listOfObject)
					if (!object.isObjectOutOfCameraView()) object.draw(g2);
			}


		}
	}
