package src;

public class KeyBinding implements KeyBindingInterface
{
	
	/**
	 * Key binding to be fired only once when key is pressed.
	 */
	public void singleBinding(String key, AnimPanel game)
	{
		
	}
	
	/**
	 * Key binding to be fired while key is pressed.
	 */
	public void repeatBinding(String key, AnimPanel game)
	{
		
	}
}

interface KeyBindingInterface
{
	public void singleBinding(String key, AnimPanel game);
	
	public void repeatBinding(String key, AnimPanel game);
}
