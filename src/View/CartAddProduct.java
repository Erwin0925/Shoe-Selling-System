package View;

import Controller.CartController;
import Controller.CustomerController;
import Controller.ProductController;
import Utility.GlobalScanner;
import java.sql.SQLException;
import java.util.Scanner;


public class CartAddProduct {
    private final Scanner scanner;

    public CartAddProduct() {
        this.scanner = GlobalScanner.getInstance().getScanner();
    }

    public void productAdd() throws SQLException {

        while(true) {

            System.out.print("Enter the product id that you wanted to add into cart : ");
            int productId = scanner.nextInt();

            CustomerController customerController = new CustomerController();
            ProductController productController = new ProductController();
            if(productController.availabilityChecker(productId)){
                if (customerController.checkProductEligibility(productId)) {
                    System.out.print("Enter the product quantity that you wanted to acquire : ");
                    int productQuantity = scanner.nextInt();

                    if (productController.isProductSufficient(productId, productQuantity)) {
                        CartController cartController = new CartController();
                        cartController.addToCart(productId,productQuantity);

                        System.out.println("Product with id " + productId + " is added to the cart successfully!!");
                        System.out.println();
                    } else {
                        System.out.println("Product quantity is insufficient to fulfill your requirement");
                    }
                } else {
                    System.out.println("You need to verified yourself to the product stall before adding its product to your cart!!");
                }
            }else{
                System.out.println("Product is currently unavailable ");
            }
            if (!promptUserForBrowsing()){
                return;
            }
        }
    }

    public boolean promptUserForBrowsing (){
        System.out.print("Press 1 to continue browsing and ANY keys to previous page  : ");
        return scanner.nextInt() == 1;
    }

}