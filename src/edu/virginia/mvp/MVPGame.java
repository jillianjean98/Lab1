package edu.virginia.mvp;

import edu.virginia.engine.display.*;
import edu.virginia.engine.util.SoundManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class MVPGame extends Game{
	//set background
	public Sprite workspace = new Sprite("workspace", "objects/grid.jpg");

	//setup lightbulb
	private String[] bulbFiles = {"objects/bulb_off.png", "objects/bulb_on.png"};
	private ArrayList<String> filenames = new ArrayList<>(Arrays.asList(bulbFiles));
	MultiModeSprite bulb = new MultiModeSprite("bulb",filenames);
	//setup battery
	Sprite battery = new Sprite("block", "objects/battery.png");
	//setup cursor
	Sprite cursor = new Sprite("cursor", "objects/cursor.png");

	Sprite toolbox = new Sprite("toolbox", "objects/toolbox.png");

	private String[] wireFiles = {"objects/wire.png", "objects/wire_select.png"};
	private ArrayList<String> wirefilenames = new ArrayList<>(Arrays.asList(wireFiles));
	MultiModeSprite wire = new MultiModeSprite("wire", wirefilenames );

	int score = 1000;
	boolean won = false;
	boolean colliding = false;

	private Sprite toolSelected = null;
	//public static SoundManager sm = new SoundManager();
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public MVPGame() {
		super("Lab Five Test Game", 1000, 800);
		workspace.addChild(bulb);
		workspace.addChild(battery);
		workspace.addChild(cursor);
		workspace.setPosition(new Point(150, 50));
		bulb.setPosition(new Point(770, 215));
		battery.setPosition(new Point(150, 270));

		cursor.setPosition(new Point(150, 50));
		battery.setScaleX(0.7);
		battery.setScaleY(0.7);
		bulb.setScaleX(0.3);
		bulb.setScaleY(0.3);
		cursor.setScaleX(0.8);
		cursor.setScaleY(0.8);

		toolbox.addChild(wire);
		toolbox.setPosition(new Point(350, 575));
		toolbox.setScaleX(1.3);
		toolbox.setScaleY(0.55);
		wire.setPosition(new Point(590, 626));
		wire.setScaleX(0.3);
		wire.setScaleY(0.3);
		/*bulb.setHitbox(new Rectangle(mario.getPosition().x, mario.getPosition().y,
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
		*/
	}

	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		super.update(pressedKeys);
		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		if(workspace != null && !won) {
			//sm.PlayMusic();

			/*if(mario.collidesWith(block)) {
				if(!colliding) {
					if(!won)
						score = score -100;
					sm.PlaySoundEffect("test");
					colliding = true;
				}
				if(!mario.onBaseline()){
					mario.setPosition(new Point(mario.getPosition().x,
							mario.getPosition().y - 40));
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



			if (pressedKeys.contains(KeyEvent.VK_S)) {
				mario.setScaleX(mario.getScaleX() - 0.1);
				mario.setScaleY(mario.getScaleY() - 0.1);
			}

			if (pressedKeys.contains(KeyEvent.VK_UP)) {
				if(colliding) {
					mario.setPosition(new Point(mario.getPosition().x,
							mario.getPosition().y + 40));
					//colliding = false;
				} else {
					mario.setPosition(new Point(mario.getPosition().x,
							mario.getPosition().y - 5));
				}
			}
			if (pressedKeys.contains(KeyEvent.VK_DOWN)) {

				if(!mario.onBaseline()) {
					mario.setPosition(new Point(mario.getPosition().x,
							mario.getPosition().y + 5));
				}
			}
			if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
				if(colliding) {
					mario.setPosition(new Point(mario.getPosition().x + 40,
							mario.getPosition().y));
					colliding =false;
				} else {
					mario.setPosition(new Point(mario.getPosition().x - 5,
							mario.getPosition().y));
				}
			}
			if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
				if(colliding){
					mario.setPosition(new Point(mario.getPosition().x - 40,
							mario.getPosition().y));
					colliding = false;
				} else {
					mario.setPosition(new Point(mario.getPosition().x + 5,
							mario.getPosition().y));
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

			mario.setHitbox(new Rectangle(mario.getPosition().x, mario.getPosition().y,
					(int)(mario.getUnscaledWidth()*mario.getScaleX()), (int)(mario.getUnscaledHeight()*mario.getScaleY())));
					*/

			if (pressedKeys.contains(KeyEvent.VK_UP)) {
				if(cursor.inParentYTop()) {
					cursor.setPosition(new Point(cursor.getPosition().x,
							cursor.getPosition().y - 5));
				}
			}
			if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
				if(cursor.inParentYBottom()) {
					cursor.setPosition(new Point(cursor.getPosition().x,
							cursor.getPosition().y + 5));
				}
			}
			if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
				if(cursor.inParentXLeft()) {
					cursor.setPosition(new Point(cursor.getPosition().x - 5,
							cursor.getPosition().y));
				}
			}
			if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
				if(cursor.inParentXRight()) {
					cursor.setPosition(new Point(cursor.getPosition().x + 5,
							cursor.getPosition().y));
				}
			}
			workspace.update(pressedKeys);
		}

		if(toolbox != null && !won) {
			if (pressedKeys.contains(KeyEvent.VK_W)) {
				wire.nextMode();
				if(!wire.isSwitched()) {
					if(toolSelected != null && toolSelected.getId().compareTo(wire.getId()) == 0) {
						toolSelected = null;
					} else {
						toolSelected = wire;
					}
				}
				wire.setSwitched(true);
			} else {
				wire.setSwitched(false);
			}
			toolbox.update(pressedKeys);
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
		if((workspace != null) && workspace.getVisible()) workspace.draw(g);
		if((toolbox != null) && toolbox.getVisible()) toolbox.draw(g);

	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		MVPGame game = new MVPGame();
		//sm.LoadMusic("music", "mario_09.wav");
		//sm.PlayMusic();
		//sm.LoadSoundEffect("test", "Mario_Jumping-Mike_Koenig-989896458.wav");
		game.start();
	}
}