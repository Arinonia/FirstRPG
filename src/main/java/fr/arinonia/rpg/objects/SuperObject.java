package fr.arinonia.rpg.objects;

import fr.arinonia.rpg.ui.GamePanel;
import fr.arinonia.rpg.utils.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    protected BufferedImage image;
    protected String name;
    protected  boolean collision = false;
    protected int worldX;
    protected int worldY;

    protected final Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    protected int solidAreadDefaultX = 0;
    protected int solidAreadDefaultY = 0;

    protected final UtilityTool utilityTool = new UtilityTool();

    protected SuperObject() {}

    public void draw(final Graphics2D g2, final GamePanel gamePanel) {
        final int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().screenX;
        final int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().screenY;

        if (worldX + 48 > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().screenX &&
                worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().screenX &&
                worldY + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().screenY &&
                worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().screenY) {
            g2.drawImage(this.image, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public Rectangle getSolidArea() {
        return this.solidArea;
    }

    public int getSolidAreadDefaultX() {
        return this.solidAreadDefaultX;
    }

    public void setSolidAreadDefaultX(final int solidAreadDefaultX) {
        this.solidAreadDefaultX = solidAreadDefaultX;
    }

    public int getSolidAreadDefaultY() {
        return this.solidAreadDefaultY;
    }

    public void setSolidAreadDefaultY(final int solidAreadDefaultY) {
        this.solidAreadDefaultY = solidAreadDefaultY;
    }
}
