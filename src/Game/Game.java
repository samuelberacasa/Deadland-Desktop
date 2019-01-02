package Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable, KeyListener{
    BufferStrategy strategy;
    Graphics batch;
    GameController game;
    int XI, YI;

    public Game(){
        game = new GameController();
    }

    public void render(){
        strategy = getBufferStrategy();
        if(strategy == null){
            createBufferStrategy(3);
            return;
        }
        batch = strategy.getDrawGraphics();
        ///////////////////////////////////////////////////////
        batch.setColor(Color.BLACK);
        batch.fillRect(0,0,GameVariables.FrameWidth, GameVariables.FrameHeight);
        XI = 0 - (game.hero.x*GameVariables.TileSize+game.hero.image.getWidth()/(4*game.hero.sprites)- GameVariables.FrameWidth/2);
        for(int x = 0; x < game.board.mapRaw.length; x++){
            if(XI>-GameVariables.TileSize/2 && XI < GameVariables.FrameWidth){
                YI = 0 - (game.hero.y*GameVariables.TileSize+game.hero.image.getHeight()/3 - GameVariables.FrameHeight/2);
                for(int y = 0; y < game.board.mapRaw[0].length; y++){
                    /*if(game.board.boardGame[x][y]==1){
                        batch.drawImage(game.board.boardTiles[x][y].getImage(), XI, YI,this);
                    }*/
                    if(YI>-GameVariables.TileSize && YI < GameVariables.FrameHeight){
                        batch.drawImage(game.board.boardTiles[x][y].getImage(), XI, YI,this);
                    }
                    YI+=GameVariables.TileSize;
                }
            }
            XI+=GameVariables.TileSize;
        }
        batch.drawImage(game.hero.getImage(), game.hero.posX, game.hero.posY,this);
        ///////////////////////////////////////////////////////
        batch.dispose();
        strategy.show();
    }

    public void start(){
        game.gameState = game.gameState.active;
        new Thread(this,"Main-Thread").start();
    }

    public void stop(){
        game.gameState = game.gameState.stop;
    }

    @Override
    public void run() {
        /*long timer = System.currentTimeMillis();
        int fps = 0;*/

        while(game.gameState == game.gameState.active){
            try {
                Thread.sleep(30);
            }catch (InterruptedException e){
                System.err.println("Sleep Error");
            }
            render();

            /*fps++;
            if(System.currentTimeMillis()-1000 > timer){
                timer +=1000;
                System.out.printf("FPS:%d\n", fps);
                fps = 0;
            }*/
        }
        System.exit(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        game.KeyDown(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        game.KeyUp(e.getKeyCode());
    }
}
