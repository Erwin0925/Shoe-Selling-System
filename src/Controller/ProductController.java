package Controller;

import Database.DatabaseConnection;
import Model.Cart;
import Model.Products;
import ModelDto.UserDto;
import Utility.CartSession;
import Utility.UserSession;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductController {

    public String addProduct(Products product) throws SQLException {
        UserDto userDto = UserSession.getInstance().getCurrentUser();
        if (userDto.getUserRole().equals("Stall")){
            String query = "INSERT INTO products (Name, Quantity, Availability, Price, StallID) VALUES (?,?,?,?,?)";

            try(Connection connection = new DatabaseConnection().getConnection()){
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,product.getProductName());
                preparedStatement.setInt(2,product.getProductQuantity());
                preparedStatement.setBoolean(3,true);
                preparedStatement.setDouble(4,product.getProductPrice());
                preparedStatement.setInt(5, product.getProduct_stall_Id());

                preparedStatement.executeUpdate();

                return "Product add successfully";
            }

        }else{
            return "No permission to access this site. Please Login through stall account.";
        }
    }

    public String updateProduct(Products product) throws SQLException {
        UserDto userDto = UserSession.getInstance().getCurrentUser();
        if (userDto.getUserRole().equals("Stall")){

            String query = "Update Products SET NAME =?, Quantity =?, Availability =?, Price =? WHERE ProductID =?";

            try(Connection connection = new DatabaseConnection().getConnection()){

                int productId = product.getProductId();
                String productName = product.getProductName();
                int productQuantity = product.getProductQuantity();
                boolean productAvailability = product.getProductAvailability();
                double productPrice = product.getProductPrice();

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, productName);
                preparedStatement.setInt(2, productQuantity);
                preparedStatement.setBoolean(3,productAvailability);
                preparedStatement.setDouble(4,productPrice);
                preparedStatement.setInt(5,productId);

                int rowAffected = preparedStatement.executeUpdate();

                return "There are a total of " + rowAffected + " got affected";
            }

        }else{
            return "No permission to access this site. Please Login through stall account.";
        }
    }

    public Products checkStallPermission(int productId){
        int stallId = UserSession.getInstance().getCurrentUser().getUserId();
        String query = String.format("Select * from Products where ProductID = %d and StallID =%d", productId, stallId );

        try(Connection connection = new DatabaseConnection().getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next()){
                int productId2 = resultSet.getInt("ProductID");
                String productName = resultSet.getString("Name");
                int productQuantity = resultSet.getInt("Quantity");
                boolean productAvailability = resultSet.getBoolean("Availability");
                double productPrice = resultSet.getDouble("Price");
                int product_stall_Id = resultSet.getInt("StallID");

                return new Products(productId2, productName, productQuantity, productAvailability, productPrice, product_stall_Id);

            }else{
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Products> fetchStallProducts() throws SQLException {

        List<Products> products = new ArrayList<>();
        int stallId = UserSession.getInstance().getCurrentUser().getUserId();
        String query = String.format("Select * from Products where StallID = %d", stallId);

        try(Connection connection = new DatabaseConnection().getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                int productId = resultSet.getInt("ProductID");
                String productName = resultSet.getString("Name");
                int productQuantity = resultSet.getInt("Quantity");
                boolean productAvailability = resultSet.getBoolean("Availability");
                double productPrice = resultSet.getDouble("Price");
                int product_stall_Id = resultSet.getInt("StallID");

                Products product = new Products(productId, productName, productQuantity, productAvailability, productPrice, product_stall_Id);
                products.add(product);
            }
        }
        return products;
    }

    public String deleteProduct(int ProductId) throws SQLException {

        String query = String.format("Delete FROM Products WHERE ProductID = %d",ProductId);

        try(Connection connection = new DatabaseConnection().getConnection()){
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(query);

            return "Successfully deleted and there are " + rowAffected + " row is Affected";
        }
    }

    public String deductProductCount() throws SQLException{

        List<Cart> cartList = CartSession.getInstance().getCartList();

        String retrieveQuery = "Select Quantity, Price from Products where ProductID =?";
        String updateQuery = "Update Products SET Quantity =?, Availability  =? WHERE ProductID =?";

        try(Connection connection = new DatabaseConnection().getConnection()){
            int rowAffected = 0;
            double totalPrice = 0;
                for(Cart cartProduct : cartList) {
                    int productId = cartProduct.getProductId();
                    PreparedStatement preparedStatement = connection.prepareStatement(retrieveQuery);
                    preparedStatement.setInt(1, productId);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {

                        int productQuantity = resultSet.getInt("Quantity");
                        if (productQuantity == 0 || productQuantity < cartProduct.getProductQuantity()) {
                            return "ProductId '" + productId + "' is Out Of Stock (╥‸╥)";
                        } else {
                            int newQuantity = productQuantity - cartProduct.getProductQuantity();
                            boolean availability = !(newQuantity == 0);
                            PreparedStatement preparedStatement2 = connection.prepareStatement(updateQuery);
                            preparedStatement2.setInt(1, newQuantity);
                            preparedStatement2.setBoolean(2,availability);
                            preparedStatement2.setInt(3, productId);
                            totalPrice += resultSet.getDouble("Price");

                            rowAffected = rowAffected + preparedStatement2.executeUpdate();
                        }

                    } else {
                        return "ProductId '" + productId + "' Not Found";
                    }
                }
                OrderController orderController = new OrderController();
                int orderId = orderController.createOrder(totalPrice);
                return "Your orderId for this purchase will be : " + orderId;
        }
    }

    public List<Products> fetchAllProducts() throws SQLException {

        List<Products> products = new ArrayList<>();

        String query = "Select * from Products Order By StallID, Availability DESC";

        try(Connection connection = new DatabaseConnection().getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                int productId = resultSet.getInt("ProductID");
                String productName = resultSet.getString("Name");
                int productQuantity = resultSet.getInt("Quantity");
                boolean productAvailability = resultSet.getBoolean("Availability");
                double productPrice = resultSet.getDouble("Price");
                int product_stall_Id = resultSet.getInt("StallID");

                Products product = new Products(productId, productName, productQuantity, productAvailability, productPrice, product_stall_Id);
                products.add(product);
            }
        }
        return products;
    }

    public Products fetchProduct(int productId) throws SQLException{
        String query = String.format("Select * from Products where ProductID = %d",productId);

        try(Connection connection = new DatabaseConnection().getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next()){
                int productId2 = resultSet.getInt("ProductID");
                String productName = resultSet.getString("Name");
                int productQuantity = resultSet.getInt("Quantity");
                boolean productAvailability = resultSet.getBoolean("Availability");
                double productPrice = resultSet.getDouble("Price");
                int product_stall_Id = resultSet.getInt("StallID");

                return new Products(productId2, productName, productQuantity, productAvailability, productPrice, product_stall_Id);
            }

        }
        return null;
    }

    public boolean QuantityChecker(int productId, int desireQuantity) throws SQLException {

        Products product = fetchProduct(productId);

        if (product.getProductQuantity() >= desireQuantity) {
            return true;
        }
        return false;
    }

    public boolean availabilityChecker(int productId) throws SQLException {

        Products product = fetchProduct(productId);

        if (product.getProductAvailability()) {
            return true;
        }
        return false;
    }

    public boolean isProductSufficient(int newProductId, int newProductQuantity) throws SQLException {

        List<Cart> cart= CartSession.getInstance().getCartList();
        for (int i = 0; i < cart.size(); i++ ){
            if (cart.get(i).getProductId() == newProductId ){
                int initialQuantity = cart.get(i).getProductQuantity();
                int totalQuantity = initialQuantity + newProductQuantity;

                boolean isStockEnough = QuantityChecker(newProductId, totalQuantity);
                if (isStockEnough){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return QuantityChecker(newProductId, newProductQuantity);
    }

}
