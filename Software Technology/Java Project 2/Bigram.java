/** Create bigrams. */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Bigram {
	
	// Method for converting an input into an ArrayList of bigrams
	public ArrayList<String> BigramGenerate(String name, BufferedReader in) throws IOException {
		
		// Create temp file
		File fileOutTemp = new File("Temp/" + name + "Temp.txt");
		PrintWriter out = new PrintWriter(new FileWriter(fileOutTemp));
		int ch = 0;
		
		// Change alphabetic characters to lower case and only print alphabetic characters to temp file separated by new lines
		while ((ch = in.read()) != -1) {
			if (Character.isUpperCase(ch) || Character.isLowerCase(ch)) {
				ch = Character.toLowerCase(ch);
				out.write(ch);
			} else {
				out.write("\n");
			}
		}
		
		in.close();
		out.close();
		
		// Read temp file for storing into ArrayList
		Scanner sc = new Scanner(fileOutTemp);
		ArrayList<String> words = new ArrayList<String>();
		
		// Only store words with two or more characters in ArrayList
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (line.length() >= 2) {
				words.add(line);
			}
		}
		
		sc.close();
		
		// ArrayList for bigrams
		ArrayList<String> bigrams = new ArrayList<String>();
		
		// Convert words from previous ArrayList into bigrams and store in new ArrayList
		for (int a = 0; a < words.size(); a++) {
			String currentWord = words.get(a).toString();
			for (int b = 0; b < currentWord.length() - 1; b++) {
				bigrams.add(currentWord.substring(b, b + 2));
			}
		}
		
		// Sort bigrams
		Collections.sort(bigrams);
		
		return bigrams;
		
	}

}