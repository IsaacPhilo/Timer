import java.io.FileWriter;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Formatter {
	private double restTime;
	private double HWTime;
	private String response;
	private SoundPlayer simpleSoundPlayer;
	private double elapsedTime;
	private double elapsedWorkingTime;
	private double elapsedRestTime;
	boolean wouldLikeTimes;
	private TheGUI GUI;
	
	private static String timeTemp = "";
	
	
	public Formatter() throws Exception, IOException, LineUnavailableException {
		response = "";
		simpleSoundPlayer = new SoundPlayer();
		wouldLikeTimes = false;
		GUI = new TheGUI();
	}
		
	public void waitingOnMultipleThings() throws Exception, Exception, Exception {
		int numTasks;
		String[] taskNames;
		double[] taskLengths;
		double[] elapsedTimes;
		
		do {
			GUI.changeLabel("How many things would you like to cycle?");
			numTasks = Integer.valueOf(GUI.take());
			
			System.out.println("numTasks taken");
			
			taskNames = new String[numTasks];
			taskLengths = new double[numTasks];
			elapsedTimes = new double[numTasks];
			
			for(int i = 0; i < numTasks; i++) {//initializes the array of tasks and their length
				GUI.changeLabel("For task #" + (i + 1) + ", what will you be doing?");
				taskNames[i] = GUI.take();
				
				GUI.changeLabel("For how many minutes would you like to do this?");
				taskLengths[i] = Double.valueOf(GUI.take());
				
			}
			
			GUI.changeLabel("Would you like to see elapsed times? (Y/N)");
			String temporary = GUI.take();
			if(temporary.equalsIgnoreCase("Y")) {
				wouldLikeTimes = true;
			}
			
			GUI.changeLabel("If you would like to begin, type \"Start\". If you would like to change your choices, type anything else. \nThen press enter.");
			response = GUI.take();
		}while(!response.equalsIgnoreCase("Start"));
		
		String timeShowSetup = "Set times:\n";
		for(int position = 0; position < taskNames.length; position++) {
			timeShowSetup += taskNames[position] + ": " + taskLengths[position] + " minutes\n";
		}
		GUI.setTimesToDo(timeShowSetup);
		
		while(true) {
			try {
				for(int i = 0; i < numTasks; i++) {
					GUI.changeLabel("Time for you to do task #" + (i + 1) + ": " + taskNames[i]);
					Thread.sleep((long)(taskLengths[i]*1000*60));
					simpleSoundPlayer.play();
					
					timeTemp = "";
					elapsedTime += taskLengths[i];//total elapsed time is added to each time
					elapsedTimes[i] += taskLengths[i];//adds time elapsed for each section on each task
					
					timeTemp+=("Total elapsed time: " + elapsedTime + " minutes\n");
					for(int j = 0; j < numTasks; j++) {
						timeTemp+=("Total elapsed time for task #" + (j + 1) + " (" + taskNames[j] + "): " + elapsedTimes[j] + " minutes\n");
					}
					if(wouldLikeTimes) {
						GUI.changeTime(timeTemp);
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public static String getTimes() {
		return timeTemp;
	}
}