package view;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class Music extends Thread {
    private File f;
    private URI uri;
    private URL url;
    AudioClip aau;

    public void run() { // 注意，java只能播放无损音质，如.wav这种格式
        try {

            f = new File("BackgroundMusic.wav"); // 绝对路径
            uri = f.toURI();
            url = uri.toURL(); // 解析路径

            aau = Applet.newAudioClip(url);
            aau.loop(); // 单曲循环
            System.out.println("开始播放音乐");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void stopp(){
        aau.stop();
    }


}