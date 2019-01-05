package Music;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by 上官刀刀 on 2017/10/13.
 */
public class Music extends Thread{
    private Player player = null;
    private  BufferedInputStream buffer = null;
    private String music_url;
   public Music(String url)
   {
      music_url=url;
   }
    @Override
    public void run() {
        super.run();
        try {
            buffer = new BufferedInputStream(new FileInputStream(music_url));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            player = player = new Player(buffer);
        } catch (JavaLayerException e1) {
            e1.printStackTrace();
        }
        try {
            player.play();
        } catch (JavaLayerException e1) {
            e1.printStackTrace();
        }
    }

    public void music_stop()
    {
        try {
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.close();

    }
}
