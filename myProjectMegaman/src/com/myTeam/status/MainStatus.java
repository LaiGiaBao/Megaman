package com.myTeam.status;

import com.myTeam.user_interface.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class MainStatus {
	protected GamePanel panel;
	public MainStatus(GamePanel panel) {
		this.panel=panel;
	}
	
	public abstract void Reset();
    public abstract void Render();
    public abstract BufferedImage getBufferedImage();
    
    public abstract void setPressedButton(int key);
    public abstract void setReleasedButton(int key);
}