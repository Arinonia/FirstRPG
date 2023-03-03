package fr.arinonia.rpg.ui;

import fr.arinonia.rpg.objects.ObjBoots;
import fr.arinonia.rpg.objects.ObjChest;
import fr.arinonia.rpg.objects.ObjDoor;
import fr.arinonia.rpg.objects.ObjKey;

public class AssetSetter {

    private final GamePanel gamePanel;


    public AssetSetter(final GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        this.gamePanel.getObj()[0] = new ObjKey(this.gamePanel);
        this.gamePanel.getObj()[0].setWorldX(23 * this.gamePanel.getTileSize());
        this.gamePanel.getObj()[0].setWorldY(7 * this.gamePanel.getTileSize());

        this.gamePanel.getObj()[1] = new ObjKey(this.gamePanel);
        this.gamePanel.getObj()[1].setWorldX(23 * this.gamePanel.getTileSize());
        this.gamePanel.getObj()[1].setWorldY(40 * this.gamePanel.getTileSize());

        this.gamePanel.getObj()[2] = new ObjKey(this.gamePanel);
        this.gamePanel.getObj()[2].setWorldX(38 * this.gamePanel.getTileSize());
        this.gamePanel.getObj()[2].setWorldY(8 * this.gamePanel.getTileSize());

        this.gamePanel.getObj()[3] = new ObjDoor(this.gamePanel);
        this.gamePanel.getObj()[3].setWorldX(10 * this.gamePanel.getTileSize());
        this.gamePanel.getObj()[3].setWorldY(11 * this.gamePanel.getTileSize());

        this.gamePanel.getObj()[4] = new ObjDoor(this.gamePanel);
        this.gamePanel.getObj()[4].setWorldX(8 * this.gamePanel.getTileSize());
        this.gamePanel.getObj()[4].setWorldY(28 * this.gamePanel.getTileSize());

        this.gamePanel.getObj()[5] = new ObjDoor(this.gamePanel);
        this.gamePanel.getObj()[5].setWorldX(12 * this.gamePanel.getTileSize());
        this.gamePanel.getObj()[5].setWorldY(22 * this.gamePanel.getTileSize());

        this.gamePanel.getObj()[6] = new ObjChest(this.gamePanel);
        this.gamePanel.getObj()[6].setWorldX(10 * this.gamePanel.getTileSize());
        this.gamePanel.getObj()[6].setWorldY(7 * this.gamePanel.getTileSize());

        this.gamePanel.getObj()[7] = new ObjBoots(this.gamePanel);
        this.gamePanel.getObj()[7].setWorldX(37 * this.gamePanel.getTileSize());
        this.gamePanel.getObj()[7].setWorldY(42 * this.gamePanel.getTileSize());
    }
}
