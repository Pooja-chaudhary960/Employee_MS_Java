package Employee_Management;

import java.sql.*;

public class ConnectionClass {
    Connection con;
    Statement stm;

    // Constructor to establish connection
    ConnectionClass() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establishing connection to MySQL
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/Employee_Management", "root", "Pooja@123");

            // Initialize the Statement object
            stm = con.createStatement();

            System.out.println("Connection established successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main method to run the class
    public static void main(String[] args) {
        new ConnectionClass();  // instance of ConnectionClass
    }
}