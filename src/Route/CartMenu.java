package Route;

import Controller.CartController;
import Utility.GlobalScanner;
import Utility.UserSession;
import View.*;

import java.sql.SQLException;
import java.util.Scanner;

public class CartMenu {
    private final Scanner scanner;

    public CartMenu() {
        this.scanner = GlobalScanner.getInstance().getScanner();
    }

    public int displayMenu() throws SQLException {

        System.out.println("------------------------------------------------");
        System.out.println("\uD83D\uDED2\uD83D\uDECD\uFE0F✨" +
                "Happy-Go-Lucky Platform • " + UserSession.getInstance().getCurrentUser().getUserName() + "'s Cart");
        System.out.println("------------------------------------------------");
        System.out.println();
        System.out.println("Please Choose One Of The Option To Proceed...");
        System.out.println(" 1. -- Proceed CheckOut");
        System.out.println(" 2. -- Remove product from Cart");
        System.out.println(" 3. -- Back to Previous Page");
        System.out.print("Enter your the number of choice to proceed : ");
        return scanner.nextInt();
    }

    public void userOption() throws SQLException {
        int choice = displayMenu();
        handleUserOption(choice);
    }

    public void handleUserOption(int option) throws SQLException {
        switch (option){
            case 1:
                CartCheckOut cartCheckOut = new CartCheckOut();
                cartCheckOut.CheckOutCart();
                CustomerMenu customerMenu = new CustomerMenu();
                customerMenu.userOption();
            case 2:
                CartRemoveProduct cartRemoveProduct = new CartRemoveProduct();
                cartRemoveProduct.removeProduct();
                userOption();
                break;
            case 3 :
                MainMenu mainMenu = new MainMenu();
                mainMenu.getUserOption();
                break;
            default :
                System.out.println();
                System.out.println("Option unavailable, please only choose 1 - 3 only.");
                System.out.println();
                userOption();
                break;
        }
    }
}
