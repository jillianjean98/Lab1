package edu.virginia.mvp;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.MultiModeSprite;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class LevelThree extends Game{
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
	//shoe in toolbox
	private String[] shoeFiles = {"objects/shoe_label.png", "objects/shoe_label_select.png"};
	private ArrayList<String> shoefilenames = new ArrayList<>(Arrays.asList(shoeFiles));
	MultiModeSprite shoe = new MultiModeSprite("shoe", shoefilenames );
	//log in toolbox
	private String[] logFiles = {"objects/log_label.png", "objects/log_label_select.png"};
	private ArrayList<String> logfilenames = new ArrayList<>(Arrays.asList(logFiles));
	MultiModeSprite log = new MultiModeSprite("log", logfilenames );
	//pan in toolbox
	private String[] panFiles = {"objects/pan_label.png", "objects/pan_label_select.png"};
	private ArrayList<String> panfilenames = new ArrayList<>(Arrays.asList(panFiles));
	MultiModeSprite pan = new MultiModeSprite("pan", panfilenames );
	//pan in toolbox
	private String[] nailFiles = {"objects/nail_label.png", "objects/nail_label_select.png"};
	private ArrayList<String> nailfilenames = new ArrayList<>(Arrays.asList(nailFiles));
	MultiModeSprite nail = new MultiModeSprite("nail", nailfilenames );

	Sprite panG = new Sprite("pan", "objects/pan.png");
	Sprite logG = new Sprite("log", "objects/log.png");
	Sprite nailG = new Sprite("nail", "objects/nail.png");
	Sprite shoeG = new Sprite("shoe", "objects/shoe.png");

	ArrayList<ArrayList<Sprite>> wireSegments = new ArrayList<>();

	//setup character
	private String[] phil_files = {"jump_character3/Charac_F-F03-Jump_0.png", "jump_character3/Charac_F-F03-Jump_1.png", "jump_character3/Charac_F-F03-Jump_2.png", "jump_character3/Charac_F-F03-Jump_3.png", "jump_character3/Charac_F-F03-Jump_4.png", "jump_character3/Charac_F-F03-Jump_5.png", "jump_character3/Charac_F-F03-Jump_6.png", "jump_character3/Charac_F-F03-Jump_7.png", "jump_character3/Charac_F-F03-Jump_8.png", "jump_character3/Charac_F-F03-Jump_9.png", "jump_character3/Charac_F-F03-Jump_10.png", "jump_character3/Charac_F-F03-Jump_11.png", "jump_character3/Charac_F-F03-Jump_12.png", "jump_character3/Charac_F-F03-Jump_13.png", "jump_character3/Charac_F-F03-Jump_14.png", "jump_character3/Charac_F-F03-Jump_15.png", "jump_character3/Charac_F-F03-Jump_16.png", "jump_character3/Charac_F-F03-Jump_17.png", "jump_character3/Charac_F-F03-Jump_18.png", "jump_character3/Charac_F-F03-Jump_19.png", "jump_character3/Charac_F-F03-Jump_20.png", "jump_character3/Charac_F-F03-Jump_21.png", "jump_character3/Charac_F-F03-Jump_22.png", "jump_character3/Charac_F-F03-Jump_23.png", "jump_character3/Charac_F-F03-Jump_24.png", "jump_character3/Charac_F-F03-Jump_25.png", "jump_character3/Charac_F-F03-Jump_26.png", "jump_character3/Charac_F-F03-Jump_27.png", "jump_character3/Charac_F-F03-Jump_28.png", "jump_character3/Charac_F-F03-Jump_29.png",  "jump_character3/Charac_F-F03-Jump_30.png", "idle_character3/Charac_F-F03-idle_0.png", "idle_character3/Charac_F-F03-idle_1.png", "idle_character3/Charac_F-F03-idle_2.png", "idle_character3/Charac_F-F03-idle_3.png", "idle_character3/Charac_F-F03-idle_4.png", "idle_character3/Charac_F-F03-idle_5.png", "idle_character3/Charac_F-F03-idle_6.png", "idle_character3/Charac_F-F03-idle_7.png", "idle_character3/Charac_F-F03-idle_8.png", "idle_character3/Charac_F-F03-idle_9.png", "idle_character3/Charac_F-F03-idle_10.png", "idle_character3/Charac_F-F03-idle_11.png", "idle_character3/Charac_F-F03-idle_12.png", "idle_character3/Charac_F-F03-idle_13.png", "idle_character3/Charac_F-F03-idle_14.png", "idle_character3/Charac_F-F03-idle_15.png", "idle_character3/Charac_F-F03-idle_16.png", "idle_character3/Charac_F-F03-idle_17.png", "idle_character3/Charac_F-F03-idle_18.png", "idle_character3/Charac_F-F03-idle_19.png",  "idle_character3/Charac_F-F03-idle_20.png", "idle_character3/Charac_F-F03-idle_21.png", "idle_character3/Charac_F-F03-idle_22.png", "idle_character3/Charac_F-F03-idle_23.png", "idle_character3/Charac_F-F03-idle_24.png", "idle_character3/Charac_F-F03-idle_25.png", "idle_character3/Charac_F-F03-idle_26.png", "idle_character3/Charac_F-F03-idle_27.png", "idle_character3/Charac_F-F03-idle_28.png", "idle_character3/Charac_F-F03-idle_29.png",  "idle_character3/Charac_F-F03-idle_30.png"};
	private ArrayList<String> phil_filenames = new ArrayList<>(Arrays.asList(phil_files));
	AnimatedSprite phil = new AnimatedSprite("phil", phil_filenames);

	Sprite speech1a = new Sprite("speech1a", "objects/speech1_3a.png");
	Sprite speech1b = new Sprite("speech1b", "objects/speech1_3b.png");
	Sprite speech1c = new Sprite("speech1c", "objects/speech1_3c.png");
	Sprite speech2wrong = new Sprite("speech2wrong", "objects/speech2_3a.png");
	Sprite speech2right = new Sprite("speech2right", "objects/speech2_3b.png");

	Sprite speechWon = new Sprite("speechWon", "objects/speech2.png");

	JLabel question = new JLabel("Why isn't the bulb lighting up?", JLabel.CENTER);
	JLabel optionA = new JLabel("<html>A. The battery is out of power</html>", JLabel.LEFT);
	JLabel optionB = new JLabel("<html>B. Rope is an insulator</html>", JLabel.LEFT);
	JLabel optionC = new JLabel("<html>C. Rope is a conductor</html>", JLabel.LEFT);
	JLabel optionD = new JLabel("<html>D. The bulb is broken</html>", JLabel.LEFT);
	JLabel instruct = new JLabel("(Use letter keys to choose your answer)", JLabel.CENTER);
	Font font = new Font("Ayuthaya", Font.PLAIN, 16);

	Sprite title = new Sprite("title", "level3.png");

	private boolean playing;
	private boolean started = true;
	private boolean won = false;
	private boolean questionAnswered = false;
	private boolean tried[] = new boolean[4];
	private int attempts = 0;

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

	public static SoundManager sm = new SoundManager();
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LevelThree() {
		super("Level 3", 1050, 800);
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
		cursor.setVisible(false);

		toolbox.addChild(log);
		toolbox.setPosition(new Point(135, 575));
		toolbox.setScaleX(2.0);
		toolbox.setScaleY(0.55);
		log.setPosition(new Point(250, 630));
		log.setScaleX(0.2);
		log.setScaleY(0.2);
		toolbox.addChild(nail);
		nail.setPosition(new Point(820, 650));
		nail.setScaleX(0.2);
		nail.setScaleY(0.2);
		toolbox.addChild(shoe);
		shoe.setPosition(new Point(630, 630));
		shoe.setScaleX(0.2);
		shoe.setScaleY(0.2);
		toolbox.addChild(pan);
		pan.setPosition(new Point(440, 650));
		pan.setScaleX(0.2);
		pan.setScaleY(0.2);
		toolbox.setVisible(false);

		phil.addChild(speech1a);
		phil.addChild(speech1b);
		phil.addChild(speech1c);
		phil.addChild(speech2wrong);
		phil.addChild(speech2right);
		phil.addChild(speechWon);
		phil.setPosition(new Point(20, 440));
		phil.setScaleX(0.3);
		phil.setScaleY(0.3);
		phil.animate("idle");

		speech1c.setPosition(new Point(10, 350));
		speech1b.setPosition(new Point(10, 275));
		speech1a.setPosition(new Point(10, 200));
		speech2wrong.setPosition(speech1c.getPosition());
		speech2wrong.setVisible(false);
		speech2right.setPosition(new Point(2, 350));
		speech2right.setVisible(false);
		speechWon.setPosition(speech2right.getPosition());
		speechWon.setVisible(false);


		question.setSize(400, 100);
		question.setLocation(10, 580);
		question.setFont (font);
		instruct.setSize(400, 100);
		instruct.setLocation(10, 610);
		instruct.setFont (font.deriveFont (12.0f));
		optionA.setSize(400, 100);
		optionA.setLocation(500, 580);
		optionA.setFont (font);
		optionB.setSize(300, 30);
		optionB.setLocation(500, 650);
		optionB.setFont (font);
		optionC.setSize(400, 100);
		optionC.setLocation(500, 650);
		optionC.setFont (font);
		optionD.setSize(400, 100);
		optionD.setLocation(500, 685);
		optionD.setFont (font);

		getScenePanel().add(question);
		getScenePanel().add(instruct);
		getScenePanel().add(optionA);
		getScenePanel().add(optionB);
		getScenePanel().add(optionC);
		getScenePanel().add(optionD);

		title.setPosition(new Point(5, 5));

		for(int i = 0; i<9; i++){
			wireSegments.add(i, new ArrayList<Sprite>());
			for(int j = 0; j<6; j++) {
				Sprite seg;
				if(i == 4 && j == 2){
					seg = new Sprite("seg" + i + j, "objects/rope.png");
					seg.setScaleY(1.3);
					seg.setPosition(new Point(215 +(89*i), 50 + 90*j));
				} else {
					seg = new Sprite("seg" + i + j, "objects/segment.png");
					seg.setPosition(new Point(220 +(89*i), 90 + 90*j));
				}
				workspace.addChild(seg);

				if(solution[i][j] == 1){
					seg.setVisible(true);
				} else {
					seg.setVisible(false);
				}

				wireSegments.get(i).add(j, seg);
			}

		}
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
		playing = false;
		sm.LoadSoundEffect("won", "ting.wav");
	}

	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		if (workspace != null) {
			if ((panG != null && panG.getVisible()) || (nailG != null && nailG.getVisible())) {
				won = true;
			}
			//developer shortcut to complete the level
			if (pressedKeys.contains(KeyEvent.VK_Q) && pressedKeys.contains(KeyEvent.VK_SLASH)) {
				won = true;
			}
			if (!questionAnswered && !playing) {
				if (attempts < 2) {
					if (pressedKeys.contains(KeyEvent.VK_A)) {
						optionA.setText("<html><strike>A. The battery is out of power</strike></html>");
						speech1a.setVisible(false);
						speech1b.setVisible(false);
						speech1c.setVisible(false);
						speech2wrong.setVisible(true);
						if (!tried[0]) attempts++;
						tried[0] = true;

					}
					if (pressedKeys.contains(KeyEvent.VK_B)) {
						optionB.setBorder(BorderFactory.createLineBorder(Color.BLUE, 4));
						speech1a.setVisible(false);
						speech1b.setVisible(false);
						speech1c.setVisible(false);
						speech2wrong.setVisible(false);
						speech2right.setVisible(true);
						questionAnswered = true;
						question.setText("Correct! Now can you fix the circuit?");
						instruct.setText("Press enter to view your toolbox.");
					}
					if (pressedKeys.contains(KeyEvent.VK_C)) {
						optionC.setText("<html><strike>C. Rope is a conductor</strike></html>");
						speech1a.setVisible(false);
						speech1b.setVisible(false);
						speech1c.setVisible(false);
						speech2wrong.setVisible(true);
						if (!tried[2]) attempts++;
						tried[2] = true;
					}
					if (pressedKeys.contains(KeyEvent.VK_D)) {
						optionD.setText("<html><strike>D. The bulb is broken</strike></html>");
						speech1a.setVisible(false);
						speech1b.setVisible(false);
						speech1c.setVisible(false);
						speech2wrong.setVisible(true);
						if (!tried[3]) attempts++;
						tried[3] = true;
					}
				} else {
					getScenePanel().remove(optionA);
					getScenePanel().remove(optionB);
					getScenePanel().remove(optionC);
					getScenePanel().remove(optionD);
					question.setSize(800, 100);
					question.setText("It looks like you might want to go review conductors and insulators again!");
					instruct.setText("Press enter to go back and replay level 2.");
					if (pressedKeys.contains(KeyEvent.VK_ENTER)) {
						System.out.println("moving to previous level");
						LevelTwo levelTwo = new LevelTwo();
						levelTwo.start();
						this.closeGame();
					}
				}
			} else if(!won){
				if (pressedKeys.contains(KeyEvent.VK_ENTER)) {
					getScenePanel().remove(optionA);
					getScenePanel().remove(optionB);
					getScenePanel().remove(optionC);
					getScenePanel().remove(optionD);
					getScenePanel().remove(question);
					getScenePanel().remove(instruct);
					toolbox.setVisible(true);
					cursor.setVisible(true);
					playing = true;
				}
			}
			if (!won && playing) {
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
					if (pressedKeys.contains(KeyEvent.VK_BACK_SPACE) || pressedKeys.contains(KeyEvent.VK_DELETE)) {
						int gridX = (cursor.getPosition().x - cursor.getParent().getPosition().x) / 90;
						int gridY = (cursor.getPosition().y - cursor.getParent().getPosition().y) / 90;
						if (gridX == 4 && gridY == 2) {
							if (logG.getVisible()) {
								logG.setVisible(false);
							}
							if (shoeG.getVisible()) {
								shoeG.setVisible(false);
							}
							wireSegments.get(4).get(2).setVisible(false);
						}
					}
					if (pressedKeys.contains(KeyEvent.VK_SPACE)) {
						if(!wireSegments.get(4).get(2).getVisible()) {


							if (toolSelected != null && toolSelected.compareTo("log") == 0) {
								int gridX = (cursor.getPosition().x - cursor.getParent().getPosition().x) / 90;
								int gridY = (cursor.getPosition().y - cursor.getParent().getPosition().y) / 90;
								if (gridX == 4 && gridY == 2) {
									if (shoeG.getVisible()) {
										shoeG.setVisible(false);
									}
									logG.setVisible(true);
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
								if (gridX == 4 && gridY == 2) {
									if (logG.getVisible()) {
										logG.setVisible(false);
									}
									shoeG.setVisible(true);
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
								if (gridX == 4 && gridY == 2) {
									if (!nailG.getVisible()) {
										if (logG.getVisible()) {
											logG.setVisible(false);
										}
										if (shoeG.getVisible()) {
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
								if (gridX == 4 && gridY == 2) {
									if (!panG.getVisible()) {
										if (logG.getVisible()) {
											logG.setVisible(false);
										}
										if (shoeG.getVisible()) {
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
					}
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
		}
		if (!playing) {
			if (pressedKeys.contains(KeyEvent.VK_ENTER)) {
				this.closeGame();
				//end the game
				return;
			}
		}
		if (phil != null) {
			phil.update(pressedKeys);
		}
	}

	@Override
	public void draw(Graphics g){
		super.draw(g);
		if(won == true && started) {
			g.drawString("You finished the MVP game!" , 20,80);
			g.drawString("Press \"Enter\" to exit" , 20,100);

			if(playing) {
				phil.stopAnimation();
				phil.setPosition(new Point(phil.getPosition().x, phil.getPosition().y - 67));
				phil.animate("jump");
				sm.PlaySoundEffect("won");
				speech2right.setVisible(false);
				speechWon.setVisible(true);
			}
			playing = false;
		}
		if((workspace != null) && workspace.getVisible()) workspace.draw(g);
		if((toolbox != null) && toolbox.getVisible()) toolbox.draw(g);
		if((phil != null) && phil.getVisible()) phil.draw(g);
		if((title != null) && title.getVisible()) title.draw(g);

	}
}
