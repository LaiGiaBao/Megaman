
package com.myTeam.user_interface;

 import com.myTeam.game_object.GameObject;
import com.myTeam.game_object.ObjectO;
import com.myTeam.status.*;

 import com.myTeam.status.GameWorld;
import com.myTeam.status.MainStatus;
 import java.awt.event.KeyEvent;


public class InputManager {
   /*  private GamePanel gamePanel;
    public GameWorld gameWorld;
    public MainStatus mainStatus;
    public InputManager(GameWorld gameWorld) {
        this.gameWorld=gameWorld;

    /*public MainStatus mainStatus;
    
    public InputManager(MainStatus ){
        this.mainStatus = status;
    }
    
    public void setState(MainStatus status) {
    	mainStatus=status;
     }*/
    
   /* public void processedKeyPress(int keyCode){
         switch (keyCode){
            case KeyEvent.VK_UP:
                System.out.println("Pressed UP");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("Pressed DOWN");
                break;
            case KeyEvent.VK_LEFT:
               gameWorld.getMegaMan().setDirection(gameWorld.getMegaMan().LEFTDIR);
              // gameWorld.getMegaMan().run();
               break;
            case KeyEvent.VK_RIGHT:
                gameWorld.getMegaMan().setDirection(gameWorld.getMegaMan().RIGHTDIR);
               // gameWorld.getMegaMan().run();
                break;
            case KeyEvent.VK_ENTER:
                System.out.println("Pressed ENTER");
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("Pressed SPACE");
                break;
        }
     	mainStatus.processedKeyPress(keyCode);
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
     	mainStatus.processedKeyRelease(keyCode);
     }
    */
   private MainStatus gameState;

    public InputManager(MainStatus state){
        this.gameState = state;
    }

    public void setState(MainStatus state) {
        gameState = state;
    }

    public void setPressedButton(int code){
        gameState.processedKeyRelease(code);
    }

    public void setReleasedButton(int code){
        gameState.processedKeyRelease(code);
    }


    public void setState() {
        setState();
    }
}
