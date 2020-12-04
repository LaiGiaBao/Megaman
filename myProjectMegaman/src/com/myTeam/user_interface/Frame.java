package com.myTeam.user_interface;

import com.myTeam.effect.CacheDataLoader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Frame extends JFrame {
    public static final int SCREEN_WIDTH = 1600;
    public static final int SCREEN_HEIGHT = 900;
    GamePanel gamePanel;
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
        gamePanel.start();
    }
    public static void main(String[] args) {
        Frame gameFrame = new Frame();
        gameFrame.setVisible(true);
        gameFrame.startGame();
    }
}
