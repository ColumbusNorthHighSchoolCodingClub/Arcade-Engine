package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import main.ArcadeDemo;
import src.AnimPanel;
import src.ResUtil;
import src.gui.Gui;
import src.gui.GuiButton;
import src.gui.GuiButtonToggle;
import src.gui.GuiComponent;

public class GuiMainMenu extends Gui
{
	private ArcadeDemo demo = (ArcadeDemo) panel;
	
	private Image test = ResUtil.loadImage("bees.gif", this.getClass());
	
	private GuiButton start = 	  new GuiButton(demo, 200, 22, "Start Demo!"),
					  options =	  new GuiButton(demo, 200, 22, "Options"),
					  exit =	  new GuiButton(demo,200, 22, "Exit");
					 
	private GuiButtonToggle easterEgg = new GuiButtonToggle(demo, 30, 698, 20, 20, "EE", "EE", false);
	
	
	public GuiMainMenu(AnimPanel panel)
	{
		super(panel);
		
		this.setTitle("Arcade Engine");
		this.setTitleFont(new Font("Century Gothic", 3, 60));
		this.setTitleColor(Color.GRAY);
		
		this.setBGImage(demo.getCurrentBG());
		
		this.components = new GuiComponent[]
		{
			start, 
			options,
			exit,
			easterEgg
		};
	}
	
	@Override
	public void drawGui(Graphics g)
	{
		// Draw the title screen
		drawTitle(g, demo.getWidth());
		
		if(easterEgg.getState())
		{
			int x = 0, y = 0;
			
			boolean cont = true;
			
			while(cont) {
				
				if(x < demo.getWidth()) {
					
					g.drawImage(test, x, y, null);
					x+=test.getWidth(null);
				}
				else {
					
					if(y < demo.getHeight()) {
						
						x = 0;
						y+=test.getHeight(null);
					} else 
						cont = false;
				}
			}
		}
		
		// GUI Elements-------------------------------
		
		// Draws the buttons, LAST!
		drawComponents(g, demo.getWidth() / 2 - 200 / 2, 210);
	}
	
	@Override
	public void updateGui()
	{
		Color temp = new Color(68, 68, 68, 160);
		
		if(start.checkMouse()) temp = 		 new Color(12, 144, 44, 160);
		else if(options.checkMouse()) temp = new Color(44, 12, 144, 160);
		else if(exit.checkMouse()) temp = 	 new Color(144, 12, 44, 160);
		
		if(this.getBGColor() != temp)  this.setBGColor(temp);
		// Update the button to see if it is hovered or not.
		updateComponents();
	}
	
	@Override
	public void updateOnClick()
	{
		if(start.checkMouse())
		{
			demo.getGuiHandler().switchGui(new GuiInGame(this.panel));
		}
		else if(options.checkMouse())
		{
			demo.getGuiHandler().switchGui(new GuiOptions(this.panel));
		}
		else if(exit.checkMouse())
		{
			demo.getGuiHandler().switchGui(new GuiQuit(this.panel));
		}
		else if(easterEgg.checkMouse())
		{
			easterEgg.invertState();
		}
	}
}
