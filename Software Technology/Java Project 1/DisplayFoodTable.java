/**
 * This class is for displaying food tables.
 *
 * @author Rafi Rahman
 * @version 31/10/2018
 */
public class DisplayFoodTable {
    
    Tables tables = new Tables();
    
    /**
     * Constructor for objects of class DisplayFoodTable.
     */
    public DisplayFoodTable() {
        
        System.out.println();
        System.out.println("Displaying food table:");
        
        System.out.println();
        System.out.println("///////////////////////////////////////////////////");
        System.out.println();
        
        // Table format
        String leftAlignFormat = "|%-5s|%-20s|%-10s|%-10s|%n"; 
        
        // Table headings
        System.out.format("+-----+--------------------+----------+----------+%n");        
        System.out.format(leftAlignFormat,"ID","Name","Weight (g)","Cost ($)");
        System.out.format("+-----+--------------------+----------+----------+%n");        
        
        //Table contents
        tables.printFoodDetails(tables.foodId[0]);
        tables.printFoodDetails(tables.foodId[1]);
        tables.printFoodDetails(tables.foodId[2]);
        tables.printFoodDetails(tables.foodId[3]);
        tables.printFoodDetails(tables.foodId[4]);
        
        System.out.format("+-----+--------------------+----------+----------+%n");        
        
        System.out.println();
        System.out.println("///////////////////////////////////////////////////");
        System.out.println();
        
    }
    
}
