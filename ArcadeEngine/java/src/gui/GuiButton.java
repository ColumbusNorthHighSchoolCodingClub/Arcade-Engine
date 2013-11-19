package src.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import src.AnimPanel;


public class GuiButton extends GuiComponent
{
	/** The Outline Rectangle of the Button. */
	protected Rectangle buttonShadow;
	/** The Inner Rectangle of the Button. */
	protected Rectangle button;
	
	
	/**
	 * Creates a Button.
	 * 
	 * @param x X Coord of the Button
	 * @param y Y Coord of the Button
	 * @param w The Width of the Button
	 * @param h The Height of the Button
	 * @param l The Label of the button to be used for actions. if there is not titles for it.
	 */
	public GuiButton(AnimPanel panel, int x, int y, int w, int h, String l)
	{
		super(panel, l, x, y, w, h);
		
		this.autoplaced = false;
		
		this.buttonShadow = new Rectangle(x, y, w, h);
		this.button = new Rectangle(x + 2, y + 2, w - 4, h - 4);
		this.label = l;
	}
	
	/**
	 * Creates a Button.
	 * 
	 * @param w The Width of the Button
	 * @param h The Height of the Button
	 * @param l The Label of the button to be used for actions. if there is not titles for it.
	 */
	public GuiButton(AnimPanel panel, int w, int h, String l)
	{
		super(panel, l, w, h);
		
		this.buttonShadow = new Rectangle(0, 0, w, h);
		this.button = new Rectangle(0 + 2, 0 + 2, w - 4, h - 4);
	}
	
	// End Constructors ------------------------------------------------
	
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
		page.setColor(secColor);
		page.fill(this.buttonShadow);
		
		// Highlight the button if hovered.
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
		
		Rectangle2D rect = page.getFontMetrics().getStringBounds(label, page);
		
		int drawX = (this.button.width / 2 + this.button.x) - (int) rect.getWidth() / 2;
		int drawY = (this.button.height / 2 + this.button.y) - (int) (rect.getHeight() / 2 + rect.getY());
		
		page.drawString(label, drawX, drawY);
		
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
		Graphics2D page = (Graphics2D) g;
		
		this.button.setLocation(x + 2, y + 2);
		this.buttonShadow.setLocation(x, y);
		
		page.setColor(secColor);
		page.fill(this.buttonShadow);
		
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
		
		Rectangle2D rect = page.getFontMetrics().getStringBounds(label, page);
		
		int drawX = (this.button.width / 2 + this.button.x) - (int) rect.getWidth() / 2;
		int drawY = (this.button.height / 2 + this.button.y) - (int) (rect.getHeight() / 2 + rect.getY());
		
		page.drawString(label, drawX, drawY);
		
		g.setFont(old);
	}
	
	public void onUpdate() {
		super.onUpdateDefault(this.panel.getMousePosition());
	}
	
	/**
	 * True if the pointer is inside of the button area.
	 */
	public boolean checkMouse()
	{
		Point point = this.panel.getMousePosition();
		
		Rectangle mouse;
		try
		{
			mouse = new Rectangle(point.x - 2, point.y - 2, 4, 4);
			
			if(mouse.intersects(buttonShadow) && isEnabled()) return true;
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
}
