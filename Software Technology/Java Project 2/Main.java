/** Start program. */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	// Array of languages used for this program
	public static final String[] LANGUAGES = {"English", "French", "German", "Italian", "Spanish"};
	
	// Main method
	public static void main(String[] args) throws IOException {
		
		System.out.println("Initialising...\n");
		
		// Create necessary folders
		new File("Temp").mkdir();
		new File("Models").mkdir();
		
		// Learning
		new Learning();
		
		System.out.println("\nLearning complete. Opening menu...");
		
		// Menu
		while (true) {
            Menu();
        }
        
    }
	
	// Method for menu
	@SuppressWarnings("resource")
	public static void Menu() throws IOException {
		
		System.out.println("\nI: Identify language with input.");
		System.out.println("T: Identify language with text file.");
		System.out.println("M: View Model files.");
		System.out.println("Q: Quit\n");
        System.out.println("What would you like to do?");
        System.out.println("(Inputs are case-sensitive.)");
		
        // Process input
        switch (new Scanner(System.in).nextLine()) {
	            case "Q":
	            System.out.println("\nGoodbye.");
	            System.exit(0);
	            break;
	            case "I":
	            new ScanInput();
	            break;
	            case "T":
	            new FileInput();
	            break;
	            case "M":
	            new OpenModels();
	            break;
	            default:
	            System.out.println("Invalid input. Try again:");
	            break;
	    }
        
    }

}