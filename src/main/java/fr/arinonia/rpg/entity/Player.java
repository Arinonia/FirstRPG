package fr.arinonia.rpg.entity;

import fr.arinonia.rpg.Game;
import fr.arinonia.rpg.handler.KeyHandler;
import fr.arinonia.rpg.ui.GamePanel;
import fr.arinonia.rpg.utils.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    private final GamePanel gamePanel;
    private final KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    private int key = 0;


    public Player(final GamePanel gamePanel, final KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.screenX = gamePanel.getScreenWidth() / 2 - (gamePanel.getTileSize() / 2);
        this.screenY = gamePanel.getScreenHeight() / 2 - (gamePanel.getTileSize() / 2);
        this.solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        this.solidAreadDefaultX = solidArea.x;
        this.solidAreadDefaultY = solidArea.y;
        solidArea.width = 20;
        solidArea.height = 20;
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
        this.up1 = setup("char_up_1");
        this.up2 = setup("char_up_2");
        this.down1 = setup("char_down_1");
        this.down2 = setup("char_down_2");
        this.left1 = setup("char_left_1");
        this.left2 = setup("char_left_2");
        this.right1 = setup("char_right_1");
        this.right2 = setup("char_right_2");
    }

    public BufferedImage setup(final String imageName) {
        final UtilityTool utilityTool = new UtilityTool();
        BufferedImage scaledImage = null;

        try {
            scaledImage = ImageIO.read(Game.class.getResourceAsStream("/images/player/" + imageName + ".png"));
            scaledImage = utilityTool.scaleImage(scaledImage, this.gamePanel.getTileSize(), this.gamePanel.getTileSize());
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return scaledImage;
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
            int objIndex = gamePanel.getCollisionChecker().checkObject(this, true);
            pickObject(objIndex);


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

    public void pickObject(int index) {
        if (index != -1) {
            final String name = this.gamePanel.getObj()[index].getName();

            switch (name) {
                case "Key":
                    this.gamePanel.playSE(1);
                    this.gamePanel.getObj()[index] = null;
                    this.key++;
                    this.gamePanel.getUi().showMessage("You got a key");
                    break;
                case "Door":
                    if (this.key > 0 || this.keyHandler.isCheckDrawTime()) {
                        this.gamePanel.playSE(3);
                        this.key--;
                        this.gamePanel.getObj()[index] = null;
                        this.gamePanel.getUi().showMessage("You just open a new door!");
                    } else {
                        this.gamePanel.getUi().showMessage("You need to find a key!");
                    }
                    break;
                case "Boots":
                    this.gamePanel.playSE(2);
                    speed += 2;
                    this.gamePanel.getObj()[index] = null;
                    this.gamePanel.getUi().showMessage("You find boots! that's increase your speed");
                    break;
                case "Chest":
                    this.gamePanel.getUi().setGameFinished(true);
                    this.gamePanel.stopMusic();
                    this.gamePanel.playSE(4);
                    break;
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
       g2.drawImage(image, screenX, screenY, null);
    }

    public int getScreenX() {
        return this.screenX;
    }

    public int getScreenY() {
        return this.screenY;
    }

    public int getKey() {
        return this.key;
    }
}
