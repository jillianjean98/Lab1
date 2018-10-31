package edu.virginia.engine.display;

import java.awt.*;
import java.util.ArrayList;

/**
 * Nothing in this class (yet) because there is nothing specific to a Sprite yet that a DisplayObject
 * doesn't already do. Leaving it here for convenience later. you will see!
 * */
public class Sprite extends DisplayObjectContainer {

	private int rate = 5;
	private int angle = 0;


	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
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
		//rather, we should use setRotation method, then set the pivot point to be the origin of the parent
		if(this.getParent() != null) {
			Point pivotPoint = new Point(this.getParent().getPosition().x -this.getPosition().x,
					this.getParent().getPosition().y -this.getPosition().y);
			//translate pivot point to center of object, rather than corner
			int shift = (int)Math.round(400*this.getGlobalScale(this.getScaleX()));
			//System.out.println(this.getId() + " shift: " + shift);
			setPivotPoint(new Point(pivotPoint.x + shift, pivotPoint.y + shift));
			System.out.println(this.getClockwise());
			if(this.getClockwise()) {
				setRotation(getRotation() + this.rate);
			}
			else{
				setRotation(getRotation() - this.rate);
			}
			//System.out.println(this.getId() + " pivot point: " + this.getPivotPoint());
			/*double x = Math.cos(Math.toRadians(angle)) * 50;
			double y = Math.sin(Math.toRadians(angle)) * 50;

			Point newPoint = new Point((int) x + getParent().getGlobalPosition().x, (int) y + getParent().getGlobalPosition().y);
			this.setPosition(newPoint);
			System.out.println(this.getId() + ": " + localToGlobal(newPoint));
			this.angle = (this.angle + this.rate) % 360;*/
		}
		ArrayList<DisplayObject> children = this.getChildren();
		for(DisplayObject child : children){
			if(child instanceof Sprite) {
				((Sprite) child).rotateAroundParent();
			}
		}
	}
}
