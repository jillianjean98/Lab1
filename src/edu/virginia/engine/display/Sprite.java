package edu.virginia.engine.display;

import java.awt.*;
import java.util.ArrayList;

/**
 * Nothing in this class (yet) because there is nothing specific to a Sprite yet that a DisplayObject
 * doesn't already do. Leaving it here for convenience later. you will see!
 * */
public class Sprite extends DisplayObjectContainer {

	private int rate = 500;

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public Sprite(String id) {
		super(id);
	}

	public Sprite(String id, String imageFileName) {
		super(id, imageFileName);
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
	}

	public void rotateAroundParent() {
		int rate = this.getRate();
		int x = (int)Math.cos(rate);
		int y = (int)Math.sin(rate);
		Point global = this.localToGlobal(new Point(0,0));
		Point newPoint = new Point(x*global.x, y*global.y);
		this.setPosition(newPoint);
		ArrayList<DisplayObject> children = this.getChildren();
		for(DisplayObject child : children){
			if(child instanceof Sprite) {
				((Sprite) child).rotateAroundParent();
			}
		}
	}
}
