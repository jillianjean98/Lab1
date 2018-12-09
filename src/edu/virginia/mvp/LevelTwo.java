package edu.virginia.mvp;

import edu.virginia.engine.display.AnimatedSprite;
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
	public boolean levelComplete = false;
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
	//adding lvl 2 objs to toolbox
	private String[] nailFiles = {"objects/nail_label.png", "objects/nail_label_select.png"};
	private ArrayList<String> nailfilenames = new ArrayList<>(Arrays.asList(nailFiles));
	MultiModeSprite nail = new MultiModeSprite("nail", nailfilenames );

	private String[] logFiles = {"objects/log_label.png", "objects/log_label_select.png"};
	private ArrayList<String> logfilenames = new ArrayList<>(Arrays.asList(logFiles));
	MultiModeSprite log = new MultiModeSprite("log", logfilenames );

	private String[] panFiles = {"objects/pan_label.png", "objects/pan_label_select.png"};
	private ArrayList<String> panfilenames = new ArrayList<>(Arrays.asList(panFiles));
	MultiModeSprite pan = new MultiModeSprite("pan", panfilenames );

	private String[] shoeFiles = {"objects/shoe_label.png", "objects/shoe_label_select.png"};
	private ArrayList<String> shoefilenames = new ArrayList<>(Arrays.asList(shoeFiles));
	MultiModeSprite shoe = new MultiModeSprite("shoe", shoefilenames );

	ArrayList<ArrayList<Sprite>> wireSegments = new ArrayList<>();

	//setup character
	private String[] phil_files = {"jump_character2/Charac_F-F1-Jump_0.png", "jump_character2/Charac_F-F1-Jump_1.png", "jump_character2/Charac_F-F1-Jump_2.png", "jump_character2/Charac_F-F1-Jump_3.png", "jump_character2/Charac_F-F1-Jump_4.png", "jump_character2/Charac_F-F1-Jump_5.png", "jump_character2/Charac_F-F1-Jump_6.png", "jump_character2/Charac_F-F1-Jump_7.png", "jump_character2/Charac_F-F1-Jump_8.png", "jump_character2/Charac_F-F1-Jump_9.png", "jump_character2/Charac_F-F1-Jump_10.png", "jump_character2/Charac_F-F1-Jump_11.png", "jump_character2/Charac_F-F1-Jump_12.png", "jump_character2/Charac_F-F1-Jump_13.png", "jump_character2/Charac_F-F1-Jump_14.png", "jump_character2/Charac_F-F1-Jump_15.png", "jump_character2/Charac_F-F1-Jump_16.png", "jump_character2/Charac_F-F1-Jump_17.png", "jump_character2/Charac_F-F1-Jump_18.png", "jump_character2/Charac_F-F1-Jump_19.png", "jump_character2/Charac_F-F1-Jump_20.png", "jump_character2/Charac_F-F1-Jump_21.png", "jump_character2/Charac_F-F1-Jump_22.png", "jump_character2/Charac_F-F1-Jump_23.png", "jump_character2/Charac_F-F1-Jump_24.png", "jump_character2/Charac_F-F1-Jump_25.png", "jump_character2/Charac_F-F1-Jump_26.png", "jump_character2/Charac_F-F1-Jump_27.png", "jump_character2/Charac_F-F1-Jump_28.png", "jump_character2/Charac_F-F1-Jump_29.png",  "jump_character2/Charac_F-F1-Jump_30.png", "idle_character2/Charac_F-F1-idle_0.png", "idle_character2/Charac_F-F1-idle_1.png", "idle_character2/Charac_F-F1-idle_2.png", "idle_character2/Charac_F-F1-idle_3.png", "idle_character2/Charac_F-F1-idle_4.png", "idle_character2/Charac_F-F1-idle_5.png", "idle_character2/Charac_F-F1-idle_6.png", "idle_character2/Charac_F-F1-idle_7.png", "idle_character2/Charac_F-F1-idle_8.png", "idle_character2/Charac_F-F1-idle_9.png", "idle_character2/Charac_F-F1-idle_10.png", "idle_character2/Charac_F-F1-idle_11.png", "idle_character2/Charac_F-F1-idle_12.png", "idle_character2/Charac_F-F1-idle_13.png", "idle_character2/Charac_F-F1-idle_14.png", "idle_character2/Charac_F-F1-idle_15.png", "idle_character2/Charac_F-F1-idle_16.png", "idle_character2/Charac_F-F1-idle_17.png", "idle_character2/Charac_F-F1-idle_18.png", "idle_character2/Charac_F-F1-idle_19.png",  "idle_character2/Charac_F-F1-idle_20.png", "idle_character2/Charac_F-F1-idle_21.png", "idle_character2/Charac_F-F1-idle_22.png", "idle_character2/Charac_F-F1-idle_23.png", "idle_character2/Charac_F-F1-idle_24.png", "idle_character2/Charac_F-F1-idle_25.png", "idle_character2/Charac_F-F1-idle_26.png", "idle_character2/Charac_F-F1-idle_27.png", "idle_character2/Charac_F-F1-idle_28.png", "idle_character2/Charac_F-F1-idle_29.png",  "idle_character2/Charac_F-F1-idle_30.png"};
	private ArrayList<String> phil_filenames = new ArrayList<>(Arrays.asList(phil_files));
	AnimatedSprite phil = new AnimatedSprite("phil", phil_filenames);

	Sprite speech1 = new Sprite("speech1", "objects/speech1.png");
	Sprite speech2 = new Sprite("speech2", "objects/speech2.png");

	Sprite title = new Sprite("title", "level2.png");

	Sprite panG = new Sprite("pan", "objects/pan.png");
	Sprite logG = new Sprite("log", "objects/log.png");
	Sprite nailG = new Sprite("nail", "objects/nail.png");
	Sprite shoeG = new Sprite("shoe", "objects/shoe.png");

	boolean playing = false;
	boolean started = true;
	boolean won = false;

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
	private int[][] logPositions = new int[9][6];
	private int[][] nailPositions = new int[9][6];
	private int[][] panPositions = new int[9][6];
	private int[][] shoePositions = new int[9][6];
	public static SoundManager sm = new SoundManager();
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LevelTwo() {
		super("Level 2", 1050, 800);
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

		toolbox.addChild(log);
		toolbox.addChild(nail);
		toolbox.addChild(pan);
		toolbox.addChild(shoe);
		toolbox.setPosition(new Point(140, 575));
		toolbox.setScaleX(1.95);
		toolbox.setScaleY(0.55);
		log.setPosition(new Point(250, 660));
		log.setScaleX(0.2);
		log.setScaleY(0.2);
		System.out.println(log.isSwitched());
		nail.setPosition(new Point(440, 660));
		nail.setScaleX(0.2);
		nail.setScaleY(0.2);
		shoe.setPosition(new Point(627, 660));
		shoe.setScaleX(0.2);
		shoe.setScaleY(0.2);
		pan.setPosition(new Point(820, 660));
		pan.setScaleX(0.2);
		pan.setScaleY(0.2);

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

		//level setup wire
		for(int i = 0; i < solution.length; i++){
			for(int j = 0; j < solution[0].length; j++){
				if(solution[i][j] == 1){
					wireSegments.get(i).get(j).setVisible(true);
				}
			}
		}

		wireSegments.get(4).get(2).setVisible(false);

		//put four items in place, but invisible

		workspace.addChild(panG);
		workspace.addChild(logG);
		workspace.addChild(shoeG);
		workspace.addChild(nailG);
		panG.setPosition(new Point(220 + (89 * 4), 70 + 90 * 2));
		logG.setPosition(new Point(220 + (89 * 4), 70 + 90 * 2));
		shoeG.setPosition(new Point(220 + (89 * 4), 70 + 90 * 2));
		nailG.setPosition(new Point(220 + (89 * 4), 70 + 90 * 2));
		logG.setScaleX(0.1);
		logG.setScaleY(0.1);
		logG.setVisible(false);
		panG.setScaleX(0.1);
		panG.setScaleY(0.1);
		panG.setVisible(false);
		shoeG.setScaleX(0.1);
		shoeG.setScaleY(0.1);
		shoeG.setVisible(false);
		nailG.setScaleX(0.1);
		nailG.setScaleY(0.1);
		nailG.setVisible(false);

		playing = true;
		sm.LoadSoundEffect("won", "ting.wav");
	}

	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		boolean match = Arrays.deepEquals(wirePositions, solution);
		if(panG.getVisible() || nailG.getVisible()){
			this.won = true;
		}
		//this.won = match;
		//this.won = false;
		if (pressedKeys.contains(KeyEvent.VK_B)) {
			won = true;
		}
		if (!won && playing) {
			if (pressedKeys.contains(KeyEvent.VK_SPACE)) {
				if (toolSelected != null && toolSelected.compareTo("log") == 0) {
					int gridX = (cursor.getPosition().x - cursor.getParent().getPosition().x) / 90;
					int gridY = (cursor.getPosition().y - cursor.getParent().getPosition().y) / 90;
					if(gridX == 4 && gridY == 2){
						if (!logG.getVisible()){
							if(shoeG.getVisible()){
								shoeG.setVisible(false);
							}
							logG.setVisible(true);
						} else {
							logG.setVisible(false);
						}
						try {
							Thread.sleep(200);
						} catch (InterruptedException ex) {
							Thread.currentThread().interrupt();
						}
					}
				}

				if (toolSelected != null && toolSelected.compareTo("shoe") == 0) {
					int gridX = (cursor.getPosition().x - cursor.getParent().getPosition().x) / 90;
					int gridY = (cursor.getPosition().y - cursor.getParent().getPosition().y) / 90;
					System.out.println("what");
					if(gridX == 4 && gridY == 2) {
						if (!shoeG.getVisible()) {
							if(logG.getVisible()){
								logG.setVisible(false);
							}
							shoeG.setVisible(true);
						} else {
							shoeG.setVisible(false);
						}
						try {
							Thread.sleep(200);
						} catch (InterruptedException ex) {
							Thread.currentThread().interrupt();
						}
					}
				}

				if (toolSelected != null && toolSelected.compareTo("nail") == 0) {
					int gridX = (cursor.getPosition().x - cursor.getParent().getPosition().x) / 90;
					int gridY = (cursor.getPosition().y - cursor.getParent().getPosition().y) / 90;
					if(gridX == 4 && gridY == 2) {
						if (!nailG.getVisible()) {
							if(logG.getVisible()){
								logG.setVisible(false);
							}
							if(shoeG.getVisible()){
								shoeG.setVisible(false);
							}
							nailG.setVisible(true);
						} else {
							nailG.setVisible(false);
						}
						try {
							Thread.sleep(200);
						} catch (InterruptedException ex) {
							Thread.currentThread().interrupt();
						}
					}
				}

				if (toolSelected != null && toolSelected.compareTo("pan") == 0) {
					int gridX = (cursor.getPosition().x - cursor.getParent().getPosition().x) / 90;
					int gridY = (cursor.getPosition().y - cursor.getParent().getPosition().y) / 90;
					if(gridX == 4 && gridY == 2) {
						if (!panG.getVisible()) {
							if(logG.getVisible()){
								logG.setVisible(false);
							}
							if(shoeG.getVisible()){
								shoeG.setVisible(false);
							}
							panG.setVisible(true);
						} else {
							panG.setVisible(false);
						}
						try {
							Thread.sleep(200);
						} catch (InterruptedException ex) {
							Thread.currentThread().interrupt();
						}
					}
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

				if (pressedKeys.contains(KeyEvent.VK_L)) {
					log.nextMode();
					if (!log.isSwitched()) {
						if (toolSelected != null && toolSelected.compareTo(log.getId()) == 0) {
							toolSelected = null;
						} else {
							if (toolSelected != null && toolSelected.compareTo(pan.getId()) == 0) {
								pan.nextMode();
							}
							if (toolSelected != null && toolSelected.compareTo(nail.getId()) == 0) {
								nail.nextMode();
							}
							if (toolSelected != null && toolSelected.compareTo(shoe.getId()) == 0) {
								shoe.nextMode();
							}
							toolSelected = log.getId();
						}
					}
					log.setSwitched(true);
				} else {
					log.setSwitched(false);
				}


				if (pressedKeys.contains(KeyEvent.VK_P)) {
					pan.nextMode();
					if (!pan.isSwitched()) {
						if (toolSelected != null && toolSelected.compareTo(pan.getId()) == 0) {
							toolSelected = null;
						} else {
							if (toolSelected != null && toolSelected.compareTo(log.getId()) == 0) {
								log.nextMode();
							}
							if (toolSelected != null && toolSelected.compareTo(nail.getId()) == 0) {
								nail.nextMode();
							}
							if (toolSelected != null && toolSelected.compareTo(shoe.getId()) == 0) {
								shoe.nextMode();
							}
							toolSelected = pan.getId();
						}
					}
					pan.setSwitched(true);
				} else {
					pan.setSwitched(false);
				}

				if (pressedKeys.contains(KeyEvent.VK_N)) {
					nail.nextMode();
					if (!nail.isSwitched()) {
						if (toolSelected != null && toolSelected.compareTo(nail.getId()) == 0) {
							toolSelected = null;
						} else {
							if (toolSelected != null && toolSelected.compareTo(log.getId()) == 0) {
								log.nextMode();
							}
							if (toolSelected != null && toolSelected.compareTo(pan.getId()) == 0) {
								pan.nextMode();
							}
							if (toolSelected != null && toolSelected.compareTo(shoe.getId()) == 0) {
								shoe.nextMode();
							}
							toolSelected = nail.getId();
						}
					}
					nail.setSwitched(true);
				} else {
					nail.setSwitched(false);
				}

				if (pressedKeys.contains(KeyEvent.VK_S)) {
					shoe.nextMode();
					if (!shoe.isSwitched()) {
						if (toolSelected != null && toolSelected.compareTo(shoe.getId()) == 0) {
							toolSelected = null;
						} else {
							if (toolSelected != null && toolSelected.compareTo(log.getId()) == 0) {
								log.nextMode();
							}
							if (toolSelected != null && toolSelected.compareTo(nail.getId()) == 0) {
								nail.nextMode();
							}
							if (toolSelected != null && toolSelected.compareTo(pan.getId()) == 0) {
								pan.nextMode();
							}
							toolSelected = shoe.getId();
						}
					}
					shoe.setSwitched(true);
				} else {
					shoe.setSwitched(false);
				}

				toolbox.update(pressedKeys);
			}

			}
			if (!playing) {
				if (pressedKeys.contains(KeyEvent.VK_ENTER)) {
					System.out.println("moving to next level");
					//LevelTwo levelTwo  = new LevelTwo();
					//levelTwo.start();
					this.closeGame();
					//end the game
					return;
				}
			}
			if (phil != null) {
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
		if(won == true && started){
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
		/* Same, just check for null in case a frame gets thrown in before Mario is initialized */
		if((workspace != null) && workspace.getVisible()) workspace.draw(g);
		if((toolbox != null) && toolbox.getVisible()) toolbox.draw(g);
		if((phil != null) && phil.getVisible()) phil.draw(g);
		if((title != null) && title.getVisible()) title.draw(g);

	}
}
