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
public class MVPGame{

	public static SoundManager sm = new SoundManager();

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		LevelOne levelOne = new LevelOne();
		//uncomment to play music
		//sm.LoadMusic("music", "mario_09.wav");
		//sm.PlayMusic();
		levelOne.start();
	}
}
