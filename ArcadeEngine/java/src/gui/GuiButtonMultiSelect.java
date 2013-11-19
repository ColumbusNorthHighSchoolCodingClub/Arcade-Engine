package src.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import src.AnimPanel;


public class GuiButtonMultiSelect extends GuiButton
{
	/** The state of the button. */
	public Enum<?> state;
	
	/**
	 * Creates a togglable Button.
	 * 
	 * @param x The X Coordinate of the Button
	 * @param y The Y Coordinate of the Button
	 * @param w The Width of the Button
	 * @param h The Height of the Button
	 * @param label The Label of the button to be used for actions. if there is not titles for it.
	 * @param state The starting state of the button.
	 */
	public GuiButtonMultiSelect(AnimPanel panel, int x, int y, int w, int h, String label, Enum<?> state)
	{
		super(panel, x, y, w, h, label);
		
		this.state = state;
	}
	
	/**
	 * Creates a togglable Button.
	 * 
	 * @param w The Width of the Button
	 * @param h The Height of the Button
	 * @param label The Label of the button to be used for actions. if there is not titles for it.
	 * @param state The starting state of the button.
	 */
	public GuiButtonMultiSelect(AnimPanel panel, int w, int h, String label, Enum<?> state)
	{
		super(panel, w, h, label);
		
		this.state = state;
	}
	
	/**
	 * Draws the button on to the screen.
	 * 
	 * @param g The graphics object
	 */
	public void draw(Graphics g)
	{
		// Draws the Button to be clicked upon.
		Graphics2D page = (Graphics2D) g;
		
		// The Background color of the button.
		page.setColor(new Color(254, 212, 196));
		page.fill(this.buttonShadow);
		
		// The main color of the button.
		if(hovered)
		{
			if(isEnabled()) page.setColor(new Color(208, 81, 40));
			if(!isEnabled()) page.setColor(new Color(60, 101, 228));
		}
		else
		{
			if(isEnabled()) page.setColor(new Color(228, 101, 60));
			if(!isEnabled()) page.setColor(new Color(60, 101, 228));
		}
		
		page.fill(this.button);
		
		// The color of the button text.
		page.setColor(new Color(254, 212, 196));
		
		page.setColor(new Color(254, 212, 196));
		
		Font font = new Font("Noto Sans", Font.BOLD, 14);
		Font old = g.getFont();
		g.setFont(font);
		
		Rectangle2D rect = page.getFontMetrics().getStringBounds(label + state.name(), page);
		
		int drawX = (this.button.width / 2 + this.button.x) - (int) rect.getWidth() / 2;
		int drawY = (this.button.height / 2 + this.button.y) - (int) (rect.getHeight() / 2 + rect.getY());
		
		page.drawString(label + state.name(), drawX, drawY);
		
		g.setFont(old);
	}
	
	/**
	 * Draws the button on to the screen.
	 * 
	 * @param g The graphics object
	 * @param x The current x coord in the for loop for auto placement of buttons.
	 * @param y The current y coord in the for loop for auto placement of buttons.
	 */
	public void draw(int x, int y, Graphics g)
	{
		// Draws the Button to be clicked upon.
		Graphics2D page = (Graphics2D) g;
		
		this.button.setLocation(x + 2, y + 2);
		this.buttonShadow.setLocation(x, y);
		
		// The Background color of the button.
		page.setColor(secColor);
		page.fill(this.buttonShadow);
		
		// The main color of the button.
		
		if(hovered)
		{
			
			if(isEnabled()) page.setColor(primColor.darker());
			if(!isEnabled()) page.setColor(disabledColor);
		}
		else
		{
			if(isEnabled()) page.setColor(primColor);
			if(!isEnabled()) page.setColor(disabledColor);
		}
		
		page.fill(this.button);
		
		// The color of the button text.
		page.setColor(secColor);
		
		Font font = new Font("Noto Sans", Font.BOLD, 14);
		Font old = g.getFont();
		g.setFont(font);
		
		Rectangle2D rect = page.getFontMetrics().getStringBounds(label + state.name(), page);
		
		int drawX = (this.button.width / 2 + this.button.x) - (int) rect.getWidth() / 2;
		int drawY = (this.button.height / 2 + this.button.y) - (int) (rect.getHeight() / 2 + rect.getY());
		
		page.drawString(label + state.name(), drawX, drawY);
		
		g.setFont(old);
	}
	
	public void setState(Enum<?> state)
	{
		this.state = state;
	}
	
	public Enum<?> moveOn(Enum<?> state, Enum<?>[] gameModes)
	{
		// Compare the button's Enum's ordinal to the indexes of the enum.
		// If it is the last Enum, set it to the first one, or else proceed to the next Enum.
		if(state.ordinal() >= gameModes.length - 1) state = gameModes[0];
		else state = gameModes[state.ordinal() + 1];
		
		// Set the new state for the button.
		this.state = state;
		
		return state;
	}
}
