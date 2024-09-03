package dev.hisoka;

import dev.hisoka.characters.Entity;
import dev.hisoka.characters.Player;
import dev.hisoka.input.KeyInput;
import dev.hisoka.maps.Map;
import dev.hisoka.tile.TileManager;
import dev.hisoka.util.CollisionChecker;
import dev.hisoka.util.OutOfMapChecker;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.Serial;

public class Game extends JPanel implements Runnable{
    @Serial
    private static final long serialVersionUID = 1L;

    public static final int ACTUAL_TILE_SIZE = 16;
    public static final int SCALE = 3;

    public static final int TILE_SIZE = ACTUAL_TILE_SIZE * SCALE;
    public static final int MAX_SCREEN_COL = 16;
    public static final int MAX_SCREEN_ROW = 12;

    public static final int MAX_WORLD_COL = 20;

    public static final int MAX_WORLD_ROW = 10;

    //480 * 960
    public static final int WIDTH = MAX_SCREEN_COL * TILE_SIZE ;
    public static final int HEIGHT = MAX_SCREEN_ROW * TILE_SIZE;

    public static String title = "Hisoka";
    public boolean running = false;
    private Thread thread;
    private final KeyInput keyInput = new KeyInput();

    public Player player = new Player(this, keyInput);

    public TileManager tileManager = new TileManager(this);

    public CollisionChecker collisionChecker = new CollisionChecker(this);

    //private Map map = new Map(this, "/map/map1.txt", 26);

    public OutOfMapChecker outOfMapChecker = new OutOfMapChecker(this);


    public Game() throws IOException {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyInput);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        requestFocus();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            repaint();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }

        stop();
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        try {
            tileManager.render(graphics2D);
        } catch (Exception e) {
            e.printStackTrace();
        }
        player.render(graphics2D);

    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle(title);
        Game game = new Game();
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        game.start();
    }

}