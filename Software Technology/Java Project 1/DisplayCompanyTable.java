/**
 * This class is for displaying food tables.
 *
 * @author Rafi Rahman
 * @version 31/10/2018
 */
public class DisplayCompanyTable {
    
    Tables tables = new Tables();
    
    /**
     * Constructor for objects of class DisplayCompanyTable.
     */
    public DisplayCompanyTable() {
        
        System.out.println();
        System.out.println("Displaying company table:");
        
        System.out.println();
        System.out.println("///////////////////////////////////////////////////");
        System.out.println();

        // Table format
        String leftAlignFormat = "|%-5s|%-20s|%-10s|%-10s|%n"; 
        
        // Table headings
        System.out.format("+-----+--------------------+----------+----------+%n");        
        System.out.format(leftAlignFormat,"ID","Name","Tax Exempt","Pickup Bay");
        System.out.format("+-----+--------------------+----------+----------+%n");        
        
        //Table contents
        tables.printCompanyDetails(tables.companyID[0]);
        tables.printCompanyDetails(tables.companyID[1]);
        tables.printCompanyDetails(tables.companyID[2]);
        tables.printCompanyDetails(tables.companyID[3]);
        tables.printCompanyDetails(tables.companyID[4]);
        
        System.out.format("+-----+--------------------+----------+----------+%n");        
        
        System.out.println();
        System.out.println("///////////////////////////////////////////////////");
        System.out.println();
        
    }
    
}