package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/medicare";  //database connection string 
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 

    private static Connection connection = null;

    // Private constructor to prevent direct instantiation
    private Database() {}

    // Method to get a single shared connection
    public static Connection getConnection() {
        try {
            // ✅ Check if connection is null OR closed before returning it
            if (connection == null || connection.isClosed()) {  
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                //used in Java with JDBC to establish a database connection
                
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database Connected Successfully!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver Not Found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
        }
        return connection;
    }

    // Method to close the connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database Connection Closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Test connection
    public static void main(String[] args) {
        getConnection();
    }
}
