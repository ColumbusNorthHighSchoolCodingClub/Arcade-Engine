package gui;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import main.ArcadeDemo;
import src.AnimPanel;
import src.ResUtil;
import src.gui.Gui;
import src.gui.GuiButtonMultiSelect;
import src.gui.GuiButtonToggle;
import src.gui.GuiComponent;
import src.gui.GuiSlider;

public class GuiInGame extends Gui
{
	private ArcadeDemo demo = (ArcadeDemo) panel;
	
	private Image bg1 = ResUtil.loadImage("bg1.png", ArcadeDemo.class);;
	private Image bg2 = ResUtil.loadImage("bg2.png", ArcadeDemo.class);
		
	/**
	 * The enum used for changing the background color of this GUI.
	 */
	private enum BgColor
	{
		// Create the availible Selections for the button to cycle through.
		None(new Color(0, 0, 0, 0)),
		Blue(new Color(0, 0, 255, 100)),
		Green(new Color(0, 255, 0, 100)),
		Red(new Color(255, 0, 0, 100)),
		Magenta(new Color(255, 0, 255, 100)),
		Yellow(new Color(255, 255, 0, 100));
		
		// Treat the rest below this like a class
		private Color myColor;
		
		// Constructor has no modifier statements.
		BgColor(Color color)
		{
			myColor = color;
		}
		
		public Color getColor() {
			
			return myColor;
		}
	}
	
	// The local Enum for the button
	private BgColor bgColor = BgColor.None;
	
	
	private GuiButtonMultiSelect colorChanger = new GuiButtonMultiSelect(panel, 150, 22, "BG Color: ", bgColor, BgColor.values()); // Will be the first button vertically.
	
	private GuiButtonToggle imageSwitch =  new GuiButtonToggle(panel,150, 22, "Background: 1", "Background: 2", false),
							sliderSwitch = new GuiButtonToggle(panel,150, 22, "Slider: Off", "Slider: On", true);
	
	private GuiSlider		slider = new GuiSlider(panel, 150, 42, 54, 1000, true, "Slider");

	
	public GuiInGame(AnimPanel panel)
	{
		super(panel);
		
		imageSwitch.setColors(new Color(20, 200, 40, 90), new Color(200, 90, 10, 90));
		colorChanger.setColors(Color.ORANGE.darker(), Color.darkGray.brighter());
		// Construct new Components in the order you want them to appear if you don't want a special coordinate for each button.
		// All components left without coordinates will be auto-placed vertically downwards from the given coordinate in the drawButtons method.
		this.components = new GuiComponent[]
		{
			colorChanger,
			imageSwitch,
			slider,
			sliderSwitch,
		};
		
	}
	
	@Override
	public void drawGui(Graphics g)
	{
		// GUI Elements-------------------------------
		
		// Draws the components, LAST!
		this.drawComponents(g, 4, 4);
	}
	
	@Override
	public void updateGui()
	{
		if(!getBGColor().equals(this.bgColor.myColor)) this.setBGColor(this.bgColor.myColor);
		

		// Update the button to see if it is hovered or not.
		super.updateComponents();
	}
	
	@Override
	public void updateOnClick()
	{
		if(colorChanger.checkMouse())
		{
			colorChanger.moveOn();
			// Update the "Enum" by refering back to the "enum" that we are working with.;
			bgColor = (BgColor) colorChanger.getState();
		
			// Set the new "Enum"
			this.setBGColor(this.bgColor.getColor());
		}
		
		else if(imageSwitch.checkMouse())
		{
			// Need to invert the Button's Local state
			imageSwitch.invertState();
			
			if(imageSwitch.getState()) demo.setCurrentBG(bg1);
			else demo.setCurrentBG(bg2);
		}
		else if(sliderSwitch.checkMouse())
		{
			// Need to invert the Button's Local state
			sliderSwitch.invertState();
			
			slider.setEnabled(sliderSwitch.getState());
			demo.setPauseState(!sliderSwitch.getState());
		}
	}
}
