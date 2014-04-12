package gui;


import java.awt.Graphics;

import main.ArcadeDemo;
import src.AnimPanel;
import src.gui.Gui;
import src.gui.GuiButton;
import src.gui.GuiComponent;

public class GuiPaused extends Gui
{
	private ArcadeDemo demo = (ArcadeDemo) panel;
	
	private GuiButton resume  = new GuiButton(panel, 200, 22, "Resume Game!"),
					  options = new GuiButton(panel, 200, 22, "Options"),
					  mainmenu= new GuiButton(panel, 200, 22, "To Main Menu"),
					  exit    = new GuiButton(panel, 200, 22, "Exit");
	
	public GuiPaused(AnimPanel panel)
	{
		super(panel);
		
		this.setTitle("* Paused *");
		
		this.setBGColor(68, 68, 68, 160);
		
		this.setBGImage(demo.getCurrentBG());
		
		this.components = new GuiComponent[]
		{
			resume,
			options,
			mainmenu,
			exit
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
		updateComponents();
	}
	
	@Override
	public boolean updateOnClick(int btn)
	{
		if(resume.isHovered()) {
			demo.getGuiHandler().previousGui();
			return true;
		}
		else if(options.isHovered()) {
			demo.getGuiHandler().switchGui(new GuiOptions(this.panel));
			return true;
		}
		else if(mainmenu.isHovered()){ 
			demo.getGuiHandler().switchGui(new GuiMainMenu(this.panel));
			return true;
		}
		else if(exit.isHovered()) {
			demo.getGuiHandler().switchGui(new GuiQuit(this.panel));
			return true;
		}
		
		return false;
	}
}
