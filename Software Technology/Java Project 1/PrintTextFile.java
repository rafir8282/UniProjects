/**
 * This class is for generating quotes to text files instead of the console.
 *
 * @author Rafi Rahman
 * @version 1/11/2018
 */
import java.util.Scanner;
import java.io.*;

public class PrintTextFile {
    
    Scanner sc = new Scanner(System.in);
    Tables tables = new Tables();
    PrintWriter fileWriter;
    
    /**
     * Constructor for objects of class PrintTextFile.
     */
    public PrintTextFile() {
        
        // Error checker
        boolean errorChecker = true;
        
        while(errorChecker) {
            
            // Input text file name
            System.out.println();
            System.out.println("Please enter the file name you would like to print the quote to:");
            System.out.println("(Enter a valid file name, or there will be problems saving the file.)");
            String fileName = sc.nextLine();
            
            // Creates text file in the project folder
            try {
                
                fileWriter = new PrintWriter(fileName + ".txt");
                
            } catch(Exception e) {
                
                System.out.printf("Error: The file could not be saved. Try another name:");
                continue;
                
            }
            
            errorChecker = false;
        
        }
        
        // Choose company
        System.out.println();
        System.out.println("Enter company ID: ");
        String companyID = sc.nextLine();
        CompanyDeal c = tables.lookupCompany(companyID);
        
        // Checks to see that a valid company ID was entered
        while(c == null) {
            
            System.out.println("Company not found. Try a valid company ID:");
            companyID = sc.nextLine();
            c = tables.lookupCompany(companyID);
        
        }
        
        // Input number of each food item
        System.out.println();
        System.out.println("Ordering from " + c.companyName + ". Make your order:");
        System.out.println("(You can only order up to 24 of each food product.)");
        System.out.println();
        
        System.out.println("How many date cakes?");
        int noDC = foodNumberInput();
        int weightDC = Tables.foodWeight[0]*noDC;
        double costDC = Tables.foodCost[0]*noDC;
            
        System.out.println("How many beef sandwiches?");
        int noBEEF = foodNumberInput();
        int weightBEEF = Tables.foodWeight[1]*noBEEF;
        double costBEEF = Tables.foodCost[1]*noBEEF;
        
        System.out.println("How many cheese sandwiches?");
        int noCS = foodNumberInput();
        int weightCS = Tables.foodWeight[2]*noCS;
        double costCS = Tables.foodCost[2]*noCS;
        
        System.out.println("How many strawberry dessert?");
        int noSTRAW = foodNumberInput();
        int weightSTRAW = Tables.foodWeight[3]*noSTRAW;
        double costSTRAW = Tables.foodCost[3]*noSTRAW;
        
        System.out.println("How many scrambled eggs?");
        int noEGG2 = foodNumberInput();
        int weightEGG2 = Tables.foodWeight[4]*noEGG2;
        double costEGG2 = Tables.foodCost[4]*noEGG2;
        
        /**
         * Print quotation to text file.
         */        
        fileWriter.println("///////////////////////////////////////////////////");
        fileWriter.println();
        
        fileWriter.println("Quotation for order from " + c.companyName + ":");
        fileWriter.println("(Pack at " + c.pickupBay + ".)");
        fileWriter.println();
        
        // For formatting the contents of the table
        String leftAlignFormat1 = "|%-10s|%-10s|%-10s|%-10s|%n";
        String leftAlignFormat = "|%-10s|%-10s|%-,10.2f|%-,10d|%n";
        
        // Table headings
        fileWriter.format("+----------+----------+----------+----------+%n");        
        fileWriter.format(leftAlignFormat1,"ID","Quantity","Cost ($)","Weight (g)");
        fileWriter.format("+----------+----------+----------+----------+%n");
        
        // Table contents
        if(noDC > 0) {
            
            fileWriter.format(leftAlignFormat,"DC",noDC,costDC,weightDC); 
        
        }
        
        if(noBEEF > 0) {
            
            fileWriter.format(leftAlignFormat,"BEEF",noBEEF,costBEEF,weightBEEF); 
        
        }
        
        if (noCS > 0) {
            
            fileWriter.format(leftAlignFormat,"CS",noCS,costCS,weightCS); 
        
        }
        
        if(noSTRAW > 0) {
            
            fileWriter.format(leftAlignFormat,"STRAW",noSTRAW,costSTRAW,weightSTRAW); 
        
        }
        
        if(noEGG2 > 0) {
            
            fileWriter.format(leftAlignFormat,"EGG2",noEGG2,costEGG2,weightEGG2); 
        
        }
        
        fileWriter.format("+----------+----------+----------+----------+%n");
        fileWriter.println();
        
        // Total weight
        int totalWeight = weightDC + weightBEEF + weightCS + weightSTRAW + weightEGG2;
        fileWriter.printf("Total weight of your order: %,d grams%n",totalWeight);
        
        // Total food cost
        double totalFoodCost = costDC + costBEEF + costCS + costSTRAW + costEGG2;
        fileWriter.printf("Total food cost of your order: $%,.2f%n",totalFoodCost);
        
        // Billing weight
        int billingWeight = (int)Math.ceil((double)totalWeight/1000);
        fileWriter.printf("Billing weight of your order: %,d kg%n",billingWeight);
        
        // Packing cost
        double packingCost = 0;
        
        if(billingWeight < 5 && billingWeight >= 1) {
            
            packingCost = 3192;
                     
        } else if(billingWeight > 15) {
            
            packingCost = 457*billingWeight;
            
        } else if(billingWeight < 1) {
            
            packingCost = 0;
            
        } else {
            
            packingCost = 6823.5;
            
        }
        
        fileWriter.printf("Packing cost of your order: $%,.2f%n",packingCost);
        
        // To-orbit cost
        double toOrbitCost = 18127*billingWeight;
        fileWriter.printf("To-orbit cost of your order: $%,.2f%n",toOrbitCost);
        
        // Total cost of the order
        double totalMealCost = toOrbitCost + packingCost + totalFoodCost;
        fileWriter.printf("%nTotal cost of your order: $%,.2f%n",totalMealCost);
        
        fileWriter.println();
        fileWriter.println("///////////////////////////////////////////////////");
        
        fileWriter.close();
        
        // In-console confirmation
        System.out.println();
        System.out.println("///////////////////////////////////////////////////");
        System.out.println();
        System.out.println("Quotation generated. Text file created.");
        System.out.println();
        System.out.println("///////////////////////////////////////////////////");
        System.out.println();
        
    }
    
    // Method for inputting number of food items
    public int foodNumberInput() {
        
        int retV = 0;
        
        // Error checker
        boolean errorChecker = true;
        
        while(errorChecker) {
            
            if(sc.hasNextInt()) {
                
               retV = sc.nextInt();
                
            } else {
                
                System.out.println("Input must be an integer. Try another number:");
                sc.next();
                continue;
                
            }
            
            if(retV > -1 && retV < 25) {
                
                errorChecker = false;
                
            } else {
                
                System.out.println("Input must be between 0 and 24. Try another number:");
                continue;
                
            }
            
        }
        
        return retV;
        
    }
    
}
