package com.myTeam.game_object;

import com.myTeam.status.GameWorld;


import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List; 

// dung de add remove, add va collision giua megaman vs enemy
public class ObjectManager {

	// danh sach cach cac Object0 ton tai
	protected static List<ObjectO> listOfObject;
	private GameWorld gameWorld;

	public ObjectManager(GameWorld gameWorld) {
		listOfObject =  Collections.synchronizedList(new LinkedList<ObjectO>());
		this.gameWorld=gameWorld;
	}

	//public GameWorld getGameWorld() {
	//	return gameWorld;
	//}
	// bi trung ten trong bullet
	public static void addobject(ObjectO object) {
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

	/*public void addObject(ObjectO objectO){
		synchronized (listOfObjects){
			listOfObjects.add(objectO);
		}
	}

	public void removeObject(ObjectO ){
		synchronized (listOfObjects){
			for(int id =0;id <listOfObjects.size();id++){
				ObjectO object = listOfObjects.get(id);
				if(object == objectO){
					listOfObjects.remove(id);

				}
			}
		}
	}*/
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
	// kiem tra va chan vs object



	// only Update Objects being in cameraView
	public void UpdateObject() {
		synchronized (listOfObject) {
			for (int i = 0; i < listOfObject.size(); i++) {
				ObjectO objectO = listOfObject.get(i);
				if (!objectO.isoutofcameraView()) {
					objectO.Update();
					;
				}
				if (objectO.getState() == ObjectO.DEATH) {
					listOfObject.remove(i);

				}
			}
		}
	}
	/*public void UpdateObjects(){
		synchronized (listOfObjects){
			for(int id =0; id<listOfObjects.size();id++){
				ObjectO object = listOfObjects.get(id);

				if(!object.isObjectOutOfCameraView()) object.Update();

				if(object.getState()== ObjectO.DEATH){
					listOfObjects.remove(id);

				}
			}
		}
	}*/

		public void draw (Graphics2D g2){
			synchronized (listOfObject) {
				for (ObjectO object : listOfObject)
					if (!object.isoutofcameraView()) object.draw(g2);
			}


		}
	}
