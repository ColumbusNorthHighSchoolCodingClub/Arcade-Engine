package gui;

import java.awt.Color;
import java.awt.Graphics;

import main.GameDemo;
import main.GameDemo.GameModes;
import src.AnimPanel;
import src.gui.Gui;
import src.gui.GuiButton;
import src.gui.GuiButtonToggle;
import src.gui.GuiComponent;
import src.gui.GuiSlider;

public class GuiOptions extends Gui
{
	private GameDemo demo = (GameDemo) panel;
	
	private GuiButtonToggle debug      = new GuiButtonToggle(panel, 200, 22, "Debug Screen: Off", "Debug Screen: On", true);
	
	private GuiButton gamemode = new GuiButton(panel, 200, 25, "Game Mode: " + demo.getGameMode());
	
	private GuiSlider 		brightness = new GuiSlider(panel, 200, 38, 0, 100, true, "Brightness");
	private GuiButton 		back       = new GuiButton(panel, 200, 22, "Back");
	

	public GuiOptions(AnimPanel panel)
	{
		super(panel);
		
		this.setTitle("Options");
		this.setTitleColor(Color.blue);
		
		this.setBGColor(44, 12, 144, 160);
		
		this.components = new GuiComponent[]
		{
			debug,
			gamemode,
			brightness,
			back
		};
	}
	
	@Override
	public void drawGui(Graphics g)
	{
		drawTitle(g, demo.getWidth());
		
		// Draws the buttons, LAST!
		drawComponents(g, demo.getWidth() / 2 - 200 / 2, 210);
	}
	
	@Override
	public void updateGui()
	{
		// Update button to correct state.
		if(debug.getState() != demo.getGuiHandler().getDebugState())
			debug.setState(demo.getGuiHandler().getDebugState());
		
		// reset the brightness of demo.
		if(brightness.isHovered() && demo.isLeftClickHeld())
			demo.setBrightness((int) brightness.getValue());
		else
			brightness.setValue(demo.getBrightness());
		
		// Update the button to see if it is hovered or not.
		updateComponents();
	}
	
	@Override
	public boolean updateOnClick(int btn)
	{
		if(debug.isHovered())
		{
			debug.invertState();
			
			demo.getGuiHandler().setDebugState(debug.getState());
			return true;
		}
		else if(gamemode.isHovered()) {
						
			if(demo.getGameMode().ordinal() < GameModes.values().length - 1) demo.setGameMode(GameModes.values()[demo.getGameMode().ordinal()  + 1]);
			else demo.setGameMode(GameModes.values()[0]);
			// Set the new "Enum"
			
			gamemode.setLabel("Game Mode: " + demo.getGameMode());	
			return true;
		}
		else if(back.isHovered()) {
			demo.getGuiHandler().previousGui();
			return true;
		}
		
		return false;
	}
}
