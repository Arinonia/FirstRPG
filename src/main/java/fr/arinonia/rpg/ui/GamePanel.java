package fr.arinonia.rpg.ui;

import fr.arinonia.rpg.handler.KeyHandler;

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

    private final int fps = 60;
    private final KeyHandler keyHandler = new KeyHandler();
    private Thread gameThread;

    //set player's default position
    private int playerX = 100;
    private int playerY = 100;
    private int playerSpeed = 4;

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
        if (this.keyHandler.isUpPressed()) {
            this.playerY -= this.playerSpeed;
        } else if (this.keyHandler.isDownPressed()) {
            this.playerY += this.playerSpeed;
        } else if (this.keyHandler.isRightPressed()) {
            this.playerX += this.playerSpeed;
        } else if (this.keyHandler.isLeftPressed()) {
            this.playerX -= this.playerSpeed;
        }
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLUE);
        g2.fillRect(this.playerX, this.playerY, tileSize, tileSize);
        g2.dispose();
    }
}
