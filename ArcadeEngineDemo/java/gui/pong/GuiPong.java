package gui.pong;

import gui.Pausable;

import java.awt.Color;
import java.awt.Graphics;

import com.arcadeengine.AnimPanel;
import com.arcadeengine.gui.Gui;
import com.arcadeengine.gui.GuiButton;
import com.arcadeengine.sound.SoundHandler;

public class GuiPong extends Gui implements Pausable {
	
	private Paddle leftPaddle, rightPaddle;
	private Ball ball;
	
	public GuiPong(AnimPanel panel) {
		super(panel);
		setBGColor(Color.BLACK);
		SoundHandler.getInstance().pause();
		
		ball = new Ball(panel);
		leftPaddle = new Paddle(panel, true);
		rightPaddle = new Paddle(panel, false);
	}

	@Override
	public void drawGui(Graphics g) {
		leftPaddle.draw(g);
		rightPaddle.draw(g);
		ball.draw(g);
	}

	@Override
	public void updateGui() {
		leftPaddle.think();
		rightPaddle.think();
		ball.think();
	}

	@Override
	protected boolean onClick(int mouseBtn) {
		return false;
	}

	@Override
	public void actionPerformed(GuiButton btn) {

	}

}
