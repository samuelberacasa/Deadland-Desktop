package Characters;

import Extra.Weapon;
import Images.HeroCollection;
import Time.Stopwatch;

import java.awt.image.BufferedImage;

public class Hero extends Character {
    public enum State{stand,move,knife,shoot};
    public Weapon weapon;
    public State state;
    int imageIndex;
    HeroCollection imageCollection;
    BufferedImage bufferedImage;

    public Hero(int x, int y, int frameWidth, int frameHeight, Weapon weapon){
        imageCollection = new HeroCollection();
        direc = direc.up;
        state = state.stand;
        this.weapon = weapon;
        hp = 3;
        damage = 1;
        this.x = x;
        this.y = y;
        stepTime = new Stopwatch(3);
        this.spriteSelection();
        imageRadar = (BufferedImage) imageCollection.getImage(15);
        this.image = (BufferedImage) imageCollection.getImage(imageIndex);
        this.posX = frameWidth / 2 - image.getWidth()/2;
        this.posY = frameHeight / 2 - image.getHeight()/2;
    }

    public void setState(State e){
        if(this.state != e){
            this.state = e;
            step = 0;
            this.spriteSelection();
        }
    }

    public void spriteSelection(){
        switch (state){
            case knife:
                speed = 1;
                sprites = 6;
                switch(direc){
                    case down:
                        imageIndex = 0;
                        break;
                    case left:
                        imageIndex = 1;
                        break;
                    case right:
                        imageIndex = 2;
                        break;
                    case up:
                        imageIndex = 3;
                        break;
                }
                break;
            case move:
                speed = 1;
                sprites = 6;
                switch(direc){
                    case down:
                        imageIndex = 4;
                        break;
                    case left:
                        imageIndex = 5;
                        break;
                    case right:
                        imageIndex = 6;
                        break;
                    case up:
                        imageIndex = 7;
                        break;
                }
                break;
            case shoot:
                speed = 0;
                sprites = 1;
                switch(direc){
                    case down:
                        imageIndex = 8;
                        break;
                    case left:
                        imageIndex = 9;
                        break;
                    case right:
                        imageIndex = 10;
                        break;
                    case up:
                        imageIndex = 11;
                        break;
                }
                break;
            case stand:
                speed = 1;
                sprites = 1;
                switch (direc){
                    case down:
                        imageIndex = 12;
                        break;
                    case left:
                        imageIndex = 13;
                        break;
                    case right:
                        imageIndex = 14;
                        break;
                    case up:
                        imageIndex = 15;
                        break;
                }
                break;
        }
        this.image = (BufferedImage) imageCollection.getImage(imageIndex);
    }

    public BufferedImage getImage(){
        int ix, iy, width, height;
        width = image.getWidth() / sprites;
        height = image.getHeight();
        ix = width*step;
        iy = 0;

        switch (state){
            case knife:
            case move:
                if(stepTime.isTime()){
                    step = (step+1)%sprites;
                }
                break;
        }
        return image.getSubimage(ix,iy,width,height);
    }
}
