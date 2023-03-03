package fr.arinonia.rpg.objects;

import fr.arinonia.rpg.Game;
import fr.arinonia.rpg.ui.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjChest extends SuperObject {

    public ObjChest(final GamePanel gamePanel) {
        this.name = "Chest";

        try {
            this.image = ImageIO.read(Game.class.getResourceAsStream("/images/objects/chest.png"));
            this.utilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

}
