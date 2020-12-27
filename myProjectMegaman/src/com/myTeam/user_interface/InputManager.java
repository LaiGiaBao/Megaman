package com.myTeam.user_interface;

import com.myTeam.status.GameWorld;
import com.myTeam.game_object.MegaMan;

import java.awt.event.KeyEvent;

public class InputManager {
    private GameWorld gameWorld;

    public InputManager(GameWorld gameWorld) {
        this.gameWorld=gameWorld;
    }
    public void processedKeyPress(int keyCode){
        switch (keyCode){
            case KeyEvent.VK_UP:

                break;
            case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_LEFT:
                gameWorld.megaman.setDirection(MegaMan.LEFTDIR);
                gameWorld.megaman.setSpeedX(-3);
                break;
            case KeyEvent.VK_RIGHT:
                gameWorld.megaman.setDirection(MegaMan.RIGHTDIR);
                gameWorld.megaman.setSpeedX(3);
                break;
            case KeyEvent.VK_ENTER:
                if(gameWorld.state == GameWorld.INIT_GAME){
                    if(gameWorld.previousState == GameWorld.GAMEPLAY)
                        gameWorld.switchState(GameWorld.GAMEPLAY);
                    else gameWorld.switchState(GameWorld.TUTORIAL);

                }
                if(gameWorld.state == GameWorld.TUTORIAL && gameWorld.storyTutorial >= 1){
                    if(gameWorld.storyTutorial<=3){
                        gameWorld.storyTutorial ++;
                        gameWorld.currentSize = 1;
                        gameWorld.textTutorial = gameWorld.texts1[gameWorld.storyTutorial-1];
                    }else{
                        gameWorld.switchState(GameWorld.GAMEPLAY);
                    }

                    // for meeting boss tutorial
                    if(gameWorld.tutorialState == GameWorld.MEETFINALBOSS){
                        gameWorld.switchState(GameWorld.GAMEPLAY);
                    }
                }
                break;
            case KeyEvent.VK_SPACE:
                gameWorld.megaman.jump();
                break;
            case KeyEvent.VK_C:
                gameWorld.megaman.attack();
                break;
        }
    }
    public void processedKeyRelease(int keyCode){
        switch (keyCode){
            case KeyEvent.VK_UP:
                System.out.println("Released UP");
                break;
            case KeyEvent.VK_DOWN:
                gameWorld.megaman.standUp();
                System.out.println("Released DOWN");
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("Released LEFT");
                if(gameWorld.megaman.getSpeedX()<0)
                    gameWorld.megaman.stopRun();
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("Released RIGHT");
                if(gameWorld.megaman.getSpeedX()>0)
                    gameWorld.megaman.stopRun();
                break;
            case KeyEvent.VK_ENTER:
                System.out.println("Released ENTER");
                break;
            case KeyEvent.VK_SPACE:
                break;
        }
    }
}
