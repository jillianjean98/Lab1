package edu.virginia.engine.display;

import edu.virginia.engine.util.GameClock;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MultiModeSprite extends Sprite{

    private ArrayList<Animation> animations = new ArrayList<>();
    private Boolean playing;
    private String fileName;
    private ArrayList<BufferedImage> images = new ArrayList<>();
    private int currentMode = 0;
    private int numModes = 0;
    private boolean switched;
    private boolean fixedMode = false;
    private BufferedImage displayImage;

    private GameClock gameClock;

    public MultiModeSprite(String ID, ArrayList<String> fileNames) {
        super(ID);
        this.initializeFrames(fileNames);
        super.setImage(this.images.get(this.currentMode));
        super.setAlpha(1.0f);
        super.setVisible(true);
        super.setOldAlpha(0.0f);
        super.setScaleX(4.0);
        super.setScaleY(4.0);
        super.setPosition(new Point(0, 0));
        super.setPivotPoint(new Point(0, 0));
        super.setRotation(0);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    public void setSwitched(boolean switched) {
        this.switched = switched;
    }

    public boolean isSwitched() {
        return switched;
    }

    private void initializeFrames(ArrayList<String> imageNames) {
        if (imageNames == null) {
            return;
        }
        int frameCounter = 0;
        for(String imageName : imageNames) {
            BufferedImage img = readImage(imageName);
            if (img == null) {
                System.err.println("[DisplayObject.setImage] ERROR: " + imageName + " does not exist!");
            }
            //add the image even if it is null, in order to keep numbering accurate
            images.add(img);
            numModes++;
        }
        this.currentMode = 0;
        this.displayImage = this.images.get(0);
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

    public void nextMode() {
        if(!this.switched) {
            this.currentMode = (this.currentMode + 1) % this.numModes;
            this.displayImage = this.images.get(currentMode);
            super.setImage(displayImage);
        }

    }

    public void nextModeStatic() {
        if(!this.fixedMode) {
            this.currentMode = (this.currentMode + 1) % this.numModes;
            this.displayImage = this.images.get(currentMode);
            super.setImage(displayImage);
            this.fixedMode=true;
        }

    }
}
