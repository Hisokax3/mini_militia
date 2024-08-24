package dev.hisoka.maps;

import dev.hisoka.Game;
import dev.hisoka.maps.util.MapConstants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

public class Map{
    private final int size;
    private final int[][] grid;

    private Game game;
    private BufferedImage[] image;
    public Map(Game game, String path, int size) throws IOException {
        this.game = game;
        this.size = size;
        grid = new int[size][size];
        image = new BufferedImage[2];
        for(int i=0; i<image.length; i++){
            image[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(MapConstants.mp.get(i))));
        }

        BufferedReader br = new BufferedReader(new FileReader(Objects.requireNonNull(getClass().getResource(path)).getPath()));

        for (int i = 0; i < size; i++) {
            String[] st = br.readLine().trim().split(" ");
            for (int j = 0;j < size; j++) {
                grid[i][j] = Integer.parseInt(st[j]);
            }
        }
        printMap();

    }

    public void render(Graphics2D graphics) throws IOException {
        int screenx, screeny;
        for (int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                int me = grid[i][j];
                int worldx = j * Game.TILE_SIZE;
                int worldy = i * Game.TILE_SIZE;

                screenx = worldx - game.player.worldX + game.player.screenX;
                screeny = worldy - game.player.worldY + game.player.screenY;
                if(screenx >= -Game.TILE_SIZE && screenx <= Game.WIDTH + Game.TILE_SIZE && screeny >=-Game.TILE_SIZE && screeny<= Game.HEIGHT + Game.TILE_SIZE) {
                    graphics.drawImage(image[me], screenx , screeny, Game.TILE_SIZE, Game.TILE_SIZE, null);
                }
            }
        }
    }

    public void printMap(){
        for (int i = 0; i<size; i++){
            for(int j=0; j<size; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
