package gui;

import java.awt.Color;
import java.awt.Graphics;

import main.ArcadeDemo;

import com.arcadeengine.AnimPanel;
import com.arcadeengine.gui.Gui;
import com.arcadeengine.gui.GuiButton;

public class GuiQuit extends Gui {
	private ArcadeDemo demo = (ArcadeDemo) panel;

	private GuiButton quit = new GuiButton(panel, 200, 22, "Quit"), back = new GuiButton(panel, 200, 22, "Back");

	public GuiQuit(AnimPanel panel) {
		super(panel);

		this.setTitle("Quit?");
		this.setTitleColor(Color.RED);

		this.setBGColor(144, 10, 40, 160);

		this.setBGImage(demo.getCurrentBG());

		components.add(quit);
		components.add(back);
	}

	@Override
	public void drawGui(Graphics g) {
		// Draws the title of the GUI.
		drawTitle(g, demo.getWidth());

		// GUI Elements-------------------------------

		// Draws the buttons, LAST!
		drawComponents(g, components, demo.getWidth() / 2 - 200 / 2, 210);
	}

	@Override
	public void updateGui() {
		// Update the button to see if it is hovered or not.
		updateComponents();
	}

	@Override
	protected boolean onClick(int mouseBtn) {
		return false;
	}

	@Override
	public void actionPerformed(GuiButton btn) {
		if (btn.equals(quit)) {
			System.out.println("*** Closing ***");
			System.exit(0);
		} else if (btn.equals(back)) {
			demo.getGuiHandler().previousGui();
		}
	}
}
