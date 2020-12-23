package com.myTeam.game_object;
import com.myTeam.status.*;
import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// dung de add remove, add va collision giua megaman vs enemy
public class ObjectManager {
	// danh sach cach cac Object0 ton tai
	protected static List<ObjectO> listOfObject;
	private GameWorld gameWorld;

	public ObjectManager(GameWorld gameWorld) {
		listOfObject = (List<ObjectO>) Collections.synchronizedCollection(new LinkedList<ObjectO>());
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
	// kiem tra va chan vs object
	public ObjectO collisionwithenemy(ObjectO object) {
		synchronized (listOfObject) {
			for (int i=0;i< listOfObject.size();i++) {
				ObjectO object1 = listOfObject.get(i);
				// neu cung team thi ko co xay ra chuyen gi ( dinh lam them may con team de buff suc manh cho X nhung code ko chay :<<)
				if (object.getTeamType()!= object1.getTeamType() && object.getBoundForCollisionWithEnemy().intersects(object1.getBoundForCollisionWithEnemy()) ) {
					return object1;
				}
			}
		}
		return null;
	}
	// only Update Objects being in cameraView
	public void UpdateObject() {
		synchronized (listOfObject) {
			for (int i=0;i< listOfObject.size();i++) {
				ObjectO object= listOfObject.get(i);
				if (!object.isoutofcameraView()){
					object.Update();;
				}
				if (object.getState() == ObjectO.DEATH) {
					listOfObject.remove(i);
				}
			}
		}
	}
	public void draw(Graphics2D g) {
		synchronized (listOfObject) {
			for (ObjectO object : listOfObject) {
				if (!object.isoutofcameraView()) {
					object.draw(g);
				}
			}
		}
	}
}

