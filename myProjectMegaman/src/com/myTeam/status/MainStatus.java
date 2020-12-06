package com.myTeam.status;

import com.myTeam.user_interface.GamePanel;
import java.awt.image.BufferedImage;

public abstract class MainStatus {
	private GamePanel panel;
	public MainStatus(GamePanel panel) {
		this.panel=panel;
	}
	
	public abstract void Refresh();
    public abstract void Render();
    public abstract BufferedImage getBufferedImage();
    
    public abstract void setPressedButton(int instance);
    public abstract void setReleasedButton(int instance);
}