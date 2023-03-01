package fr.arinonia.rpg.tile;

import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage image;
    private boolean collision = false;

    public BufferedImage getImage() {
        return this.image;
    }

    public boolean isCollision() {
        return this.collision;
    }

    public void setImage(final BufferedImage image) {
        this.image = image;
    }

    public void setCollision(final boolean collision) {
        this.collision = collision;
    }

}
