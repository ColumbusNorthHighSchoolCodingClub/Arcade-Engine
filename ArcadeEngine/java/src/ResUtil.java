package src;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;

import javax.swing.ImageIcon;

import src.gui.Gui;

/**
 * This class provides utility methods for loading resources
 * 
 * @author D.Baker
 */
public abstract class ResUtil
{
	/**
	 * Safely attempts to get the requested image.
	 * 
	 * @param filename Name of the file.
	 * @param ob : put this.getClass()
	 * @return The desired image.
	 */
	public static Image loadImage(String filename, Class<?> ob)
	{
		try
		{
			return new ImageIcon(ob.getResource("res/" + filename)).getImage();
		}
		catch(NullPointerException e)
		{
			if(filename.equalsIgnoreCase("null.png"))
			{
				System.out.println("res/null.png Doesn't Exist!");
				return null;
				
			}
			else
			{
				System.out.println("res/" + filename + " Doesn't Exist!");
				return loadImage("null.png", Gui.class);
			}
		}
	}
	
	
	/**
	 * Safely attempts to get the requested sound file.
	 * 
	 * @param filename Name of the file.
	 * @param ob Class Being Used in, Example : Player.class
	 * @return The desired sound file.
	 */
	public static AudioClip loadClip(String filename, Class<?> ob)
	{
		try
		{
			return Applet.newAudioClip(ob.getResource("res/" + filename));
		}
		catch(NullPointerException e)
		{
			System.out.println("res/" + filename + " Doesn't Exist!");
			return null;
		}
	}
	
}
