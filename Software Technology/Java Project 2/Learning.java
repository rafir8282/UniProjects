/** Learn languages. */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Learning {
	
	public Learning() throws IOException {
		
		new Main();
		String[] languages = Main.LANGUAGES;
		
		// Exit if any Learning file does not exist
		System.out.println("Confirming that learning files exist...");
		for (int x = 0; x < 5; x++) {
			if (new Check().CheckFile("Learning/" + languages[x] + ".txt") == false) {
				System.out.println("Exiting...");
				System.exit(0);
			}
		}
		
		// Generate language models
		System.out.println("\nCreating Model files...");
		for (int x = 0; x < 5; x++) {
			ModelGenerate(languages[x]);
		}
		
	}
	
	// Method for creating language models
	public static void ModelGenerate(String name) throws IOException {
		
		String modelFileName = "Models/" + name + "Model.txt";
		BufferedReader in = new BufferedReader(new FileReader(new File("Learning/" + name + ".txt")));
		ArrayList<String> bigramList = new Bigram().BigramGenerate(name, in); // Convert Learning file words into bigrams
		PrintWriter out = new PrintWriter(new FileWriter(new File(modelFileName)));
		int bigramTotal = bigramList.size();
		String bigram = "";
		
		// Print each unique bigram to model file as well as their respective probability of occurrence
		for (int counter = 0; counter < bigramTotal; counter++) {
			bigram = bigramList.get(counter);
			if (counter < 1 || ((counter >= 1) && !(bigram.equals(bigramList.get(counter - 1))))) {
				out.write(bigram + " " + ((double)Collections.frequency(bigramList, bigram)/bigramTotal) + "\n");
			}
		}
		
		out.close();
		
		System.out.println(modelFileName + " created.");
		
	}

}
