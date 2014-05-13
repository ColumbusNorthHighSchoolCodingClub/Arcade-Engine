package com.arcadeengine.sound;

import javax.sound.sampled.Clip;

class Track {
	private final String trackName;
	private final Clip clip;
	
	public Track(String trackName, Clip soundClip) {
		this.trackName = trackName;
		this.clip = soundClip;
	}

	public String getTrackName() {
		return trackName;
	}

	public Clip getClip() {
		return clip;
	}
}