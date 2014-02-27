package main;

import gui.GuiDebug;
import gui.GuiInGame;
import gui.GuiMainMenu;
import gui.GuiPaused;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import src.Entity;
import src.GamePanel;
import src.KeyBinding;
import src.Wall;

/**
 * Demo engine of new Game class.
 * 
 * @author David Baker
 * @version Demo v2.1_1
 */
@SuppressWarnings("serial")
public class GameDemo extends GamePanel
{
	
	public enum GameModes
	{
		BEGINNER, EASY, NORMAL, HARD,
	}
	
	private GameModes mode = GameModes.NORMAL;
	private Random rand = new Random();
	
	private KeyBinding systemBindings = new KeyBinding(GameDemo.this)
	{
		@Override
		public void singleBinding(String key)
		{
			if(GameDemo.this.getGuiHandler() != null) {
				// F3
				if(key.equals("F3")) GameDemo.this.getGuiHandler().invertDebugState();
				
				// Escape
				else if(key.equals("Escape")) {
			
					if(GameDemo.this.getGuiHandler().getGui() instanceof GuiInGame) GameDemo.this.getGuiHandler().switchGui(new GuiPaused(GameDemo.this));
					else if(GameDemo.this.getGuiHandler().getGui() instanceof GuiPaused) GameDemo.this.getGuiHandler().previousGui();
				}
			}
			
			else if(key.equals("Q"))
				GameDemo.this.addEntity(new ExampleEntity(GameDemo.this, GameDemo.this.getMousePosition().x - 10, GameDemo.this.getMousePosition().y - 10, rand.nextInt(61) + 20, rand.nextInt(61) + 20));
			
			else if(key.equals("W")) {
				GameDemo.this.getEntities().clear();
				
				GameDemo.this.addEntity(new Wall(GameDemo.this, 0, 0, 5, 700));
				GameDemo.this.addEntity(new Wall(GameDemo.this, 595, 0, 5, 700));
				GameDemo.this.addEntity(new Wall(GameDemo.this, 5, 0, 590, 5));
				GameDemo.this.addEntity(new Wall(GameDemo.this, 5, 695, 590, 5));
			}
		}
	};
	
	private int brightness = 0;
	
	public GameDemo() {
		
		this.createInstance("Demo", 600, 700);
		
		this.setResizable(false);
		
		this.setTimerDelay(60);
		
		this.getKeyBoardHandler().addBindings(((GameDemo) this).systemBindings);
		
		
	 	//Un-Comment These Lines to Activate The Gui System
		/*	    
		this.createGuiHandler(new GuiMainMenu(this));
	    this.getGuiHandler().addDebug(new GuiDebug(this));
	    this.getGuiHandler().setDebugState(true);
	    */
		
	 	//Un-Comment These Lines To Add Walls Around the Window
		/*	   
	    this.addEntity(new Wall(this, 0, 0, 5, 700));
	    this.addEntity(new Wall(this, 595, 0, 5, 700));
	  	this.addEntity(new Wall(this, 5, 0, 590, 5));
	  	this.addEntity(new Wall(this, 5, 695, 590, 5));
	  	*/
	}
	
	@Override
	public void initRes() {
		
	}
	
	public void reset() {
		
		this.getEntities().clear();
		
		this.addEntity(new Wall(this, 0, 0, 5, 700));
		this.addEntity(new Wall(this, 595, 0, 5, 700));
		this.addEntity(new Wall(this, 5, 0, 590, 5));
		this.addEntity(new Wall(this, 5, 695, 590, 5));
	}

	/**
	 * Only graphical processes are run through here.
	 */
	@Override
	public Graphics renderFrame(Graphics g) {
		super.calculateRenderFPS();

		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
		//Draws the entities velocity.
		for(Entity ent: entities)
			ent.drawDrag(g);
		
		//This renders all of the entities.
		super.renderFrame(g);
		
		if(!this.isPaused()) {
		}
		
		// --------------------GUI--------------------
		this.drawGui(g);
		
		//Makes the screen more white depending on the brightness.
		g.setColor(new Color(255, 255, 255, brightness));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		return g;
	}
	
	/**
	 * Non-graphical processes are done through here.
	 */
	@Override
	public void process() {
		// Makes sure the game pauses if the GUI isn't GuiInGame
		if(!this.isPaused())
			super.process();
		
		this.updateGui();
	}
	
	public GameModes[] getGameModes() {
		return GameModes.values();
	}
	
	/**
	 * The current GameMode Enum.
	 */
	public GameModes getGameMode() {
		return mode;
	}
	
	/**
	 * Sets the current GameMode Enum.
	 */
	public void setGameMode(GameModes gm) {
		this.mode = gm;
	}
	
	public int getBrightness() {
		return this.brightness;
	}
	
	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}
	
}
