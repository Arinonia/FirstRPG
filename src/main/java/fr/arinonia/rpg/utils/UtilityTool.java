package fr.arinonia.rpg.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {

    public BufferedImage scaleImage(final BufferedImage original, final int width, final int height) {
        final BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        final Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }
}
