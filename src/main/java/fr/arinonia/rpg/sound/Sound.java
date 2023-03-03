package fr.arinonia.rpg.sound;

import fr.arinonia.rpg.Game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    private Clip clip;
    private URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = Game.class.getResource("/sounds/music.wav");
        soundURL[1] = Game.class.getResource("/sounds/coin.wav");
        soundURL[2] = Game.class.getResource("/sounds/powerup.wav");
        soundURL[3] = Game.class.getResource("/sounds/unlock.wav");
        soundURL[4] = Game.class.getResource("/sounds/fanfare.wav");
    }

    public void setFile(final int index) {
        try {
            final AudioInputStream ais = AudioSystem.getAudioInputStream(this.soundURL[index]);
            this.clip = AudioSystem.getClip();
            this.clip.open(ais);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
    public void play() {
        this.clip.start();
    }
    public void loop() {
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        this.clip.stop();
    }
}
