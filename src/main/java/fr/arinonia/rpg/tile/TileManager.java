package fr.arinonia.rpg.tile;

import fr.arinonia.rpg.Game;
import fr.arinonia.rpg.ui.GamePanel;
import fr.arinonia.rpg.utils.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TileManager {
    private final GamePanel gamePanel;
    private final Tile[] tile;
    private int[][] mapTileNum;

    private final ArrayList<String> fileNames = new ArrayList<>();
    private final ArrayList<String> collisionStatus = new ArrayList<>();

    public TileManager(final GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        InputStream is = Game.class.getResourceAsStream("/maps/tiledata.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;

        try {
            while ((line = br.readLine()) != null) {
                this.fileNames.add(line);
                this.collisionStatus.add(br.readLine());
            }
            br.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        this.tile = new Tile[this.fileNames.size()];
        loadTileImage();

        is = Game.class.getResourceAsStream("/maps/maptest.txt");
        br = new BufferedReader(new InputStreamReader(is));

        try {
            String l = br.readLine();
            String[] maxTile = l.split(" ");
            this.gamePanel.setMaxWorldCol(maxTile.length);
            this.gamePanel.setMaxWorldRow(maxTile.length);

            this.mapTileNum = new int[gamePanel.getMaxWorldCol()][gamePanel.getMaxWorldRow()];
            br.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        loadMap("/maps/maptest.txt");
    }

    public void loadTileImage() {

        for (int i = 0; i < this.fileNames.size(); i++) {
            String fileName;
            boolean collision;

            fileName = this.fileNames.get(i);
            collision = Boolean.parseBoolean(collisionStatus.get(i));

            setup(i, fileName, collision);
        }
    }

    public void setup(final int index, final String imageName, final boolean collision) {
        final UtilityTool utilityTool = new UtilityTool();

        try {
            this.tile[index] = new Tile();
            this.tile[index].setImage(ImageIO.read(getClass().getResourceAsStream("/images/tiles/" + imageName)));
            this.tile[index].setImage(utilityTool.scaleImage(this.tile[index].getImage(), this.gamePanel.getTileSize(), this.gamePanel.getTileSize()));
            this.tile[index].setCollision(collision);
        } catch (final IOException e) {
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
            final int worldX = worldCol * this.gamePanel.getTileSize();
            final int worldY = worldRow * this.gamePanel.getTileSize();
            final int screenX = worldX - this.gamePanel.getPlayer().getWorldX() + this.gamePanel.getPlayer().screenX;
            final int screenY = worldY - this.gamePanel.getPlayer().getWorldY() + this.gamePanel.getPlayer().screenY;

            if (worldX + 48 > this.gamePanel.getPlayer().getWorldX() - this.gamePanel.getPlayer().screenX &&
                    worldX - this.gamePanel.getTileSize() < this.gamePanel.getPlayer().getWorldX() + this.gamePanel.getPlayer().screenX &&
                    worldY + this.gamePanel.getTileSize() > this.gamePanel.getPlayer().getWorldY() - this.gamePanel.getPlayer().screenY &&
                    worldY - this.gamePanel.getTileSize() < this.gamePanel.getPlayer().getWorldY() + this.gamePanel.getPlayer().screenY) {
                g2.drawImage((this.tile[tileNum]).getImage(), screenX, screenY, null);
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