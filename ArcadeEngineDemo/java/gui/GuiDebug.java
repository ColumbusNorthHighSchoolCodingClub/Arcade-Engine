package gui;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import main.ArcadeDemo;
import src.AnimPanel;
import src.gui.Gui;

public class GuiDebug extends Gui
{
	private ArcadeDemo demo = (ArcadeDemo) panel;
	
	private String addBreak = "----------------------";
	private String debug[];
	
	public GuiDebug(AnimPanel panel)
	{
		super(panel);
		
		debug = new String[]
		{
			"Loading Debug..."
		};
	}
	
	@Override
	public void drawGui(Graphics g)
	{
		int height = 0;
		int spacing = -2;
		
		for(String str : debug)
		{
			Rectangle2D rect = g.getFontMetrics().getStringBounds(str, g);
			height += rect.getHeight();
			
			g.setColor(new Color(85, 85, 85, 190));
			g.fillRect(this.panel.getWidth() - (int) rect.getWidth() - 1, height - 11, (int) rect.getWidth() + 1, 12);
			
			g.setColor(Color.white);
			g.drawString(str, this.panel.getWidth() - (int) rect.getWidth(), height);
			
			height += spacing;
		}
	}
	
	@Override
	public void updateGui()
	{

		// All of the info to be displayed on the screen if debug is on.
		debug = new String[]
		{
			"FPS: " + demo.getFPS(),
			addBreak,
			"Mouse X: " + demo.getMousePosition().x,
			"Mouse Y: " + demo.getMousePosition().y,
			"Left Click Held: " + demo.isLeftClickHeld(),
			"Middle Click Held: " + demo.isMiddleClickHeld(),
			"Right Click Held: " + demo.isRightClickHeld(),
			addBreak,
			"BG Red: " + demo.getGuiHandler().getBGColor().getRed(),
			"BG Blue: " + demo.getGuiHandler().getBGColor().getBlue(),
			"BG Green: " + demo.getGuiHandler().getBGColor().getGreen(),
			"BG Alpha: " + demo.getGuiHandler().getBGColor().getAlpha(),
		};
	}
	
	@Override
	public void updateOnClick()
	{
		
	}
}
