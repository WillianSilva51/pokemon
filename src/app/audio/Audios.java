package app.audio;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audios {

    private static Clip clip;

    public static void iniciarMusica(String path) throws Exception {
        File audioFile = new File(path);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }

    public static void pararMusica() {
        clip.stop();
    }

    public static void loopMusica() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void pararLoop() {
        clip.loop(0);
    }

    public static void pararTudo() {
        clip.stop();
        clip.close();
    }
}
