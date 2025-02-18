package View;

import Controller.ProductController;
import Utility.GlobalScanner;
import Utility.UserSession;

import java.sql.SQLException;
import java.util.Scanner;

public class ProductDelete_Stall {
    private final Scanner scanner;

    public ProductDelete_Stall() {
        this.scanner = GlobalScanner.getInstance().getScanner();
    }

    public String deleteProduct() throws SQLException {

        int stallId = UserSession.getInstance().getCurrentUser().getUserId();

        System.out.println("-----Product Deletion Page-----");
        System.out.print("Please Enter the ProductId that you wanted to delete : ");
        int productId = scanner.nextInt();
        ProductController productController = new ProductController();

        if (!productController.checkStallPermission(productId).isEmpty()){
            return productController.deleteProduct(productId);

        }else{
            return "Error 404!! Please Enter a valid ProductID that register under your stall";
        }
    }
}
