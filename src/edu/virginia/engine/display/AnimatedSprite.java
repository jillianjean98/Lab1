package edu.virginia.engine.display;
import edu.virginia.engine.util.GameClock;

import java.util.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class AnimatedSprite extends Sprite{

    private ArrayList animations = new ArrayList<Animation>();
    private Boolean playing;
    private String fileName;
    private ArrayList frames = new ArrayList<BufferedImage>();
    private int currentFrame;
    private int startFrame;
    private int endFrame;
    private static final int DEFAULT_ANIMATION_SPEED = 1;
    private int animationSpeed;

    private GameClock gameClock;

    public AnimatedSprite(String ID, String fileName) {
        super(ID, fileName);
        gameClock = new GameClock();
        animationSpeed = DEFAULT_ANIMATION_SPEED;
    }

    private int getAnimationSpeed(){
        return this.animationSpeed;
    }

    private void setAnimationSpeed(int speed){
        this.animationSpeed = speed;
    }

    public void initGameClock(){
        if(gameClock == null){
                gameClock = new GameClock();
        }
    }

    private void setFrames(ArrayList<String> imageNames) {
        if (imageNames == null) {
            return;
        }
        for(String imageName : imageNames) {
            BufferedImage img = readImage(imageName);
            if (img == null) {
                System.err.println("[DisplayObject.setImage] ERROR: " + imageName + " does not exist!");
            }
            //add the image even if it is null, in order to keep numbering accurate
            frames.add(img);
        }
    }

    public Animation getAnimation(String id) {
        for(Animation a: this.animations){
            if(a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    public void animate(Animation animation) {
        this.startFrame = animation.getStartFrame();
        this.endFrame = animation.getEndFrame();
    }

}
