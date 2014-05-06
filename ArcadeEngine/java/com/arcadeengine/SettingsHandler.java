package com.arcadeengine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;

/**
 * Handles settings for AnimPanel
 * 
 * @author Byron Zaharako
 */
public abstract class SettingsHandler {

	/**
	 * Set of default properties Set these in setDefaults
	 */
	protected final Properties defaults = new Properties();

	/**
	 * The runtime configuration
	 */
	protected Properties config = new Properties();

	private String projectName;
	private String settingsFileName;
	private File settingsFile;

	public SettingsHandler(String projectName, String fileName) {
		this.settingsFileName = fileName;
		this.projectName = projectName;
		setDefaults();
		loadConfig();
	}

	protected abstract void setDefaults();

	/**
	 * Attemps to load the saved config file If the file cannot load the
	 * defaults are used If the file does not exist one is created with the
	 * defaults saved to it
	 */
	protected void loadConfig() {
		config = new Properties(defaults);
		File gameSettingsDir = new File(System.getenv("APPDATA") + "\\" 	+ projectName + "\\");
		gameSettingsDir.mkdirs();
		try {
			settingsFile = new File(gameSettingsDir, settingsFileName);
			if (this.settingsFile.exists()) {
				System.out.println("Config file found, loading...");
				this.config.load(new FileInputStream(this.settingsFile));
			} else {
				System.out.println("No config file found, creating...");
				this.createConfig();
			}
		} catch (Exception ex) {
			System.out.println("Error Loading config: "
					+ ex.getLocalizedMessage());
		}
	}

	/**
	 * Create the config Only used to generate a new config file
	 */
	protected void createConfig() {
		try {
			this.config.putAll(this.defaults);
			this.config.store(new FileWriter(settingsFile), null);
		} catch (Exception ex) {
			System.out.println("Error creating config: "
					+ ex.getLocalizedMessage());
		}
	}

	/**
	 * Saves the configuration to file
	 */
	protected void saveConfig() {
		try {
			this.config.store(new FileWriter(settingsFile), null);
		} catch (Exception e) {
			System.out.println("Error saving config: " + e.getLocalizedMessage());
		}
	}
	
	public void setProperty(String propertyName, int value) {
		config.setProperty(propertyName, String.valueOf(value));
		saveConfig();
	}
	
	public int getIntProperty(String propertyName) {
		return Integer.parseInt(config.getProperty(propertyName));
	}
	
	public void setProperty(String propertyName, float value) {
		config.setProperty(propertyName, String.valueOf(value));
		saveConfig();
	}
	
	public float getFloatProperty(String propertyName) {
		return Float.parseFloat(config.getProperty(propertyName));
	}
	
	public void setProperty(String propertyName, double value) {
		config.setProperty(propertyName, String.valueOf(value));
		saveConfig();
	}
	
	public double getDoubleProperty(String propertyName) {
		return Double.parseDouble(config.getProperty(propertyName));
	}
	
	public void setProperty(String propertyName, String value) {
		config.setProperty(propertyName, value);
		saveConfig();
	}
	
	public String getStringProperty(String propertyName) {
		return config.getProperty(propertyName);
	}
	
	public void setProperty(String propertyName, boolean b) {
		config.setProperty(propertyName, String.valueOf(b));
        saveConfig();
	}

	public boolean getBoolProperty(String propertyName) {
		return Boolean.parseBoolean(config.getProperty(propertyName));
	}
	
	/**
     * Swaps the state of a boolean option
     */
    public void toggleOption(String propertyName){
        setProperty(propertyName, !this.getBoolProperty(propertyName));
        saveConfig();
    }

}
