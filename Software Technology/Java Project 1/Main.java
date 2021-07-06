/**
 * This class is for generating the menu.
 *
 * @author Rafi Rahman
 * @version 31/10/2018
 */
import java.util.Scanner;

public class Main {

    Scanner sc = new Scanner(System.in);
    
    /**
     * Constructor for objects of class Main.
     */
    public Main() {
        
        // Menu options
        System.out.println("Menu:");
        System.out.println();
        System.out.println("C - Display company table.");
        System.out.println("F - Display food table.");
        System.out.println("QU - Generate quote.");
        System.out.println("QF - Generate quote to text file.");
        System.out.println("Q - Quit.");
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("(Inputs are case-sensitive.)");
        
        // Used to loop the menu when the input is invalid
        boolean menuLoop = true;
        
        while(menuLoop) {
            
            String menuInput = sc.nextLine();
        
            switch(menuInput) {
                
                // Quit
                case "Q":
                menuLoop = false;
                System.out.println();
                System.out.println("Goodbye.");
                System.exit(0);
                break;
                
                // Generate quote
                case "QU":
                menuLoop = false;
                new GenerateQuote();
                break;
                
                // Food table
                case "F":
                menuLoop = false;
                new DisplayFoodTable();
                break;
                
                // Company table
                case "C":
                menuLoop = false;
                new DisplayCompanyTable();
                break;
                
                // Generate quote to text file
                case "QF":
                menuLoop = false;
                new PrintTextFile();
                break;
                
                // Invalid input
                default:
                menuLoop = true;
                System.out.println("Invalid input. Try something from the menu:");
                break;
                         
            }
            
        }
            
    }
    
    /**
     * The main method: this is where the program starts.
     */
    public static void main(String[] args) {
        
        System.out.print("\u000c");
        
        // Used to loop the whole program
        boolean loop = true;
        
        while(loop) {
            
            new Main();
            
        }
        
    }

}