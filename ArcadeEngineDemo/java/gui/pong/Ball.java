package gui.pong;

import java.awt.Color;
import java.awt.Graphics;

import com.arcadeengine.AnimPanel;
import com.arcadeengine.game.Entity;

public class Ball extends Entity {

	public Ball(AnimPanel panel) {
		super(panel, panel.getWidth() / 2 - 20, panel.getHeight() / 2 - 20, 40, 40);
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
