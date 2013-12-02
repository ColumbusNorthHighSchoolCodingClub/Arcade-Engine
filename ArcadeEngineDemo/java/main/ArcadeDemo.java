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
 * Demo showing uses of AnimPanel class.
 * 
 * @author David Baker
 * @version Demo v3.1.0
 */
@SuppressWarnings("serial")
public class ArcadeDemo extends AnimPanel
{
	private static Image currentBG = ResUtil.loadImage("bg1.png", ArcadeDemo.class);

	private KeyBinding systemBindings = new KeyBinding(this)
	{
		@Override
		public void singleBinding(String key)
		{
			// F3
			if(key.equals("F3")) ArcadeDemo.this.getGuiHandler().invertDebugState();
			
			// Escape
			else if(key.equals("Escape"))
			{
				if(ArcadeDemo.this.getGuiHandler().getGui() instanceof GuiInGame) ArcadeDemo.this.getGuiHandler().switchGui(new GuiPaused(ArcadeDemo.this));
				else if(ArcadeDemo.this.getGuiHandler().getGui() instanceof GuiPaused) ArcadeDemo.this.getGuiHandler().previousGui();
			}
			
		}
	};
	
	public ArcadeDemo() {

		this.createInstance("Demo", 600, 700);
		
		this.setResizable(true);
		
		this.setTimerDelay(60);
		
		this.getKeyBoardHandler().addBindings(this.systemBindings);
		
		this.createGuiHandler(new GuiMainMenu(this));
		this.getGuiHandler().addDebug(new GuiDebug(this));
		this.getGuiHandler().setDebugState(true);
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
		guiHandler.drawGui(g);
	
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
		
		guiHandler.updateGui();
	}

	@Override
	public void initRes() {
		
	}
	
	public Image getCurrentBG() {
		return currentBG;
	}
	
	public void setCurrentBG(Image img) {
		
		if(img != null)
			currentBG = img;
	}

}
