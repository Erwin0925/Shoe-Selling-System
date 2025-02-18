package Controller;

import Database.DatabaseConnection;
import Model.Stall_User_Restriction;
import Model.Stalls;
import ModelDto.UserDto;
import Utility.UserSession;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StallController {

    public String stallRegistration(Stalls stalls){

        String name = stalls.getStallName();
        String password = stalls.getStallPassword();

        String query = "INSERT INTO stalls (Name, Password) VALUES (?, ?)";

        try(Connection connection = new DatabaseConnection().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,password);
            preparedStatement.executeUpdate();
            return "Stall Registration Successfully";

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean stallLogin(Stalls stall){
        String name = stall.getStallName();
        String password = stall.getStallPassword();

        String query = "Select * from stalls where Name =?";

        try(Connection connection = new DatabaseConnection().getConnection()){
            PreparedStatement preparedStatement =connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                String stallPassword = resultSet.getString("Password");
                if(stallPassword.equals(password)){
                    int stallId = resultSet.getInt("StallID");
                    UserDto userDto = new UserDto(stallId,name,stallPassword,"Stall");
                    UserSession.getInstance().setCurrentUser(userDto);
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Stalls> fetchStalls() throws SQLException {
        String query = "Select * from Stalls ORDER BY StallID";
        List<Stalls> stalls = new ArrayList<>();

        try(Connection connection = new DatabaseConnection().getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                int stallId = resultSet.getInt("StallID");
                String stallName = resultSet.getString("Name");

                Stalls stall = new Stalls(stallId, stallName);
                stalls.add(stall);
            }
        }
        return stalls;
    }

    public List<Stall_User_Restriction> fetchCustomerRequest() throws SQLException {
        List<Stall_User_Restriction> stallUserRestrictions = new ArrayList<>();

        try(Connection connection = new DatabaseConnection().getConnection()){
            int userId = UserSession.getInstance().getCurrentUser().getUserId();

            String query = "Select * from StallUserRestriction where StallID = ? ORDER BY Status Desc";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int stallUserRestrictId = resultSet.getInt("StallUserRestrictID");
                int customerId = resultSet.getInt("CustomerID");
                int stallId = resultSet.getInt("StallID");
                String status = resultSet.getString("Status");

                Stall_User_Restriction stallUserRestriction = new Stall_User_Restriction(stallUserRestrictId,customerId,stallId,status);
                stallUserRestrictions.add(stallUserRestriction);
            }
        }
        return stallUserRestrictions;
    }

    public String customerRequestApproval(Stall_User_Restriction stallUserRestriction) throws SQLException {

        String query = "UPDATE StallUserRestriction SET Status = ? where StallUserRestrictID =?";
        try(Connection connection = new DatabaseConnection().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,stallUserRestriction.getStatus());
            preparedStatement.setInt(2,stallUserRestriction.getStallUserRestrictId());
            int rowAffect = preparedStatement.executeUpdate();

            return "Request Updated Successfully and there are " + rowAffect + " row got affected";
        }
    }
}
