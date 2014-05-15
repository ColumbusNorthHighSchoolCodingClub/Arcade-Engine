package gui.pong;

import gui.Pausable;

import java.awt.Color;
import java.awt.Graphics;

import com.arcadeengine.AnimPanel;
import com.arcadeengine.gui.Gui;
import com.arcadeengine.gui.GuiButton;
import com.arcadeengine.sound.SoundHandler;

public class GuiPong extends Gui implements Pausable {	
	
	public GuiPong(AnimPanel panel) {
		super(panel);
		setBGColor(Color.BLACK);
		SoundHandler.getInstance().pause();
	}

	@Override
	public void drawGui(Graphics g) {
		
	}

	@Override
	public void updateGui() {
		
	}

	@Override
	protected boolean onClick(int mouseBtn) {
		return false;
	}

	@Override
	public void actionPerformed(GuiButton btn) {

	}

}
