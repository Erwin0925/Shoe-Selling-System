package Controller;

import Database.DatabaseConnection;
import Model.Cart;
import Utility.CartSession;
import Utility.UserSession;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class OrderController {
    public int createOrder(double price) throws SQLException {
        String query = "INSERT Orders (TotalPrice, OrderDate, CustomerID) VALUES (?,?,?)";

        int customerId = UserSession.getInstance().getCurrentUser().getUserId();
        LocalDateTime localDateTime = LocalDateTime.now();
        java.sql.Timestamp currentDateTime = Timestamp.valueOf(localDateTime);

        try(Connection connection = new DatabaseConnection().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(1,price);
            preparedStatement.setTimestamp(2,currentDateTime);
            preparedStatement.setInt(3,customerId);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()){
                int orderId = resultSet.getInt(1);
                createOrderDetails(orderId);
                return orderId;
            }

        }
        return -1;
    }

    public void createOrderDetails(int orderId) throws SQLException {
        String query = "INSERT OrderDetails (OrderID, ProductID, ProductQuantity) VALUES (?,?,?)";

        List<Cart> cartList = CartSession.getInstance().getCartList();

        try(Connection connection = new DatabaseConnection().getConnection()){

            for(Cart product : cartList){
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1,orderId);
                preparedStatement.setInt(2,product.getProductId());
                preparedStatement.setInt(3,product.getProductQuantity());
                preparedStatement.executeUpdate();
            }
            CartSession.getInstance().clearCart();
        }
    }
}