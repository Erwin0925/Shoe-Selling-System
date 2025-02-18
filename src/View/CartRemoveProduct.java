package View;

import Controller.CartController;
import Utility.CartSession;
import Utility.GlobalScanner;

import java.util.Scanner;

public class CartRemoveProduct {

    private final Scanner scanner;

    public CartRemoveProduct() {
        this.scanner = GlobalScanner.getInstance().getScanner();
    }


    public void removeProduct(){
        System.out.print("Enter the product id that you remove from the cart : ");
        int productId = scanner.nextInt();
        System.out.println("You wanted to remove all the product or only certain quantity ?");
        System.out.print("Press 1 to remove every product '" + productId + "' from Cart and" +
                " 2 to proceed with the amount you wanted to remove : ");
        int option = scanner.nextInt();
        CartController cartController = new CartController();
        if (option == 1){
            cartController.removeCartProduct(productId);
        }else if (option == 2){
            int originalQuantity = CartSession.getInstance().getProductQuantity(productId);
            System.out.print("The quantity that you desired to remove :  ");
            int quantity = scanner.nextInt();
            if (originalQuantity < quantity){
                System.out.println("The number you entered is more than the quantity inside your cart");
            }else{
                cartController.removeCartProduct(productId,quantity);
            }
        }
    }
}
