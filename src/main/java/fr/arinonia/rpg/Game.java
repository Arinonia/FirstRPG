package fr.arinonia.rpg;

import fr.arinonia.rpg.ui.GamePanel;
import fr.arinonia.rpg.utils.Constants;

import javax.swing.*;

public class Game {

    public void initGame() {
        final JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle(Constants.APP_NAME);
        final GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }

}
