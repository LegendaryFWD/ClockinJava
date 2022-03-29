import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class soundhndl {
     private static final String srcPath = "res/";
     public static void playSound(String path){
        try{
            AudioInputStream audioIS = AudioSystem.getAudioInputStream(new File(srcPath + path).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIS);
            clip.start();
        } 
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    public static void loopSound(String path){
        try{
            AudioInputStream audioIS = AudioSystem.getAudioInputStream(new File(srcPath + path).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIS);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } 
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
