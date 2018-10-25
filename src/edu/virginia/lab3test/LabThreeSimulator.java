package edu.virginia.lab3test;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabThreeSimulator extends Game{

	private String[] fileStrings = {"mario/walk_00.png", "mario/walk_01.png", "mario/run_00.png", "mario/run_01.png","mario/jump_00.png", "mario/jump_01.png", "mario/thumbs_00.png", "mario/thumbs_01.png"};
	private ArrayList<String> filenames = new ArrayList<>(Arrays.asList(fileStrings));
	/* Create a sprite object for our game. We'll use mario */
	AnimatedSprite mario = new AnimatedSprite("Mario", filenames);

	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LabThreeSimulator() {
		super("Lab Three Test Game", 500, 300);
	}
	
	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		super.update(pressedKeys);
		
		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */

		if(mario != null) {

		if(pressedKeys.contains(KeyEvent.VK_V)) {
			mario.setVisible(!mario.getVisible());
			try
			{
				Thread.sleep(300);
			}
			catch(InterruptedException ex)
			{
				Thread.currentThread().interrupt();
			}
		}
			boolean animated = false;
			if (pressedKeys.contains(KeyEvent.VK_X)) {
				if(mario.getAlpha()-0.1f>0.0f) {
					mario.setAlpha(mario.getAlpha() - 0.01f);
				}
			}

			if (pressedKeys.contains(KeyEvent.VK_Z)) {
				if(mario.getAlpha()!=1.00f) {
					mario.setAlpha(mario.getAlpha() + 0.01f);
				}
			}

			if (pressedKeys.contains(KeyEvent.VK_A)) {
				mario.setScaleX(mario.getScaleX() + 0.1);
				mario.setScaleY(mario.getScaleY() + 0.1);
			}

			if (pressedKeys.contains(KeyEvent.VK_S)) {
				mario.setScaleX(mario.getScaleX() - 0.1);
				mario.setScaleY(mario.getScaleY() - 0.1);
			}

			if (pressedKeys.contains(KeyEvent.VK_UP)) {
				mario.setPosition(new Point(mario.getPosition().x,
						mario.getPosition().y - 1));
			}
			if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
				mario.setPosition(new Point(mario.getPosition().x,
						mario.getPosition().y + 1));
			}
			if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
				mario.setPosition(new Point(mario.getPosition().x - 1,
						mario.getPosition().y));
				if(pressedKeys.contains(KeyEvent.VK_SHIFT)) {
					mario.animate("run");
					animated = true;
				} else {
					mario.animate("walk");
					animated = true;
				}
			}
			if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
				mario.setPosition(new Point(mario.getPosition().x + 1,
						mario.getPosition().y));
				if(pressedKeys.contains(KeyEvent.VK_SHIFT)) {
					mario.animate("run");
					animated = true;
				} else {
					mario.animate("walk");
					animated = true;
				}
			}

			if (pressedKeys.contains(KeyEvent.VK_W)) {
				mario.setRotation(mario.getRotation() + 5);
			}
			if (pressedKeys.contains(KeyEvent.VK_Q)) {
				mario.setRotation(mario.getRotation() - 5);
			}
			if (pressedKeys.contains(KeyEvent.VK_J)) {
				mario.setPivotPoint(new Point(mario.getPivotPoint().x - 1,
						mario.getPivotPoint().y));
			}
			if (pressedKeys.contains(KeyEvent.VK_L)) {
				mario.setPivotPoint(new Point(mario.getPivotPoint().x + 1,
						mario.getPivotPoint().y));
			}
			if (pressedKeys.contains(KeyEvent.VK_I)) {
				mario.setPivotPoint(new Point(mario.getPivotPoint().x,
						mario.getPivotPoint().y - 1));
			}
			if (pressedKeys.contains(KeyEvent.VK_K)) {
				mario.setPivotPoint(new Point(mario.getPivotPoint().x,
						mario.getPivotPoint().y + 1));
			}
			if(pressedKeys.contains(KeyEvent.VK_EQUALS)) {
				mario.setAnimationSpeed(mario.getAnimationSpeed() - 10);
			}
			if(pressedKeys.contains(KeyEvent.VK_MINUS)) {
				mario.setAnimationSpeed(mario.getAnimationSpeed() + 10);
			}
			if (pressedKeys.contains(KeyEvent.VK_SPACE)) {
				mario.animate("jump");
				animated = true;
			}
			if (pressedKeys.contains(KeyEvent.VK_ENTER)) {
				mario.animate("thumbs");
				animated = true;
			}

			if(!animated) {
				mario.stopAnimation();
			}
			mario.update(pressedKeys);
		}
	}
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	@Override
	public void draw(Graphics g){
		super.draw(g);

		/* Same, just check for null in case a frame gets thrown in before Mario is initialized */
		if((mario != null) && mario.getVisible()) mario.draw(g);
	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		LabThreeSimulator game = new LabThreeSimulator();
		game.start();

		DisplayObjectContainer level = new DisplayObjectContainer("level");
		DisplayObject ralph = new DisplayObject("ralph");
		DisplayObjectContainer mariocontainer = new DisplayObjectContainer("mariocontainer");
		DisplayObject hammer = new DisplayObject("hammer");
		DisplayObject bag = new DisplayObject("bag");
		level.addChild(ralph);
		level.addChild(mariocontainer);
		mariocontainer.addChild(hammer);
		mariocontainer.addChild(bag);
		mariocontainer.setPosition(new Point(10, 10));
		hammer.setPosition(new Point(5, 5));


	}
}
