package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import main.ExampleEntity;
import main.GameDemo;
import src.AnimPanel;
import src.ResUtil;
import src.gui.Gui;
import src.gui.GuiButton;
import src.gui.GuiButtonToggle;
import src.gui.GuiComponent;

public class GuiMainMenu extends Gui
{
	private GameDemo demo = (GameDemo) panel;
	
	private Image test = ResUtil.loadImage("bees.gif", this.getClass());
	
	private GuiButton start = 	  new GuiButton(demo, 200, 22, "Start Demo!"),
					  options =	  new GuiButton(demo, 200, 22, "Options"),
					  exit =	  new GuiButton(demo,200, 22, "Exit"),
					  easterEgg = new GuiButtonToggle(demo, 30, 698, 20, 20, "EE", "EE", false);
	
	
	public GuiMainMenu(AnimPanel panel)
	{
		super(panel);
		
		this.setTitle("Game Engine");
		this.setTitleColor(Color.GRAY);
		
		demo.addEntity(new ExampleEntity(demo, 20, 20, 20, 20));
		demo.addEntity(new ExampleEntity(demo, 570, 670, 20, 20));
		demo.addEntity(new ExampleEntity(demo, 20, 670, 20, 20));
		demo.addEntity(new ExampleEntity(demo, 570, 20, 20, 20));
		
		demo.setPauseState(false);
		
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
		
		if(((GuiButtonToggle) easterEgg).getState())
		{
			int x = 0;
			
			int y = 0;
			
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
		// Update the button to see if it is hovered or not.
		updateComponents();
	}
	
	@Override
	public boolean updateOnClick(int btn)
	{
		if(start.isHovered())
		{
			demo.getGuiHandler().switchGui(new GuiIntro(this.panel));
			return true;
		}
		else if(options.isHovered())
		{
			demo.getGuiHandler().switchGui(new GuiOptions(this.panel));
			return true;
		}
		else if(exit.isHovered())
		{
			demo.getGuiHandler().switchGui(new GuiQuit(this.panel));
			return true;
		}
		else if(easterEgg.isHovered())
		{
			((GuiButtonToggle) easterEgg).invertState();
			return true;
		}
		return false;
	}
}
