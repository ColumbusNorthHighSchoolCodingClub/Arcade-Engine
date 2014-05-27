package com.arcadeengine.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.arcadeengine.AnimPanel;

public class ScrollingBackground {

	private final AnimPanel panel;
	private final BufferedImage bg;
	
	private int drawNum = 0;
	
	public ScrollingBackground(AnimPanel panel, BufferedImage bg) {
		this.bg = bg;
		this.panel = panel;
	}

	/**
	 * This is an example of a diagonally scrolling background
	 * 
	 * @param g
	 * @param speed
	 */
	public void drawBackground(Graphics g, int speed) {
		for (int i = -1; i < panel.getWidth() / bg.getWidth() + 1; i++) {
			for (int i2 = -1; i2 < panel.getHeight() / bg.getHeight() + 1; i2++) {
				g.drawImage(bg, i * bg.getWidth() + ((drawNum * speed) % bg.getWidth()), i2 * bg.getHeight() + ((drawNum * speed) % bg.getHeight()), panel);
			}
		}
		drawNum++;
	}
	
}
