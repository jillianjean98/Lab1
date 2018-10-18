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

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public int getStartFrame() {
        return startFrame;
    }

    public void setStartFrame(int sf){
        this.startFrame = sf;
    }

    public int getEndFrame(){
        return endFrame;
    }

    public void setEndFrame(int ef){
        this.endFrame = ef;
    }



}
