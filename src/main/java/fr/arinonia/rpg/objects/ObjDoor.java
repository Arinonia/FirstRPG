package fr.arinonia.rpg.objects;

import fr.arinonia.rpg.Game;
import fr.arinonia.rpg.ui.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjDoor extends SuperObject {

    public ObjDoor(final GamePanel gamePanel) {
        this.name = "Door";

        try {
            this.image = ImageIO.read(Game.class.getResourceAsStream("/images/objects/door.png"));
            this.utilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());

        } catch (final IOException e) {
            e.printStackTrace();
        }
        this.collision = true;
    }
}
