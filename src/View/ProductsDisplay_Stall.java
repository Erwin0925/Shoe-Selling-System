package View;

import Controller.ProductController;
import Model.Products;

import java.sql.SQLException;
import java.util.List;

public class ProductsDisplay_Stall {
    public boolean displayProducts() throws SQLException {
        ProductController productController = new ProductController();
        List<Products> products = productController.fetchStallProducts();
        if (products.isEmpty()){
            System.out.println("˗ˏˋ ★ ˎˊ˗ welcome !! Register a Product to start you business.");
            return false;
        }else{
            int totalProduct = products.size();
            System.out.println("There are a total of " + totalProduct + " Products register under your stall : ");

            int i = 1;
            for (Products product : products){
                System.out.println("⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘ " + i++ + " ⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘");
                System.out.println("Product ID  : " + product.getProductId());
                System.out.println("Product Name  : " + product.getProductName());
                System.out.println("Product Quantity  : " + product.getProductQuantity());
                System.out.println("Product Price  : RM" + product.getProductPrice());
                System.out.println("꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷");
            }
            return true;
        }
    }
}
