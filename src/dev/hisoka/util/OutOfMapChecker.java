package dev.hisoka.util;

import dev.hisoka.Game;
import dev.hisoka.characters.Entity;

public class OutOfMapChecker {
    private Game game;

    public OutOfMapChecker(Game game){
        this.game = game;
    }

    public boolean CheckOutOfBounds(Entity entity){

        System.out.println("inside bound check" + entity.direction);
        switch(entity.direction) {
            case "up":
                if(entity.worldY <= 0 ) return true;
                else return false;
            case "down":
                if (entity.worldY >= (game.MAX_WORLD_ROW * game.TILE_SIZE - (game.TILE_SIZE + game.TILE_SIZE/8))) return true;
                else return false;
            case "left":
                if(entity.worldX <= 0) return true;
                else return false;
            case "right":
                if(entity.worldX >= (game.MAX_WORLD_COL * game.TILE_SIZE - (game.TILE_SIZE + game.TILE_SIZE/8))) return true;
                else return false;
        }

        return false;
    }
}
