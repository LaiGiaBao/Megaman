package com.myTeam.user_interface;

import com.myTeam.effect.Animation;
import com.myTeam.effect.CacheDataLoader;
import com.myTeam.effect.FrameImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private Thread thread;
    private boolean isRunning;
    InputManager inputManager;
    FrameImage frame1;
    Animation anim1;
    GamePanel(){
        inputManager = new InputManager();
        frame1 = CacheDataLoader.getInstance().getFrameImage("idleshoot1");
        anim1 = CacheDataLoader.getInstance().getAnimation("robotRbullet");
        anim1.flipAllImage();
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0,Frame.SCREEN_WIDTH,Frame.SCREEN_HEIGHT);
        Graphics2D g2 = (Graphics2D) g;
        frame1.draw(130,130,g2);
        anim1.draw(300,300,g2);
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
            anim1.Update(System.nanoTime());
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
}
