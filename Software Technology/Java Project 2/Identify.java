/** Identify language of a file. */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Identify {
	
	@SuppressWarnings("resource")
	public Identify(File file) throws IOException {
		
		System.out.println("\nIdentifying language...");
		
		ArrayList<String> bigramList = new Bigram().BigramGenerate("Input", new BufferedReader(new FileReader(file))); // Convert input to bigrams
		new Main();
		String[] languages = Main.LANGUAGES;
		String line = "";
		ArrayList<Double> probabilities = new ArrayList<Double>();
		double[] matchingScores = new double[5];
		
		System.out.println("\nCalculating matching scores...");
		
		// Compare input bigrams to those of each model file and determine matching scores for each model
		for (int x = 0; x < 5; x++) {
			Scanner sc = new Scanner(new File("Models/" + languages[x] + "Model.txt"));
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				String[] parts = line.split(" ");
				for (int a = 0; a < bigramList.size(); a++) {
					if (bigramList.get(a).equals(parts[0])) {
						probabilities.add(Double.parseDouble(parts[1]));
					}
				}
			}
			int b = bigramList.size() - probabilities.size();
			for (int c = 0; c < b; c++) {
				probabilities.add(0.0);
			}
			double[] probArr = new double[probabilities.size()];
			for (int i = 0; i < probArr.length; i++) {
				probArr[i] = probabilities.get(i);
			}
			matchingScores[x] = 1;
			for (double d : probArr) {
				matchingScores[x] *= d;
			}
			probabilities.clear();
		}
		
		double max = matchingScores[0];
		int l = 0;
		
		// Determine highest matching score and its respective language
		for (int i = 0; i < matchingScores.length;i++) {
			System.out.println(languages[i] + " = " + matchingScores[i]);
			if (matchingScores[i] > max) {
				max = matchingScores[i];
				l = i;
			}
		}
		
		if (max > 0) {
			System.out.println("\nLanguage: " + languages[l]);
		} else {
			System.out.println("\nSorry. The language could not be identified.");
		}
		
	}

}