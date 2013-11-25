package runners;


import java.applet.Applet;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import main.ArcadeDemo;
import src.AnimPanel;

/**
 * Class AppletArcadeRunner Runs and animates subclasses of MotionPanel
 * 
 * @author David Baker
 * @version 11-25-2013
 */

@SuppressWarnings("serial")
public class AppletRunner extends Applet
{
	AnimPanel world = ArcadeDemo.getInstance();
	
	// ==============================================================================
	// --- Typically you will never need to edit any of the code below this line. ---
	// ==============================================================================
	
	public AppletRunner()
	{
		this.setSize(world.getWidth(), world.getHeight());
		addFrameComponents();
		
		startAnimation();
	}
	
	public void addFrameComponents()
	{
		this.setName(this.world.getMyName() + " - " + this.world.getPreferredSize().width + "x" + this.world.getPreferredSize().height);
		
		this.setPreferredSize(new Dimension(world.getWidth(), world.getHeight()));
		this.setSize(new Dimension(world.getWidth(), world.getHeight()));
		
		this.add(this.world);
	}
	
	public void startAnimation()
	{
		Timer animation = new Timer(1000 / this.world.getFPS(), new ActionListener()
		{ // This is something you may not have seen before...
			// We are coding a method within the ActionListener object during it's construction!
					public void actionPerformed(ActionEvent e)
					{
						AppletRunner.this.getComponent(0).repaint();
						AppletRunner.this.world.process();
					}
				}); // --end of construction of Timer--
			
		animation.start();
	}
}
