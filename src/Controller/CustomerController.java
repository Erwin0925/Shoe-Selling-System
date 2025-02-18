package Controller;

import Database.DatabaseConnection;
import Model.Customers;
import ModelDto.UserDto;
import Utility.UserSession;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {

    public List<Customers> fetchCustomers() throws SQLException {

        List<Customers> CustomerList = new ArrayList<>();

        String query = "Select * from customers";

        try(Connection connection = new DatabaseConnection().getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                int id = resultSet.getInt("CustomerID");
                String name = resultSet.getString("Name");
                String password = resultSet.getString("Password");

                CustomerList.add(new Customers(id,name,password));
            }

        }catch (SQLException e){
            throw new SQLException("!! Cannot access to the table due to : ",e);
        }
        return CustomerList;
    }

    public String registerCustomer(Customers customers){
        String query = "INSERT customers(Name,Password) VALUES";

        String name = customers.getCustomerName();
        String password = customers.getCustomerPassword();

        String input = String.format("('%s', '%s')", name, password);

        try(Connection connection = new DatabaseConnection().getConnection()){
            Statement statement = connection.createStatement();

            statement.executeUpdate(query + input);

            return "Registration Successful";

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean loginCustomer(Customers customer){

        String name = customer.getCustomerName();
        String password = customer.getCustomerPassword();

        String query = "SELECT * FROM customers WHERE Name=?";

        try(Connection connection = new DatabaseConnection().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                String UserPassword = resultSet.getString("Password");

                if (password.equals(UserPassword)){
                    int UserId = resultSet.getInt("CustomerID");
                    String UserName = resultSet.getString("Name");

                    UserDto userDto = new UserDto(UserId,UserName,UserPassword,"Customer");
                    UserSession.getInstance().setCurrentUser(userDto);
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public String registerStallAccess(int stallId) throws SQLException {

        int userId = UserSession.getInstance().getCurrentUser().getUserId();

        String checkStallExistenceQuery = String.format("Select EXISTS(Select * from Stalls where stallID = %d)",stallId);
        String checkAccessRequestQuery = String.format("Select * from StallUserRestriction where CustomerID =%d AND stallID =%d",userId, stallId);

        try(Connection connection = new DatabaseConnection().getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(checkStallExistenceQuery);

            if(resultSet.next() && resultSet.getBoolean(1)){
                Statement statement2 = connection.createStatement();
                ResultSet resultSet2 = statement2.executeQuery(checkAccessRequestQuery);

                if(resultSet2.next()){
                    String status = resultSet2.getString("Status");
                    return "You have already made a request before and the status for your request is " + status;
                }else{
                    String insertQuery = "INSERT StallUserRestriction (CustomerID, StallID, Status) VALUES (?,?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setInt(1, userId);
                    preparedStatement.setInt(2,stallId);
                    preparedStatement.setString(3,"Pending");
                    preparedStatement.executeUpdate();

                    return "Request Successful and waiting for approval from stall owner";
                }
            }else{
                return "Stall not Found !";
            }

        }
    }

    public boolean checkProductEligibility(int productId) throws SQLException {
        int userId = UserSession.getInstance().getCurrentUser().userId;

        String getStallIdQuery = String.format("Select StallID from Products where ProductID = %s",productId);

        String checkExistanceQuery = "Select EXISTS(Select * from StallUserRestriction where CustomerID =? AND StallId =?)";

        try(Connection connection = new DatabaseConnection().getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getStallIdQuery);

            if (resultSet.next()){
                int stallId = resultSet.getInt("StallID");

                PreparedStatement preparedStatement = connection.prepareStatement(checkExistanceQuery);
                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, stallId);
                ResultSet resultSet2 = preparedStatement.executeQuery();

                if(resultSet2.next()){
                    return resultSet2.getBoolean(1);
                }

            }

        }
        return false;
    }

}
