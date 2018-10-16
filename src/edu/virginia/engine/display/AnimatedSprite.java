package edu.virginia.engine.display;
import edu.virginia.engine.util.GameClock;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class AnimatedSprite extends Sprite{

    private ArrayList animations = new ArrayList();
    private Boolean playing;
    private String fileName;
    private ArrayList frames = new ArrayList();
    private int currentFrame;
    private int startFrame;
    private int endFrame;
    private static final int DEFAULT_ANIMATION_SPEED = 1;
    private int animationSpeed;

    private BufferedImage displayImage;

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

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        if(playing){
            super.setImage(frames.get(currentFrame));
            if(currentFrame == endFrame){
                currentFrame = startFrame;
            }
            else{
                currentFrame++;
            }
        }

    }
}

