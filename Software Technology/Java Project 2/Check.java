/** Check existence of a file or folder. */

import java.io.File;

public class Check {
	
	// Returns true if file or folder exists, else false
	public boolean CheckFile(String fileName) {
		
		if (new File(fileName).exists()) {
	    	System.out.println(fileName + " exists.");
	    	return true;
	    } else {
	    	System.out.println(fileName + " does not exist.");
	    	return false;
	    }
		
	}
	
}
