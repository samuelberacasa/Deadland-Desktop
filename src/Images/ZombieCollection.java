package Images;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.LinkedList;
import javax.imageio.ImageIO;

public class ZombieCollection {
    LinkedList sprites = new LinkedList();

    public ZombieCollection(){
        sprites.add(loadImage("Resources//Zombie//appear.png"));
        sprites.add(loadImage("Resources//Zombie//moveDown.png"));
        sprites.add(loadImage("Resources//Zombie//moveLeft.png"));
        sprites.add(loadImage("Resources//Zombie//moveRight.png"));
        sprites.add(loadImage("Resources//Zombie//moveUp.png"));
        sprites.add(loadImage("Resources//Zombie//damageDown.png"));
        sprites.add(loadImage("Resources//Zombie//damageLeft.png"));
        sprites.add(loadImage("Resources//Zombie//damageRight.png"));
        sprites.add(loadImage("Resources//Zombie//damageUp.png"));
        sprites.add(loadImage("Resources//Zombie//die.png"));
        sprites.add(loadImage("Resources//Zombie//zombieRadar.png"));
    }


    public static BufferedImage loadImage(String path){
        try{
            BufferedImage imageRead = ImageIO.read(new File(path));
                return imageRead;
        }catch(Exception e){
            System.out.println("OI Error: "+path);
            return null;
        }
    }

    public Object getImage(int index){
        return sprites.get(index);
    }
}
