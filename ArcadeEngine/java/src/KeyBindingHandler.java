package src;


import java.util.ArrayList;


public class KeyBindingHandler
{
	private ArrayList<String> keysPressed = new ArrayList<String>();
	private ArrayList<KeyBinding> customBindings = new ArrayList<KeyBinding>();
	
	public void addBindings(KeyBinding binding)
	{
		customBindings.add(binding);
	}
	
	public void runBindings(String key, AnimPanel game)
	{
		for(KeyBinding b : customBindings)
			b.repeatBinding(key, game);
		
		if(this.keysPressed.contains(key) == false)
		{
			keysPressed.add(key);
			
			singleBindings(key, game);
		}
	}
	
	public void removeKey(String key)
	{
		if(keysPressed.contains(key)) keysPressed.remove(key);
	}
	
	private void singleBindings(String key, AnimPanel game)
	{
		for(KeyBinding b : customBindings)
			b.singleBinding(key, game);
	}
}
