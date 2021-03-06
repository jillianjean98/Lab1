package edu.virginia.engine.display;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import javax.imageio.ImageIO;

/**
 * A very basic display object for a java based gaming engine
 * 
 * */
public class DisplayObject {

	/* All DisplayObject have a unique id */
	private String id;

	/* The image that is displayed by this object */
	private BufferedImage displayImage;

	private Boolean visible;
	private Float alpha;
	private Float oldAlpha;
	private Double scaleX;
	private Double scaleY;
	private Point position;
	private Point pivotPoint;
	private double rotation;
	private Shape hitbox;
	private boolean staticObject = false; //allow for static objects that shouldn't be redrawn

	/**
	 * Constructors: can pass in the id OR the id and image's file path and
	 * position OR the id and a buffered image and position
	 */
	public DisplayObject(String id) {
		this.setId(id);
		this.setPosition(new Point(0, 0));
		this.setPivotPoint(new Point(0, 0));
		this.setRotation(0);
	}

	public DisplayObject(String id, String fileName) {
		this.setId(id);
		this.setImage(fileName);
		this.setAlpha(1.0f);
		this.setVisible(true);
		this.setOldAlpha(0.0f);
		this.setScaleX(1.0);
		this.setScaleY(1.0);
		this.setPosition(new Point(0, 0));
		this.setPivotPoint(new Point(0, 0));
		this.setRotation(0);
	}

	public DisplayObject(String id, String fileName, Shape hitbox) {
		this.setId(id);
		this.setImage(fileName);
		this.setAlpha(1.0f);
		this.setVisible(true);
		this.setOldAlpha(0.0f);
		this.setScaleX(1.0);
		this.setScaleY(1.0);
		this.setPosition(new Point(0, 0));
		this.setPivotPoint(new Point(0, 0));
		this.setRotation(0);
		this.setHitbox(hitbox);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setVisible(Boolean x){ this.visible = x; }
	public Boolean getVisible(){ return visible; }

	public void setAlpha(Float a){ this.alpha = a; }
	public Float getAlpha(){return alpha;}

	public void setOldAlpha(Float a){ this.oldAlpha = a; }
	public Float getOldAlpha(){return oldAlpha;}

	public void setScaleX(Double scaleX){this.scaleX = scaleX;}
	public Double getScaleX() { return scaleX;}

	public void setScaleY(Double scaleY){this.scaleY = scaleY;}
	public Double getScaleY() { return scaleY;}

    public void setPosition(Point position) {
        this.position = position;
    }
    public Point getPosition() {
        return position;
    }

    public void setPivotPoint(Point pivotPoint) { this.pivotPoint = pivotPoint; }
    public Point getPivotPoint() {
        return pivotPoint;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }
    public double getRotation() {
        return rotation;
    }

	public void setHitbox(Shape hitbox) {
		this.hitbox = hitbox;
	}
	public Shape getHitbox() {
		return this.hitbox;
	}

	public boolean isStaticObject() {
		return staticObject;
	}

	public void setStaticObject(boolean staticObject) {
		this.staticObject = staticObject;
	}

	/**
	 * Returns the unscaled width and height of this display object
	 * */
	public int getUnscaledWidth() {
		if(displayImage == null) return 0;
		return displayImage.getWidth();
	}

	public int getUnscaledHeight() {
		if(displayImage == null) return 0;
		return displayImage.getHeight();
	}

	public BufferedImage getDisplayImage() {
		return this.displayImage;
	}

	protected void setImage(String imageName) {
		if (imageName == null) {
			return;
		}
		displayImage = readImage(imageName);
		if (displayImage == null) {
			System.err.println("[DisplayObject.setImage] ERROR: " + imageName + " does not exist!");
		}
	}

	/**
	 * Helper function that simply reads an image from the given image name
	 * (looks in resources\\) and returns the bufferedimage for that filename
	 * */
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

	public void setImage(BufferedImage image) {
		if(image == null) return;
		displayImage = image;
	}


	/**
	 * Invoked on every frame before drawing. Used to update this display
	 * objects state before the draw occurs. Should be overridden if necessary
	 * to update objects appropriately.
	 * */
	protected void update(ArrayList<Integer> pressedKeys) {

	}

	/**
	 * Draws this image. This should be overloaded if a display object should
	 * draw to the screen differently. This method is automatically invoked on
	 * every frame.
	 * */
	public void draw(Graphics g) {

		if (displayImage != null) {

			/*
			 * Get the graphics and apply this objects transformations
			 * (rotation, etc.)
			 */
			Graphics2D g2d = (Graphics2D) g;

				applyTransformations(g2d);
				g2d.drawImage(displayImage, 0, 0, (int) (getUnscaledWidth()),
						(int) (getUnscaledHeight()), null);

				/*
				 * undo the transformations so this doesn't affect other display
				 * objects
				 */
				reverseTransformations(g2d);
		}
	}

	/**
	 * Applies transformations for this display object to the given graphics
	 * object
	 * */

	protected void applyTransformations(Graphics2D g2d){
		AffineTransform tx1 = new AffineTransform();
		tx1.translate(this.position.x, this.position.y);
		tx1.rotate(Math.toRadians(this.getRotation()), this.pivotPoint.x, this.pivotPoint.y);
		tx1.scale(this.scaleX, this.scaleY);
		g2d.setTransform(tx1);
		float curAlpha;
		this.oldAlpha = curAlpha = ((AlphaComposite)
				g2d.getComposite()).getAlpha();
		g2d.setComposite(AlphaComposite.getInstance(3, curAlpha *
				this.alpha));
		if(!staticObject){
			Shape newHitbox = tx1.createTransformedShape(hitbox);
			this.setHitbox(newHitbox);
		}
	}

	/**
	 * Reverses transformations for this display object to the given graphics
	 * object
	 * */

	protected void reverseTransformations(Graphics2D g2d){
		g2d.setComposite(AlphaComposite.getInstance(3,
				this.oldAlpha));
		AffineTransform tx1 = new AffineTransform();
		tx1.scale(1/this.scaleX, 1/this.scaleY);
		tx1.rotate(Math.toRadians(-this.getRotation()), this.pivotPoint.x, this.pivotPoint.y);
		tx1.translate(-this.position.x, -this.position.y);
		g2d.setTransform(tx1);
		if(!staticObject) {
			Shape newHitbox = tx1.createTransformedShape(hitbox);
			this.setHitbox(newHitbox);
		}


	}

	public boolean collidesWith(DisplayObject other) {
		Area thisHitbox = new Area(getHitbox());
		Area otherHitbox = new Area(other.getHitbox());
		thisHitbox.intersect(otherHitbox);
		return !thisHitbox.isEmpty();
	}


}
