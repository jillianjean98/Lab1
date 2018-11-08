package edu.virginia.engine.util;
import javax.sound.sampled.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class SoundManager {
    Clip music;
    public Map<String, Clip> soundEffectMap = new HashMap<>();
    //public Map<String, Clip> MMap = new HashMap<>();
    //public Map<String, Clip> musicMap = new HashMap<>();


    public void LoadSoundEffect(String id, String filename) {
        Clip clip;
        //filename->clip
        //then put id and clip into hashmap

       try {
            File f = new File("resources" + File.separator + filename);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(f);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            soundEffectMap.put(id, clip);
       } catch (UnsupportedAudioFileException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       } catch (LineUnavailableException e) {
           e.printStackTrace();
       }

    }

    public void PlaySoundEffect(String id){
        //retrieve sound frm the hashmap
        Clip curr = soundEffectMap.get(id);
        curr.setFramePosition(0);
       // System.out.println("play");
        curr.start();
    }

    public void LoadMusic(String id, String filename){
        Clip clip;
        try {
            File a = new File("resources" + File.separator + filename);
            //System.out.println(a);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(a);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            //soundEffectMap.put(id, clip);
            this.music = clip;
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void PlayMusic(){
        music.setFramePosition(0);
        music.start();
        music.loop(Clip.LOOP_CONTINUOUSLY);


    }

}