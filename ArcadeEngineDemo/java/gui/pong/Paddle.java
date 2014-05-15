package gui.pong;

import java.awt.Color;
import java.awt.Graphics;

import com.arcadeengine.AnimPanel;
import com.arcadeengine.game.Entity;

public class Paddle extends Entity {

	public Paddle(AnimPanel panel, boolean left) {
		super(panel, left ? 0 : panel.getWidth() - 20, 0, 20, 120);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillPolygon(bounds);
		g.drawPolygon(bounds);
	}

	@Override
	public void think() {
		
	}

}
