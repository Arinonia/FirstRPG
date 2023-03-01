package fr.arinonia.rpg.ui;

import fr.arinonia.rpg.entity.Player;
import fr.arinonia.rpg.handler.KeyHandler;
import fr.arinonia.rpg.tile.CollisionChecker;
import fr.arinonia.rpg.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    private final int originalTileSize = 16; //16x16 tiles
    private final int scale = 3;
    private final int tileSize = originalTileSize * scale; //48x48 tiles
    private final int maxScreenCol = 16;
    private final int maxScreenRow = 12;
    private final int screenWidth = tileSize * maxScreenCol; //768px
    private final int screenHeight = tileSize * maxScreenRow; //576px

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxScreenCol;
    public final int worldHeight = tileSize * maxScreenRow;
    private final int fps = 60;
    private final TileManager tileManager = new TileManager(this);
    private final KeyHandler keyHandler = new KeyHandler();
    private Thread gameThread;

    private final CollisionChecker collisionChecker = new CollisionChecker(this);
    private final Player player = new Player(this, keyHandler);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.setName("Game-Thread");
        this.gameThread.start();
    }
    @Override
    public void run() {

        final double drawInterval = 1000000000 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while (this.gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                //1 UPDATE: update information such as character position
                update();
                //2 DRAW: draw the screen with the updated information
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D)g;
        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose();
    }

    public int getTileSize() {
        return this.tileSize;
    }

    public int getMaxScreenCol() {
        return this.maxScreenCol;
    }

    public int getMaxScreenRow() {
        return this.maxScreenRow;
    }

    public int getScreenWidth() {
        return this.screenWidth;
    }

    public int getScreenHeight() {
        return this.screenHeight;
    }

    public int getMaxWorldCol() {
        return this.maxWorldCol;
    }

    public int getMaxWorldRow() {
        return this.maxWorldRow;
    }

    public int getWorldWidth() {
        return this.worldWidth;
    }

    public int getWorldHeight() {
        return this.worldHeight;
    }

    public Player getPlayer() {
        return this.player;
    }

    public TileManager getTileManager() {
        return this.tileManager;
    }

    public CollisionChecker getCollisionChecker() {
        return this.collisionChecker;
    }
}
