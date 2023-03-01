package fr.arinonia.rpg.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    protected int worldX;
    protected int worldY;
    protected int speed;

    protected BufferedImage up1;
    protected BufferedImage up2;
    protected BufferedImage down1;
    protected BufferedImage down2;
    protected BufferedImage left1;
    protected BufferedImage left2;
    protected BufferedImage right1;
    protected BufferedImage right2;

    protected String direction;

    protected int spriteCounter = 0;
    protected int spriteNumber = 1;

    protected Rectangle solidArea;
    protected boolean collisionOn = false;


    public int getWorldX() {
        return this.worldX;
    }

    public int getWorldY() {
        return this.worldY;
    }

    public Rectangle getSolidArea() {
        return this.solidArea;
    }

    public void setCollisionOn(final boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public int getSpeed() {
        return this.speed;
    }

    public BufferedImage getUp1() {
        return this.up1;
    }

    public BufferedImage getUp2() {
        return this.up2;
    }

    public BufferedImage getDown1() {
        return this.down1;
    }

    public BufferedImage getDown2() {
        return this.down2;
    }

    public BufferedImage getLeft1() {
        return this.left1;
    }

    public BufferedImage getLeft2() {
        return this.left2;
    }

    public BufferedImage getRight1() {
        return this.right1;
    }

    public BufferedImage getRight2() {
        return this.right2;
    }

    public String getDirection() {
        return this.direction;
    }

    public int getSpriteCounter() {
        return this.spriteCounter;
    }

    public int getSpriteNumber() {
        return this.spriteNumber;
    }

    public boolean isCollisionOn() {
        return this.collisionOn;
    }
}
