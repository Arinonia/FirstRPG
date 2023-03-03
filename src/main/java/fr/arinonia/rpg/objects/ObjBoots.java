package fr.arinonia.rpg.objects;

import fr.arinonia.rpg.Game;
import fr.arinonia.rpg.ui.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjBoots extends SuperObject {

    public ObjBoots(final GamePanel gamePanel) {
        this.name = "Boots";

        try {
            this.image = ImageIO.read(Game.class.getResourceAsStream("/images/objects/boots.png"));
            this.utilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());

        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
