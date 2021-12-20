package view;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;


public class MusicMp3 extends Thread {
    Player player;
    File music;

    public MusicMp3(String s) {
        this.music = new File(s+".mp3");;
    }

    @Override
    public void run() {
        super.run();
        try {
            play();
        } catch (FileNotFoundException | JavaLayerException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void play() throws FileNotFoundException, JavaLayerException, InterruptedException {
        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
        player = new Player(buffer);
        player.play();
        Thread.sleep(5000);
        player.close();

    }
}