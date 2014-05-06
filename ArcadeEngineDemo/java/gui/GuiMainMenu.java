package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import com.arcadeengine.AnimPanel;
import com.arcadeengine.ResourceUtil;
import com.arcadeengine.gui.Gui;
import com.arcadeengine.gui.GuiButton;
import com.arcadeengine.gui.GuiButtonToggle;
import com.arcadeengine.gui.GuiComponent;

import main.ArcadeDemo;

public class GuiMainMenu extends Gui
{
	private ArcadeDemo demo = (ArcadeDemo) panel;
	
	private Image test = ResourceUtil.loadInternalImage( "gui.res", "bees.gif");
	
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
		
		if(start.isHovered())        temp = new Color(12, 144, 44, 180);
		else if(options.isHovered()) temp = new Color(44, 12, 144, 180);
		else if(exit.isHovered())    temp = new Color(144, 12, 44, 180);
		else {
			double r = Math.sin(Math.toRadians(System.currentTimeMillis()) / 25) * 255;
			double g = Math.cos(Math.toRadians(System.currentTimeMillis()) / 42) * 255;
			double b = (r + g) / 2;
	
			if(r < 0) r = Math.abs(r);
			if(g < 0) g = Math.abs(g);
			if(b < 0) b = Math.abs(b);
			
			temp = new Color((int) r,(int) g,(int) b, 180);
		}
		
		if(this.getBGColor() != temp)  this.setBGColor(temp);
		// Update the button to see if it is hovered or not.
		updateComponents();
		
		//We need to update our own array of components aswell.
		updateComponents(utilButtons);
	}
	
	@Override
	public boolean updateOnClick(int btn) {
		if(start.isHovered()) {
			demo.getGuiHandler().switchGui(new GuiInGame(this.panel));
			return true;
		}
		else if(options.isHovered()) {
			demo.getGuiHandler().switchGui(new GuiOptions(this.panel));
			return true;
		}
		else if(exit.isHovered()) {
			demo.getGuiHandler().switchGui(new GuiQuit(this.panel));
			return true;
		}
		else if(easterEgg.isHovered()) {
			easterEgg.invertState();
			return true;
		}
		return false;
	}
}
