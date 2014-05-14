package com.arcadeengine.sound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Timer;

public class SoundHandler implements ActionListener {

	private static SoundHandler instance;

	public static SoundHandler getInstance() {
		if (instance == null) {
			instance = new SoundHandler();
		}
		return instance;
	}

	private SoundHandler() {
		Timer t = new Timer(50, this);
		t.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Clip c = currentTrack.getClip();
		if (c.getMicrosecondLength() <= c.getMicrosecondPosition()) {
			playing = false;
			currentTrack.getClip().setMicrosecondPosition(0);
		}
	}	

	private ArrayList<Track> audioTracks = new ArrayList<Track>();

	private Track currentTrack;
	private boolean playing = false;
	private boolean paused = false;

	public void playTrack(String trackName) {
		if (!playing) {
			currentTrack = getTrack(trackName);
			currentTrack.getClip().start();
			playing = true;
		} else {
			currentTrack.getClip().stop();
			currentTrack = getTrack(trackName);
			currentTrack.getClip().start();
		}
	}

	public void resume() {
		currentTrack.getClip().start();
		paused = false;
	}

	public void pause() {
		currentTrack.getClip().stop();
		paused= true;
	}

	private Track getTrack(String name) {
		for (Track t : audioTracks) {
			if (t.getTrackName().equals(name)) {
				return t;
			}
		}
		return null;
	}

	public void addAudioTrack(String name, URL path) {
		Clip clip = null;
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(path);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
		}
		audioTracks.add(new Track(name, clip));
	}
	
	public boolean isPlaying() {
		return playing;
	}

}
