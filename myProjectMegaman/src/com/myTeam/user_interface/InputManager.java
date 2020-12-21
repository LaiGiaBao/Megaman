
package com.myTeam.user_interface;

import com.myTeam.status.GameWorld;
import com.myTeam.status.MainStatus;
import java.awt.event.KeyEvent;


public class InputManager {
    
    private MainStatus status;
    
    public InputManager(MainStatus status){
        this.status = status;
    }
    
    public void setState(MainStatus status) {
        this.status=status;
    }
    
    public void processedKeyPress(int keyCode){
        status.processedKeyPress(keyCode);
    }
    
    public void processedKeyRelease(int keyCode){
        status.processedKeyRelease(keyCode);
    }
    
}
