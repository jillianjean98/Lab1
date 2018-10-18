package edu.virginia.engine.display;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A very basic display object for a java based gaming engine
 * 
 * */
public class DisplayObjectContainer extends DisplayObject{

	private ArrayList<DisplayObject> children;

	/**
	 * Constructors: can pass in the id OR the id and image's file path and
	 * position
	 */
	public DisplayObjectContainer(String id) {
		super(id);
		children = new ArrayList<>();
	}

	public DisplayObjectContainer(String id, String fileName) {
		super(id, fileName);
		children = new ArrayList<>();
	}

	public void addChild(DisplayObject child) {
		children.add(child);
	}

	public void addChildAtIndex(DisplayObject child, int index) {
		children.add(index, child);
	}

	public void removeChild(DisplayObject child) {
		children.remove(child);
	}

	public void removeByIndex(int index) {
		children.remove(index);
	}

	public void removeAll() {
		children.clear();
	}

	public Boolean contains(DisplayObject obj){
		Boolean found = false;
		if(children.isEmpty()) {
			return false;
		}
		if(children.contains(obj)){
			return true;
		}
		else {
			for (DisplayObject child : children) {
				if (children.contains(obj)) {
					found = true;
				}
			}
		}
		return found;
	}

	public DisplayObject getChild(String id){
		if(children.isEmpty()) {
			return null;
		}
		for(DisplayObject child: children){
			if(child.getId() == id){
				return child;
			}
			else{
				return child.getChild(id);
			}
		}
	}

	/**
	 * Draws this image. This should be overloaded if a display object should
	 * draw to the screen differently. This method is automatically invoked on
	 * every frame.
	 * */
	@Override
	public void draw(Graphics g) {

		super.draw(g);

			/*
			 * Get the graphics and apply this objects transformations
			 * (rotation, etc.)
			 */
			Graphics2D g2d = (Graphics2D) g;
			applyTransformations(g2d);

			/* Actually draw the image, perform the pivot point translation here */
			for(DisplayObject child : children) {
				child.draw(g2d);
			}
			/*
			 * undo the transformations so this doesn't affect other display
			 * objects
			 */
			reverseTransformations(g2d);
		}
	}

	protected void applyTransformations(Graphics2D g2d){
			g2d.translate(this.getPosition().x, this.getPosition().y);
			g2d.rotate(Math.toRadians(this.getRotation()), this.getPivotPoint().x, this.getPivotPoint().y);
			g2d.scale(this.getScaleX(), this.getScaleY());
			float curAlpha;
			this.setOldAlpha(curAlpha = ((AlphaComposite)
					g2d.getComposite()).getAlpha());
			g2d.setComposite(AlphaComposite.getInstance(3, curAlpha *
					this.getAlpha()));

	}

	/**
	 * Reverses transformations for this display object to the given graphics
	 * object
	 * */

	protected void reverseTransformations(Graphics2D g2d){
		g2d.setComposite(AlphaComposite.getInstance(3,
				this.getOldAlpha()));
		g2d.scale(0.5, 0.5);
		g2d.rotate(Math.toRadians(-this.getRotation()), this.getPivotPoint().x, this.getPivotPoint().y);
		g2d.translate(0,0);

	}

}
