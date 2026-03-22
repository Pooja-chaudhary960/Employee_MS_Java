package Employee_Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class View_Employee extends JFrame {
    private JLabel idLabel, nameLabel, positionLabel, departmentLabel;
    private JTextField idField, nameField, positionField, departmentField, searchField;
    private JButton searchButton;
    private Connection con;

    public View_Employee() {
        setTitle("View Employee Profile");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));  // GridLayout for better spacing

        // Add search field and label
        idLabel = new JLabel("Enter Employee ID:");
        searchField = new JTextField();
        searchButton = new JButton("Search");

        // Add labels for displaying employee details
        nameLabel = new JLabel("Name:");
        positionLabel = new JLabel("Position:");
        departmentLabel = new JLabel("Department:");

        // Add text fields for displaying employee details
        nameField = new JTextField();
        positionField = new JTextField();
        departmentField = new JTextField();

        // Set fields to be non-editable
        nameField.setEditable(false);
        positionField.setEditable(false);
        departmentField.setEditable(false);

        // Add components to the frame
        add(idLabel);
        add(searchField);
        add(new JLabel()); // Empty label for spacing
        add(searchButton);
        
        add(nameLabel);
        add(nameField);
        add(positionLabel);
        add(positionField);
        add(departmentLabel);
        add(departmentField);

        // Connect to the database and initialize search functionality
        connectToDatabase();

        // Add action listener for the search button
        searchButton.addActionListener(e -> searchEmployeeById());

        setVisible(true);
    }

    // Connect to the database
    private void connectToDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3307/Employee_Management";
            String user = "root";
            String password = "Pooja@123";
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection failed!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Search for employee by ID
    private void searchEmployeeById() {
        String employeeId = searchField.getText().trim();

        if (employeeId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Employee ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String query = "SELECT * FROM employees WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(employeeId));
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Set the fields with employee details
                nameField.setText(rs.getString("name"));
                positionField.setText(rs.getString("position"));
                departmentField.setText(rs.getString("department"));
            } else {
                JOptionPane.showMessageDialog(this, "Employee not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error searching employee.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Employee ID", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new View_Employee(); // Open the View Employee page
    }
}