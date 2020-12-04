package com.myTeam.effect;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;
//Singleton
public class CacheDataLoader {
    private static CacheDataLoader instance;

    private String framePath = "myProjectMegaman/data/frame.txt";
    private String animationPath ="myProjectMegaman/data/animation.txt";
    private Hashtable<String,FrameImage> frameImages;
    private Hashtable<String, Animation> animations;
    private CacheDataLoader(){

    }
    public static CacheDataLoader getInstance(){
        if(instance == null)
            instance = new CacheDataLoader();
        return instance;
    }

    public FrameImage getFrameImage(String name){
        FrameImage frameImage = new FrameImage(instance.frameImages.get(name));// <- Call copy constructor
        return frameImage;
    }

    public Animation getAnimation(String name){
        Animation animation = new Animation(instance.animations.get(name));
        return animation;
    }

    public void LoadFrame() throws IOException {
        frameImages = new Hashtable<String, FrameImage>();
        FileReader fr = new FileReader(framePath);
        BufferedReader br = new BufferedReader(fr);

        String line = null;

        if(br.readLine()==null){
            System.out.println("No data");
            throw new IOException();
        }
        else {
            // Đưa con trỏ về đầu file
            fr = new FileReader(framePath);
            br = new BufferedReader(fr);
            //Skip dòng trống
            while((line = br.readLine()).equals(""));

            int n = Integer.parseInt(line);
            for(int i =0; i<n;i++){
                String[] str;
                FrameImage frame = new FrameImage();
                while((line = br.readLine()).equals(""));
                frame.setName(line);

                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                String path = str[1];

                while((line = br.readLine()).equals(""));
                str= line.split(" ");
                int x = Integer.parseInt(str[1]);

                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int y = Integer.parseInt(str[1]);

                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int w = Integer.parseInt(str[1]);

                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int h = Integer.parseInt(str[1]);

                BufferedImage imageData = ImageIO.read(new File(path));
                BufferedImage image = imageData.getSubimage(x,y,w,h);
                frame.setImage(image);

                instance.frameImages.put(frame.getName(),frame);
            }
        }
    }

    public void LoadAnimation() throws IOException{
        animations = new Hashtable<String,Animation>();

        FileReader fr = new FileReader(animationPath);
        BufferedReader br = new BufferedReader(fr);

        String line = null;
        if(br.readLine()==null){
            System.out.println("No data");
            throw new IOException();
        }
        else{
            while((line = br.readLine()).equals(""));
            int n = Integer.parseInt(line);

            for(int i =0;i< n;i++){
                String[] str;
                Animation animation = new Animation();
                while((line = br.readLine()).equals(""));
                animation.setName(line);

                while((line = br.readLine()).equals(""));
                str = line.split(" ");

                for(int j =0;j <str.length;j+=2)
                    animation.add(getFrameImage(str[j]),Double.parseDouble(str[j+1]));
                instance.animations.put(animation.getName(),animation);
            }
        }
        br.close();
    }
    public void LoadData() throws IOException{
        LoadFrame();
        LoadAnimation();
    }
}
