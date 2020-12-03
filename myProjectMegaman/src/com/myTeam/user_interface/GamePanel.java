package com.myTeam.user_interface;

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
    GamePanel(){
        inputManager = new InputManager();
        try {
            BufferedImage image = ImageIO.read(new File("C:\\Users\\PC\\Documents\\GitHub\\Megaman\\myProjectMegaman\\data\\megasprite.png"));
            BufferedImage image1 = image.getSubimage(529,38,70,100);
            frame1 = new FrameImage("frame1",image1);
        } catch  (IOException e) { }
    }
    @Override
    public void paint(Graphics g){
        g.setColor(new Color (205,38,38));
        g.fillRect(0,0,Frame.SCREEN_WIDTH,Frame.SCREEN_HEIGHT);
        Graphics2D g2 = (Graphics2D) g;
        frame1.draw(30,30,g2);
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
