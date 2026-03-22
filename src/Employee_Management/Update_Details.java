package Employee_Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Update_Details extends JFrame {
    private JLabel idLabel, nameLabel, positionLabel, departmentLabel;
    private JTextField idField, nameField, positionField, departmentField, searchField;
    private JButton searchButton, updateButton;
    private Connection con;

    public Update_Details() {
        setTitle("Update Employee Details");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10));

        // Add search field and label
        idLabel = new JLabel("Enter Employee ID:");
        searchField = new JTextField();
        searchButton = new JButton("Search");

        // Add labels for displaying employee details
        nameLabel = new JLabel("Name:");
        positionLabel = new JLabel("Position:");
        departmentLabel = new JLabel("Department:");

        // Add text fields for displaying employee details
        idField = new JTextField();
        nameField = new JTextField();
        positionField = new JTextField();
        departmentField = new JTextField();

        // Make the ID field non-editable
        idField.setEditable(false);

        // Set fields to be non-editable initially
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

        // Add update button
        updateButton = new JButton("Update Details");
        add(new JLabel()); // Empty label for alignment
        add(updateButton);

        // Connect to the database
        connectToDatabase();

        // Add action listener for search button
        searchButton.addActionListener(e -> searchEmployeeById());

        // Add action listener for update button
        updateButton.addActionListener(e -> updateEmployeeDetails());

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
                idField.setText(rs.getString("id"));
                nameField.setText(rs.getString("name"));
                positionField.setText(rs.getString("position"));
                departmentField.setText(rs.getString("department"));

                // Make the fields editable
                nameField.setEditable(true);
                positionField.setEditable(true);
                departmentField.setEditable(true);
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

    // Update employee details
    private void updateEmployeeDetails() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String position = positionField.getText().trim();
        String department = departmentField.getText().trim();

        if (name.isEmpty() || position.isEmpty() || department.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String query = "UPDATE employees SET name = ?, position = ?, department = ? WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, position);
            pst.setString(3, department);
            pst.setInt(4, Integer.parseInt(id));

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Employee details updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error updating employee details.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating employee details.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Employee ID format", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Update_Details(); // Open the Update Employee page
    }
}