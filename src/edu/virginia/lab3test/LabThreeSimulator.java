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
		Sprite sun = new Sprite("Sun", "sun.png");
		Sprite earth = new Sprite("Earth", "earth.png");
		Sprite mars = new Sprite("Mars", "mars.png");
		Sprite moon1 = new Sprite("Moon1", "moon1.png");
		Sprite moon2 = new Sprite("Moon2", "moon2.png");
		sun.addChild(earth);
		sun.addChild(mars);
		earth.addChild(moon1);
		mars.addChild(moon2);
		sun.setPosition(new Point(250, 150));
		earth.setPosition(new Point(100,0));
		mars.setPosition(new Point(200,100));
		earth.setPivotPoint(new Point(250, 150));
		mars.setPivotPoint(new Point(250, 150));
		

	}
}
