package com.arcadeengine.sound;

import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundHandler {

	private static SoundHandler instance;

	public static SoundHandler getInstance() {
		if (instance == null) {
			instance = new SoundHandler();
		}
		return instance;
	}

	private SoundHandler() {
	}

	private ArrayList<Track> audioTracks = new ArrayList<Track>();

	private Track currentTrack;
	private boolean playing = false;

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
	}

	public void pause() {
		currentTrack.getClip().stop();
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

}
