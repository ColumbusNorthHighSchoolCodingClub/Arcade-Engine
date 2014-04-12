package src.gui;

import java.awt.Graphics;

import src.AnimPanel;


public class GuiTransition extends Gui
{
	private Gui current, next;
	
	private double currX = 0, transspeed = 28;
	
	private boolean forward = true;
	
	public GuiTransition(AnimPanel panel, Gui current, Gui next)
	{
		super(panel);
		
		this.setBGColor(next.getBGColor());
		this.setBGImage(next.getBGImage());
		
		this.current = current;
		this.next = next;
		
		this.setTitleFont(null);
	}
	
	public void updateGui()
	{
		if(current.getParent() == (null)) this.forward = true;
		else if(!current.getParent().equals(next)) this.forward = true;
		else this.forward = false;
		
		if(forward) currX -= transspeed * (((double) panel.getWidth()) / 500D);
		else currX += transspeed * (((double) panel.getWidth()) / 500D);
		
		this.current.updateGui();
		this.next.updateGui();
	}
	
	public void drawGui(Graphics g)
	{
		if(current.getParent() == (null)) drawNext(g);
		else if(!current.getParent().equals(next)) drawNext(g);
		else drawPrev(g);
	}
	
	
	private void drawPrev(Graphics g)
	{
		if(currX <= panel.getWidth())
		{
			g.translate((int) currX, 0);
			current.drawGui(g);
			
			g.translate(-panel.getWidth(), 0);
			next.drawGui(g);
			
			g.translate(panel.getWidth() - (int) currX, 0);
		}
		else
		{
			g.translate(0, 0);
			
			panel.getGuiHandler().setGui(next);
			next.drawGui(g);
		}
	}
	
	private void drawNext(Graphics g)
	{
		if(currX >= -panel.getWidth())
		{
			g.translate((int) currX, 0);
			current.drawGui(g);
			
			g.translate(panel.getWidth(), 0);
			next.drawGui(g);
			
			g.translate(-panel.getWidth() - (int) currX, 0);
		}
		else
		{
			g.translate(0, 0);
			panel.getGuiHandler().setGui(next);
			next.drawGui(g);
		}
	}
	
	// UNUSED
	public boolean updateOnClick(int btn)
	{
		return false;
	}
	
	
}
