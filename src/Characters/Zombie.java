package Characters;

import Images.ZombieCollection;
import Time.Stopwatch;
import java.awt.image.BufferedImage;

public class Zombie extends Character{
    enum State{appear, follow, attack, damage, die}

    ZombieCollection imageCollection;
    Stopwatch appearTime;
    Stopwatch attackTime;
    Stopwatch recoverTime;
    public State state;

    public Zombie(){
        imageCollection = new ZombieCollection();
        x = 0;
        y = 0;
        posX = 0;
        posY = 0;
        hp = 6;
        step = 0;
        damage = 1;
        speed = 1;
        sprites = 5;
        state = state.appear;
        direc = direc.left;
        image = (BufferedImage) imageCollection.getImage(0);
        imageRadar = (BufferedImage) imageCollection.getImage(10);
        appearTime = new Stopwatch(10);
        attackTime = new Stopwatch(50);
        recoverTime = new Stopwatch(20);
        stepTime = new Stopwatch(4);
        moveTime = new Stopwatch(8);
    }

    public void move(int map[][]){
        if(moveTime.isTime()){
            map[this.x][this.y]=0;
            switch (direc){
                case left:
                    this.x = this.x - speed;
                break;
                case right:
                    this.x = this.x + speed;
                    break;
                case down:
                    this.y = this.y + speed;
                    break;
                case up:
                    this.y = this.y - speed;
                    break;
            }
            map[this.x][this.y]=1;
        }
    }

    public void setState(State e){
        if(this.state != e){
            this.state = e;
            step = 0;
            speed = 1;
            this.spriteSelection();
        }
    }

    public void spriteSelection(){
        int imageIndex = 0;
        switch (state){
            case appear:
                speed = 0;
                sprites = 5;
                imageIndex = 0;
                break;
            case follow:
                speed = 1;
                sprites = 4;
                switch (direc){
                    case down:
                        imageIndex = 1;
                        break;
                    case left:
                        imageIndex = 2;
                        break;
                    case right:
                        imageIndex = 3;
                        break;
                    case up:
                        imageIndex = 4;
                        break;
                }
                break;
            case damage:
                speed = 0;
                sprites = 1;
                switch(direc){
                    case down:
                        imageIndex = 5;
                        break;
                    case left:
                        imageIndex = 6;
                        break;
                    case right:
                        imageIndex = 7;
                        break;
                    case up:
                        imageIndex = 8;
                        break;
                }
                break;
            case attack:
                speed = 0;
                sprites = 4;
                switch(direc){
                    case down:
                        imageIndex = 1;
                        break;
                    case left:
                        imageIndex = 2;
                        break;
                    case right:
                        imageIndex = 3;
                        break;
                    case up:
                        imageIndex = 4;
                        break;
                }
                break;
            case die:
                speed = 0;
                sprites = 6;
                imageIndex = 9;
                break;
        }
        this.image = (BufferedImage) imageCollection.getImage(imageIndex);
    }

    public void action(Character p, int map[][], int camera[][]){
        switch(state){
            case appear:
                break;
            case follow:
                this.follow(p,map,camera);
                break;
            case attack:
                this.attack(p);
                break;
            case damage:
                break;
            case die:
                break;
        }
    }

    public void follow(Character p, int map[][], int blocks[][]){
        ///TBD
    }

    public void attack(Character p){
        if(attackTime.isTime()){
            switch (direc){
                case down:
                    if((p.y-5 == this.y) && (p.x-3 < this.x) && (p.x > this.x)){
                        p.hp -=1;
                    }else{
                        this.state = state.follow;
                    }
                    break;
                case left:
                    if((p.x-1 == this.x) && (p.y-1 > this.y) && (p.y-5 < this.y)){
                        p.hp-=1;
                    }else{
                        this.state = state.follow;
                    }
                    break;
                case right:
                    if((p.x-2 == this.x) && (p.y-1 > this.y) && (p.y-5 < this.y)){
                        p.hp-=1;
                    }else{
                        this.state = state.follow;
                    }
                    break;
                case up:
                    if((p.y-1 == this.y) && (p.x-3 < this.x) && (p.x > this.x)){
                        p.hp -=1;
                    }else{
                        this.state = state.follow;
                    }
                    break;
            }
        }
    }

    public boolean recieveDamage(boolean attacked, int damage){
        if(attacked = true){
            if(state!=state.die){
                this.setState(state.damage);
                this.spriteSelection();
                this.hp -= damage;
                if(this.hp < 0){
                    this.setState(state.die);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean print(int ix, int fx, int iy, int fy){
        if(this.x > ix && this.x < fx && this.y > iy && this.y < fy){
            return true;
        }else{
            return false;
        }
    }

    public BufferedImage getImage(){
        int ix, iy, width, height;
        width = image.getWidth() / sprites;
        height = image.getHeight();
        ix = width*step;
        iy = 0;

        switch (state){
            case appear:
                if(appearTime.isTime()){
                    step +=1;
                    if(step == 4){
                        this.setState(state.follow);
                    }
                }
                break;
            case follow:
            case attack:
                if(stepTime.isTime()){
                    step = (step+1)%4;
                }
                break;
            case damage:
                if(recoverTime.isTime()){
                    this.setState(state.follow);
                }
                break;
            case die:
                if(stepTime.isTime()){
                    if(step < 5){
                        step +=1;
                    }
                }
        }
        return image.getSubimage(ix,iy,width,height);
    }
}
