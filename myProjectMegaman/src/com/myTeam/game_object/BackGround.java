package com.myTeam.game_object;
import com.myTeam.status.GameWorld;
import com.myTeam.effect.CacheDataLoader;
import com.myTeam.user_interface.Frame;
import java.awt.Color;
import java.awt.Graphics2D;

 /*public class BackGround extends GameObject{

}*/
 public class BackGround extends GameObject{
	public int[][] map;
    private int rectSize;
    
    public BackGround(float x, float y, GameWorld gameWorld) {
        super(x, y, gameWorld);
        map = CacheDataLoader.getInstance().getBackgroundMap();
        rectSize = 30;
    }

    @Override
    public void Update() {}
    
    public void draw(Graphics2D g2){
        
        Camera camera = getGameWorld().camera;
        
        g2.setColor(Color.RED);
        for(int i = 0;i< map.length;i++)
            for(int j = 0;j<map[0].length;j++)
                if(map[i][j]!=0 && j*rectSize - camera.getPosX() > -30 && j*rectSize - camera.getPosX() < Frame.SCREEN_WIDTH
                        && i*rectSize - camera.getPosY() > -30 && i*rectSize - camera.getPosY() < Frame.SCREEN_HEIGHT){ 
                    g2.drawImage(CacheDataLoader.getInstance().getFrameImage("RectSize"+map[i][j]).getImage(), (int) getPosX() + j*rectSize - (int) camera.getPosX(), 
                        (int) getPosY() + i*rectSize - (int) camera.getPosY(), null);
                }
        
    }
    
}

