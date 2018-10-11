package edu.virginia.engine.display;

import edu.virginia.engine.util.GameClock;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimatedSprite extends Sprite {

    private ArrayList<Animation> animations;
    private boolean playing;
    private String fileName;
    private ArrayList<BufferedImage> frames;
    private int currentFrame;
    private int startFrame;
    private int endFrame;

    static int DEFAULT_ANIMATION_SPEED = 1;

    private int animationSpeed;
    private GameClock gameClock;

    public AnimatedSprite(String id, String imageFileName, Point position) {
        super(id, imageFileName);
        gameClock = new GameClock();
        animationSpeed = DEFAULT_ANIMATION_SPEED;
    }
}
