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

	public Boolean contains(DisplayObject child){
//		Boolean found = false;
		if(children.isEmpty()) {
			return false;
		}
		return children.contains(child);
	}

	public ArrayList<DisplayObjectContainer> getChildren(){
		return this.children;
	}


}

