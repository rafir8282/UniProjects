/** Input text file for language identification. */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileInput {
	
	public FileInput() throws IOException {
		
		Check c = new Check();
		
		System.out.println("\nConfirming that Testing folder exists...");
		
		// Exit if Testing folder does not exist
		if (c.CheckFile("Testing") == false) {
			System.out.println("Exiting...");
			System.exit(0);
		}
		
		String fileName = "";
		boolean looper = true;
		
		// Input name of test file, try again if file does not exist
		while (looper) {
			System.out.println("\nType the name of the text file you would like to identify (excluding .txt, must be in the Testing folder):");
			fileName = "Testing/" + new Scanner(System.in).nextLine() + ".txt";
			if (c.CheckFile(fileName) == true) {
				looper = false;
			} else {
				System.out.println("Try again.");
			}
		}
		
		// Identify language
		new Identify(new File(fileName));
		
	}
	
}