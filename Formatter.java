import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Formatter {
	private Scanner sc;
	private double restTime;
	private double HWTime;
	private String response;
	SoundPlayer simpleSoundPlayer;
	private double elapsedTime;
	private double elapsedWorkingTime;
	private double elapsedRestTime;
	boolean wouldLikeTimes;
	
	public Formatter() throws Exception, IOException, LineUnavailableException {
		sc = new Scanner(System.in);
		response = "";
		simpleSoundPlayer = new SoundPlayer();
		wouldLikeTimes = false;
	}
	
	public void waiting() throws Exception, Exception, Exception {
		
		do {
			System.out.println("For how many minutes would you like to do homework?");
			HWTime = sc.nextDouble();
			sc.nextLine();
			System.out.println("For how many minutes would you like to rest?");
			restTime = sc.nextDouble();
			sc.nextLine();
			
			System.out.println("Would you like to see elapsed times? (Y/N)");
			String temporary = sc.nextLine();
			if(temporary.equalsIgnoreCase("Y")) {
				wouldLikeTimes = true;
			}
			
			System.out.println("If you would like to begin, type \"Start\". If you would like to change your choices, type anything else. \nThen press enter.");
			response = sc.nextLine();
			System.out.println();
		}while(!response.equalsIgnoreCase("Start"));
		sc.close();
		
		while(true) {
			try {
				System.out.println("Homework time!");
				Thread.sleep((long)(HWTime*1000*60));
				if(wouldLikeTimes) {
					elapsedTime += HWTime;
					elapsedWorkingTime += HWTime;
				}
				simpleSoundPlayer.play();
				if(wouldLikeTimes) {
					System.out.println("Total time: " + elapsedTime + " minutes\nTotal working time: " + elapsedWorkingTime + " minutes\nTotal resting time: " + elapsedRestTime + " minutes\n\n\n");
				}
				
				System.out.println("Rest time!");
				Thread.sleep((long)(restTime*1000*60));
				if(wouldLikeTimes) {//we could put the check outside of the while block to reduce code complexity and running time, but it's O(1) time, and that would create an ugly duplicate block of code
					elapsedTime += restTime;
					elapsedRestTime += restTime;
				}
				
				simpleSoundPlayer.play(); 
				
				if(wouldLikeTimes) {//technically O(4) at this point
					System.out.println("Total time: " + elapsedTime + " minutes\nTotal working time: " + elapsedWorkingTime + " minutes\nTotal resting time: " + elapsedRestTime + " minutes\n\n\n");
				}
				
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void waitingOnMultipleThings() throws Exception, Exception, Exception {
		int numTasks;
		String[] taskNames;
		double[] taskLengths;
		double[] elapsedTimes;
		
		do {
			System.out.println("How many things would you like to cycle?");
			numTasks = sc.nextInt();
			sc.nextLine();
			
			taskNames = new String[numTasks];
			taskLengths = new double[numTasks];
			elapsedTimes = new double[numTasks];
			
			for(int i = 0; i < numTasks; i++) {//initializes the array of tasks and their length
				System.out.println("For task #" + (i + 1) + ", what will you be doing?");
				taskNames[i] = sc.nextLine();
				
				System.out.println("For how many minutes would you like to do this?");
				taskLengths[i] = sc.nextDouble();
				sc.nextLine();
				
				System.out.println();
			}
			
			System.out.println("Would you like to see elapsed times? (Y/N)");
			String temporary = sc.nextLine();
			if(temporary.equalsIgnoreCase("Y")) {
				wouldLikeTimes = true;
			}
			
			System.out.println("If you would like to begin, type \"Start\". If you would like to change your choices, type anything else. \nThen press enter.");
			response = sc.nextLine();
			System.out.println();
		}while(!response.equalsIgnoreCase("Start"));
		sc.close();
		
		while(true) {
			try {
					for(int i = 0; i < numTasks; i++) {
						System.out.println("Time for you to do task #" + (i + 1) + ": " + taskNames[i]);
						Thread.sleep((long)(taskLengths[i]*1000*60));
						simpleSoundPlayer.play();
						if(wouldLikeTimes) {
							elapsedTime += taskLengths[i];//total elapsed time is added to each time
							elapsedTimes[i] += taskLengths[i];//adds time elapsed for each section on each task
							
							System.out.println("Total elapsed time: " + elapsedTime + " minutes");
							for(int j = 0; j < numTasks; j++) {
								System.out.println("Total elapsed time for task #" + (j + 1) + " (" + taskNames[j] + "): " + elapsedTimes[j] + " minutes");
							}
						}
						System.out.println("\n");
					}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}