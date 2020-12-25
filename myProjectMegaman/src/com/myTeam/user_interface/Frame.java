package com.myTeam.user_interface;

import com.myTeam.effect.CacheDataLoader;
import com.myTeam.effect.FrameImage;
import com.myTeam.status.GameWorld;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class Frame extends JFrame {
    public static final int SCREEN_WIDTH = 1600;
    public static final int SCREEN_HEIGHT = 900;
    GamePanel gamePanel;
   // GameWorld gameWorld;
    public Frame()  {
        Toolkit toolkit = this.getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds((dimension.width-SCREEN_WIDTH)/2,(dimension.height-SCREEN_HEIGHT)/2,SCREEN_WIDTH,SCREEN_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            CacheDataLoader.getInstance().LoadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setResizable(false);
        gamePanel = new GamePanel();
        this.add(gamePanel);
        this.addKeyListener(gamePanel);
    }
    public void startGame(){

        gamePanel.stargame();
        gamePanel.setVisible(true);
    }
    public static void main(String[] args) throws IOException {
        Frame gameFrame = new Frame();
        gameFrame.setVisible(true);
        gameFrame.startGame();

    }
}
