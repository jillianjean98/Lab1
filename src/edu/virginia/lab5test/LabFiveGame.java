package edu.virginia.lab5test;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.SoundManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabFiveGame extends Game{
	/* Create a sprite object for our game. We'll use mario */
	Sprite mario = new Sprite("Mario", "Mario.png");
	public static SoundManager sm = new SoundManager();
	Sprite block = new Sprite("block", "block.png");
	Sprite star = new Sprite("star", "Star.png");
	int score = 1000;
	boolean won = false;
	boolean colliding = false;
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LabFiveGame() {
		super("Lab Four Test Game", 600, 400);
		mario.setPosition(new Point(0, 250));
		mario.setHitbox(new Rectangle(mario.getPosition().x, mario.getPosition().y,
				(int)(mario.getUnscaledWidth()*mario.getScaleX()), (int)(mario.getUnscaledHeight()*mario.getScaleY())));
		mario.setHasPhysics(true);
		block.setPosition(new Point(150, 150));
		block.setHitbox(new Rectangle(block.getPosition().x, block.getPosition().y,
				(int)(block.getUnscaledWidth()*block.getScaleX()), (int)(block.getUnscaledHeight()*block.getScaleY())));
		block.setStaticObject(true);
		star.setPosition(new Point(450, 250));
		star.setHitbox(new Ellipse2D.Double(star.getPosition().x, star.getPosition().y,
				(int) (star.getUnscaledWidth() * star.getScaleX()), (int) (star.getUnscaledHeight() * star.getScaleY())) {
		});
		star.setStaticObject(true);
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
			//sm.PlayMusic();

			if(mario.collidesWith(block)) {
				if(!colliding) {
					score = score -100;
					sm.PlaySoundEffect("test");
					colliding = true;
				}
			} else {
				colliding = false;
			}
			if(mario.collidesWith(star)) {
				if(!won){
					sm.PlaySoundEffect("test");
					won = true;
				}
			}

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
						mario.getPosition().y - 5));
			}
			if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
				if(!mario.onBaseline()) {
					mario.setPosition(new Point(mario.getPosition().x,
							mario.getPosition().y + 5));
				}
			}
			if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
				mario.setPosition(new Point(mario.getPosition().x - 5,
						mario.getPosition().y));
			}
			if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
				mario.setPosition(new Point(mario.getPosition().x + 5,
						mario.getPosition().y));
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

			mario.setHitbox(new Rectangle(mario.getPosition().x, mario.getPosition().y,
					(int)(mario.getUnscaledWidth()*mario.getScaleX()), (int)(mario.getUnscaledHeight()*mario.getScaleY())));
			mario.update(pressedKeys);
			block.update(pressedKeys);
			star.update(pressedKeys);
		}
	}

	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	@Override
	public void draw(Graphics g){
		super.draw(g);
		g.drawString("score: " + score, 500,20);
		if(won) {
			g.drawString("Congratulations! You won! " , 400,40);
		}
		/* Same, just check for null in case a frame gets thrown in before Mario is initialized */
		if((mario != null) && mario.getVisible()) mario.draw(g);
		if((block != null) && block.getVisible()) block.draw(g);
		if((star != null) && star.getVisible()) star.draw(g);

	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		LabFiveGame game = new LabFiveGame();
		sm.LoadMusic("music", "mario_09.wav");
		sm.PlayMusic();
		sm.LoadSoundEffect("test", "Mario_Jumping-Mike_Koenig-989896458.wav");
		game.start();
	}
}
