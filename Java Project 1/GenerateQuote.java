/**
 * This class is for generating quotes.
 *
 * @author Rafi Rahman
 * @version 30/10/2018
 */
import java.util.Scanner;

public class GenerateQuote {
    
    Tables tables = new Tables();
    Scanner sc = new Scanner(System.in);
    
    /**
     * Constructor for objects of class GenerateQuote.
     */
    public GenerateQuote() {
        
        // Choose company
        System.out.println();
        System.out.println("Generate quote. Enter company ID:");
        String companyID = sc.nextLine();
        
        // Checks to see that a valid company ID was entered
        CompanyDeal c = tables.lookupCompany(companyID);
        
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
         * Print quotation.
         */
        System.out.println();
        System.out.println("///////////////////////////////////////////////////");
        System.out.println();
        
        System.out.println("Quotation for order from " + c.companyName + ":");
        System.out.println("(Pack at " + c.pickupBay + ".)");
        System.out.println();
        
        // For formatting the contents of the table
        String leftAlignFormat1 = "|%-10s|%-10s|%-10s|%-10s|%n";
        String leftAlignFormat = "|%-10s|%-10s|%-,10.2f|%-,10d|%n";
        
        // Table headings
        System.out.format("+----------+----------+----------+----------+%n");        
        System.out.format(leftAlignFormat1,"ID","Quantity","Cost ($)","Weight (g)");
        System.out.format("+----------+----------+----------+----------+%n");
        
        // Table contents
        if(noDC > 0) {
            
            System.out.format(leftAlignFormat,"DC",noDC,costDC,weightDC); 
        
        }
        
        if(noBEEF > 0) {
            
            System.out.format(leftAlignFormat,"BEEF",noBEEF,costBEEF,weightBEEF); 
        
        }
        
        if (noCS > 0) {
            
            System.out.format(leftAlignFormat,"CS",noCS,costCS,weightCS); 
        
        }
        
        if(noSTRAW > 0) {
            
            System.out.format(leftAlignFormat,"STRAW",noSTRAW,costSTRAW,weightSTRAW); 
        
        }
        
        if(noEGG2 > 0) {
            
            System.out.format(leftAlignFormat,"EGG2",noEGG2,costEGG2,weightEGG2); 
        
        }
        
        System.out.format("+----------+----------+----------+----------+%n");
        System.out.println();
        
        // Total weight
        int totalWeight = weightDC + weightBEEF + weightCS + weightSTRAW + weightEGG2;
        System.out.printf("Total weight of your order: %,d grams%n",totalWeight);
        
        // Total food cost
        double totalFoodCost = costDC + costBEEF + costCS + costSTRAW + costEGG2;
        System.out.printf("Total food cost of your order: $%,.2f%n",totalFoodCost);
        
        // Billing weight
        int billingWeight = (int)Math.ceil((double)totalWeight/1000);
        System.out.printf("Billing weight of your order: %,d kg%n",billingWeight);
        
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
        
        System.out.printf("Packing cost of your order: $%,.2f%n",packingCost);
        
        // To-orbit cost
        double toOrbitCost = 18127*billingWeight;
        System.out.printf("To-orbit cost of your order: $%,.2f%n",toOrbitCost);
        
        // Total cost of the order
        double totalMealCost = toOrbitCost + packingCost + totalFoodCost;
        System.out.printf("%nTotal cost of your order: $%,.2f%n",totalMealCost);
        
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