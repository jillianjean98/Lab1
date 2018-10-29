package edu.virginia.lab3test;

import edu.virginia.engine.display.Sprite;
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

	Sprite sun = new Sprite("Sun", "sun.png");
	Sprite earth = new Sprite("Earth", "earth.png");
	Sprite mars = new Sprite("Mars", "mars.png");
	Sprite moon1 = new Sprite("Moon1", "moon.png");
	Sprite moon2 = new Sprite("Moon2", "moon.png");

	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LabThreeSimulator() {
		super("Lab Three Test Game", 1000, 800);
		sun.setPosition(new Point(350, 250));
		sun.addChild(mars);
		sun.addChild(earth);
		earth.addChild(moon1);
		mars.addChild(moon2);
		//scale sprites
		sun.setScaleX(0.2);
		sun.setScaleY(0.2);
		earth.setScaleX(0.5);
		earth.setScaleY(0.5);
		mars.setScaleX(0.5);
		mars.setScaleY(0.5);
		moon1.setScaleX(0.5);
		moon1.setScaleY(0.5);
		moon2.setScaleX(0.5);
		moon2.setScaleY(0.5);

		mars.setPosition(new Point(1000,300));
		earth.setPosition(new Point(1500,300));
		moon1.setPosition(new Point(-500, -50));
		moon2.setPosition(new Point(-500, -50));
		mars.setRate(1);
		earth.setRate(2);
	}
	
	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		if(sun!=null){
			sun.rotateAroundParent();
			sun.update(pressedKeys);
		}

		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */

	}
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	@Override
	public void draw(Graphics g){
		super.draw(g);
		if(sun != null) {
			sun.draw(g);
		}
		/* Same, just check for null in case a frame gets thrown in before Mario is initialized */

	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		LabThreeSimulator game = new LabThreeSimulator();
		game.start();
/*
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
*/

	}
}
