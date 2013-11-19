package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import src.gui.Gui;
import src.gui.GuiHandler;

/**
 * Main component for Arcade Engine.
 * 
 * @author David Baker
 * @version 1.1
 */
@SuppressWarnings("serial")
public abstract class AnimPanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener, GameInterface
{
	protected static AnimPanel instance;
	
	// Variables
	private String myName;
	
	private int timerDelay = 60;
	
	private int FPS = 60;
	private long nextFPSTime = 0;
	private int fpsLoop = 0;
	
	protected int frameNumber;
	
	private Point lastMouseCoord;
	
	// Handlers
	protected GuiHandler guihandler;
	protected KeyBindingHandler kbhandler = new KeyBindingHandler();
	
	protected SettingsHandler sethelper = new SettingsHandler();
	
	// Booleans
	private boolean paused = true;
	
	private boolean resizable = false;
	
	/**
	 * Constructor for objects of class Game
	 * 
	 * @param name The name of the AnimPanel.
	 * @param width The width (in pixels) of the AnimPanel.
	 * @param height The height (in pixels) of the AnimPanel.
	 */
	protected void createInstance(String name, int width, int height)
	{
		frameNumber = 0;
		this.myName = name;
		
		setPreferredSize(new Dimension(width, height));
		// this.setSize(new Dimension(width,height));
		// this.setLocation(80, 80); // move to the right
		setVisible(true); // make it visible to the user
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		
		// ---LOAD ALL RESOURCES---
		initGraphics();
	}
	
	
	public void createGuiHandler(Gui gui) {
		
		this.guihandler = new GuiHandler(this, gui);
	}
	
	// Getters
	public GuiHandler getGuiHandler()
	{
		return guihandler;
	}
	
	public KeyBindingHandler getKeyBoardHandler()
	{
		return kbhandler;
	}
	
	public String getMyName()
	{
		return myName;
	}
	
	public void setResizable(boolean value) {
		
		this.resizable = value;
	}
	
	public boolean isResizeable() {
	
		return this.resizable;
	}
	
	public void calculateRenderFPS() {
		
		if(System.currentTimeMillis() >= nextFPSTime) {
			
			FPS = Integer.valueOf(fpsLoop);
			
			fpsLoop = 0;
			
			nextFPSTime = System.currentTimeMillis() + 1000L;
		}
		
		fpsLoop++;
	}
	
	public void setTimerDelay(int delay)
	{
		this.timerDelay = delay;
	}
	
	public int getTimerDelay() {
		
		return timerDelay;
	}
	
	public int getFPS()
	{
		return FPS;
	}
	
	public boolean isPaused()
	{
		return paused;
	}
	
	public void setPauseState(boolean state)
	{
		paused = state;
	}
	
	@Override
	public Point getMousePosition()
	{
		try
		{
			Integer.valueOf(super.getMousePosition().x + 1);
			Integer.valueOf(super.getMousePosition().y + 1);
			
			if(!(Double.isNaN(super.getMousePosition().x) && Double.isNaN(super.getMousePosition().y)))
			{	
				lastMouseCoord = super.getMousePosition();
				return super.getMousePosition();
			}
		}
		catch(Exception e)
		{
			try
			{
				if(!this.lastMouseCoord.equals(null))
					return lastMouseCoord;
				
			}
			catch(NullPointerException es)
			{
			}
		}
		
		return new Point(-1, -1);
	}
	
	public boolean isLeftClickHeld() {
		return this.leftClickHeld;
	}
	
	public boolean isRightClickHeld() {
		return this.rightClickHeld;
	}
	
	public boolean isMiddleClickHeld() {
		return this.middleClickHeld;
	}
	

	public void paintComponent(Graphics g)
	{
		frameNumber++;
		this.requestFocusInWindow();
		
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g = renderFrame(g);
	}
	
	private boolean leftClickHeld = false, rightClickHeld = false, middleClickHeld = false;
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		if(e.getButton() == MouseEvent.BUTTON1) {
			if(leftClickHeld != true) leftClickHeld = true;
		}
		
		else if(e.getButton() == MouseEvent.BUTTON3) {
				if(rightClickHeld != true) rightClickHeld = true;
		}
		
		else if(e.getButton() == MouseEvent.BUTTON2) {
			if(middleClickHeld != true) middleClickHeld = true;
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		if(e.getButton() == MouseEvent.BUTTON1) {
			if(leftClickHeld != false) leftClickHeld = false;
		}
		
		else if(e.getButton() == MouseEvent.BUTTON3) {
			if(rightClickHeld != false) rightClickHeld = false;
		}
		
		else if(e.getButton() == MouseEvent.BUTTON2) {
			if(middleClickHeld != false) middleClickHeld = false;
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getButton() == MouseEvent.BUTTON1) guihandler.getGui().updateOnClick();
	}
	
	public void mouseEntered(MouseEvent e)
	{
	}
	
	public void mouseExited(MouseEvent e)
	{
	}
	
	public void mouseMoved(MouseEvent e)
	{
		new Point(e.getXOnScreen(), e.getYOnScreen());
	}
	
	public void mouseDragged(MouseEvent e)
	{
		new Point(e.getXOnScreen(), e.getYOnScreen());
	}
	
	public void keyPressed(KeyEvent e)
	{
		String key = KeyEvent.getKeyText(e.getKeyCode());
		
		
		this.kbhandler.runBindings(key, this);	
	}
	
	public void keyReleased(KeyEvent e)
	{
		String key = KeyEvent.getKeyText(e.getKeyCode());
		
		this.kbhandler.removeKey(key);
	}
	
	public void keyTyped(KeyEvent e)
	{
		
	}
	
	public void initGraphics()
	{
		
	}
	
	public void initMusic()
	{
		
	}
	
}

interface GameInterface
{
	public Graphics renderFrame(Graphics g);
	
	public void process();
}
