package Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable, KeyListener{
    BufferStrategy strategy;
    Graphics batch;
    GameController game;
    int xTiles, yTiles;

    public Game(){
        game = new GameController();
        xTiles = (int) Math.ceil(GameVariables.FrameWidth/GameVariables.TileSize);
        yTiles = (int) Math.ceil(GameVariables.FrameHeight/GameVariables.TileSize);
    }

    public void render(){
        strategy = getBufferStrategy();
        if(strategy == null){
            createBufferStrategy(3);
            return;
        }
        batch = strategy.getDrawGraphics();
        ///////////////////////////////////////////////////////
        //batch.setColor(Color.BLACK);
        //batch.fillRect(0,0,GameVariables.FrameWidth, GameVariables.FrameHeight);
        for(int x = 0; x < game.board.boardGame.length; x++){
            for(int y = 0; y < game.board.boardGame[y].length; y++){
                batch.drawImage(game.board.boardTiles[x][y].getImage(),
                        x*GameVariables.TileSize,
                        y*GameVariables.TileSize,this);
            }
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
