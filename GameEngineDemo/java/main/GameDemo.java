package main;

import gui.GuiDebug;
import gui.GuiInGame;
import gui.GuiMainMenu;
import gui.GuiPaused;

import java.awt.Color;
import java.awt.Graphics;

import src.AnimPanel;
import src.KeyBinding;

import com.anangrybeaver.src.Entity;
import com.anangrybeaver.src.GamePanel;
import com.anangrybeaver.src.Wall;

/**
 * Demo engine of new Game class.
 * 
 * @author David Baker
 * @version Demo v2.1_1
 */
@SuppressWarnings("serial")
public class GameDemo extends GamePanel
{
	private static GameDemo instance;
	
	public enum GameModes
	{
		BEGINNER, EASY, NORMAL, HARD,
	}
	
	private GameModes mode = GameModes.NORMAL;
	
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
				if(game.getGuiHandler().getGui() instanceof GuiInGame) game.getGuiHandler().switchGui(new GuiPaused(GameDemo.getInstance()));
				else if(game.getGuiHandler().getGui() instanceof GuiPaused) game.getGuiHandler().previousGui();
			}
			
			else if(key.equals("Q"))
				instance.addEntity(new ExampleEntity(instance, instance.getMousePosition().x - 10, instance.getMousePosition().y - 10, 20, 20));

			else if(key.equals("A"))
				instance.addEntity(new ExampleEntity(instance, instance.getMousePosition().x - 50, instance.getMousePosition().y - 50, 100, 100));
			
			else if(key.equals("W")) {
				instance.getEntities().clear();
				
				instance.addEntity(new Wall(instance, 0, 0, 5, 700));
				instance.addEntity(new Wall(instance, 595, 0, 5, 700));
				instance.addEntity(new Wall(instance, 5, 0, 590, 5));
				instance.addEntity(new Wall(instance, 5, 695, 590, 5));
			}
		}
	};
	
	private int brightness = 0;
	
	public static AnimPanel getInstance() {
		
		if(instance == null) {
			
			instance = new GameDemo();
			
			instance.createInstance("Demo", 600, 700);
			
			instance.setResizable(true);
			
			instance.setTimerDelay(60);
			
			instance.getKeyBoardHandler().addBindings(((GameDemo) instance).systemBindings);
			
			instance.createGuiHandler(new GuiMainMenu(instance));
			instance.getGuiHandler().addDebug(new GuiDebug(instance));
			instance.getGuiHandler().setDebugState(true);
			
			// ---LOAD ALL RESOURCES---
			instance.initGraphics();
			
			instance.addEntity(new Wall(instance, 0, 0, 5, 700));
			instance.addEntity(new Wall(instance, 595, 0, 5, 700));
			instance.addEntity(new Wall(instance, 5, 0, 590, 5));
			instance.addEntity(new Wall(instance, 5, 695, 590, 5));
		}
		
		return instance;
	}
	
	public void reset() {
		
		instance.getEntities().clear();
		
		instance.addEntity(new Wall(instance, 0, 0, 5, 700));
		instance.addEntity(new Wall(instance, 595, 0, 5, 700));
		instance.addEntity(new Wall(instance, 5, 0, 590, 5));
		instance.addEntity(new Wall(instance, 5, 695, 590, 5));
	}

	/**
	 * Only graphical processes are run through here.
	 */
	@Override
	public Graphics renderFrame(Graphics g)
	{
		super.calculateRenderFPS();

		//g.drawImage(this.guihandler.getCurrentGui().getBG(), 0, 0, null);
		
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
		for(Entity ent: entities)
			ent.drawDrag(g);
		
		
		super.renderFrame(g);
		
		if(!this.isPaused())
		{
			
		}
		
		// --------------------GUI--------------------
		guihandler.drawGui(g);
		
		g.setColor(new Color(255, 255, 255, brightness));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
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
			super.process();
		}
		
		guihandler.updateGui();
	}
	
	public GameModes[] getGameModes() {
		
		return GameModes.values();
	}
	
	/**
	 * The current GameMode Enum.
	 */
	public GameModes getGameMode()
	{
		return mode;
	}
	
	/**
	 * Sets the current GameMode Enum.
	 */
	public void setGameMode(GameModes gm)
	{
		this.mode = gm;
	}
	
	
	
	public int getBrightness()
	{
		return this.brightness;
	}
	
	public void setBrightness(int brightness)
	{
		this.brightness = brightness;
	}
	

	public void initGraphics()
	{
		
	}
	
	public void initMusic()
	{
		
	}
	
	
}
