package Employee_Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Add_Employee extends JFrame {

    private JTextField nameField, positionField, departmentField;
    private JButton saveButton;

    // Database connection
    private Connection con;

    // Constructor to set up the GUI
    Add_Employee() {
        setTitle("Add Employee");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Set layout
        setLayout(new GridLayout(5, 2));

        // Add form labels and fields
        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Position:"));
        positionField = new JTextField();
        add(positionField);

        add(new JLabel("Department:"));
        departmentField = new JTextField();
        add(departmentField);

        // Add the save button
        saveButton = new JButton("Save");
        add(saveButton);

        // Action listener for the save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the data from form fields
                String name = nameField.getText();
                String position = positionField.getText();
                String department = departmentField.getText();

                // Insert data into database
                saveEmployeeToDatabase(name, position, department);
            }
        });

        // Connect to database
        connectToDatabase();

        setVisible(true);
    }

    // Method to connect to the MySQL database
    private void connectToDatabase() {
        try {
            // Replace with your MySQL credentials
            String url = "jdbc:mysql://localhost:3307/Employee_Management";
            String user = "root";  
            String password = "Pooja@123";  

            // Establish connection
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection failed!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to save employee data to the database
    private void saveEmployeeToDatabase(String name, String position, String department) {
        if (name.isEmpty() || position.isEmpty() || department.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String query = "INSERT INTO employees (name, position, department) VALUES (?, ?, ?)";

        try (PreparedStatement pst = con.prepareStatement(query)) {
         
            pst.setString(1, name);
            pst.setString(2, position);
            pst.setString(3, department);

            // Execute the update (insert)
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Employee added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields(); 
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error adding employee.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to clear form fields after data is saved
    private void clearFields() {
        nameField.setText("");
        positionField.setText("");
        departmentField.setText("");
    }

    public static void main(String[] args) {
        new Add_Employee(); 
    }
}