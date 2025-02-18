package View;

import Controller.ProductController;
import Model.Products;
import ModelDto.UserDto;
import Utility.GlobalScanner;
import Utility.UserSession;

import java.sql.SQLException;
import java.util.Scanner;

public class ProductAdd_Stall {
    private final Scanner scanner;

    public ProductAdd_Stall() {
        this.scanner = GlobalScanner.getInstance().getScanner();
    }

    public String productRegistration(){
        try{
            UserDto userDto = UserSession.getInstance().getCurrentUser();
            if (userDto.getUserRole().equals("Stall")) {

                System.out.println("-----Product Registration Page-----");
                System.out.print("Please Enter your Product Display Name : ");
                String productName = scanner.next();
                System.out.print("Please Enter your initial  Quantity Size : ");
                int quantity = scanner.nextInt(); //later on need to configure to prevent negative quantity
                System.out.print("Please Enter your product's selling price : ");
                int price = scanner.nextInt(); //later on need to configure to prevent negative quantity
                int product_stall_Id = UserSession.getInstance().getCurrentUser().getUserId();

                Products product = new Products(productName, quantity, true, price, product_stall_Id);
                ProductController productsController = new ProductController();

                return productsController.addProduct(product);
            }else{
                return "No permission to access this site. Please Login through stall account.";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
