package com.arcadeengine;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * This class provides utility methods for loading resources
 * 
 * @author D.Baker
 * @author Byron Zaharako
 */
public abstract class ResourceUtil {

	/**
	 * Get a BufferedImage from an image within the classpath
	 * 
	 * @param packageName
	 * @param fileName
	 * @return
	 */
	public static BufferedImage loadInternalImage(String packageName, String fileName) {
		try {
			String pathName = packageName.replaceAll("\\.", "/") + "/" + fileName;
			URL path = ClassLoader.getSystemClassLoader().getResource(pathName);
			return ImageIO.read(path);
		} catch (IOException e) {
			e.printStackTrace();
			return getNullImage();
		}
	}

	/**
	 * Get the null image to show something went wrong when loading
	 */
	private static BufferedImage getNullImage() {
		return loadInternalImage("com.arcadeengine.res", "null.png");
	}

	/**
	 * Gets an Image from outside the classpath
	 */
	public static BufferedImage loadExternalImage(String folder, String fileName) {
		return loadExternalImage(folder + "/" + fileName);
	}

	public static BufferedImage loadExternalImage(String path) {
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			return getNullImage();
		}
	}
	
	public static URL getResourceURL(String packageName, String fileName) {
		String pathName = packageName.replaceAll("\\.", "/") + "/" + fileName;
 		return ClassLoader.getSystemClassLoader().getResource(pathName);
 	}

}
