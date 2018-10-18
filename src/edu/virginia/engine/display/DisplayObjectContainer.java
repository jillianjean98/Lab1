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

	private ArrayList<DisplayObjectContainer> children;

	/**
	 * Constructors: can pass in the id OR the id and image's file path and
	 * position OR the id and a buffered image and position
	 */
	public DisplayObjectContainer(String id) {
		super(id);
		children = new ArrayList<>();
	}

	public DisplayObjectContainer(String id, String fileName) {
		super(id, fileName);
		children = new ArrayList<>();
	}

	public void addChild(DisplayObjectContainer child) {
		children.add(child);
	}

	public void addChildAtIndex(DisplayObjectContainer child, int index) {
		children.add(index, child);
	}

	public void removeChild(DisplayObjectContainer child) {
		children.remove(child);
	}

	public void removeByIndex(int index) {
		children.remove(index);
	}

	public void removeAll() {
		children.clear();
	}
}
