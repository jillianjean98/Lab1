package edu.virginia.mvp;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.MultiModeSprite;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.SoundManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LevelTwo extends Game{
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

	ArrayList<ArrayList<Sprite>> wireSegments = new ArrayList<>();
	boolean playing = false;
	boolean won = false;
	boolean soundPlaying = false;

	private String toolSelected = null;

	private int[][] solution = {{0,0,0,0,0,0},
							{0,0,1,0,1,0},
							{0,0,1,0,1,0},
							{0,0,1,0,1,0},
							{0,0,1,0,1,0},
							{0,0,1,0,1,0},
							{0,0,1,0,1,0},
							{0,0,0,0,1,0},
							{0,0,0,0,0,0}};

	private int[][] wirePositions = new int[9][6];
	public static SoundManager sm = new SoundManager();
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LevelTwo() {
		super("Level 2", 1050, 800);
		workspace.addChild(bulb);
		workspace.addChild(battery);
		workspace.addChild(cursor);
		workspace.setPosition(new Point(150, 50));
		bulb.setPosition(new Point(770, 215));
		battery.setPosition(new Point(150, 273));

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
		for(int i = 0; i<9; i++){
			wireSegments.add(i, new ArrayList<Sprite>());
			for(int j = 0; j<6; j++) {
				Sprite seg = new Sprite("seg" + i + j, "objects/segment.png");
				workspace.addChild(seg);
				seg.setPosition(new Point(150 +(89*i), 90 + 90*j));
				seg.setVisible(false);
				wireSegments.get(i).add(j, seg);
			}

		}
		playing = true;
		sm.LoadSoundEffect("won", "ting.wav");
	}

	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		super.update(pressedKeys);
		boolean match = Arrays.deepEquals(wirePositions, solution);
		this.won = match;
		if(!won) {
			if (pressedKeys.contains(KeyEvent.VK_SPACE)) {
				if (toolSelected != null && toolSelected.compareTo("wire") == 0) {
					int gridX = (cursor.getPosition().x - cursor.getParent().getPosition().x) / 90;
					int gridY = (cursor.getPosition().y - cursor.getParent().getPosition().y) / 90;
					wireSegments.get(gridX).get(gridY).setVisible(true);
					Point cursorGridPos = new Point(((cursor.getPosition().x - cursor.getParent().getPosition().x) / 90),
							((cursor.getPosition().y - cursor.getParent().getPosition().y) / 90));
					System.out.println("space key: cursor at " + cursorGridPos);
					wirePositions[gridX][gridY] = 1;
				}
			}
			if (pressedKeys.contains(KeyEvent.VK_BACK_SPACE) || pressedKeys.contains(KeyEvent.VK_DELETE)) {
				int gridX = (cursor.getPosition().x - cursor.getParent().getPosition().x) / 90;
				int gridY = (cursor.getPosition().y - cursor.getParent().getPosition().y) / 90;
				wireSegments.get(gridX).get(gridY).setVisible(false);
				Point cursorGridPos = new Point(((cursor.getPosition().x - cursor.getParent().getPosition().x) / 90),
						((cursor.getPosition().y - cursor.getParent().getPosition().y) / 90));
				System.out.println("deleting wire at " + cursorGridPos);
				wirePositions[gridX][gridY] = 0;
			}
			/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
			if (workspace != null && !won) {
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
					if (cursor.inParentYTop()) {
						cursor.setPosition(new Point(cursor.getPosition().x,
								cursor.getPosition().y - 5));
					}
				}
				if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
					if (cursor.inParentYBottom()) {
						cursor.setPosition(new Point(cursor.getPosition().x,
								cursor.getPosition().y + 5));
					}
				}
				if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
					if (cursor.inParentXLeft()) {
						cursor.setPosition(new Point(cursor.getPosition().x - 5,
								cursor.getPosition().y));
					}
				}
				if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
					if (cursor.inParentXRight()) {
						cursor.setPosition(new Point(cursor.getPosition().x + 5,
								cursor.getPosition().y));
					}
				}
				workspace.update(pressedKeys);
			}

			if (toolbox != null) {
				if (pressedKeys.contains(KeyEvent.VK_W)) {
					wire.nextMode();
					if (!wire.isSwitched()) {
						if (toolSelected != null && toolSelected.compareTo(wire.getId()) == 0) {
							toolSelected = null;
						} else {
							toolSelected = wire.getId();
						}
					}
					wire.setSwitched(true);
				} else {
					wire.setSwitched(false);
				}
				toolbox.update(pressedKeys);
			}
		} else {
			if(pressedKeys.contains(KeyEvent.VK_ENTER)) {
				System.out.println("enter key  pressed");
				this.closeGame();
			} else {
				System.out.println("game over, enter not pressed");
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
		if(won == true && playing) {
			g.drawString("Congratulations! You won! " , 400,25);
			g.drawString("Press \"Enter\" to move on to the next level " , 400,40);
			bulb.nextModeStatic();
			if(!this.soundPlaying) {
				sm.PlaySoundEffect("won");
				this.soundPlaying=true;
			}
			//this.stop();
			//this.closeGame();
		}
		/* Same, just check for null in case a frame gets thrown in before Mario is initialized */
		if((workspace != null) && workspace.getVisible()) workspace.draw(g);
		if((toolbox != null) && toolbox.getVisible()) toolbox.draw(g);

	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 *
	public static void main(String[] args) {
		LevelOne game = new LevelOne();
		//sm.LoadMusic("music", "mario_09.wav");
		//sm.PlayMusic();
		sm.LoadSoundEffect("won", "ting.wav");
		game.start();
	}
	 */
}
