package fr.arinonia.rpg.ui;

import fr.arinonia.rpg.Game;
import fr.arinonia.rpg.objects.ObjKey;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class Ui {

    private final GamePanel gamePanel;
    private final Font arial;
    private final Font arial_80B;

    private final BufferedImage keyImage;
    private boolean messageOn = false;
    private String message;

    private int messageCooldown = 0;
    private boolean gameFinished = false;
    private double playTimer = 0;
    private DecimalFormat format = new DecimalFormat("#0.00");

    public Ui(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.arial = new Font("Arial", Font.PLAIN, 40);
        this.arial_80B = new Font("Arial", Font.BOLD, 72);

        this.keyImage = new ObjKey(gamePanel).getImage();
    }

    public void draw(final Graphics2D g2) {

        if (this.gameFinished) {
            g2.setFont(this.arial);
            g2.setColor(Color.WHITE);

            String text;
            int textLenght;
            int x;
            int y;

            text = "You found the treasure!";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = this.gamePanel.getScreenWidth() / 2 - textLenght / 2;
            y = this.gamePanel.getScreenHeight() / 2 - (this.gamePanel.getTileSize() * 3);
            g2.drawString(text, x, y);

            text = "Your time is: " + format.format(playTimer) + "!";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = this.gamePanel.getScreenWidth() / 2 - textLenght / 2;
            y = this.gamePanel.getScreenHeight() / 2 + (this.gamePanel.getTileSize() * 4);
            g2.drawString(text, x, y);

            g2.setFont(this.arial_80B);
            g2.setColor(Color.yellow);
            text = "Congratulation!!!";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = this.gamePanel.getScreenWidth() / 2 - textLenght / 2;
            y = this.gamePanel.getScreenHeight() / 2 + (this.gamePanel.getTileSize() * 2);
            g2.drawString(text, x, y);

            this.gamePanel.gameThread = null;
        } else {
            g2.setFont(this.arial);
            g2.setColor(Color.WHITE);
            g2.drawImage(this.keyImage, this.gamePanel.getTileSize() / 2, this.gamePanel.getTileSize() / 2, this.gamePanel.getTileSize(), this.gamePanel.getTileSize(), null);
            g2.drawString("x " + this.gamePanel.getPlayer().getKey(), 74, 64);
            playTimer += (double) 1 / 60 ;
            g2.drawString("Time: " + this.format.format(this.playTimer), this.gamePanel.getTileSize() * 11, 65);
            if (messageOn) {
                g2.setFont(this.arial.deriveFont(30.0F));
                g2.drawString(this.message, this.gamePanel.getTileSize() / 2, this.gamePanel.getTileSize() * 5);
                this.messageCooldown++;

                if (this.messageCooldown > 120) {
                    this.messageCooldown = 0;
                    this.messageOn = false;
                }
            }
        }
    }

    public void showMessage(final String text) {
        this.message = text;
        this.messageOn = true;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
}
