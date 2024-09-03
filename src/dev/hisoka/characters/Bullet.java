package dev.hisoka.characters;

import dev.hisoka.Game;
import dev.hisoka.input.MouseInput;

import java.awt.*;

public class Bullet extends Entity{
    private Game game;
    int velx;
    int vely;
    public Bullet(int x, int y, Game game, int destX, int destY){
        worldX = x;
        worldY = y;

        velx = (destX - x) /20;
        vely = (destY - y) /20;
    }

    public void update(){
        worldX += velx;
        worldY += vely;
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillOval(worldX, worldY, 8, 8);
    }
}
