
package com.myTeam.user_interface;

<<<<<<< Updated upstream
 import com.myTeam.game_object.GameObject;
import com.myTeam.game_object.ObjectO;
import com.myTeam.status.*;

 import com.myTeam.status.GameWorld;
import com.myTeam.status.MainStatus;
 import java.awt.event.KeyEvent;
=======
import com.myTeam.game_object.GameObject;
import com.myTeam.game_object.GameWorld;
>>>>>>> Stashed changes


public class InputManager {
<<<<<<< Updated upstream
   /*  private GamePanel gamePanel;
    public GameWorld gameWorld;
    public MainStatus mainStatus;
    public InputManager(GameWorld gameWorld) {
        this.gameWorld=gameWorld;

    /*public MainStatus mainStatus;
    
    public InputManager(MainStatus ){
        this.mainStatus = status;
=======
  //  private GamePanel gamePanel;
    private GameWorld gameWorld;
    public InputManager(GameWorld gameWorld) {
        this.gameWorld=gameWorld;

>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
               gameWorld.getMegaMan().setDirection(gameWorld.getMegaMan().LEFTDIR);
              // gameWorld.getMegaMan().run();
               break;
            case KeyEvent.VK_RIGHT:
                gameWorld.getMegaMan().setDirection(gameWorld.getMegaMan().RIGHTDIR);
               // gameWorld.getMegaMan().run();
=======
                //System.out.println("Pressed LEFT");
                //gamePanel.megaman.setDicrection(Mega);
                gameWorld.megaman.setSpeedX(-5);
                break;
            case KeyEvent.VK_RIGHT:
                //System.out.println("Pressed RIGHT");
                gameWorld.megaman.setSpeedX(5);
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
                gameWorld.getMegaMan().setSpeedX(0);
                break;
            case KeyEvent.VK_RIGHT:
               System.out.println("Released RIGHT");
                gameWorld.getMegaMan().setSpeedX(0);
=======
                gameWorld.megaman.setSpeedX(0);
                break;
            case KeyEvent.VK_RIGHT:
 //               System.out.println("Released RIGHT");
                gameWorld.megaman.setSpeedX(0);
>>>>>>> Stashed changes
                break;
            case KeyEvent.VK_ENTER:
                System.out.println("Released ENTER");
                break;
            case KeyEvent.VK_SPACE:
<<<<<<< Updated upstream
                System.out.println("Released SPACE");
               gameWorld.getMegaMan().jump();// toc do nhay khi an space

                   gameWorld.getMegaMan().setSpeedY(-3);
=======
             //   System.out.println("Released SPACE");

               gameWorld.megaman.setSpeedY(-3);// toc do nhay khi an space



>>>>>>> Stashed changes
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
