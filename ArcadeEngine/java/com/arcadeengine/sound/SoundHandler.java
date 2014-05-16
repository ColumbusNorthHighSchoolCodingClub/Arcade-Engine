package com.arcadeengine.sound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Timer;

/**
 * SoundHandler is a class that hadles all sound related actions in ArcadeEngine
 * It is a Singleton - a class that can only have ONE static instance at any time
 * To get this instance call SoundHandler.getInstance()
 * @author Byron Zaharako
 */
public class SoundHandler implements ActionListener {

	/**
	 * The static instance of SoundHandler
	 */
	private static SoundHandler instance;

	/**
	 * @return The one instance of SoundHandler
	 */
	public static SoundHandler getInstance() {
		if (instance == null) {
			instance = new SoundHandler();
		}
		return instance;
	}

	/**
	 * Constructor starts the timer that calls actionPerformed every 25 milliseconds
	 */
	private SoundHandler() {
		Timer t = new Timer(25, this);
		t.start();
	}

	/**
	 * This method will be called every 25 milliseconds after the first time SoundHandler is called
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (currentTrack != null) {
			Clip c = currentTrack.getClip();
			if (c.getMicrosecondLength() <= c.getMicrosecondPosition()) {
				playing = false;
				currentTrack.getClip().setMicrosecondPosition(0);
				if (repeat) { // If the engine wants the track to loop repeat the track
					play();
				}
			}
		}
	}	

	/**
	 * List of all available tracks for the engine to play
	 */
	private ArrayList<Track> soundPool = new ArrayList<Track>();

	/**
	 * Track instance that is currently loaded for play
	 */
	private Track currentTrack;
	
	/**
	 * True if a sound is playing
	 */
	private boolean playing = false;
	
	/**
	 * True if the track is to repeat continuously
	 */
	private boolean repeat = false;

	/**
	 * 
	 * @param trackName The track to load and play
	 * @throws Exception if the track does not exist in the soundPool
	 */
	public void playTrack(String trackName) throws Exception {
		if (!playing) {
			currentTrack = getTrack(trackName);
			play();
		} else {
			currentTrack.getClip().stop();
			currentTrack = getTrack(trackName);
			play();
		}
	}

	/**
	 * Start or resume the audio
	 */
	public void play() {
		currentTrack.getClip().start();
		playing = true;
	}

	/**
	 * Halt the audio playback
	 */
	public void pause() {
		currentTrack.getClip().stop();
		playing = false;
	}

	/**
	 * @param name Name of the audio clip to load
	 * @return audio clip with that name
	 */
	private Track getTrack(String name) throws Exception {
		for (Track t : soundPool) {
			if (t.getTrackName().equals(name)) {
				return t;
			}
		}
		throw new Exception("Track: \"" + name + "\" does not exist");
	}

	/**
	 * Add an audio track to the sound pool
	 * @param name name of the track
	 * @param path path to the audio file
	 */
	public void addAudioTrack(String name, URL path) {
		Clip clip = null;
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(path);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
		}
		soundPool.add(new Track(name, clip));
	}
	
	/**
	 * @return True if the soundhandler is playing sound
	 */
	public boolean isPlaying() {
		return playing;
	}
	
	/**
	 * Set true to constantly repeat track
	 */
	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}
	
	/**
	 * Wrapper to give audio clips a name
	 * @author Byron Zaharako
	 */
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

}
