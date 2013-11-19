package src.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import src.AnimPanel;

public abstract class GuiComponent implements GuiComponentInterface
{
	protected AnimPanel panel;
	
	protected Color primColor = new Color(228, 101, 60);
	protected Color secColor = new Color(254, 212, 196);
	protected Color disabledColor = new Color(60, 101, 228);
	
	protected String label;
	
	protected int x;
	protected int y;
	
	protected int height;
	protected int width;
	
	protected boolean enabled = true;
	protected boolean autoplaced = false;
	protected boolean hovered = false;
	
	public GuiComponent(AnimPanel panel, String label, int x, int y, int width, int height) {
		
		this(panel, label, width, height);
		
		this.x = x;
		this.y = y;
		
		this.autoplaced = false;
	}
	
	public GuiComponent(AnimPanel panel, String label, int width, int height) {
		
		this.panel = panel;
		
		this.label = label;
		
		this.width = width;
		this.height = height;
		
		this.autoplaced = true;
	}
	
	
	protected int getHeight()
	{
		try
		{
			return this.height;
		}
		catch(NullPointerException e)
		{
			return 0;
		}
	}
	
	protected int getWidth()
	{
		try
		{
			return this.width;
		}
		catch(NullPointerException e)
		{
			return 0;
		}
	}
	
	/**
	 * Sets the Primary and Secondary Color of the button.
	 * 
	 * @param prim Primary Fill Color of the Button.
	 * @param sec Secondary Color for the Highlight and Text of the Button.
	 */
	public GuiComponent setColors(Color prim, Color sec)
	{
		this.primColor = prim;
		this.secColor = sec;
		
		return this;
	}
	
	/**
	 * Sets the Primary of the Button.
	 * 
	 * @param prim Primary Fill Color of the Button.
	 */
	public GuiComponent setPrimColor(Color prim)
	{
		this.primColor = prim;
		
		return this;
	}
	
	/**
	 * Sets the Secondary of the Button.
	 * 
	 * @param sec Secondary Color for the Highlight and Text of the Button.
	 */
	public GuiComponent setSecColor(Color sec)
	{
		this.secColor = sec;
		
		return this;
	}
	
	/**
	 * Set the button to be hovered or not.
	 */
	public GuiComponent setHovered(boolean state)
	{
		this.hovered = state;
		
		return this;
	}
	
	/**
	 * Set the button to be enabled or not.
	 */
	public GuiComponent setEnabled(boolean enabled)
	{
		this.enabled = enabled;
		
		return this;
	}
	
	/**
	 * Returns true if the given label matches the buttons label.
	 */
	public boolean checkLabel(String label)
	{
		if(this.label.equals(label)) return true;
		else return false;
	}
	
	/**
	 * Returns true if the button is disabled.
	 */
	public boolean isEnabled()
	{
		return this.enabled;
	}
	
	public void onUpdateDefault(Point mouse) {
	
		if(this.checkMouse()) this.setHovered(true);
		else this.setHovered(false);
	}
	
	/**
	 * Disable the button.
	 */
	public void disable()
	{
		this.enabled = false;
	}
	
	/**
	 * Enabel the button.
	 */
	public void enable()
	{
		this.enabled = true;
	}
	
}

interface GuiComponentInterface
{
	/**
	 * Draw the component onto the Screen.
	 * 
	 * @param g The graphics object.
	 * @param panel The game. (put "this" here)
	 */
	public void draw(Graphics g);
	
	/**
	 * Draw the component onto the Screen.
	 * 
	 * @param g The graphics object.
	 * @param panel The game. (put "this" here)
	 */
	public void draw(int x, int y, Graphics g);
	
	/**
	 * Updates the component every tick
	 */
	public void onUpdate();
	
	/**
	 * Returns true if the mouse is on the component.
	 */
	public boolean checkMouse();
}
