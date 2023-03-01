package fr.arinonia.rpg.tile;

import fr.arinonia.rpg.entity.Entity;
import fr.arinonia.rpg.ui.GamePanel;

public class CollisionChecker {
    private final GamePanel gamePanel;

    public CollisionChecker(final GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(final Entity entity) {
        int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x;
        int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y;
        int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftCol = entityLeftWorldX / this.gamePanel.getTileSize();
        int entityRightCol = entityRightWorldX / this.gamePanel.getTileSize();
        int entityTopRow = entityTopWorldY / this.gamePanel.getTileSize();
        int entityBottomRow = entityBottomWorldY / this.gamePanel.getTileSize();

        int tileNum1;
        int tileNum2;

        switch (entity.getDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / this.gamePanel.getTileSize();
                tileNum1 = this.gamePanel.getTileManager().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = this.gamePanel.getTileManager().getMapTileNum()[entityRightCol][entityTopRow];
                if ((this.gamePanel.getTileManager().getTile()[tileNum1]).isCollision() ||
                        (this.gamePanel.getTileManager().getTile()[tileNum2]).isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / this.gamePanel.getTileSize();
                tileNum1 = this.gamePanel.getTileManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                tileNum2 = this.gamePanel.getTileManager().getMapTileNum()[entityRightCol][entityBottomRow];
                if ((this.gamePanel.getTileManager().getTile()[tileNum1]).isCollision() ||
                        (this.gamePanel.getTileManager().getTile()[tileNum2]).isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / 48;
                tileNum1 = this.gamePanel.getTileManager().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = this.gamePanel.getTileManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                if ((this.gamePanel.getTileManager().getTile()[tileNum1]).isCollision() ||
                        (this.gamePanel.getTileManager().getTile()[tileNum2]).isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / 48;
                tileNum1 = this.gamePanel.getTileManager().getMapTileNum()[entityRightCol][entityTopRow];
                tileNum2 = this.gamePanel.getTileManager().getMapTileNum()[entityRightCol][entityBottomRow];
                if ((this.gamePanel.getTileManager().getTile()[tileNum1]).isCollision() ||
                        (this.gamePanel.getTileManager().getTile()[tileNum2]).isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
        }
    }
}
