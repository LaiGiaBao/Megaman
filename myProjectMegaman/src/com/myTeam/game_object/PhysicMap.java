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
    public Rectangle collisionland(Rectangle rectangle) {
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
    //va cham phia tren ( tuong tu vs va cham land)
    public Rectangle collistiontop(Rectangle rectangle) {
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
        if (posX1 < 0) {
            posX1 = 0;
        }
        if (posX2 >= physmap[0].length) {
            posX2 = physmap[0].length-1;
        }
        for (int y=posY;y>=0;y--) {
            for (int x=posX1;x<=posX2;x++) {
            if (physmap[y][x] == 1) {
                Rectangle r =new Rectangle((int) getPosX()+x*sizeofsquares,(int) getPosY()+y*sizeofsquares,sizeofsquares,sizeofsquares);
                if (rectangle.intersects(r)) {
                    return r;
                }

            }
        }
        return null;
        }
        return null;
    }
    public Rectangle collisionright(Rectangle rect){


        int posY1 = rect.y/sizeofsquares;
        posY1-=2;
        int posY2 = (rect.y + rect.height)/sizeofsquares;
        posY2+=2;

        int posX1 = (rect.x + rect.width)/sizeofsquares;
        int posX2 = posX1 + 3;
        if(posX2 >= physmap[0].length) posX2 = physmap[0].length - 1;

        if(posY1 < 0) posY1 = 0;
        if(posY2 >= physmap.length) posY2 = physmap.length - 1;


        for(int x = posX1; x <= posX2; x++){
            for(int y = posY1; y <= posY2;y++){
                if(physmap[y][x] == 1){
                    Rectangle r = new Rectangle((int) getPosX() + x * sizeofsquares, (int) getPosY() + y * sizeofsquares, sizeofsquares, sizeofsquares);
                    if(r.y < rect.y + rect.height - 1 && rect.intersects(r))
                        return r;
                }
            }
        }
        return null;

    }
    public Rectangle collisionlefl(Rectangle rectangle) {
        int posY1= rectangle.y/sizeofsquares;
        posY1-=2;
        int posY2=(rectangle.y+rectangle.height)/sizeofsquares;
        posY2+=2;
        int posX1=rectangle.x/sizeofsquares;
        posX1-=3;
        int posX2=(rectangle.x+rectangle.width)/sizeofsquares;
        posX2+=3;
        for (int i=posX2;i>posX1;i--) {
            for (int j=posY1;j<posY2;j++) {
                if (physmap[j][i] == 1) {
                    Rectangle r = new Rectangle((int) getPosX()+i*sizeofsquares,(int) getPosY()+j*sizeofsquares,sizeofsquares,sizeofsquares);
                    if (rectangle.intersects(r)) {
                        return r;
                    }
                }
            }
            return  null;

    }
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
