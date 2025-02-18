package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static final String dbUrl = "jdbc:mysql://127.0.0.1:3306/ordercreationsystemdb";
    public static final String dbUser = "root";
    public static final String dbPassword = "Qa123490";

    public Connection getConnection() {

        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            System.out.println("Database Connected");
            return connection;
        } catch (SQLException exception) {
            throw new IllegalStateException("Cannot connect the database", exception);
        }

    }

}