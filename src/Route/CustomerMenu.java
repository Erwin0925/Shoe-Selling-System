package Route;

import Controller.CartController;
import Model.Cart;
import Utility.GlobalScanner;
import Utility.UserSession;
import View.CartAddProduct;
import View.CartView;
import View.CustomerRequestAccess;
import View.ProductDisplay_Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class CustomerMenu {
    private final Scanner scanner;

    public CustomerMenu() {
        this.scanner = GlobalScanner.getInstance().getScanner();
    }

    public int displayMenu() throws SQLException {

        System.out.println("------------------------------------------------");
        System.out.println("\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2 " +
                "Welcome to Happy-Go-Lucky Platform " +
                "\uD83D\uDED2\uD83D\uDED2\uD83D\uDED2\uD83D");
        System.out.println("------------------------------------------------");
        System.out.println();
        System.out.println("Please Choose One Of The Option To Proceed...");
        System.out.println(" 1. -- Request Access from Stall");
        System.out.println(" 2. -- View Produtcs");
        System.out.println(" 3. -- View Cart");
        System.out.println(" 4. -- Log Out");
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
                CustomerRequestAccess customerRequestAccess = new CustomerRequestAccess();
                System.out.println(customerRequestAccess.requestAccess());
                userOption();
                break;
            case 2:
                ProductDisplay_Customer productDisplayCustomer = new ProductDisplay_Customer();
                productDisplayCustomer.displayProducts();

                CartAddProduct cartAddProduct = new CartAddProduct();
                cartAddProduct.productAdd();
                userOption();
                break;
            case 3:
                CartView cartView = new CartView();
                cartView.DisplayCart();
                CartMenu cartMenu = new CartMenu();
                cartMenu.userOption();
                break;
            case 4 :
                UserSession.getInstance().clear();
                MainMenu mainMenu = new MainMenu();
                mainMenu.getUserOption();
                break;
            default :
                System.out.println();
                System.out.println("Option unavailable, please only choose 1 - 4 only.");
                System.out.println();
                userOption();
        }
    }




}
