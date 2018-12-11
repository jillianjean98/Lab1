package edu.virginia.mvp;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.MultiModeSprite;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.SoundManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class MVPGame{

	public static void main(String[] args) {
		LevelOne level = new LevelOne();
		level.start();
	}
}
