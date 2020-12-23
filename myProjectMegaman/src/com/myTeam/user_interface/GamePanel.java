package com.myTeam.user_interface;

import com.myTeam.effect.Animation;
import com.myTeam.effect.CacheDataLoader;
import com.myTeam.effect.FrameImage;
import com.myTeam.game_object.GameObject;
import com.myTeam.game_object.MegaMan;
import com.myTeam.status.*;
import com.myTeam.status.MainStatus;
import com.myTeam.status.Menu;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private Thread thread;
    private boolean isRunning;
    InputManager inputManager;
    private BufferedImage bufImage;
    private Graphics2D bufG2D;
    GameWorld gameWorld;
    //InputManager inputManager;
    MegaMan megaMan;
    //MegaMan megaman = new GameObject(300, 300, gameWorld);
    Menu menu;
    GamePanel(){
        gameWorld = new GameWorld(this);
        //inputManager = new InputManager(this);
        bufImage = new BufferedImage(Frame.SCREEN_WIDTH, Frame.SCREEN_HEIGHT,BufferedImage.TYPE_INT_ARGB);// RGB -> 3 main colors
      //  megaMan = new GameObject(300, 300, this);
    }
    public void UpdateGame() {
        gameWorld.Update();
    }
    public void RenderGame(){
        if(bufImage == null){
            bufImage = new BufferedImage(Frame.SCREEN_WIDTH, Frame.SCREEN_HEIGHT,BufferedImage.TYPE_INT_ARGB);
        }
        else{
            bufG2D = (Graphics2D) bufImage.getGraphics();
        }
        if(bufG2D != null){
            //draw every object in here
            bufG2D.setColor(Color.DARK_GRAY);
            bufG2D.fillRect(0,0,Frame.SCREEN_WIDTH,Frame.SCREEN_HEIGHT);
            gameWorld.Render();
            bufG2D.setColor(Color.CYAN);
            bufG2D.fillRect(40,50,100,100);
         //   megaman.draw(bufG2D);

            bufG2D.setColor(Color.CYAN);
            bufG2D.fillRect(40,50,100,100);
           // megaman.draw(bufG2D);

        }
    }
    @Override
    public void paint(Graphics g){

      //  g.drawImage(bufImage,0,0,this);

    }
    public void start(){
        if (thread == null){
            isRunning = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    @Override
    public void run() {
        long FPS = 60;
        long period = 1000 *1000000 /FPS; //nanoseconds
        long beginTime;
        long sleepTime;
        beginTime = System.nanoTime();
        while(isRunning){
            /*
            * Update game*/
            UpdateGame();
           /* Render game
            * */
            RenderGame();
            repaint();
            long deltaTime = System.nanoTime() - beginTime;
            sleepTime = period - deltaTime;
            try {
                if(sleepTime >0) {
                    Thread.sleep(sleepTime/2000000);
                }
                else {Thread.sleep(period/2000000);}
            } catch (InterruptedException e) {
                e.printStackTrace();
             }
            beginTime = System.nanoTime();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

         inputManager.processedKeyPress(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        inputManager.processedKeyRelease(e.getKeyCode());

    }

    public void setGameWorld(GameWorld gameWorld) {

        this.gameWorld = gameWorld;
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }
}
