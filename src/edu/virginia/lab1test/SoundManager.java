package edu.virginia.lab1test;
import javax.sound.sampled.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class SoundManager {

    Clip music;
    public Map<String, Clip> soundEffectMap = new HashMap<>();
    //public Map<String, Clip> musicMap = new HashMap<>();

    public void LoadSoundEffect(String id, String filename){
        Clip clip;
        //filename->clip
        //then put id and clip into hashmap
        try{
            URL loc = this.getClass().getClassLoader().getResource(filename);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(loc);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            soundEffectMap.put(id, clip);
        }
        catch (UnsupportedAudioFileException e) {
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
        curr.start();
    }

    public void LoadMusic(String id, String filename){
        try{
            URL loc = this.getClass().getClassLoader().getResource(filename);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(loc);
            music = AudioSystem.getClip();
            music.open(audioInputStream);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void PlayMusic(String id){
        music.setFramePosition(0);
        music.start();
        music.loop(Clip.LOOP_CONTINUOUSLY);
    }

}