package fr.arinonia.rpg.objects;

import fr.arinonia.rpg.Game;
import fr.arinonia.rpg.ui.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjKey extends SuperObject {
    public ObjKey(final GamePanel gamePanel) {
        this.name = "Key";

        try {
            this.image = ImageIO.read(Game.class.getResourceAsStream("/images/objects/key.png"));
            this.utilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
