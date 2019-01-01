package Images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

public class HeroCollection {
    LinkedList sprites = new LinkedList();

    public HeroCollection(){
        sprites.add(loadImage("Resources//Hero//knifeDown.png"));
        sprites.add(loadImage("Resources//Hero//knifeLeft.png"));
        sprites.add(loadImage("Resources//Hero//knifeRight.png"));
        sprites.add(loadImage("Resources//Hero//knifeUp.png"));
        sprites.add(loadImage("Resources//Hero//moveDown.png"));
        sprites.add(loadImage("Resources//Hero//moveLeft.png"));
        sprites.add(loadImage("Resources//Hero//moveRight.png"));
        sprites.add(loadImage("Resources//Hero//moveUp.png"));
        sprites.add(loadImage("Resources//Hero//shootDown.png"));
        sprites.add(loadImage("Resources//Hero//shootLeft.png"));
        sprites.add(loadImage("Resources//Hero//shootRight.png"));
        sprites.add(loadImage("Resources//Hero//shootUp.png"));
        sprites.add(loadImage("Resources//Hero//standDown.png"));
        sprites.add(loadImage("Resources//Hero//standLeft.png"));
        sprites.add(loadImage("Resources//Hero//standRight.png"));
        sprites.add(loadImage("Resources//Hero//standUp.png"));
        sprites.add(loadImage("Resources//Hero//heroRadar.png"));
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
