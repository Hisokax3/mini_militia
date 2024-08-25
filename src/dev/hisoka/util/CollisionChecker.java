package dev.hisoka.util;

import dev.hisoka.Game;
import dev.hisoka.characters.Entity;

public class CollisionChecker {
    private Game game;
    public CollisionChecker(Game game){
        this.game = game;
    }

    public void checkTileCollion(Entity entity){

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/game.TILE_SIZE;
        int entityRightCol = entityRightWorldX/game.TILE_SIZE;
        int entityTopRow = entityTopWorldY/game.TILE_SIZE;
        int entityBottomRow = entityBottomWorldY/game.TILE_SIZE;

        int tileNum1, tileNum2;

        switch(entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/game.TILE_SIZE;
                tileNum1 = game.tileManager.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = game.tileManager.mapTileNum[entityTopRow][entityRightCol];
                if(game.tileManager.tiles[tileNum1].collision || game.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/game.TILE_SIZE;
                tileNum1 = game.tileManager.mapTileNum[entityBottomRow][entityLeftCol];
                tileNum2 = game.tileManager.mapTileNum[entityBottomRow][entityRightCol];
                if(game.tileManager.tiles[tileNum1].collision || game.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/game.TILE_SIZE;
                tileNum1 = game.tileManager.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = game.tileManager.mapTileNum[entityBottomRow][entityLeftCol];
                if(game.tileManager.tiles[tileNum1].collision || game.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/game.TILE_SIZE;
                tileNum1 = game.tileManager.mapTileNum[entityTopRow][entityRightCol];
                tileNum2 = game.tileManager.mapTileNum[entityBottomRow][entityRightCol];
                if(game.tileManager.tiles[tileNum1].collision || game.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
