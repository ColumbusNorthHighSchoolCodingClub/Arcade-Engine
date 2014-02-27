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
	
	private GuiComponent[] utilButtons;
	
	private GuiButton start = 	  new GuiButton(demo, 120, 22, "Start Demo!"),
					  options =	  new GuiButton(demo, 120, 22, "Options"),
					  arrayTitle1 =	  new GuiButton(demo, 120, 22, "--Default Array--"),
					  arrayTitle2 =	  new GuiButton(demo, 120, 22, "--Util Array--"),
					  exit =	  new GuiButton(demo, 120, 22, "Exit");
					 
	private GuiButtonToggle easterEgg = new GuiButtonToggle(demo, 120, 20, "???", "BEES!!!", false);
	
	public GuiMainMenu(AnimPanel panel)
	{
		super(panel);
		
		this.setTitle("Arcade Engine");
		this.setTitleFont(new Font("Century Gothic", 3, 60));
		this.setTitleColor(Color.GRAY);
		
		this.setBGImage(demo.getCurrentBG());
		
		arrayTitle1.setColors(Color.ORANGE, Color.ORANGE.darker().darker().darker());
		arrayTitle2.setColors(Color.GREEN, Color.GREEN.darker().darker().darker());
		
		//Every Gui has a predefined array of GuiComponents, you are welcome to make more, like below.
		this.components = new GuiComponent[] {
			arrayTitle1,
			start, 
			exit,	
		};
		
		//Here we make our own independent array of components
		this.utilButtons = new GuiComponent[] {
			arrayTitle2,
			options,
			easterEgg
		};
	}
	
	@Override
	public void drawGui(Graphics g)
	{
		// Draw the title text
		drawTitle(g, demo.getWidth());
		
		//This controls the effects created when the hidden button is pressed!
		if(easterEgg.getState()) {
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
					} 
					else cont = false;
				}
			}
		}
		
		// GUI Elements-------------------------------
		
		//These components can have predefined locations on screen, 
		// or you can have them stacked automatically at given coordinates.
		drawComponents(g, 20, 210);
		
		//Here we are drawing our own array of buttons aswell.
		drawComponents(g, utilButtons, demo.getWidth() - 140, 210);
	}
	
	@Override
	public void updateGui() {
		Color temp = new Color(68, 68, 68, 160);
		
		if(start.isHovered())        temp = new Color(12, 144, 44, 160);
		else if(options.isHovered()) temp = new Color(44, 12, 144, 160);
		else if(exit.isHovered())    temp = new Color(144, 12, 44, 160);
		
		if(this.getBGColor() != temp)  this.setBGColor(temp);
		// Update the button to see if it is hovered or not.
		updateComponents();
		
		//We need to update our own array of components aswell.
		updateComponents(utilButtons);
	}
	
	@Override
	public void updateOnClick() {
		if(start.isHovered()) {
			demo.getGuiHandler().switchGui(new GuiInGame(this.panel));
		}
		else if(options.isHovered()) {
			demo.getGuiHandler().switchGui(new GuiOptions(this.panel));
		}
		else if(exit.isHovered()) {
			demo.getGuiHandler().switchGui(new GuiQuit(this.panel));
		}
		else if(easterEgg.isHovered()) {
			easterEgg.invertState();
		}
	}
}
