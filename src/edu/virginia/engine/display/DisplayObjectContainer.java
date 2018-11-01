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
		child.setParent(this);
	}

	public void addChildAtIndex(DisplayObject child, int index) {
		children.add(index, child);
		child.setParent(this);
	}

	public void removeChild(DisplayObject child) {
		children.remove(child);
		child.setParent(null);
	}

	public void removeByIndex(int index) {
		children.get(index).setParent(null);
		children.remove(index);
	}

	public void removeAll() {
		for(DisplayObject child: children) {
			child.setParent(null);
		}
		children.clear();
	}

	public ArrayList<DisplayObject> getChildren(){
		return this.children;
	}


	public Boolean contains(DisplayObject obj){
		if(children.isEmpty()) {
			return false;
		}
		return children.contains(obj);
	}

	public DisplayObject getChild(String id){
		if(children.isEmpty()) {
			return null;
		}
		for(DisplayObject child: children){
			if(child.getId() == id){
				return child;
			}
		}
		return null;
	}
	/**
	 * Invoked on every frame before drawing. Used to update this display
	 * objects state before the draw occurs. Should be overridden if necessary
	 * to update objects appropriately.
	 * */
	@Override
	protected void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		for(DisplayObject child: children) {
			child.update(pressedKeys);
		}
	}

	/**
	 * Draws this image. This should be overloaded if a display object should
	 * draw to the screen differently. This method is automatically invoked on
	 * every frame.
	 * */
	@Override
	public void draw(Graphics g) {
		if (this.getDisplayImage() != null) {
			super.draw(g);

			/*
			 * Get the graphics and apply this objects transformations
			 * (rotation, etc.)
			 */

			Graphics2D g2d = (Graphics2D) g;
			applyTransformations(g2d);
			/* draw each child */

			for (DisplayObject child : children) {

				child.draw(g2d);

			}

			/*
			 * undo the transformations so this doesn't affect other display
			 * objects
			 */
			reverseTransformations(g2d);

		}
	}

}

