package fr.arinonia.rpg.tile;

import fr.arinonia.rpg.entity.Entity;
import fr.arinonia.rpg.objects.SuperObject;
import fr.arinonia.rpg.ui.GamePanel;

public class CollisionChecker {
    private final GamePanel gamePanel;

    public CollisionChecker(final GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    private boolean checkCollision(int entitypos1, int entitypos2, int entitypos3, int entitypos4) {
        int tileNum1 = this.gamePanel.getTileManager().getMapTileNum()[entitypos1][entitypos2];
        int tileNum2 = this.gamePanel.getTileManager().getMapTileNum()[entitypos3][entitypos4];
        return (this.gamePanel.getTileManager().getTile()[tileNum1].isCollision() ||
                this.gamePanel.getTileManager().getTile()[tileNum2].isCollision()) && !this.gamePanel.getKeyHandler().isCheckDrawTime();
    }

    public void checkTile(final Entity entity) {
        int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x + 5;
        int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y;
        int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftCol = entityLeftWorldX / this.gamePanel.getTileSize();
        int entityRightCol = entityRightWorldX / this.gamePanel.getTileSize();
        int entityTopRow = entityTopWorldY / this.gamePanel.getTileSize();
        int entityBottomRow = entityBottomWorldY / this.gamePanel.getTileSize();

        switch (entity.getDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / this.gamePanel.getTileSize();
                if (checkCollision(entityLeftCol, entityTopRow, entityRightCol, entityTopRow)) entity.setCollisionOn(true);
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / this.gamePanel.getTileSize();
                if (checkCollision(entityLeftCol, entityBottomRow, entityRightCol, entityBottomRow)) entity.setCollisionOn(true);
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / this.gamePanel.getTileSize();
                if (checkCollision(entityLeftCol, entityTopRow, entityLeftCol, entityBottomRow)) entity.setCollisionOn(true);
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / this.gamePanel.getTileSize();
                if (checkCollision(entityRightCol, entityTopRow, entityRightCol, entityBottomRow)) entity.setCollisionOn(true);
                break;
        }
    }

    public int checkObject(final Entity entity, boolean player) {
        int index = -1;
        for (int i = 0; i < this.gamePanel.getObj().length; i++) {
            if (this.gamePanel.getObj()[i] != null) {
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;

                this.gamePanel.getObj()[i].getSolidArea().x = this.gamePanel.getObj()[i].getWorldX() + this.gamePanel.getObj()[i].getSolidArea().x;
                this.gamePanel.getObj()[i].getSolidArea().y = this.gamePanel.getObj()[i].getWorldY() + this.gamePanel.getObj()[i].getSolidArea().y;

                switch (entity.getDirection()) {
                    case "up":
                        entity.getSolidArea().y -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(this.gamePanel.getObj()[i].getSolidArea())) {
                            if (this.gamePanel.getObj()[i].isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.getSolidArea().y += entity.getSpeed();
                        if (entity.getSolidArea().intersects(this.gamePanel.getObj()[i].getSolidArea())) {
                            if (this.gamePanel.getObj()[i].isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.getSolidArea().x -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(this.gamePanel.getObj()[i].getSolidArea())) {
                            if (this.gamePanel.getObj()[i].isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.getSolidArea().x += entity.getSpeed();
                        if (entity.getSolidArea().intersects(this.gamePanel.getObj()[i].getSolidArea())) {
                            if (this.gamePanel.getObj()[i].isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.getSolidArea().x = entity.getSolidAreadDefaultX();
                entity.getSolidArea().y = entity.getSolidAreadDefaultY();
                this.gamePanel.getObj()[i].getSolidArea().x = this.gamePanel.getObj()[i].getSolidAreadDefaultX();
                this.gamePanel.getObj()[i].getSolidArea().y = this.gamePanel.getObj()[i].getSolidAreadDefaultY();
            }
        }
        return index;
    }
}
