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
 * @version 2.1.1
 */
@SuppressWarnings("serial")
public abstract class AnimPanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener
{
	// Variables
	private String myName;
	
	private int timerDelay = 60;
	
	private int FPS = 0;
	private long nextFPSTime = 0;
	private int fpsLoop = 0;
	
	private int frameNumber;
	
	private Point lastMouseCoord = new Point(-1, -1);
	
	// Handlers
	protected GuiHandler guiHandler;
	protected KeyBindingHandler kbHandler = new KeyBindingHandler();
	protected SettingsHandler settingsHandler = new SettingsHandler();
	
	// Booleans
	private boolean paused = true;
	
	private boolean resizable = false;
	
	public abstract Graphics renderFrame(Graphics g);
	
	public abstract void process();
	
	public abstract void initRes(); 
	

	public String getMyName()
	{
		
		return myName;
	}
	
	
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
		initRes();
	}
	
	
	public void createGuiHandler(Gui gui) {
		
		this.guiHandler = new GuiHandler(this, gui);
	}
	
	// Getters
	public GuiHandler getGuiHandler()
	{
		return guiHandler;
	}
	
	public KeyBindingHandler getKeyBoardHandler()
	{
		return kbHandler;
	}
	
	public SettingsHandler getSettingsHandler() {
		
		return settingsHandler;
	}
	
	
	public void setResizable(boolean value) {
		
		this.resizable = value;
	}
	
	public boolean isResizable() {
	
		return this.resizable;
	}
	
	public void calculateRenderFPS() {
		
		if(System.currentTimeMillis() >= nextFPSTime) {
			
			FPS = Integer.valueOf(fpsLoop);
			
			fpsLoop = 0;
			
			nextFPSTime = System.currentTimeMillis() + 1000L;
		}
		else fpsLoop++;
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
	
	public int getFrameNumber() {
		
		return frameNumber;
	}
	
	public boolean isPaused()
	{
		return paused;
	}
	
	public void setPauseState(boolean state)
	{
		paused = state;
	}
	
	
	public void paintComponent(Graphics g)
	{
		frameNumber++;
		this.requestFocusInWindow();
		
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g = renderFrame(g);
	}
	
	
	@Override
	public Point getMousePosition()
	{
		try
		{
			Point temp = new Point(super.getMousePosition().x + 1 - 1, super.getMousePosition().y + 1 - 1);
				
			lastMouseCoord = temp;
			return super.getMousePosition();
		
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
		
		else if(e.getButton() == MouseEvent.BUTTON2) {
			if(middleClickHeld != false) middleClickHeld = false;
		}
		
		else if(e.getButton() == MouseEvent.BUTTON3) {
			if(rightClickHeld != false) rightClickHeld = false;
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getButton() == MouseEvent.BUTTON1) guiHandler.getGui().updateOnClick();
	}
	
	public void mouseEntered(MouseEvent e)
	{
	}
	
	public void mouseExited(MouseEvent e)
	{
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
	
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		String key = KeyEvent.getKeyText(e.getKeyCode());
		
		this.kbHandler.runBindings(key);	
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		String key = KeyEvent.getKeyText(e.getKeyCode());
		
		this.kbHandler.removeKey(key);
	}
	

}
