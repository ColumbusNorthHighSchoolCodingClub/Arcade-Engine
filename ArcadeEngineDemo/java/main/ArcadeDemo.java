package main;

import gui.GuiDebug;
import gui.GuiInGame;
import gui.GuiMainMenu;
import gui.GuiPaused;

import java.awt.Graphics;
import java.awt.Image;

import src.AnimPanel;
import src.KeyBinding;
import src.ResUtil;

/**
 * Demo engine of new Game class.
 * 
 * @author David Baker
 * @version Demo v2.1_1
 */
@SuppressWarnings("serial")
public class ArcadeDemo extends AnimPanel
{
	private static ArcadeDemo instance;
	
	private static Image currentBG = ResUtil.loadImage("bg1.png", ArcadeDemo.class);

	private KeyBinding systemBindings = new KeyBinding()
	{
		@Override
		public void singleBinding(String key, AnimPanel game)
		{
			// F3
			if(key.equals("F3")) game.getGuiHandler().invertDebugState();
			
			// Escape
			else if(key.equals("Escape"))
			{
				if(game.getGuiHandler().getGui() instanceof GuiInGame) game.getGuiHandler().switchGui(new GuiPaused(ArcadeDemo.getInstance()));
				else if(game.getGuiHandler().getGui() instanceof GuiPaused) game.getGuiHandler().previousGui();
			}
			
		}
	};
	
	public static AnimPanel getInstance() {
		
		if(instance == null) {
			
			instance = new ArcadeDemo();
			
			instance.createInstance("Demo", 600, 700);
			
			instance.setResizable(false);
			
			instance.setTimerDelay(60);
			
			instance.getKeyBoardHandler().addBindings(((ArcadeDemo) instance).systemBindings);
			
			instance.createGuiHandler(new GuiMainMenu(instance));
			instance.getGuiHandler().addDebug(new GuiDebug(instance));
			instance.getGuiHandler().setDebugState(true);
			
			// ---LOAD ALL RESOURCES---
			instance.initGraphics();
			
		}
		
		return instance;
	}
	
	public void reset() {
		
	}

	/**
	 * Only graphical processes are run through here.
	 */
	@Override
	public Graphics renderFrame(Graphics g)
	{
		super.calculateRenderFPS();

		g.drawImage(this.getCurrentBG(), 0, 0, null);
		
		if(!this.isPaused())
		{
			
		}
		
		// --------------------GUI--------------------
		guihandler.drawGui(g);
	
		return g;
	}
	
	/**
	 * Non-graphical processes are done through here.
	 */
	@Override
	public void process()
	{
		// Makes sure the game pauses if the GUI isn't GuiInGame
		if(!this.isPaused())
		{
			
		}
		
		guihandler.updateGui();
	}

	public Image getCurrentBG() {
		return currentBG;
	}
	
	public void setCurrentBG(Image img) {
		
		if(img != null)
			currentBG = img;
	}
	
	public void initGraphics()
	{
		
	}
	
	public void initMusic()
	{
		
	}
	
	
}
