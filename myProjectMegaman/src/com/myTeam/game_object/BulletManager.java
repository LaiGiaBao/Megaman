package com.myTeam.game_object;

public class BulletManager extends ObjectManager {

    public BulletManager(GameWorld gameWorld) {
        super(gameWorld);
    }

    @Override
    public void UpdateObject() {
        super.UpdateObject();
        for (int i = 0; i < listOfObject.size(); i++) {
            ObjectO object = listOfObject.get(i);
            // khi dan va cham or bay khoi camera thi xoa ( va cham tuy vao loai dan)
            if (object.getState() == ObjectO.DEATH || !object.isObjectOutOfCameraView()) {
                listOfObject.remove(i);
                System.out.print("remove ");
            }
        }

    }
}

 

