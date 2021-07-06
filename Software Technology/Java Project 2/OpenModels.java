/** Open specified Model file. */

import java.io.IOException;
import java.util.Scanner;

public class OpenModels {
	
	public OpenModels() throws IOException {
		
		boolean looper = true;
		String fileName = "";
		
		// Input language, try again if language does not exist
		while (looper) {
			System.out.println("\nWhich language?");
			fileName = "Models/" + new Scanner(System.in).nextLine() + "Model.txt";
			if (new Check().CheckFile(fileName) == true) {
				looper = false;
			} else {
				System.out.println("Try again.");
			}
		}
		
		// Open specified file
		System.out.println("Opening " + fileName + "...");
		new ProcessBuilder("Notepad.exe", fileName).start();
		
	}

}
