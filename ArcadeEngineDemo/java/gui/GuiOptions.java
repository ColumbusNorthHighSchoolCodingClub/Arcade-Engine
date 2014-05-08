package gui;

import java.awt.Color;
import java.awt.Graphics;

import main.ArcadeDemo;

import com.arcadeengine.AnimPanel;
import com.arcadeengine.gui.Gui;
import com.arcadeengine.gui.GuiButton;
import com.arcadeengine.gui.GuiButtonToggle;

public class GuiOptions extends Gui {
	private ArcadeDemo demo = (ArcadeDemo) panel;

	private GuiButton debug = new GuiButtonToggle(panel, 200, 22, "Debug Screen: Off", "Debug Screen: On", true), back = new GuiButton(panel, 200, 22, "Back");

	public GuiOptions(AnimPanel panel) {
		super(panel);

		this.setTitle("Options");
		this.setTitleColor(Color.blue);

		this.setBGColor(44, 12, 144, 160);

		this.setBGImage(demo.getCurrentBG());

		components.add(debug);
		components.add(back);
	}

	@Override
	public void drawGui(Graphics g) {
		drawTitle(g, demo.getWidth());

		// Draws the buttons, LAST!
		drawComponents(g, demo.getWidth() / 2 - 200 / 2, 210);
	}

	@Override
	public void updateGui() {
		// Update button to correct state.
		if (((GuiButtonToggle) debug).getState() != demo.getGuiHandler().getDebugState())
			((GuiButtonToggle) debug).setState(demo.getGuiHandler().getDebugState());

		// reset the brightness of demo.

		// Update the button to see if it is hovered or not.
		updateComponents();
	}

	@Override
	protected boolean onClick(int mouseBtn) {
		return false;
	}

	@Override
	public void actionPerformed(GuiButton btn) {
		if (btn.equals(debug)) {
			((GuiButtonToggle) debug).invertState();
			demo.getGuiHandler().setDebugState(((GuiButtonToggle) debug).getState());
		} else if (btn.equals(back)) {
			demo.getGuiHandler().previousGui();
		}
	}
}
