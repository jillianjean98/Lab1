package edu.virginia.engine.display;
import edu.virginia.engine.util.GameClock;

import javax.imageio.ImageIO;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class AnimatedSprite extends Sprite{

    private ArrayList<Animation> animations = new ArrayList<>();
    private Boolean playing;
    private String fileName;
    private ArrayList<BufferedImage> frames = new ArrayList<>();
    private int currentFrame = 0;
    private int startFrame;
    private int endFrame;
    private static final int DEFAULT_ANIMATION_SPEED = 1;
    private int animationSpeed;

    private GameClock gameClock;

    public AnimatedSprite(String ID, ArrayList<String> fileNames) {
        super(ID);
        gameClock = new GameClock();
        animationSpeed = DEFAULT_ANIMATION_SPEED;
        this.setFrames(fileNames);
        super.setImage(this.frames.get(this.currentFrame));
        super.setAlpha(1.0f);
        super.setVisible(true);
        super.setOldAlpha(0.0f);
        super.setScaleX(4.0);
        super.setScaleY(4.0);
        super.setPosition(new Point(0, 0));
        super.setPivotPoint(new Point(0, 0));
        super.setRotation(0);
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
            String animationID[] = imageName.split("_");
            Animation a = getAnimation(animationID[0]);
            if(a != null) {
                a.setEndFrame(a.getEndFrame()+1);
            }else {
                animations.add(new Animation(animationID[0], 0, 0));
            }
        }
        this.currentFrame = 0;
    }

    @Override
    public BufferedImage readImage(String imageName) {
        BufferedImage image = null;
        try {
            String file = ("resources" + File.separator + imageName);
            image = ImageIO.read(new File(file));
        } catch (IOException e) {
            System.out.println("[Error in DisplayObject.java:readImage] Could not read image " + imageName);
            e.printStackTrace();
        }
        return image;
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

    public void animate(int startFrame, int endFrame) {
        this.startFrame = startFrame;
        this.endFrame = endFrame;
    }

    public void animate(String id) {
       Animation a = this.getAnimation(id);
       if(a != null) {
           animate(a);
       }
    }
}
