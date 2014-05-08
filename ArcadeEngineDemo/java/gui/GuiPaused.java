package gui;

import java.awt.Graphics;

import main.ArcadeDemo;

import com.arcadeengine.AnimPanel;
import com.arcadeengine.gui.Gui;
import com.arcadeengine.gui.GuiButton;

public class GuiPaused extends Gui {
	private ArcadeDemo demo = (ArcadeDemo) panel;

	private GuiButton resume = new GuiButton(panel, 200, 22, "Resume Game!"), options = new GuiButton(panel, 200, 22, "Options"), mainmenu = new GuiButton(panel, 200, 22, "To Main Menu"), exit = new GuiButton(panel, 200, 22, "Exit");

	public GuiPaused(AnimPanel panel) {
		super(panel);

		this.setTitle("* Paused *");

		this.setBGColor(68, 68, 68, 160);

		this.setBGImage(demo.getCurrentBG());
		
		components.add(resume);
		components.add(options);
		components.add(mainmenu);
		components.add(exit);
	}

	@Override
	public void drawGui(Graphics g) {
		drawTitle(g, demo.getWidth());

		// Draws the buttons, LAST!
		drawComponents(g, demo.getWidth() / 2 - 200 / 2, 210);
	}

	@Override
	public void updateGui() {
		updateComponents();
	}

	@Override
	protected boolean onClick(int mouseBtn) {
		return false;
	}

	@Override
	public void actionPerformed(GuiButton btn) {
		if (btn.equals(resume)) {
			demo.getGuiHandler().previousGui();
		} else if (btn.equals(options)) {
			demo.getGuiHandler().switchGui(new GuiOptions(this.panel));
		} else if (btn.equals(mainmenu)) {
			demo.getGuiHandler().switchGui(new GuiMainMenu(this.panel));
		} else if (btn.equals(exit)) {
			demo.getGuiHandler().switchGui(new GuiQuit(this.panel));
		}
	}
}
