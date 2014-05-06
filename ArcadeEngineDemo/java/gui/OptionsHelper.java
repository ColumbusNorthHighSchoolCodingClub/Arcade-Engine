package gui;

import com.arcadeengine.SettingsHandler;

public class OptionsHelper extends SettingsHandler {

	public OptionsHelper() {
		super("Demo", "demo.config");
	}

	@Override
	protected void setDefaults() {
		defaults.setProperty("example1", "33");
		defaults.setProperty("example2", "true");
		defaults.setProperty("example3", "33.041");
		defaults.setProperty("example4", "313.2222222222");
	}

}
