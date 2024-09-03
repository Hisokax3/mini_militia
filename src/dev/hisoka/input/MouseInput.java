package dev.hisoka.input;

import dev.hisoka.Game;
import dev.hisoka.characters.Bullet;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    Game game;
    public MouseInput(Game game){
        this.game = game;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println("clicking");
        Bullet bullet = new Bullet(game.player.screenX, game.player.screenY,game,x,y);
        game.entities.add(bullet);
    }

}
