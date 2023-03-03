package fr.arinonia.rpg.handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;

    private boolean checkDrawTime = false;


    @Override
    public void keyTyped(final KeyEvent e) {

    }

    @Override
    public void keyPressed(final KeyEvent e) {
        final int code = e.getKeyCode();

        if (code == KeyEvent.VK_Z) {
            this.upPressed = true;
        }
        if (code == KeyEvent.VK_Q) {
            this.leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            this.rightPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            this.downPressed = true;
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        final int code = e.getKeyCode();

        if (code == KeyEvent.VK_Z) {
            this.upPressed = false;
        }
        if (code == KeyEvent.VK_Q) {
            this.leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            this.rightPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            this.downPressed = false;
        }

        //debug
        if (code == KeyEvent.VK_T) {
            this.checkDrawTime = !checkDrawTime;
        }
    }

    public boolean isUpPressed() {
        return this.upPressed;
    }

    public boolean isDownPressed() {
        return this.downPressed;
    }

    public boolean isLeftPressed() {
        return this.leftPressed;
    }

    public boolean isRightPressed() {
        return this.rightPressed;
    }

    public boolean isCheckDrawTime() {
        return this.checkDrawTime;
    }
}
