package gui;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import com.arcadeengine.AnimPanel;
import com.arcadeengine.ResourceUtil;
import com.arcadeengine.gui.Gui;
import com.arcadeengine.gui.GuiButton;
import com.arcadeengine.gui.GuiButtonToggle;
import com.arcadeengine.gui.GuiComponent;
import com.arcadeengine.gui.GuiSlider;

import main.ArcadeDemo;

public class GuiInGame extends Gui
{
	private ArcadeDemo demo = (ArcadeDemo) panel;
	
	private Image bg1 = ResourceUtil.loadInternalImage("main.res", "bg1.png");
	private Image bg2 = ResourceUtil.loadInternalImage("main.res", "bg2.png");
		
	/**
	 * The enum used for changing the background color of this GUI.
	 */
	private enum BgColor
	{
		// Create the availible Selections for the button to cycle through.
		NONE("None", new Color(0, 0, 0, 0)),
		BLUE("Blue", new Color(0, 0, 255, 100)),
		GREEN("Green", new Color(0, 255, 0, 100)),
		RED("Red", new Color(255, 0, 0, 100)),
		MAGENTA("Magenta", new Color(255, 0, 255, 100)),
		YELLOW("Yellow", new Color(255, 255, 0, 100)),
		SIN("Sin Curves", Color.BLACK);
		
		
		// Treat the rest below this like a class
		private Color myColor;
		
		private String myName;
		
		// Constructor has no modifier statements.
		BgColor(String name, Color color)
		{
			myName = name;
			myColor = color;
		}
		
		public String getName() {
			
			return myName;
		}
		
		public Color getColor() {
			
			if(myName.equals("Sin Curves")) {
				
				return sinColor();
			}
			
			return myColor;
		}
		
		public Color sinColor() {
			Color temp = new Color(68, 68, 68, 160);
			
			double r = Math.sin(Math.toRadians(System.currentTimeMillis()) / 45) * 255;
			double g = Math.cos(Math.toRadians(System.currentTimeMillis()) / 28) * 255;
			double b = (r + g) / 2;
		
			if(r < 0) r = Math.abs(r);
			if(g < 0) g = Math.abs(g);
			if(b < 0) b = Math.abs(b);
			temp = new Color((int) r,(int) g,(int) b, 180);
			
			return temp;
		}
	}
	
	// The local Enum for the button
	private BgColor bgColor = BgColor.NONE;
	
	
	private GuiButton colorChanger = new GuiButton(panel, 150, 22, "BG Color: None"); // Will be the first button vertically.
	
	private GuiButtonToggle imageSwitch =  new GuiButtonToggle(panel, 150, 22, "Background: 2", "Background: 1", true),
							sliderSwitch = new GuiButtonToggle(panel, 150, 22, "Slider: Off", "Slider: On", true);
	
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
		if(!getBGColor().equals(this.bgColor.myColor)) this.setBGColor(this.bgColor.getColor());
		
		// Update the button to see if it is hovered or not.
		super.updateComponents();
	}
	
	@Override
	public boolean updateOnClick(int btn)
	{
		switch(btn) {
			case(MouseEvent.BUTTON1):
				if(colorChanger.isHovered()) {
					if(bgColor.ordinal() < BgColor.values().length - 1) bgColor = BgColor.values()[bgColor.ordinal() + 1];
					else bgColor = BgColor.values()[0];
					// Set the new "Enum"
					this.setBGColor(this.bgColor.getColor());
					
					colorChanger.setLabel("BG Color: " + bgColor.getName());
					return true;
				}
			break;
			
			case(MouseEvent.BUTTON3):
				if(colorChanger.isHovered()) {
					if(bgColor.ordinal() > 0) bgColor = BgColor.values()[bgColor.ordinal() - 1];
					else bgColor = BgColor.values()[BgColor.values().length - 1];
					// Set the new "Enum"
					this.setBGColor(this.bgColor.getColor());
					
					colorChanger.setLabel("BG Color: " + bgColor.getName());
					return true;
				}
			break;
		}

		if(imageSwitch.isHovered())
		{
			// Need to invert the Button's Local state
			imageSwitch.invertState();
			
			if(imageSwitch.getState()) demo.setCurrentBG(bg1);
			else demo.setCurrentBG(bg2);
			return true;
		}
		else if(sliderSwitch.isHovered())
		{
			// Need to invert the Button's Local state
			sliderSwitch.invertState();
			
			slider.setEnabled(sliderSwitch.getState());
			demo.setPauseState(!sliderSwitch.getState());
			return true;
		}
		
		return false;
	}
}
