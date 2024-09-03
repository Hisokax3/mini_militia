package dev.hisoka.characters;

import dev.hisoka.Game;
import dev.hisoka.input.KeyInput;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    private Game game;
    private KeyInput keyInput;

    private BufferedImage image;

    public final int screenX;
    public final int screenY;

    public Player(Game game, KeyInput keyInput) throws IOException {
        this.game = game;
        this.keyInput = keyInput;
        screenX = Game.WIDTH/2 - Game.TILE_SIZE/2;
        screenY = Game.HEIGHT/2 - Game.TILE_SIZE/2;
        solidArea = new Rectangle(0, 0, 48, 48);
        speed = 5;
        image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player1.png")));
        setDefaultParameters();
    }

    void setDefaultParameters(){
        worldX = screenX;
        worldY = screenY;

    }

    @Override
    public void render(Graphics2D graphics){
        graphics.drawImage(image, screenX, screenY, image.getWidth() * Game.SCALE, image.getHeight() * Game.SCALE, null);
        graphics.dispose();
    }

    @Override
    public void update(){

        if(keyInput.keyStatus[KeyEvent.VK_UP] || keyInput.keyStatus[KeyEvent.VK_W]){
            direction = "up";
            collisionOn = false;
            System.out.println("player up");
            boolean outOfMap = game.outOfMapChecker.CheckOutOfBounds(this);
            if(!outOfMap){
                game.collisionChecker.checkTileCollion(this);
                if(!collisionOn) worldY -= speed;
            }
        }
        if(keyInput.keyStatus[KeyEvent.VK_DOWN] || keyInput.keyStatus[KeyEvent.VK_S]){
            direction = "down";
            collisionOn = false;
            System.out.println("player down");
            boolean outOfMap = game.outOfMapChecker.CheckOutOfBounds(this);
            System.out.println(outOfMap);
            if(!outOfMap){
                game.collisionChecker.checkTileCollion(this);
                if(!collisionOn) worldY += speed;
            }
        }
        if(keyInput.keyStatus[KeyEvent.VK_LEFT] || keyInput.keyStatus[KeyEvent.VK_A]){
            direction = "left";
            collisionOn = false;
            System.out.println("player left");
            boolean outOfMap = game.outOfMapChecker.CheckOutOfBounds(this);
            if(!outOfMap){
                game.collisionChecker.checkTileCollion(this);
                if(!collisionOn) worldX -= speed;
            }
        }
        if(keyInput.keyStatus[KeyEvent.VK_RIGHT] || keyInput.keyStatus[KeyEvent.VK_D]){
            direction = "right";
            collisionOn = false;
            System.out.println("player right");
            boolean outMap = game.outOfMapChecker.CheckOutOfBounds(this);
            if(!outMap){
                game.collisionChecker.checkTileCollion(this);
                if(!collisionOn) worldX += speed;
            }
        }
    }
}
