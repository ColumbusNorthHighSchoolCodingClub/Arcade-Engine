package src.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import src.AnimPanel;


public class GuiButtonMultiSelect extends GuiButton
{
	private Enum<?>[] values;
	
	/** The state of the button. */
	private Enum<?> state;
	
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
	public GuiButtonMultiSelect(AnimPanel panel, int x, int y, int w, int h, String label, Enum<?> state, Enum<?>[] values)
	{
		super(panel, x, y, w, h, label);
		
		this.state = state;
		this.values = values;
	}
	
	/**
	 * Creates a togglable Button.
	 * 
	 * @param w The Width of the Button
	 * @param h The Height of the Button
	 * @param label The Label of the button to be used for actions. if there is not titles for it.
	 * @param state The starting state of the button.
	 */
	public GuiButtonMultiSelect(AnimPanel panel, int w, int h, String label, Enum<?> state, Enum<?>[] values)
	{
		super(panel, w, h, label);
		
		this.state = state;
		this.values = values;
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
		page.setColor(this.secColor);
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
			
		Font font = new Font("Arial", Font.BOLD, 14);
		Font old = g.getFont();
		g.setFont(font);
		
		Rectangle2D rect = page.getFontMetrics().getStringBounds(label + state.name(), page);
		
		int drawX = (this.button.width / 2 + this.button.x) - (int) rect.getWidth() / 2;
		int drawY = (this.button.height / 2 + this.button.y) - (int) (rect.getHeight() / 2 + rect.getY());
		
		int shad = 1;
		
		g.setColor(secColor);
		g.drawString(label + state.name(), drawX, drawY);
		
		g.drawString(label + state.name(), drawX + 2, drawY);
		
		g.drawString(label + state.name(), drawX + 2, drawY + 2);
		
		g.drawString(label + state.name(), drawX, drawY + 2);
		
		g.setColor(primColor.brighter());
		g.drawString(label + state.name(), drawX + shad, drawY + shad);
		
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
		
		this.button.setLocation(x + 2, y + 2);
		this.buttonShadow.setLocation(x, y);
		
		this.draw(g);
	}
	
	public void moveOn()
	{
		// Compare the button's Enum's ordinal to the indexes of the enum.
		// If it is the last Enum, set it to the first one, or else proceed to the next Enum.
		if(state.ordinal() >= values.length - 1) state = values[0];
		else state = values[state.ordinal() + 1];
	}

	public Enum<?> getState() {
		return state;
	}
}
