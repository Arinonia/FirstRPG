package fr.arinonia.rpg.ui;

import fr.arinonia.rpg.entity.Player;
import fr.arinonia.rpg.handler.KeyHandler;
import fr.arinonia.rpg.objects.SuperObject;
import fr.arinonia.rpg.sound.Sound;
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

    public int maxWorldCol = 50;
    public int maxWorldRow = 50;

    private final int fps = 60;
    private final TileManager tileManager = new TileManager(this);
    private final KeyHandler keyHandler = new KeyHandler();
    private final Sound music = new Sound();
    private final Sound soundEffect = new Sound();


    private final CollisionChecker collisionChecker = new CollisionChecker(this);

    private final AssetSetter assetSetter = new AssetSetter(this);
    private final Ui ui = new Ui(this);

    public Thread gameThread;


    private final Player player = new Player(this, keyHandler);

    private SuperObject obj[] = new SuperObject[10];//display only 10 obj at the same time


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        this.assetSetter.setObject();
        playMusic(0);
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

        //debug
        long drawStart = 0;
        if (this.keyHandler.isCheckDrawTime()) {
            drawStart = System.nanoTime();
        }

        this.tileManager.draw(g2);
        for (final SuperObject superObject : this.obj) {
            if (superObject != null) {
                superObject.draw(g2, this);
            }
        }
        this.player.draw(g2);
        ui.draw(g2);

        if (this.keyHandler.isCheckDrawTime()) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setColor(Color.WHITE);
            g2.drawString("Draw time: " + ((double)passed / 1000000000) + "s", 10, 400);
            System.out.println("Draw time: " + passed);
            g2.drawString("Dev mod enable", 10, 480);
        }
        g2.dispose();
    }

    public void playMusic(final int index) {
        this.music.setFile(index);
        this.music.play();
        this.music.loop();
    }

    public void stopMusic() {
        this.music.stop();
    }

    public void playSE(final int index) {
        this.soundEffect.setFile(index);
        this.soundEffect.play();
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

    public void setMaxWorldCol(int maxWorldCol) {
        this.maxWorldCol = maxWorldCol;
    }

    public void setMaxWorldRow(int maxWorldRow) {
        this.maxWorldRow = maxWorldRow;
    }

    public Player getPlayer() {
        return this.player;
    }

    public TileManager getTileManager() {
        return this.tileManager;
    }

    public AssetSetter getAssetSetter() {
        return this.assetSetter;
    }

    public Sound getMusic() {
        return this.music;
    }

    public CollisionChecker getCollisionChecker() {
        return this.collisionChecker;
    }

    public SuperObject[] getObj() {
        return this.obj;
    }

    public Ui getUi() {
        return this.ui;
    }

    public KeyHandler getKeyHandler() {
        return this.keyHandler;
    }
}
