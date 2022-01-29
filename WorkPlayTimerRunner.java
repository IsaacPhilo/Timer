import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;

public class WorkPlayTimerRunner {
	public static void main(String[] args) throws Exception{
		Formatter f = new Formatter();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Would you like to use the simple timer (for homework and rest),\nor the expandable timer (for cycling through any number of tasks)? (Type 1 or 2 respectively)");
		int type = scanner.nextInt();
		if(type == 1) {
			f.waiting();
		}
		else if(type == 2){
			f.waitingOnMultipleThings();
		}
	}
}
