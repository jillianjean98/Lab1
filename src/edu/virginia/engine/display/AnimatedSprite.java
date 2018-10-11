package edu.virginia.engine.display;
import edu.virginia.engine.util.GameClock;

import java.util.*;
import java.io.*;

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

    private GameClock gameClock;

    public Sprite(String ID, String fileName) {
        super(ID, fileName);
        gameClock = new GameClock();
        animationSpeed = 2;
    }

    public void initGameClock(){
        if(gameClock == null){
                gameClock = new GameClock();
        }
    }

}

