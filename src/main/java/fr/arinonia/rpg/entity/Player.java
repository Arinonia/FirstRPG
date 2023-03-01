package fr.arinonia.rpg.entity;

import fr.arinonia.rpg.Game;
import fr.arinonia.rpg.handler.KeyHandler;
import fr.arinonia.rpg.ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    private final GamePanel gamePanel;
    private final KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public Player(final GamePanel gamePanel, final KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.screenX = gamePanel.getScreenWidth() / 2 - (gamePanel.getTileSize() / 2);
        this.screenY = gamePanel.getScreenHeight() / 2 - (gamePanel.getTileSize() / 2);
        this.solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        setDefaultValues();
        loadPlayerImages();
    }

    public void setDefaultValues() {
        this.worldX = this.gamePanel.getTileSize() * 23;
        this.worldY = this.gamePanel.getTileSize() * 21;
        this.speed = 4;
        this.direction = "down";
    }

    public void loadPlayerImages() {
        try {
            this.up1 = ImageIO.read(Game.class.getResourceAsStream("/images/player/char_up_1.png"));
            this.up2 = ImageIO.read(Game.class.getResourceAsStream("/images/player/char_up_2.png"));
            this.down1 = ImageIO.read(Game.class.getResourceAsStream("/images/player/char_down_1.png"));
            this.down2 = ImageIO.read(Game.class.getResourceAsStream("/images/player/char_down_2.png"));
            this.left1 = ImageIO.read(Game.class.getResourceAsStream("/images/player/char_left_1.png"));
            this.left2 = ImageIO.read(Game.class.getResourceAsStream("/images/player/char_left_2.png"));
            this.right1 = ImageIO.read(Game.class.getResourceAsStream("/images/player/char_right_1.png"));
            this.right2 = ImageIO.read(Game.class.getResourceAsStream("/images/player/char_right_2.png"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {

        if (this.keyHandler.isUpPressed() || this.keyHandler.isLeftPressed() || this.keyHandler.isRightPressed()
                || this.keyHandler.isDownPressed()) {
            if (this.keyHandler.isUpPressed()) {
                this.direction = "up";
            } else if (this.keyHandler.isDownPressed()) {
                this.direction = "down";
            } else if (this.keyHandler.isRightPressed()) {
                this.direction = "right";
            } else if (this.keyHandler.isLeftPressed()) {
                this.direction = "left";
            }

            collisionOn = false;
            gamePanel.getCollisionChecker().checkTile(this);

            if (!collisionOn) {
                switch (direction) {
                    case "up": this.worldY -= this.speed; break;
                    case "down": this.worldY += this.speed; break;
                    case "left": this.worldX -= this.speed; break;
                    case "right": this.worldX += this.speed; break;
                }
            }
            this.spriteCounter++;

            if (this.spriteCounter > 16) {
                if (this.spriteNumber == 1) {
                    this.spriteNumber = 2;
                } else if (this.spriteNumber == 2){
                    this.spriteNumber = 1;
                }
                this.spriteCounter = 0;
            }
        }
    }

    public void draw(final Graphics2D g2) {
       BufferedImage image = null;

       switch (this.direction) {
           case "up":
               if (this.spriteNumber == 1) {
                   image = this.up1;
               }
                if (this.spriteNumber == 2) {
                    image = this.up2;
                }
               break;
           case "down":
               if (this.spriteNumber == 1) {
                   image = this.down1;
               }
               if (this.spriteNumber == 2) {
                   image = this.down2;
               }
               break;
           case "left":
               if (this.spriteNumber == 1) {
                   image = this.left1;
               }
               if (this.spriteNumber == 2) {
                   image = this.left2;
               }
               break;
           case "right":
               if (this.spriteNumber == 1) {
                   image = this.right1;
               }
               if (this.spriteNumber == 2) {
                   image = this.right2;
               }
               break;
       }
       g2.drawImage(image, screenX, screenY, this.gamePanel.getTileSize(), this.gamePanel.getTileSize(), null);
    }

    public int getScreenX() {
        return this.screenX;
    }

    public int getScreenY() {
        return this.screenY;
    }

}
