package Employee_Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class Apply_Leave extends JFrame {
    private JLabel employeeIdLabel, leaveTypeLabel, startDateLabel, endDateLabel, reasonLabel;
    private JTextField employeeIdField, startDateField, endDateField, reasonField;
    private JComboBox<String> leaveTypeComboBox;
    private JButton applyLeaveButton;
    private Connection con;

    public Apply_Leave() {
        setTitle("Apply for Leave");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10));

        // Initialize components
        employeeIdLabel = new JLabel("Employee ID:");
        employeeIdField = new JTextField();
        
        leaveTypeLabel = new JLabel("Leave Type:");
        String[] leaveTypes = {"Sick", "Vacation", "Maternity", "Casual"};
        leaveTypeComboBox = new JComboBox<>(leaveTypes);
        
        startDateLabel = new JLabel("Start Date (YYYY-MM-DD):");
        startDateField = new JTextField();
        
        endDateLabel = new JLabel("End Date (YYYY-MM-DD):");
        endDateField = new JTextField();
        
        reasonLabel = new JLabel("Reason:");
        reasonField = new JTextField();
        
        applyLeaveButton = new JButton("Apply Leave");

        // Add components to the frame
        add(employeeIdLabel);
        add(employeeIdField);
        add(leaveTypeLabel);
        add(leaveTypeComboBox);
        add(startDateLabel);
        add(startDateField);  // Use JTextField for date input
        add(endDateLabel);
        add(endDateField);  // Use JTextField for date input
        add(reasonLabel);
        add(reasonField);
        add(new JLabel());  // Empty label for alignment
        add(applyLeaveButton);

        // Connect to database
        connectToDatabase();

        // Add action listener to apply leave button
        applyLeaveButton.addActionListener(e -> applyLeave());

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

    // Apply leave method
    private void applyLeave() {
        String employeeId = employeeIdField.getText().trim();
        String leaveType = (String) leaveTypeComboBox.getSelectedItem();
        String reason = reasonField.getText().trim();

        // Get the start and end date from text fields
        String startDate = startDateField.getText().trim();
        String endDate = endDateField.getText().trim();

        // Check if all fields are filled
        if (employeeId.isEmpty() || leaveType == null || startDate.isEmpty() || endDate.isEmpty() || reason.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate date format (YYYY-MM-DD)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);  // Ensure strict parsing

        try {
            // Parse the dates to check if they're valid
            dateFormat.parse(startDate);
            dateFormat.parse(endDate);

            // Insert leave request into the database
            String query = "INSERT INTO leave_requests (employee_id, leave_type, start_date, end_date, reason, status) VALUES (?, ?, ?, ?, ?, 'Pending')";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(employeeId));
            pst.setString(2, leaveType);
            pst.setString(3, startDate);
            pst.setString(4, endDate);
            pst.setString(5, reason);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Leave application submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error applying for leave.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please enter dates in YYYY-MM-DD format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Apply_Leave();  // Open Apply Leave page
    }
}