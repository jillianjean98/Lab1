package edu.virginia.lab3test;

import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.display.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
		moon1.setPosition(new Point(-400, -400));
		moon2.setPosition(new Point(-200, -600));
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
		if(sun!=null) {
			sun.rotateAroundParent();
			sun.update(pressedKeys);

			//zoom in
			if (pressedKeys.contains(KeyEvent.VK_Q)) {
				sun.setPosition(new Point(sun.getPosition().x - 4,
						sun.getPosition().y - 4));
				sun.setScaleX(sun.getScaleX() + 0.01);
				sun.setScaleY(sun.getScaleY() + 0.01);
			}

			//zoom out
			if (pressedKeys.contains(KeyEvent.VK_W)) {
				if (sun.getScaleX() - 0.1f > 0.0f) {
					sun.setPosition(new Point(sun.getPosition().x + 4,
							sun.getPosition().y + 4));
					sun.setScaleX(sun.getScaleX() - 0.01);
					sun.setScaleY(sun.getScaleY() - 0.01);
				}
			}

			if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
				sun.setPosition(new Point(sun.getPosition().x,
						sun.getPosition().y - 5));
			}
			if (pressedKeys.contains(KeyEvent.VK_UP)) {
				sun.setPosition(new Point(sun.getPosition().x,
						sun.getPosition().y + 5));
			}
			if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
				sun.setPosition(new Point(sun.getPosition().x - 5,
						sun.getPosition().y));
			}
			if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
				sun.setPosition(new Point(sun.getPosition().x + 5,
						sun.getPosition().y));
			}
			//ccw
			if (pressedKeys.contains(KeyEvent.VK_A)) {
				sun.setClockwise(false);
				//earth.setClockwise(false);
				//mars.setClockwise(false);
				//moon1.setClockwise(false);
			}
			//cw
			if (pressedKeys.contains(KeyEvent.VK_S)) {
				sun.setClockwise(true);
				//earth.setClockwise(true);
				//mars.setClockwise(true);
				//moon2.setClockwise(true);
			}
		}
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
	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		LabThreeSimulator game = new LabThreeSimulator();
		game.start();
	}
}
