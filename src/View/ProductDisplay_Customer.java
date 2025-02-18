package View;

import Controller.ProductController;
import Model.Products;

import java.sql.SQLException;
import java.util.List;

public class ProductDisplay_Customer {

    public void displayProducts() throws SQLException {
        ProductController productController = new ProductController();
        List<Products> products = productController.fetchAllProducts();

        int totalProduct = products.size();
        if (totalProduct >0){

            System.out.println("There are a total of " + totalProduct + " products : ");

            int i = 1;
            for (Products product : products) {
                System.out.println("⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘ " + i++ + " ⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘⫘");
                System.out.println("Product ID  : " + product.getProductId());
                System.out.println("Product Name  : " + product.getProductName());
                System.out.println("Product Quantity Left  : " + product.getProductQuantity());
                System.out.println("Product Price  : RM" + product.getProductPrice());
                System.out.println("Product Belong to Stall with ID : " + product.getProduct_stall_Id());
                System.out.println("꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷");
            }
        }else{
            System.out.println("There is still no product selling in our platform  ｡°(°.◜ᯅ◝°)°｡ ");
        }
    }
}
