package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import com.arcadeengine.AnimPanel;

/**
 * Class ArcadeRunner Runs and animates subclasses of MotionPanel
 * 
 * @author David Baker
 * @version 11-25-13
 */
public class ArcadeDemoRunner {
	
	AnimPanel world = new ArcadeDemo();
	
	// ==============================================================================
	// --- Typically you will never need to edit any of the code below this line. ---
	// ==============================================================================
	
	private JFrame myFrame;
	
	public ArcadeDemoRunner() {
		this.myFrame = new JFrame();
		this.myFrame.addWindowListener(new Closer());
		
		addFrameComponents();
		
		this.myFrame.pack();
		
		this.myFrame.setVisible(true);
		this.myFrame.setResizable(world.isResizable());
		
		startAnimation();
	}
	
	public void addFrameComponents() {
		this.myFrame.setTitle(this.world.getMyName());
		
		this.myFrame.add(this.world);
	}
	
	public void startAnimation() {
		Timer animation = new Timer(1000 / this.world.getTimerDelay(),
				new ActionListener() {
					// This is something you may not have seen before...
					// We are coding a method within the ActionListener
					// object during it's construction!
					public void actionPerformed(ActionEvent e) {
						
						if(!ArcadeDemoRunner.this.myFrame.isResizable()) ArcadeDemoRunner.this.myFrame.pack();
						ArcadeDemoRunner.this.myFrame.getComponent(0).repaint();
						ArcadeDemoRunner.this.world.process();
					}
				}); // --end of construction of Timer--
		
		animation.start();
	}
	
	public static void main(String[] args) {
		new ArcadeDemoRunner();
	}
	
	private static class Closer extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.out.println("Closing...");
			System.exit(0);
		} // ======================
	}
	
}
