package com.tutorial.main;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

// new dawn slick library for music
public class AudioPlayer {
	//	other shit, maybe click sound
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	//	bgm
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	
	public static void load() {
		
		
		try {
			musicMap.put("bgm", new Music("res/bgm.ogg"));
		} catch (SlickException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	public static float loadMusicVoulume() {
		// TODO Auto-generated method stub
		
		return 0;
	}

	public static float loadSoundVolume() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static Music getMusic(String key)
	{
		return musicMap.get(key);
		// make another (same) method for sound (for clik sound)
	}
	
	
}
