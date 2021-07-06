/** Read input for language identification. */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ScanInput {
	
	@SuppressWarnings("resource")
	public ScanInput() throws IOException {
		
		System.out.println("\nGo on then. Input something:");
		
		File inputFile = new File("Input.txt");
		PrintWriter out = new PrintWriter(new FileWriter(inputFile));
		
		// Input and write to a text file so that it can be used by the Identify() method
		out.write(new Scanner(System.in).nextLine());
		
		out.close();
		
		// Identify language
		new Identify(inputFile);
		
	}

}