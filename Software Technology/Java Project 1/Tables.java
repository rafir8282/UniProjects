/**
 * Tables
 *
 * @author R. Cox
 * @version 29/8/2018
 */
public class Tables {
   
    // Public parallel arrays to store food details
    public static String foodId[] = {"DC","BEEF","CS","STRAW","EGG2"};
    public static String foodDesc[] = {"Date Cake","Beef Sandwich","Cheese Sandwich","Strawberry Desert","Scrambled Eggs"};
    public static int foodWeight[] = {134,164,206,177,112};
    public static double foodCost[] = {59.0,49.50,93.70,157.05,77.15};
    
    // Public parallel arrays to store company details (Rafi Rahman)
    public static String companyID[] = {"BFSC","ECP","NAASA","AARG","PUB"};
    public static String companyDesc[] = {"Blue Fish Space Co","Elon Cannon Personal","NAASA","AARG","General Public"};
    public static boolean taxExempt[] = {true,false,false,false,false};
    public static String pickupBayID[] = {"MERCY","KIT","MERCY","KAT","MAIL"};
    
    public CompanyDeal companyTable[] = new CompanyDeal[5];
    
    /**
     * Constructor for objects of class Tables.
     */
    public Tables() {
        
        companyTable[0] = new CompanyDeal("BFSC","Blue Fish Space Co",true,"MERCY");
        companyTable[1] = new CompanyDeal("ECP","Elon Cannon Personal",false,"KIT");
        companyTable[2] = new CompanyDeal("NAASA","NAASA",false,"MERCY");
        companyTable[3] = new CompanyDeal("AARG","AARG",false,"KAT");
        companyTable[4] = new CompanyDeal("PUB","General Public",false,"MAIL");

    }
   
    /**
     * Returns -1 if foodId is not found, otherwise a number which is the parallel arrays index.
     */
    public int lookupFoodNum(String foodIdz) {
        
        for(int i = 0; i < 5; i++) {
            
            if(foodId[i].compareTo(foodIdz) == 0) {
                
                return i;
                
            } 
            
        }
        
        return -1; // Not found
     
    }
    
    /**
     * Returns -1 if companyID is not found, otherwise a number which is the parallel arrays index. (Rafi Rahman)
     */
    public int lookupCompanyNum(String companyIDz) {
        
        for(int i = 0; i < 5; i++) {
            
            if(companyID[i].compareTo(companyIDz) == 0) {
                
                return i;
                
            } 
            
        }
        
        return -1; // Not found
     
    }
    
    /**
     * Prints out the food details.
     */
    public void printFoodDetails(String foodIdz) {
        
        int fn = lookupFoodNum(foodIdz);
        
        if(fn == -1) {
            
            System.out.println("ERROR: " + foodIdz + " not found.");
            return; // Early exit on error
        
        }
        
        String leftAlignFormat = "|%-5s|%-20s|%-10s|%-,10.2f|%n"; 
        
        System.out.format(leftAlignFormat,foodId[fn],foodDesc[fn],foodWeight[fn],foodCost[fn]);
    
    }
    
    /**
     * Prints out the company details. (Rafi Rahman)
     */
    public void printCompanyDetails(String companyIDz) {
        
        int cn = lookupCompanyNum(companyIDz);
        
        if(cn == -1) {
            
            System.out.println("ERROR: " + companyIDz + " not found.");
            return; // Early exit on error
        
        }
        
        String leftAlignFormat = "|%-5s|%-20s|%-10s|%-10s|%n"; 
        
        System.out.format(leftAlignFormat,companyID[cn],companyDesc[cn],taxExempt[cn],pickupBayID[cn]); 
        
    }
    
    public CompanyDeal lookupCompany(String coyId) {
        
        for(int i = 0; i < 5; i++) {
        
            if(companyTable[i].id.compareTo(coyId) == 0) {
                
                return companyTable[i];
            
            }  
        
        }
        
        return null; // Not found
        
    }
    
}