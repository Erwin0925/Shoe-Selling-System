package Route;

import Utility.GlobalScanner;
import Utility.UserSession;
import View.*;

import java.sql.SQLException;
import java.util.Scanner;

public class StallMenu {
    private final Scanner scanner;

    public StallMenu() {
        this.scanner = GlobalScanner.getInstance().getScanner();
    }

    public int DisplayMenu(){
        try{
            String stallName = UserSession.getInstance().getCurrentUser().getUserName();
            System.out.println("------------------------------------------------");
            System.out.println("⌗ ωᥱℓᥴ\uD801\uDC2B꧑ᥱ ! \uD83C\uDF08 Happy-Go-Lucky Platform • " + stallName);
            System.out.println("------------------------------------------------");
            ProductsDisplay_Stall productsDisplayStall = new ProductsDisplay_Stall();
            boolean checkProduct = productsDisplayStall.displayProducts();
            System.out.println();
            if (!checkProduct){
                System.out.println("Please Choose One Of The Option To Proceed...");
                System.out.println(" 1. -- Register Product");
            }else{
                System.out.println("Please Choose One Of The Option To Proceed...");
                System.out.println(" 1. -- Register Product");
                System.out.println(" 2. -- Update Product");
                System.out.println(" 3. -- Delete Product");
                System.out.println(" 4. -- Approve Customer Access Request");
                System.out.println(" 5. -- Log out");
            }
            System.out.print("Enter your the number of choice to proceed : ");
            return scanner.nextInt();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void HandleUserOption(int choice) throws SQLException {
        switch(choice){
            case 1:
                ProductAdd_Stall productAddStall = new ProductAdd_Stall();
                System.out.println(productAddStall.productRegistration());
                getStallOption();
                break;

            case 2:
                ProductUpdate_Stall productUpdateStall = new ProductUpdate_Stall();
                System.out.println(productUpdateStall.updateProduct());
                getStallOption();
                break;
            case 3:
                ProductDelete_Stall productDeleteStall = new ProductDelete_Stall();
                System.out.println(productDeleteStall.deleteProduct());
                getStallOption();
                break;
            case 4:
                StallApproveRequest stallApproveRequest = new StallApproveRequest();
                System.out.println(stallApproveRequest.approveRequest());
                getStallOption();
            case 5 :
                UserSession.getInstance().clear();
                MainMenu mainMenu = new MainMenu();
                mainMenu.getUserOption();
                break;
            default :
                System.out.println();
                System.out.println("Option unavailable, please only choose 1 - 5 only.");
                System.out.println();
                getStallOption();
                break;
        }
    }

    public void getStallOption() throws SQLException {
        int choice = DisplayMenu();
        HandleUserOption(choice);
    }
}
