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

	
}
