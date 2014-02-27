package gui;

import java.awt.Color;
import java.awt.Graphics;

import main.GameDemo;
import src.AnimPanel;
import src.gui.Gui;
import src.gui.GuiButton;
import src.gui.GuiComponent;

public class GuiQuit extends Gui
{
	
	private GuiButton quit = new GuiButton(panel, 200, 22, "Quit"),
					  back = new GuiButton(panel, 200, 22, "Back");
	
	public GuiQuit(AnimPanel panel)
	{
		super(panel);
		
		this.setTitle("Quit?");
		this.setTitleColor(Color.RED);
		
		this.setBGColor(144, 10, 40, 160);
		
		this.components = new GuiComponent[] {
			quit,
			back
		};
	}
	
	@Override
	public void drawGui(Graphics g)
	{
		GameDemo demo = (GameDemo) panel;
		
		// Draws the title of the GUI.
		drawTitle(g, demo.getWidth());

		// GUI Elements-------------------------------
		
		// Draws the buttons, LAST!
		drawComponents(g, components, demo.getWidth() / 2 - 200 / 2, 210);
	}
	
	@Override
	public void updateGui()
	{
		// Update the button to see if it is hovered or not.
		updateComponents();
	}
	
	@Override
	public void updateOnClick()
	{
		GameDemo demo = (GameDemo) panel;
		
		if(quit.isHovered())
		{
			System.out.println("*** Closing ***");
			System.exit(0);
		}
		else if(back.isHovered()) demo.getGuiHandler().previousGui();
	}
}
