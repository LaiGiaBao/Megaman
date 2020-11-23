package com.myTeam.user_interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private Thread thread;
    private boolean isRunning;
    InputManager inputManager;
    GamePanel(){
        inputManager = new InputManager();
    }
    @Override
    public void paint(Graphics g){
        g.setColor(new Color (205,38,38));
        g.fillRect(0,0,Frame.SCREEN_WIDTH,Frame.SCREEN_HEIGHT);
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
        int a =0;
        beginTime = System.nanoTime();
        while(isRunning){
            /*
            * Update game
            * Render game
            * */
            //System.out.println("a= "+ (a++) );
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
}
