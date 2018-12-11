package edu.virginia.mvp;

import edu.virginia.engine.display.*;
import edu.virginia.engine.util.SoundManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class LevelOne extends Game{
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

	//setup toolbag
	Sprite toolbox = new Sprite("toolbox", "objects/toolbox.png");
	//wire in toolbox
	private String[] wireFiles = {"objects/wire.png", "objects/wire_select.png"};
	private ArrayList<String> wirefilenames = new ArrayList<>(Arrays.asList(wireFiles));
	MultiModeSprite wire = new MultiModeSprite("wire", wirefilenames );
	ArrayList<ArrayList<Sprite>> wireSegments = new ArrayList<>();

	//setup character
	private String[] phil_files = {"jump_character/Charac_F-F2-Jump_0.png", "jump_character/Charac_F-F2-Jump_1.png", "jump_character/Charac_F-F2-Jump_2.png", "jump_character/Charac_F-F2-Jump_3.png", "jump_character/Charac_F-F2-Jump_4.png", "jump_character/Charac_F-F2-Jump_5.png", "jump_character/Charac_F-F2-Jump_6.png", "jump_character/Charac_F-F2-Jump_7.png", "jump_character/Charac_F-F2-Jump_8.png", "jump_character/Charac_F-F2-Jump_9.png", "jump_character/Charac_F-F2-Jump_10.png", "jump_character/Charac_F-F2-Jump_11.png", "jump_character/Charac_F-F2-Jump_12.png", "jump_character/Charac_F-F2-Jump_13.png", "jump_character/Charac_F-F2-Jump_14.png", "jump_character/Charac_F-F2-Jump_15.png", "jump_character/Charac_F-F2-Jump_16.png", "jump_character/Charac_F-F2-Jump_17.png", "jump_character/Charac_F-F2-Jump_18.png", "jump_character/Charac_F-F2-Jump_19.png", "jump_character/Charac_F-F2-Jump_20.png", "jump_character/Charac_F-F2-Jump_21.png", "jump_character/Charac_F-F2-Jump_22.png", "jump_character/Charac_F-F2-Jump_23.png", "jump_character/Charac_F-F2-Jump_24.png", "jump_character/Charac_F-F2-Jump_25.png", "jump_character/Charac_F-F2-Jump_26.png", "jump_character/Charac_F-F2-Jump_27.png", "jump_character/Charac_F-F2-Jump_28.png", "jump_character/Charac_F-F2-Jump_29.png",  "jump_character/Charac_F-F2-Jump_30.png", "idle_character/Charac_F-F2-idle_0.png", "idle_character/Charac_F-F2-idle_1.png", "idle_character/Charac_F-F2-idle_2.png", "idle_character/Charac_F-F2-idle_3.png", "idle_character/Charac_F-F2-idle_4.png", "idle_character/Charac_F-F2-idle_5.png", "idle_character/Charac_F-F2-idle_6.png", "idle_character/Charac_F-F2-idle_7.png", "idle_character/Charac_F-F2-idle_8.png", "idle_character/Charac_F-F2-idle_9.png", "idle_character/Charac_F-F2-idle_10.png", "idle_character/Charac_F-F2-idle_11.png", "idle_character/Charac_F-F2-idle_12.png", "idle_character/Charac_F-F2-idle_13.png", "idle_character/Charac_F-F2-idle_14.png", "idle_character/Charac_F-F2-idle_15.png", "idle_character/Charac_F-F2-idle_16.png", "idle_character/Charac_F-F2-idle_17.png", "idle_character/Charac_F-F2-idle_18.png", "idle_character/Charac_F-F2-idle_19.png",  "idle_character/Charac_F-F2-idle_20.png", "idle_character/Charac_F-F2-idle_21.png", "idle_character/Charac_F-F2-idle_22.png", "idle_character/Charac_F-F2-idle_23.png", "idle_character/Charac_F-F2-idle_24.png", "idle_character/Charac_F-F2-idle_25.png", "idle_character/Charac_F-F2-idle_26.png", "idle_character/Charac_F-F2-idle_27.png", "idle_character/Charac_F-F2-idle_28.png", "idle_character/Charac_F-F2-idle_29.png",  "idle_character/Charac_F-F2-idle_30.png"};
	private ArrayList<String> phil_filenames = new ArrayList<>(Arrays.asList(phil_files));
	AnimatedSprite phil = new AnimatedSprite("phil", phil_filenames);

	Sprite speech1 = new Sprite("speech1", "objects/speech1.png");
	Sprite speech2 = new Sprite("speech2", "objects/speech2.png");

	Sprite title = new Sprite("title", "level1.png");

	private boolean playing;
	private boolean started = true;
	private boolean won = false;

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
	public LevelOne() {
		super("Level 1", 1050, 800);
		workspace.addChild(bulb);
		workspace.addChild(battery);
		workspace.addChild(cursor);
		workspace.setPosition(new Point(220, 50));
		bulb.setPosition(new Point(840, 215));
		battery.setPosition(new Point(220, 273));

		cursor.setPosition(new Point(220, 50));
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

		phil.addChild(speech1);
		phil.addChild(speech2);
		phil.setPosition(new Point(20, 440));
		phil.setScaleX(0.3);
		phil.setScaleY(0.3);
		phil.animate("idle");

		speech1.setPosition(new Point(10, 360));
		speech2.setPosition(speech1.getPosition());
		speech2.setVisible(false);

		title.setPosition(new Point(5, 5));

		for(int i = 0; i<9; i++){
			wireSegments.add(i, new ArrayList<Sprite>());
			for(int j = 0; j<6; j++) {
				Sprite seg = new Sprite("seg" + i + j, "objects/segment.png");
				workspace.addChild(seg);
				seg.setPosition(new Point(220 +(89*i), 90 + 90*j));
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
		if(match) won = true;
		//developer shortcut to complete the level
		if (pressedKeys.contains(KeyEvent.VK_Q) && pressedKeys.contains(KeyEvent.VK_SLASH)) {
			won = true;
		}
		if(!won && playing) {
			if (pressedKeys.contains(KeyEvent.VK_SPACE)) {
				if (toolSelected != null && toolSelected.compareTo("wire") == 0) {
					int gridX = (cursor.getPosition().x - cursor.getParent().getPosition().x) / 90;
					int gridY = (cursor.getPosition().y - cursor.getParent().getPosition().y) / 90;
					wireSegments.get(gridX).get(gridY).setVisible(true);
					wirePositions[gridX][gridY] = 1;
				}
			}
			if (pressedKeys.contains(KeyEvent.VK_BACK_SPACE) || pressedKeys.contains(KeyEvent.VK_DELETE)) {
				int gridX = (cursor.getPosition().x - cursor.getParent().getPosition().x) / 90;
				int gridY = (cursor.getPosition().y - cursor.getParent().getPosition().y) / 90;
				wireSegments.get(gridX).get(gridY).setVisible(false);
				wirePositions[gridX][gridY] = 0;
			}
			if (workspace != null) {
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

		}
		if(!playing){
			if(pressedKeys.contains(KeyEvent.VK_ENTER)) {
				//moving to next level
				LevelTwo levelTwo  = new LevelTwo();
				levelTwo.start();
				this.closeGame();
			}
		}
		if(phil != null) {
			phil.update(pressedKeys);
		}
	}

	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	@Override
	public void draw(Graphics g){
		super.draw(g);
		if(won == true && started) {
			g.drawString("Press \"Enter\" to move" , 20,80);
			g.drawString("on to the next level" , 20,100);

			if(playing) {
				phil.stopAnimation();
				phil.setPosition(new Point(phil.getPosition().x, phil.getPosition().y - 67));
				phil.animate("jump");
				speech1.setVisible(false);
				speech2.setVisible(true);
				sm.PlaySoundEffect("won");
			}
			bulb.nextModeStatic();
			playing = false;
		}
		/* check for null in case a frame gets thrown in before initialized */
		if(started) {
			if ((workspace != null) && workspace.getVisible()) workspace.draw(g);
			if ((toolbox != null) && toolbox.getVisible()) toolbox.draw(g);
			if ((phil != null) && phil.getVisible()) phil.draw(g);
			if ((title != null) && title.getVisible()) title.draw(g);
		}

	}
}
