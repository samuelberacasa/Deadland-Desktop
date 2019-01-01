package Characters;
import Time.Stopwatch;
import java.awt.image.BufferedImage;

public class Character {
    public enum Direction{up,down,left,right}

    public int x;
    public int y;
    public int posX;
    public int posY;
    public int hp;
    public int damage;
    public int speed;
    public int sprites;
    public int step;
    public BufferedImage image;
    public BufferedImage imageRadar;
    public Direction direc;
    public Stopwatch moveTime, stepTime;

    public void setDirection (Direction direc){
        if(this.direc != direc){
            this.direc = direc;
            step = 0;
            speed = 1;
        }
    }

    public boolean notCollision(int map[][]){
        switch (direc){
            case left:
                if(map[x-2][y]==0){
                    return false;
                }
                break;
            case right:
                if(map[x+1][y]==0){
                    return false;
                }
                break;
            case up:
                if(map[x][y-2]==0){
                    return false;
                }
                break;
            case down:
                if(map[x][y+1]==0){
                    return false;
                }
                break;
        }
        return true;
    }
}
