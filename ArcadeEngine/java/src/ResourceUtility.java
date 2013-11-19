package src;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * This class provides utility methods for loading resources
 * 
 * @author D.Baker
 */
public abstract class ResourceUtility
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
			return new ImageIcon(ob.getResource("resources/" + filename)).getImage();
		}
		catch(NullPointerException e)
		{
			if(filename.equalsIgnoreCase("null.png"))
			{
				System.out.println("resources/null.png Doesn't Exist!");
				return null;
				
			}
			else
			{
				System.out.println("resources/" + filename + " Doesn't Exist!");
				return loadImage("null.png", ResourceUtility.class);
			}
		}
	}
	
	public static Image loadImage(String filename, String loc)
	{
		try
		{
			return new ImageIcon(loc + filename).getImage();
		}
		catch(NullPointerException e)
		{
			System.out.println(loc + filename + " Doesn't Exist!");
			return loadImage("null.png", ResourceUtility.class);
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
			return Applet.newAudioClip(ob.getResource("resources/" + filename));
		}
		catch(NullPointerException e)
		{
			System.out.println("resources/" + filename + " Doesn't Exist!");
			return null;
		}
	}
	
}
