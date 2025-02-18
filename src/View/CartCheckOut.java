package View;

import Controller.ProductController;
import Route.CustomerMenu;
import Utility.CartSession;
import Utility.GlobalScanner;

import java.sql.SQLException;
import java.util.Scanner;

public class CartCheckOut {
    private final Scanner scanner ;

    public CartCheckOut() {
        this.scanner = GlobalScanner.getInstance().getScanner();
    }

    public void CheckOutCart() throws SQLException {
        System.out.println("-----Cart Check Out-----");
        boolean flag = true;
        while (flag){
            System.out.print("Are you sure you wanted to check out ? (yes | no) : ");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("yes") ){
                ProductController productController = new ProductController();
                System.out.println(productController.deductProductCount());
                flag = false;
            }else if (choice.equalsIgnoreCase("no")){
                System.out.println();
                System.out.println("Bringing user back to the User Menu page.....");
                System.out.println();
                CustomerMenu customerMenu = new CustomerMenu();
                customerMenu.userOption();
                flag = false;
            }else{
                System.out.println("Option unavailable, please enter only (yes | no) ");
                System.out.println();
            }
        }
    }

}
