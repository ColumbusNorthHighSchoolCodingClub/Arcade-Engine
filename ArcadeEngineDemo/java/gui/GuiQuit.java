package gui;

import java.awt.Color;
import java.awt.Graphics;

import main.ArcadeDemo;
import src.AnimPanel;
import src.gui.Gui;
import src.gui.GuiButton;
import src.gui.GuiComponent;

public class GuiQuit extends Gui
{
	private ArcadeDemo demo = (ArcadeDemo) panel;
	
	private GuiButton quit = new GuiButton(panel, 200, 22, "Quit"),
					  back = new GuiButton(panel, 200, 22, "Back");
	
	public GuiQuit(AnimPanel panel)
	{
		super(panel);
		
		this.setTitle("Quit?");
		this.setTitleColor(Color.RED);
		
		this.setBGColor(144, 10, 40, 160);
		
		this.setBGImage(demo.getCurrentBG());
		
		this.components = new GuiComponent[] {
			quit,
			back
		};
	}
	
	@Override
	public void drawGui(Graphics g)
	{
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
		if(quit.isHovered())
		{
			System.out.println("*** Closing ***");
			System.exit(0);
		}
		else if(back.isHovered()) demo.getGuiHandler().previousGui();
	}
}
