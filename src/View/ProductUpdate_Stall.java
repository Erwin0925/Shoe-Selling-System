package View;

import Controller.ProductController;
import Model.Products;
import ModelDto.UserDto;
import Utility.GlobalScanner;
import Utility.UserSession;

import java.sql.SQLException;
import java.util.Scanner;

public class ProductUpdate_Stall {

    private final Scanner scanner;

    public ProductUpdate_Stall() {
        this.scanner = GlobalScanner.getInstance().getScanner();
    }

    public String updateProduct() throws SQLException {
        UserDto userDto = UserSession.getInstance().getCurrentUser();
        if (userDto.getUserRole().equals("Stall")){
            System.out.println("-----Product Update Page-----");
            System.out.print("Please Enter the Product ID that you wanted to update : ");
            int productId = scanner.nextInt();
            ProductController productController = new ProductController();
            Products products = productController.checkStallPermission(productId);
            if (!products.isEmpty()){
                System.out.println("Context that you wanted to update : ");
                System.out.println("1\uFE0F⃣  Product Name");
                System.out.println("2\uFE0F⃣  Product Quantity");
                System.out.println("3\uFE0F⃣  Product Availability");
                System.out.println("4\uFE0F⃣  Product Price");
                System.out.println("Please Enter your choice : ");
                int userOption = scanner.nextInt();
                return handleUserOption(products,userOption);

            }else{
                return "Error 404!! Please Enter a valid ProductID that register under your stall";
            }

        }else{
            return "No permission to access this site. Please Login through stall account.";
        }
    }

    public String handleUserOption(Products products, int userOption) throws SQLException {
        String result = "Invalid option";
        switch (userOption){
            case 1:
                result = updateProductName(products);
                break;
            case 2:
                result = updateProductQuantity(products);
                break;
            case 3:
                result = updateProductAvailability(products);
                break;
            case 4:
                result = updateProductPrice(products);
                break;
        }
        return result;
    }

    public String updateProductName(Products product) throws SQLException {
        System.out.println("Please enter your product New Name : ");
        String newName = scanner.next();

        int productId = product.getProductId();
        int productQuantity = product.getProductQuantity();
        boolean productAvailability = product.getProductAvailability();
        double productPrice = product.getProductPrice();
        int product_stall_Id = product.getProduct_stall_Id();

        Products updatedProduct = new Products(productId,newName,productQuantity,
                productAvailability,productPrice,product_stall_Id);

        ProductController productController = new ProductController();
        return productController.updateProduct(updatedProduct);
    }

    public String updateProductQuantity(Products product) throws SQLException {
        System.out.println("Please enter your New Product Quantity : ");
        int newQuantity = scanner.nextInt();

        int productId = product.getProductId();
        String productName = product.getProductName();
        boolean productAvailability = product.getProductAvailability();
        double productPrice = product.getProductPrice();
        int product_stall_Id = product.getProduct_stall_Id();
        Products updatedProduct = new Products(productId,productName,newQuantity,
                productAvailability,productPrice,product_stall_Id);

        ProductController productController = new ProductController();
        return productController.updateProduct(updatedProduct);
    }

    public String updateProductAvailability(Products product) throws SQLException {
        System.out.println("Please enter your product Availability : ");
        boolean newAvailability = scanner.nextBoolean();

        int productId = product.getProductId();
        String productName = product.getProductName();
        int productQuantity = product.getProductQuantity();
        double productPrice = product.getProductPrice();
        int product_stall_Id = product.getProduct_stall_Id();
        Products updatedProduct = new Products(productId,productName,productQuantity,
                newAvailability,productPrice,product_stall_Id);

        ProductController productController = new ProductController();
        return productController.updateProduct(updatedProduct);
    }

    public String updateProductPrice(Products product) throws SQLException {
        System.out.println("Please enter your New Product Quantity : RM");
        int newPrice = scanner.nextInt();

        int productId = product.getProductId();
        String productName = product.getProductName();
        int productQuantity = product.getProductQuantity();
        boolean productAvailability = product.getProductAvailability();
        int product_stall_Id = product.getProduct_stall_Id();
        Products updatedProduct = new Products(productId,productName,productQuantity,
                productAvailability,newPrice,product_stall_Id);

        ProductController productController = new ProductController();
        return productController.updateProduct(updatedProduct);
    }


}
