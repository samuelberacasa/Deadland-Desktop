package Game;

import Board.Board;
import Characters.Character;
import Characters.Hero;
import Extra.Weapon;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import java.io.*;

public class GameController extends Canvas{
    enum State{active,pause,stop};
    public State gameState;
    Board board;
    Hero hero;

    public GameController(){
        gameState = State.stop;
        try{
            board = new Board("TestMap");
            hero = new Hero(board.getHeroX(),board.getHeroY(), GameVariables.FrameWidth, GameVariables.FrameHeight,new Weapon("pistol",1,1,1,1,1,1));
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public void KeyDown(int key) {
        switch (gameState){
            case active:
                switch (key) {
                    case KeyEvent.VK_ESCAPE:
                        System.exit(0);
                        break;
                    case KeyEvent.VK_DOWN:
                        hero.direc = Character.Direction.down;
                        hero.setState(Hero.State.move);
                        break;
                    case KeyEvent.VK_LEFT:
                        hero.direc = Character.Direction.left;
                        hero.setState(Hero.State.move);
                        break;
                    case KeyEvent.VK_RIGHT:
                        hero.direc = Character.Direction.right;
                        hero.setState(Hero.State.move);
                        break;
                    case KeyEvent.VK_UP:
                        hero.direc = Character.Direction.up;
                        hero.setState(Hero.State.move);
                        break;
                    case KeyEvent.VK_D:
                        hero.setState(Hero.State.knife);
                        break;
                    case KeyEvent.VK_S:
                        hero.setState(Hero.State.shoot);
                        break;
                }
                break;
            case pause:
                switch (key) {
                }
                break;
        }
    }

    public void KeyUp(int key) {
        switch (gameState){
            case active:
                switch (key) {
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_S:
                        hero.setState(Hero.State.stand);
                        break;
                }
                break;
            case pause:
                switch (key) {
                }
                break;
        }
    }
}
