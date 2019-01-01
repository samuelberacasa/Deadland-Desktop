package Board;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Board {
    String name;
    int height;
    public String mapRaw[][];
    public int boardGame[][];
    public ImageIcon boardTiles[][];

    public Board(String mapName) throws FileNotFoundException, IOException{
        BufferedReader file = new BufferedReader(new FileReader("Resources//Maps//"+mapName+".txt"));
        String line = file.readLine();
        int width = line.length();
        int height = 1;
        while ((line = file.readLine()) != null){
            height++;
        }
        file.close();
        mapRaw = new String[width][height];
        boardGame = new int[width][height];
        boardTiles = new ImageIcon[width][height];
        file = new BufferedReader(new FileReader("Resources//Maps//"+mapName+".txt"));
        int y = 0;
        while ((line = file.readLine()) != null){
            String split[] = line.split("");
            for(int x=0; x < width; x++){
                String tile = split[x];
                mapRaw[x][y]=tile;

            }
            y++;
        }
        fillBoardTiles();
        fillBoardCharacters();
    }

    public void fillBoardTiles(){
        ImageIcon icon;
        for (int x=0; x < mapRaw.length; x++){
            for(int y=0; y < mapRaw[x].length; y++){
                if(mapRaw[x][y]=="x"){
                    icon = new ImageIcon("Resources//Tiles//1.png");
                    boardTiles[x][y]=icon;
                }else{
                    icon = new ImageIcon("Resources//Tiles//" + mapRaw[x][y]+".png");
                    boardTiles[x][y]=icon;
                }
            }
        }
    }

    public void fillBoardCharacters(){
        for (int width=0; width < mapRaw.length; width++){
            for(int height=0; height < mapRaw[width].length; height++){
                if(mapRaw[width][height]=="x"){
                    boardGame[width][height]=1;
                }else if(mapRaw[width][height]=="0"){
                    boardGame[width][height]=9;
                }
                boardGame[width][height]=0;
            }
        }
    }

    public int getHeroX(){
        for (int width=0; width < mapRaw.length; width++){
            for(int height=0; height < mapRaw[width].length; height++) {
                if(boardGame[width][height]==1){
                    return width;
                }
            }
        }
        return 0;
    }

    public int getHeroY(){
        for (int width=0; width < mapRaw.length; width++){
            for(int height=0; height < mapRaw[width].length; height++) {
                if(boardGame[width][height]==1){
                    return height;
                }
            }
        }
        return 0;
    }
}
