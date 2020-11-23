package com.myTeam.user_interface;

import java.awt.event.KeyEvent;

public class InputManager {
    public void processedKeyPress(int keyCode){
        switch (keyCode){
            case KeyEvent.VK_UP:
                System.out.println("Pressed UP");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("Pressed DOWN");
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("Pressed LEFT");
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("Pressed RIGHT");
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
                System.out.println("Released LEFT");
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("Released RIGHT");
                break;
            case KeyEvent.VK_ENTER:
                System.out.println("Released ENTER");
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("Released SPACE");
                break;
        }
    }
}
