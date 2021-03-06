package com.myTeam.effect;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

//Singleton
public class CacheDataLoader {
    private static CacheDataLoader instance = null;

    private String framePath = "myProjectMegaman/data/frame.txt";
    private String animationPath = "myProjectMegaman/data/animation.txt";
    private String physMapPath = "myProjectMegaman/data/phys_map.txt";
    private String backgroundsMapPath = "myProjectMegaman/data/background_map.txt";

    private Hashtable<String, FrameImage> frameImages;
    private Hashtable<String, Animation> animations;

    private int[][] physmap;// ban do map trong game
    private int[][] backgroundmap;

    private CacheDataLoader() {

    }

    public static CacheDataLoader getInstance() {
        if (instance == null)
            instance = new CacheDataLoader();
        return instance;
    }

    public FrameImage getFrameImage(String name) {
        FrameImage frameImage = new FrameImage(instance.frameImages.get(name));// <- Call copy constructor
        return frameImage;
    }

    public Animation getAnimation(String name) {
        Animation animation = new Animation(instance.animations.get(name));
        return animation;
    }

    public void LoadFrame() throws IOException {

        frameImages = new Hashtable<String, FrameImage>();

        FileReader fr = new FileReader(framePath);
        BufferedReader br = new BufferedReader(fr);

        String line = null;

        if (br.readLine() == null) {
            System.out.println("No data");
            throw new IOException();
        } else {

            fr = new FileReader(framePath);
            br = new BufferedReader(fr);

            while ((line = br.readLine()).equals("")) ;
            int n = Integer.parseInt(line);
            for (int i = 0; i < n; i++) {

                FrameImage frame = new FrameImage();
                while ((line = br.readLine()).equals("")) ;
                frame.setName(line);

                while ((line = br.readLine()).equals("")) ;
                String[] str = line.split(" ");
                String path = str[1];

                while ((line = br.readLine()).equals("")) ;
                str = line.split(" ");
                int x = Integer.parseInt(str[1]);

                while ((line = br.readLine()).equals("")) ;
                str = line.split(" ");
                int y = Integer.parseInt(str[1]);

                while ((line = br.readLine()).equals("")) ;
                str = line.split(" ");
                int w = Integer.parseInt(str[1]);

                while ((line = br.readLine()).equals("")) ;
                str = line.split(" ");
                int h = Integer.parseInt(str[1]);

                BufferedImage imageData = ImageIO.read(new File(path));
                BufferedImage image = imageData.getSubimage(x, y, w, h);
                frame.setImage(image);

                instance.frameImages.put(frame.getName(), frame);
            }

        }
        System.out.println("frame Readed");
        br.close();

    }

    public void LoadAnimation() throws IOException {

        animations = new Hashtable<String, Animation>();

        FileReader fr = new FileReader(animationPath);
        BufferedReader br = new BufferedReader(fr);

        String line = null;

        if (br.readLine() == null) {
            System.out.println("No data");
            throw new IOException();
        } else {

            fr = new FileReader(animationPath);
            br = new BufferedReader(fr);

            while ((line = br.readLine()).equals("")) ;
            int n = Integer.parseInt(line);

            for (int i = 0; i < n; i++) {

                Animation animation = new Animation();

                while ((line = br.readLine()).equals("")) ;
                animation.setName(line);

                while ((line = br.readLine()).equals("")) ;
                String[] str = line.split(" ");

                for (int j = 0; j < str.length; j += 2)
                    animation.add(getFrameImage(str[j]), Double.parseDouble(str[j + 1]));

                instance.animations.put(animation.getName(), animation);

            }

        }

        br.close();
    }


        public void LoadBackgroundMap () throws IOException {
            FileReader fr = new FileReader(backgroundsMapPath);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            line = br.readLine();
            int numberOfRows = Integer.parseInt(line);
            line = br.readLine();
            int numberOfCollumns = Integer.parseInt(line);

            instance.backgroundmap = new int[numberOfRows][numberOfCollumns];

            for (int i = 0; i < numberOfRows; i++) {
                line = br.readLine();
                String[] str = line.split(" |  ");
                for (int j = 0; j < numberOfCollumns; j++)
                    instance.backgroundmap[i][j] = Integer.parseInt(str[j]);
            }

            /*for (int i = 0; i < numberOfRows; i++) {
                for (int j = 0; j < numberOfCollumns; j++) System.out.print(" " + instance.backgroundmap[i][j]);
                System.out.println();
            }*/
            br.close();
        }
        public void LoadData () throws IOException {
            LoadFrame();
            LoadAnimation();
            LoadPhysMap();
            LoadBackgroundMap();
        }
        public void LoadPhysMap () throws IOException {

            FileReader fr = new FileReader(physMapPath);
            BufferedReader br = new BufferedReader(fr);

            String line = null;

            line = br.readLine();
            int numberOfRows = Integer.parseInt(line);
            line = br.readLine();
            int numberOfColumns = Integer.parseInt(line);


            instance.physmap = new int[numberOfRows][numberOfColumns];

            for (int i = 0; i < numberOfRows; i++) {
                line = br.readLine();
                String[] str = line.split(" ");
                for (int j = 0; j < numberOfColumns; j++)
                    instance.physmap[i][j] = Integer.parseInt(str[j]);
            }

            /*for (int i = 0; i < numberOfRows; i++) {

                for (int j = 0; j < numberOfColumns; j++)
                    System.out.print(" " + instance.physmap[i][j]);

                System.out.println();
            }*/

            br.close();

        }
        public int[][] getPhysmap () {
            return physmap;
        }
    public int[][] getBackgroundMap(){
        return instance.backgroundmap;
    }
}
