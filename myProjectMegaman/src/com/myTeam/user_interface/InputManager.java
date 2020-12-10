package com.myTeam.user_interface;

import com.myTeam.game_object.GameObject;
import com.myTeam.status.GameWorld;

import java.awt.event.KeyEvent;

public class InputManager {
   // private GamePanel gamePanel;
    private GameWorld gameWorld;
    public InputManager(GameWorld gameWorld) {
        this.gameWorld=gameWorld;
    }
    public void processedKeyPress(int keyCode){
        switch (keyCode){
            case KeyEvent.VK_UP:
                System.out.println("Pressed UP");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("Pressed DOWN");
                break;
            case KeyEvent.VK_LEFT:
                //System.out.println("Pressed LEFT");
                //gameWorld.megaman.setDicrection(Mega);
                gameWorld.getMegaMan().setSpeedX(-5);
                break;
            case KeyEvent.VK_RIGHT:
                //System.out.println("Pressed RIGHT");
                gameWorld.getMegaMan().setSpeedX(5);
                break;
            case KeyEvent.VK_ENTER:
                System.out.println("Pressed ENTER");
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("Pressed SPACE");
                break;
        }
    }
    public void processedKeyRelease(int keyCode){
        switch (keyCode){
            case KeyEvent.VK_UP:
                System.out.println("Released UP");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("Released DOWN");
                break;
            case KeyEvent.VK_LEFT:
                gameWorld.getMegaMan().setSpeedX(0);
                break;
            case KeyEvent.VK_RIGHT:
 //               System.out.println("Released RIGHT");
                gameWorld.getMegaMan().setSpeedX(0);
                break;
            case KeyEvent.VK_ENTER:
                System.out.println("Released ENTER");
                break;
            case KeyEvent.VK_SPACE:
             //   System.out.println("Released SPACE");
               gameWorld.getMegaMan().setSpeedY(-3);// toc do nhay khi an space
               gameWorld.getMegaMan().setSpeedY(-3);
                break;
        }
    }
}
