package com.myTeam.user_interface;

import com.myTeam.game_object.GameObject;
import com.myTeam.game_object.ObjectO;
import com.myTeam.status.*;

import java.awt.event.KeyEvent;

public class InputManager {
    private GamePanel gamePanel;
    public GameWorld gameWorld;
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
               gameWorld.getMegaMan().setDirection(gameWorld.getMegaMan().LEFTDIR);
               gameWorld.getMegaMan().run();
               break;
            case KeyEvent.VK_RIGHT:
                gameWorld.getMegaMan().setDirection(gameWorld.getMegaMan().RIGHTDIR);
                gameWorld.getMegaMan().run();
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
               System.out.println("Released RIGHT");
                gameWorld.getMegaMan().setSpeedX(0);
                break;
            case KeyEvent.VK_ENTER:
                System.out.println("Released ENTER");
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("Released SPACE");
               gameWorld.getMegaMan().jump();// toc do nhay khi an space

                   gameWorld.getMegaMan().setSpeedY(-3);
                break;
        }
    }
}
