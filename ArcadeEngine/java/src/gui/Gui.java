package src.gui;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import src.AnimPanel;
import src.ResourceUtility;


public abstract class Gui implements GuiInterface
{
	// private Color color = new Color(123, 68, 68, 68);

	protected AnimPanel panel;
	
	protected Color myColor = new Color(68, 68, 68, 160);
	
	protected GuiComponent components[] = {};
	
	/** The title for each GUI screen **/
	protected String title = "Arcade Engine";
	
	/** Color of the title */
	protected Color titleColor = Color.BLUE;
	
	/** Y Coordinate of the title */
	private int titleY = 150;
	
	/** The image in the background of the game. **/
	protected Image background = ResourceUtility.loadImage("bg.png", Gui.class);
	
	/** The last Gui Visited **/
	public Gui parent = null;
	
	public Gui(AnimPanel panel) {
		
		this.panel = panel;
	}
	
	/**
	 * Returns the current background image.
	 */
	public Image getBG()
	{
		try
		{
			return this.background;
		}
		catch(NullPointerException e)
		{
			return null;
		}
	}
	
	/**
	 * Returns the background color.
	 */
	public Color getBGColor()
	{
		try
		{
			return myColor;
		}
		catch(NullPointerException e)
		{
			return null;
		}
	}
	
	/**
	 * Draws the title text of the GUi.
	 * 
	 * @param g The graphics object.
	 * @param width The Width of the window.
	 */
	protected void drawTitle(Graphics g, int width)
	{
		Graphics2D page = (Graphics2D) g;
		
		Font old = g.getFont();
		g.setFont(new Font("Arial", 3, 65));
		
		Rectangle2D rect = page.getFontMetrics().getStringBounds(title, page);
		
		g.setColor(Color.DARK_GRAY);
		
		page.drawString(title, ((width / 2) - ((int) rect.getWidth() / 2)) + 2, titleY + 2);
		
		g.setColor(this.titleColor);
		page.drawString(title, (width / 2) - ((int) rect.getWidth() / 2), titleY);
		
		g.setFont(old);
	}
	
	/**
	 * Draws a given String with a shadow for contrast.
	 * 
	 * @param str The String to be drawn.
	 * @param p The Point to be drawn at.
	 * @param size The size of the font to be used.
	 * @param g The Graphics object.
	 */
	protected void drawString(String str, Point p, int size, Graphics g)
	{
		int shad = 2;
		if(size <= 20) shad = 1;
		
		Font old = g.getFont();
		g.setFont(new Font("Arial", Font.BOLD, size));
		
		g.setColor(Color.DARK_GRAY);
		g.drawString(str, p.x + shad, p.y + shad);
		
		g.setColor(Color.black);
		g.drawString(str, p.x, p.y);
		
		g.setFont(old);
	}
	
	/**
	 * Draws a given String with a given Color with a shadow for contrast.
	 * 
	 * @param str The String to be drawn.
	 * @param p The Point to be drawn at.
	 * @param size The size of the font to be used.
	 * @param g The Graphics object.
	 */
	protected void drawString(String str, Color color, Point p, int size, Graphics g)
	{
		int shad = 2;
		if(size <= 20) shad = 1;
		
		Font old = g.getFont();
		g.setFont(new Font("Arial", Font.BOLD, size));
		
		g.setColor(Color.BLACK);
		g.drawString(str, p.x + shad, p.y + shad);
		
		g.setColor(color);
		g.drawString(str, p.x, p.y);
		
		g.setFont(old);
	}
	
	/**
	 * Draws the buttons that aren't given coordinates at given point.
	 * 
	 * @param g The current graphics object.
	 * @param buttons The array of buttons that the GUI has.
	 * @param x X pos where to start auto placing(vertical downwards) the buttons.
	 * @param y Y pos where to start auto placing(vertical downwards) the buttons.
	 */
	protected void drawComponents(Graphics g, GuiComponent[] buttons, int x, int y)
	{
		int height = 0;
		int spacing = 4;
		
		for(GuiComponent b : buttons)
			if(b.autoplaced == false) b.draw(g);
			else
			{
				b.draw(x, y + height, g);
				
				height += b.getHeight() + spacing;
			}
	}
	
	/**
	 * Draws the buttons that aren't given coordinates at given point.
	 * 
	 * @param g The current graphics object.
	 * @param buttons The array of buttons that the GUI has.
	 * @param x X pos where to start auto placing(vertical downwards) the buttons.
	 * @param y Y pos where to start auto placing(vertical downwards) the buttons.
	 */
	protected void drawComponents(Graphics g, int x, int y)
	{
		int height = 0;
		int spacing = 4;
		
		for(GuiComponent b : components)
			if(b.autoplaced == false) b.draw(g);
			else
			{
				b.draw(x, y + height, g);
				
				height += b.getHeight() + spacing;
			}
	}
	
	/**
	 * Used for highlighting the buttons.
	 * 
	 * @param mouse The Mouse Coordinate
	 */
	protected void updateComponents(GuiComponent[] comps)
	{
		for(GuiComponent b : comps)
		{
			if(b.checkMouse()) b.setHovered(true);
			else b.setHovered(false);
		}
	}
	
	/**
	 * Used for highlighting the buttons.
	 * 
	 * @param mouse The Mouse Coordinate
	 */
	protected void updateComponents()
	{
		for(GuiComponent b : components)
			b.onUpdate();
	}
	
	/**
	 * Sets the background color to the given R-G-B code with alpha transparency.
	 * 
	 * @param r Red integer of the given color code.
	 * @param g Green integer of the given color code.
	 * @param b Blue integer of the given color code.
	 * @param a Alpha integer of the given color code.
	 */
	public void setBG(int r, int g, int b, int a)
	{
		this.myColor = new Color(r, g, b, a);
	}
	
	/**
	 * Sets the background to the given color.
	 * 
	 * @param color The color for the background.
	 */
	public void setBG(Color color)
	{
		this.myColor = color;
	}
	
}

interface GuiInterface
{
	
	/**
	 * Draw the Gui onto the Screen.
	 * 
	 * @param g The graphics object.
	 * @param panel The game. (put "this" here)
	 */
	public void drawGui(Graphics g);
	
	/**
	 * Updates for the GUI.
	 * 
	 * @param panel The game. (put "this" here)
	 */
	public void updateGui();
	
	/**
	 * 
	 * @param panel The game. (put "this" here)
	 */
	public void updateOnClick();
}
