package main;

import gui.GuiDebug;
import gui.GuiInGame;
import gui.GuiMainMenu;
import gui.GuiPaused;
import gui.OptionsHelper;
import gui.Pausable;

import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import com.arcadeengine.AnimPanel;
import com.arcadeengine.KeyBinding;
import com.arcadeengine.ResourceUtil;
import com.arcadeengine.gui.GuiHandler;
import com.arcadeengine.sound.SoundHandler;

/**
 * Demo showing uses of AnimPanel class.
 * 
 * @author David Baker
 * @version Demo v3.1.0
 */
@SuppressWarnings("serial")
public class ArcadeDemo extends AnimPanel {
	private Image currentBG;
	
	private AudioInputStream bgMusic;

	private OptionsHelper optionsHelper = new OptionsHelper();

	private KeyBinding systemBindings = new KeyBinding(this) {
		@Override
		public void singleBinding(String key) {
			GuiHandler gui = ArcadeDemo.this.getGuiHandler();

			// If we don't have a GuiHandler, then disable these bindings!
			if (gui != null) {

				// F3
				if (key.equals("F3"))
					ArcadeDemo.this.getGuiHandler().invertDebugState();

				// Escape
				else if (key.equals("Escape")) {

					if (gui.getGui() instanceof Pausable)
						gui.switchGui(new GuiPaused(ArcadeDemo.this));

					else if (gui.getGui() instanceof GuiPaused)
						gui.previousGui();
				}
			}
		}
	};

	public ArcadeDemo() {

		this.createInstance("Demo", 600, 700);

		this.setResizable(true);

		this.setTimerDelay(60);

		this.getKeyBoardHandler().addBindings(this.systemBindings);

		// Un-Comment These Lines to Activate The Gui System

		this.createGuiHandler(new GuiMainMenu(this));
		this.getGuiHandler().addDebug(new GuiDebug(this));
		this.getGuiHandler().setDebugState(true);

	}

	@Override
	public void initRes() {
		
		//Play music
		SoundHandler sHandler = SoundHandler.getInstance();
		sHandler.addAudioTrack("pokebattle", ResourceUtil.getResourceURL("main.res", "pokebattle.wav"));
		try {
			sHandler.playTrack("pokebattle");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		currentBG = ResourceUtil.loadInternalImage("main.res", "bg1.png");
	}

	/**
	 * Only graphical processes are run through here.
	 */
	@Override
	public Graphics renderFrame(Graphics g) {
		super.calculateRenderFPS();

		g.drawImage(this.getCurrentBG(), 0, 0, null);

		if (!this.isPaused()) {

		}

		// --------------------GUI--------------------
		this.drawGui(g);

		return g;
	}

	/**
	 * Non-graphical processes are done through here.
	 */
	@Override
	public void process() {
		// Makes sure the game pauses if the GUI isn't GuiInGame
		if (!this.isPaused()) {

		}

		this.updateGui();
	}

	public Image getCurrentBG() {
		return currentBG;
	}

	public void setCurrentBG(Image img) {

		if (img != null)
			currentBG = img;
	}

}
