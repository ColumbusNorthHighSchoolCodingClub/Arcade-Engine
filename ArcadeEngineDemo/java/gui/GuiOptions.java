package gui;

import java.awt.Color;
import java.awt.Graphics;

import com.arcadeengine.AnimPanel;
import com.arcadeengine.gui.Gui;
import com.arcadeengine.gui.GuiButton;
import com.arcadeengine.gui.GuiButtonToggle;
import com.arcadeengine.gui.GuiComponent;

import main.ArcadeDemo;

public class GuiOptions extends Gui
{
	private ArcadeDemo demo = (ArcadeDemo) panel;
	
	private GuiButton debug      = new GuiButtonToggle(panel, 200, 22, "Debug Screen: Off", "Debug Screen: On", true),
					  back       = new GuiButton(panel, 200, 22, "Back");
	
	public GuiOptions(AnimPanel panel)
	{
		super(panel);
		
		this.setTitle("Options");
		this.setTitleColor(Color.blue);
		
		this.setBGColor(44, 12, 144, 160);

		this.setBGImage(demo.getCurrentBG());
		
		this.components = new GuiComponent[]
		{
			debug,
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
		if(((GuiButtonToggle) debug).getState() != demo.getGuiHandler().getDebugState())
			((GuiButtonToggle) debug).setState(demo.getGuiHandler().getDebugState());
		
		// reset the brightness of demo.

		// Update the button to see if it is hovered or not.
		updateComponents();
	}
	
	@Override
	public boolean updateOnClick(int btn)
	{
		if(debug.isHovered())
		{
			((GuiButtonToggle) debug).invertState();
			
			demo.getGuiHandler().setDebugState(((GuiButtonToggle) debug).getState());
			return true;
		}
		else if(back.isHovered()) {
			demo.getGuiHandler().previousGui();
			return true;
		}
		
		return false;
	}
}
