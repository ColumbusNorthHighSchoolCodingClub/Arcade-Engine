package src;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Handles settings for AnimPanel
 * 
 * @author David Baker
 */
public class SettingsHandler
{
	private Properties prop;
	
	private File file;
	
	public SettingsHandler()
	{
		prop = new Properties();
		file = new File("config.properties");
	}
	
	public void addProperty(Object name, Object value)
	{
		this.prop.put(name, value);
	}
	
	public void loadProperties()
	{
		try{
			this.prop.load(new FileInputStream("config.properties"));
		}
		catch(FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveProperties()
	{
		try
		{
			file.createNewFile();
			prop.store(new FileOutputStream(file), "Tesr");
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
}
