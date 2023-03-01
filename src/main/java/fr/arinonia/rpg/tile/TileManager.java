package fr.arinonia.rpg.tile;

import fr.arinonia.rpg.ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    private final GamePanel gamePanel;
    private final Tile[] tile;
    private final int[][] mapTileNum;

    public TileManager(final GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.tile = new Tile[10];
        this.mapTileNum = new int[50][50];
        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void getTileImage() {
        try {
            this.tile[0] = new Tile();
            this.tile[0].setImage(ImageIO.read(getClass().getResourceAsStream("/images/tiles/grass.png")));
            this.tile[1] = new Tile();
            this.tile[1].setImage(ImageIO.read(getClass().getResourceAsStream("/images/tiles/wall.png")));
            this.tile[1].setCollision(true);
            this.tile[2] = new Tile();
            this.tile[2].setImage(ImageIO.read(getClass().getResourceAsStream("/images/tiles/water.png")));
            this.tile[2].setCollision(true);
            this.tile[3] = new Tile();
            this.tile[3].setImage(ImageIO.read(getClass().getResourceAsStream("/images/tiles/earth.png")));
            this.tile[4] = new Tile();
            this.tile[4].setImage(ImageIO.read(getClass().getResourceAsStream("/images/tiles/tree.png")));
            this.tile[4].setCollision(true);
            this.tile[5] = new Tile();
            this.tile[5].setImage(ImageIO.read(getClass().getResourceAsStream("/images/tiles/sand.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(final String filePath) {
        try {
            final InputStream is = getClass().getResourceAsStream(filePath);
            final BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < 50 && row < 50) {
                final String line = br.readLine();
                while (col < 50) {
                    final String[] numbers = line.split(" ");
                    final int num = Integer.parseInt(numbers[col]);
                    this.mapTileNum[col][row] = num;
                    col++;
                }
                if (col == 50) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(final Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;
        while (worldCol < 50 && worldRow < 50) {
            final int tileNum = this.mapTileNum[worldCol][worldRow];
            final int worldX = worldCol * 48;
            final int worldY = worldRow * 48;
            int screenX = worldX - this.gamePanel.getPlayer().getWorldX() + this.gamePanel.getPlayer().screenX;
            int screenY = worldY - this.gamePanel.getPlayer().getWorldY() + this.gamePanel.getPlayer().screenY;
            if (worldX + 48 > this.gamePanel.getPlayer().getWorldX() - this.gamePanel.getPlayer().screenX && worldX - 48 < this.gamePanel.getPlayer().getWorldX() + this.gamePanel.getPlayer().screenX && worldY + 48 > this.gamePanel.getPlayer().getWorldY() - this.gamePanel.getPlayer().screenY && worldY - 48 < this.gamePanel.getPlayer().getWorldY() + this.gamePanel.getPlayer().screenY) {
                g2.drawImage((this.tile[tileNum]).getImage(), screenX, screenY, 48, 48, null);
            }
            worldCol++;
            if (worldCol == 50) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    public Tile[] getTile() {
        return this.tile;
    }

    public int[][] getMapTileNum() {
        return this.mapTileNum;
    }
}