package com.myTeam.game_object;

import com.myTeam.status.GameWorld;
import com.myTeam.effect.CacheDataLoader;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
//  xu ly va cham cua megaman vs map
public class PhysicMap extends GameObject{
    public int [][] physmap;
    private int sizeofsquares;//...

    public PhysicMap(float x,float y,GameWorld gameWorld) {
        super(x,y,gameWorld);
        this.sizeofsquares=20;
        physmap=CacheDataLoader.getInstance().getPhysmap();// lay phys map tu loadphysmap trong CacheDataLoader
     }
    public void Update(){}
    public void setSizeofsquares(int sizeofsquares) {
        this.sizeofsquares = sizeofsquares;
    }
    public int getSizeofsquares() {
        return sizeofsquares;
    }
    //xu ly va cham cua nhan vat vs land
    public Rectangle land(Rectangle rectangle) {
        // bat dau lap tu vi tri cua rectangle
        int posX1 = rectangle.x/sizeofsquares;
        // lay 2 hinh vuong phia' trc' cua mang
        posX1 -= 2;
        // lay vi tri cuoi ket thuc vong lap
        int posX2 = (rectangle.x + rectangle.width)/sizeofsquares;
        // lay 2 o vuong phia sau nhan vat
        posX2 += 2;
        // do chieu cao cua map chi bang chieu cao cua map nen ko can khai bao hinh vuong ket thuc ( lam bieng lam map )
        int posY = (rectangle.y + rectangle.height)/sizeofsquares;
        // suy bien (index exception) vi tri bat dau co nho hon 0
        if(posX1 < 0) posX1 = 0;
        // kiem tra xem co lon hon map  ko "
        if(posX2 >= physmap[0].length) posX2 = physmap[0].length - 1;
        for(int y = posY; y < physmap.length;y++){
            for(int x = posX1; x <= posX2; x++){
                // 1 la vat can , 0 la void
                if(physmap[y][x] == 1){
                    Rectangle r = new Rectangle((int) getPosX() + x * sizeofsquares, (int) getPosY() + y * sizeofsquares, sizeofsquares, sizeofsquares);
                    // neu co va cham thi tra ve boolean (intersects kiem ra rectangle co va cham vs hinh vuong r ko)
                    if(rectangle.intersects(r))
                        return r;
                }
            }
        }
        return null;
    }
    public void draw(Graphics2D g2){
        //chi? ve map trong vung ma camera tro den
        //Camera camera = getGameWorld().camera;
        //  moi vat can dc minh hoa bang hinh vuong co canh la' sizeofsquares
        g2.setColor(Color.GRAY);
        for(int i = 0;i< physmap.length;i++)
            for(int j = 0;j<physmap[0].length;j++)
                if(physmap[i][j]!=0) g2.fillRect((int) getPosX() + j*sizeofsquares - (int) camera.getPosX(),
                        (int) getPosY() + i*sizeofsquares - (int) camera.getPosY(), sizeofsquares, sizeofsquares);

    }
}
