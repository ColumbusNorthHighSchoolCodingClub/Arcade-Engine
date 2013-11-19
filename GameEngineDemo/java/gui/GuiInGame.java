package gui;


import java.awt.Color;
import java.awt.Graphics;

import main.GameDemo;
import src.AnimPanel;
import src.gui.Gui;
import src.gui.GuiButtonToggle;
import src.gui.GuiComponent;

public class GuiInGame extends Gui
{
	private GameDemo demo = (GameDemo) panel;

	private GuiButtonToggle pauseToggle = new GuiButtonToggle(panel, 75, 22, "Pause", "Unpause", false);
	
	public GuiInGame(AnimPanel panel)
	{
		super(panel);
		
		demo.reset();
		demo.setPauseState(false);
		
		this.setBGColor(0,0,0,0);
		// Construct new Components in the order you want them to appear if you don't want a special coordinate for each button.
		// All components left without coordinates will be auto-placed vertically downwards from the given coordinate in the drawButtons method.
		this.components = new GuiComponent[]
		{
			pauseToggle,
		};
		
	}
	
	@Override
	public void drawGui(Graphics g)
	{
		g.setColor(new Color(0,255,0,100));
		g.fillRect(demo.getMousePosition().x - 10, demo.getMousePosition().y - 10, 20, 20);
		// GUI Elements-------------------------------
		
		// Draws the components, LAST!
		this.drawComponents(g, 4, 4);
	}
	
	@Override
	public void updateGui()
	{
		// Update the button to see if it is hovered or not.
		super.updateComponents();
	}
	
	@Override
	public void updateOnClick()
	{
		if(pauseToggle.checkMouse()) {
			
			pauseToggle.invertState();
			
			demo.setPauseState(pauseToggle.getState());
		}
	}
}
