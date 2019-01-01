package Extra;

import Time.Stopwatch;

public class Weapon {
    public String name;
    public int attack;
    public int availableBullets;
    public int magazineBullets;
    public int reach;
    private int magazineSpace;
    private Stopwatch reloadTime;

    public Weapon(String name, int attack, int availableBullets, int magazineBullets, int Reach, int magazineSpace, int time){
        this.name = name;
        this.attack =  attack;
        this.availableBullets = availableBullets;
        this.magazineBullets = magazineBullets;
        this.reach = reach;
        this.magazineSpace = magazineSpace;
        this.reloadTime = new Stopwatch(time);
    }

    public void reload(){
        if(this.reloadTime.isTime()){
            int newBullets = this.magazineSpace - this.magazineBullets;
            if(this.availableBullets >= newBullets){
                this.availableBullets -= newBullets;
                this.magazineSpace += newBullets;
            }else{
                this.magazineBullets += this.availableBullets;
                this.availableBullets = 0;
            }
        }
    }
}
