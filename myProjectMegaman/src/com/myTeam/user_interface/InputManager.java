package com.myTeam.user_interface;

import com.myTeam.game_object.GameObject;

import java.awt.event.KeyEvent;

public class InputManager {
    private GamePanel gamePanel;
    public InputManager(GamePanel gamePanel) {
        this.gamePanel=gamePanel;
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
                //gamePanel.megaman.setDicrection(Mega);
                gamePanel.megaman.setSpeedX(-5);
                break;
            case KeyEvent.VK_RIGHT:
                //System.out.println("Pressed RIGHT");
                gamePanel.megaman.setSpeedX(5);
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
                gamePanel.megaman.setSpeedX(0);
                break;
            case KeyEvent.VK_RIGHT:
 //               System.out.println("Released RIGHT");
                gamePanel.megaman.setSpeedX(0);
                break;
            case KeyEvent.VK_ENTER:
                System.out.println("Released ENTER");
                break;
            case KeyEvent.VK_SPACE:
             //   System.out.println("Released SPACE");
               gamePanel.megaman.setSpeddY(-3);
                break;
        }
    }
}
