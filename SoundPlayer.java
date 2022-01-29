/*
	SoundPlayer.java, from "Play Sound in Java" by Jinku Hu, from https://www.delftstack.com/howto/java/play-sound-in-java/, is used under CC BY-SA 3.0. Inapplicable fields removed and play function modified.
	
	License: Attribution-ShareAlike 3.0 Unported (CC BY-SA 3.0)
	Human-readable license information: https://creativecommons.org/licenses/by-sa/3.0/
	Further license information: https://creativecommons.org/licenses/by-sa/3.0/legalcode
	SoundPlayer.java was copied and modified from the following URL: https://www.delftstack.com/howto/java/play-sound-in-java/
*/

import java.io.File; 
import java.io.IOException; 

import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Clip;  
import javax.sound.sampled.UnsupportedAudioFileException; 

public class SoundPlayer 
{ 

	//define storage for start position
	Long nowFrame; 
	Clip clip; 
	
	AudioInputStream audioStream; 
	static String thePath = "Alarm-clock-sound-short.wav"; //sound file converted to a WAV from an mp3 from https://orangefreesounds.com/alarm-clock-sound-short/

	// initialize both the clip and streams 
	public SoundPlayer() 
		throws UnsupportedAudioFileException, 
		IOException, LineUnavailableException 
	{ 
		// the input stream object 
		audioStream = 
				AudioSystem.getAudioInputStream(
				    new File(thePath)
				    .getAbsoluteFile()); 
		
		// the reference to the clip 
		clip = AudioSystem.getClip(); 
 
		clip.open(audioStream); 
	}
	
	// play 
	public void play() 
	{ 
		//start the clip 
		clip.start(); 
		
		clip.setMicrosecondPosition(0);
	} 
	
	// restart audio 
	public void restart() throws IOException, LineUnavailableException, 
											UnsupportedAudioFileException 
	{ 
		clip.stop(); 
		clip.close(); 
		resetAudioStream(); 
		nowFrame = 0L; 
		clip.setMicrosecondPosition(0); 
		this.play(); 
	} 
	
	// stop audio 
	public void stop() throws UnsupportedAudioFileException, 
	IOException, LineUnavailableException 
	{ 
		nowFrame = 0L; 
		clip.stop(); 
		clip.close(); 
	} 
	
	// reset the audio stream 
	public void resetAudioStream() throws UnsupportedAudioFileException, IOException, 
											LineUnavailableException 
	{ 
		audioStream = AudioSystem.getAudioInputStream( 
		new File(thePath).getAbsoluteFile()); 
		clip.open(audioStream); 
		clip.loop(Clip.LOOP_CONTINUOUSLY); 
	} 

} 