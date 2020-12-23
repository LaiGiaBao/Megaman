
package com.myTeam.user_interface;

import com.myTeam.status.GameWorld;
import com.myTeam.status.MainStatus;
import java.awt.event.KeyEvent;


public class InputManager {
    
    private MainStatus mainStatus;
    
    public InputManager(MainStatus status){
        this.mainStatus = status;
    }
    
    public void setState(MainStatus status) {
    	mainStatus=status;
    }
    
    public void processedKeyPress(int keyCode){
    	mainStatus.processedKeyPress(keyCode);
    }
    
    public void processedKeyRelease(int keyCode){
    	mainStatus.processedKeyRelease(keyCode);
    }
    
}
