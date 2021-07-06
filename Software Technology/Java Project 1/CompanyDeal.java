public class CompanyDeal {
    
    public String id; // Company ID
    public String companyName; // Company name
    public boolean taxExempt; // True if tax exempt
    public String pickupBay; // Pickup bay ID
    
    public CompanyDeal(String idz, String companyNamez, boolean taxExemptz, String pickupBayz) {
        
        id = idz; // Company ID
        companyName = companyNamez; // Company name
        taxExempt = taxExemptz; // True if tax exempt
        pickupBay = pickupBayz; // Pickup bay ID
     
    }
    
    public String toString() {
        
        String retv = null;
        
        retv = id + ", " + companyName + ", " + taxExempt + ", " + pickupBay;
        return retv;
        
    }

}