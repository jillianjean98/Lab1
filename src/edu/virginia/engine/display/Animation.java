package edu.virginia.engine.display;

public class Animation {

    private String id;
    private int startFrame;
    private int endFrame;

    public Animation(String id, int startFrame, int endFrame){
        this.setId(id);
        this.setStartFrame(startFrame);
        this.setEndFrame(endFrame);
    }

    private String getId(){
        return id;
    }

    private void setId(String id){
        this.id = id;
    }

    private int getStartFrame() {
        return startFrame;
    }

    private void setStartFrame(int sf){
        this.startFrame = sf;
    }

    private int getEndFrame(){
        return endFrame;
    }

    private void setEndFrame(int ef){
        this.endFrame = ef;
    }



}
