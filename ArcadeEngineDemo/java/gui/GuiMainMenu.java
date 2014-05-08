package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.ArcadeDemo;

import com.arcadeengine.AnimPanel;
import com.arcadeengine.ResourceUtil;
import com.arcadeengine.gui.Gui;
import com.arcadeengine.gui.GuiButton;
import com.arcadeengine.gui.GuiButtonToggle;
import com.arcadeengine.gui.GuiComponent;

public class GuiMainMenu extends Gui {
	private ArcadeDemo demo = (ArcadeDemo) panel;

	private Image test = ResourceUtil.loadInternalImage("gui.res", "bees.gif");

	private BufferedImage bg = ResourceUtil.loadInternalImage("gui.res", "bg.png");

	private ArrayList<GuiComponent> utilButtons = new ArrayList<GuiComponent>();

	private GuiButton 
	start = new GuiButton(demo, 120, 22, "Start Demo!"),
	options = new GuiButton(demo, 120, 22, "Options"),
	arrayTitle1 = new GuiButton(demo, 120, 22, "--Default Array--"),
	arrayTitle2 = new GuiButton(demo, 120, 22, "--Util Array--"),
	exit = new GuiButton(demo, 120, 22, "Exit");

	private GuiButtonToggle easterEgg = new GuiButtonToggle(demo, 120, 20, "???", "BEES!!!", false);

	public GuiMainMenu(AnimPanel panel) {
		super(panel);

		this.setTitle("Arcade Engine");
		this.setTitleFont(new Font("Century Gothic", 3, 60));
		this.setTitleColor(Color.GRAY);

		this.setBGImage(demo.getCurrentBG());

		arrayTitle1.setColors(Color.ORANGE, Color.ORANGE.darker().darker().darker());
		arrayTitle2.setColors(Color.GREEN, Color.GREEN.darker().darker().darker());

		// Every Gui has a predefined array of GuiComponents, you are welcome to
		// make more, like below.
		components.add(arrayTitle1);
		components.add(start);
		components.add(exit);

		// Here we make our own independent array of components
		componentArrays.add(utilButtons);
		utilButtons.add(arrayTitle2);
		utilButtons.add(options);
		utilButtons.add(easterEgg);
	}

	@Override
	public void drawGui(Graphics g) {
		drawBackground(g, 1);

		// Draw the title text
		drawTitle(g, demo.getWidth());

		// This controls the effects created when the hidden button is pressed!
		if (easterEgg.getState()) {
			int x = 0, y = 0;
			boolean cont = true;
			while (cont) {
				if (x < demo.getWidth()) {
					g.drawImage(test, x, y, null);
					x += test.getWidth(null);
				} else {
					if (y < demo.getHeight()) {
						x = 0;
						y += test.getHeight(null);
					} else
						cont = false;
				}
			}
		}

		// GUI Elements-------------------------------

		// These components can have predefined locations on screen,
		// or you can have them stacked automatically at given coordinates.
		drawComponents(g, 20, 210);

		// Here we are drawing our own array of buttons aswell.
		drawComponents(g, utilButtons, demo.getWidth() - 140, 210);
	}

	/**
	 * Tick is used to keep track of the scrolling of the background
	 */
	private int tick = 0;

	/**
	 * This is an example of a diagonally scrolling background
	 * 
	 * @param g
	 * @param speed
	 */
	private void drawBackground(Graphics g, int speed) {
		for (int i = -1; i < panel.getWidth() / bg.getWidth() + 1; i++) {
			for (int i2 = -1; i2 < panel.getHeight() / bg.getHeight() + 1; i2++) {
				g.drawImage(bg, i * bg.getWidth() + ((tick * speed) % bg.getWidth()), i2 * bg.getHeight() + ((tick * speed) % bg.getHeight()), panel);
			}
		}
		tick++;
	}

	@Override
	public void updateGui() {
		Color temp = new Color(68, 68, 68, 160);

		if (start.isHovered())
			temp = new Color(12, 144, 44, 180);
		else if (options.isHovered())
			temp = new Color(44, 12, 144, 180);
		else if (exit.isHovered())
			temp = new Color(144, 12, 44, 180);
		else {
			double r = Math.sin(Math.toRadians(System.currentTimeMillis()) / 25) * 255;
			double g = Math.cos(Math.toRadians(System.currentTimeMillis()) / 42) * 255;
			double b = (r + g) / 2;

			r = Math.abs(r);
			g = Math.abs(g);
			b = Math.abs(b);

			temp = new Color((int) r, (int) g, (int) b, 180);
		}

		if (this.getBGColor() != temp)
			this.setBGColor(temp);
		// Update the button to see if it is hovered or not.
		updateComponents();

		// We need to update our own array of components aswell.
		updateComponents(utilButtons);
	}

	@Override
	protected boolean onClick(int mouseBtn) {
		return false;
	}

	@Override
	public void actionPerformed(GuiButton btn) {
		if (btn.equals(start)) {
			demo.getGuiHandler().switchGui(new GuiInGame(this.panel));
		} else if (btn.equals(options)) {
			demo.getGuiHandler().switchGui(new GuiOptions(this.panel));
		} else if (btn.equals(exit)) {
			demo.getGuiHandler().switchGui(new GuiQuit(this.panel));
		} else if (btn.equals(easterEgg)) {
			easterEgg.invertState();
		}
	}
}
