package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.GameDemo;
import src.AnimPanel;
import src.gui.Gui;
import src.gui.GuiButton;
import src.gui.GuiComponent;

public class GuiIntro extends Gui {

	private GameDemo demo = (GameDemo) panel;
	
	private GuiButton cont = new GuiButton(demo, 85, 20, "Okay!");
	
	public GuiIntro(AnimPanel panel) {
		super(panel);
	
		this.components = new GuiComponent[] 
		{
			cont
		};
		
	}

	@Override
	public void drawGui(Graphics g) {
		
		Font controls = new Font("Century Gothic", Font.BOLD, 14);
		Font title = new Font("Franklin Gothic Medium", Font.BOLD, 28);
		
		Color color = new Color(102, 185, 204);
		
		this.drawString("Controls...", title, Color.LIGHT_GRAY, 30, 75, g);
		
		this.drawString("Escape: Pauses the GameDemo", controls, color, 30, 95, g);
		this.drawString("Q: Creates an ExampleEntity Where-Ever the Mouse Pointer Is", controls, color, 30, 120, g);
		this.drawString("W: Clears Out All the Existing Entities", controls, color, 30, 145, g);
		 
		this.drawComponents(g, 30, 160);
	}

	@Override
	public void updateGui() {

		this.updateComponents();
	}

	@Override
	public void updateOnClick() {

		if(cont.isHovered()) demo.getGuiHandler().switchGui(new GuiInGame(demo));
		
	}
}
