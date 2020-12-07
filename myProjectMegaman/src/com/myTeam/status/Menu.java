package com.myTeam.status;

import com.myTeam.control.Button;
import com.myTeam.control.RectButton;
import com.myTeam.user_interface.Frame;
import com.myTeam.user_interface.GamePanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Menu extends MainStatus {
    
    public final int numButton = 2;
    private BufferedImage buffImage;
    Graphics graphicsPaint;

    private Button[] button;
	private int buttonSelected = 0;
	private boolean canContinue = false;
        
    public Menu(GamePanel panel) {
        super(panel);
        buffImage = new BufferedImage(Frame.SCREEN_WIDTH, Frame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        
        button = new Button[numButton];
        button[0] = new RectButton("NEW GAME", 300, 150, 100, 50, 15, 25, Color.RED);
		button[0].setHoverBgColor(Color.BLUE);
		button[0].setPressedBgColor(Color.GREEN);
		
		button[1] = new RectButton("EXIT",300, 200, 200, 50, 15, 25, Color.RED );
		button[1].setHoverBgColor(Color.BLUE);
		button[1].setPressedBgColor(Color.GREEN);
    }
    
    @Override
    public void Reset() {
        for(int i = 0;i<numButton;i++) {
            if(i == buttonSelected) {
                button[i].setState(Button.HOVER);
            } 
            else {
                button[i].setState(Button.NONE);
            }
        }
    }

    @Override
    public void Render() {
        if(buffImage == null) {
            buffImage = new BufferedImage(Frame.SCREEN_WIDTH, Frame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
            return;
        }
        
        graphicsPaint = buffImage.getGraphics();
        if(graphicsPaint == null) {
            graphicsPaint = buffImage.getGraphics();
            return;
        }
        
        
        graphicsPaint.setColor(Color.BLACK);
		graphicsPaint.fillRect(0, 0, buffImage.getWidth(), buffImage.getHeight());
		for (Button i : button) {
			i.Draw(graphicsPaint);
		}
    }

    private void actionMenu() {
        switch(buttonSelected) {
            case 0:
                panel.setState(new GameWorld(panel));
                break;
           
            case 1:
                System.exit(0);
                break;
        }
    }
    @Override
    public BufferedImage getBufferedImage() {
        return buffImage;
    }

    @Override
    public void setPressedButton(int key) {
        switch(key) {
            case KeyEvent.VK_DOWN:
                buttonSelected++;
                if(buttonSelected >= numButton) {
                    buttonSelected = 0;
                }
                break;
                
            case KeyEvent.VK_UP:
                buttonSelected--;
                if(buttonSelected < 0) {
                    buttonSelected = numButton - 1;
                }
                break;
                
            case KeyEvent.VK_ENTER:
                actionMenu();
                break;
        }
    }

}
