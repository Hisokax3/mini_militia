package dev.hisoka.tile;

import dev.hisoka.Game;
import dev.hisoka.util.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    private Game game;
    public Tile[] tiles;

    public final int[][] mapTileNum;

    public TileManager(Game game){
        this.game = game;
        tiles = new Tile[10];
        mapTileNum = new int[Game.MAX_WORLD_ROW][Game.MAX_WORLD_COL];
        getTileImage();
        loadMap();
    }

    private void getTileImage() {
        try{
            for (int i=0; i<tiles.length; i++){
                if(Constants.TILE_INFO.containsKey(i)){
                    tiles[i] = new Tile();
                    tiles[i].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(Constants.TILE_INFO.get(i))));
                    if(Constants.COLLISION.contains(i)){
                        tiles[i].collision = true;
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadMap(){
        try {
            InputStream inputStream = getClass().getResourceAsStream("/map/map1.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            for (int i = 0; i < mapTileNum.length; i++) {
                String[] st = bufferedReader.readLine().trim().split(" ");
                for (int j = 0;j < mapTileNum[0].length; j++) {
                    mapTileNum[i][j] = Integer.parseInt(st[j]);
                }
            }
            bufferedReader.close();
            System.out.println(mapTileNum[9][5]);
        }catch (Exception e){
            e.printStackTrace();
        }

        printMap(mapTileNum);
    }

    public void render(Graphics2D graphics2D){
        int screenx, screeny;
        for (int i=0; i<mapTileNum.length; i++){
            for(int j=0; j<mapTileNum[0].length; j++){
                int curMapTile = mapTileNum[i][j];
                int worldx = j * Game.TILE_SIZE;
                int worldy = i * Game.TILE_SIZE;

                screenx = worldx - game.player.worldX + game.player.screenX;
                screeny = worldy - game.player.worldY + game.player.screenY;
                if(screenx >= -Game.TILE_SIZE && screenx <= Game.WIDTH + Game.TILE_SIZE && screeny >=-Game.TILE_SIZE && screeny<= Game.HEIGHT + Game.TILE_SIZE) {
                    graphics2D.drawImage(tiles[curMapTile].image, screenx , screeny, Game.TILE_SIZE, Game.TILE_SIZE, null);
                }
            }
        }
    }

    public void printMap(int[][] mapTileNum){
        for (int i = 0; i<mapTileNum.length; i++){
            for(int j=0; j<mapTileNum[0].length; j++){
                System.out.print(mapTileNum[i][j] + " ");
            }
            System.out.println("");
        }

        System.out.println(mapTileNum[5][9]);
    }
}
