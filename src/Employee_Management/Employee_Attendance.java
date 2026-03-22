package Employee_Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee_Attendance extends JFrame {
    private JLabel employeeIdLabel, dateLabel, statusLabel;
    private JTextField employeeIdField;
    private JComboBox<String> statusComboBox;
    private JButton markAttendanceButton;
    private Connection con;

    public Employee_Attendance() {
        setTitle("Take Employee Attendance");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Add components
        employeeIdLabel = new JLabel("Enter Employee ID:");
        employeeIdField = new JTextField();
        
        dateLabel = new JLabel("Date:");
        JLabel currentDateLabel = new JLabel(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        
        statusLabel = new JLabel("Attendance Status:");
        String[] statuses = {"Present", "Absent", "On Leave"};
        statusComboBox = new JComboBox<>(statuses);

        markAttendanceButton = new JButton("Mark Attendance");

        // Add components to the frame
        add(employeeIdLabel);
        add(employeeIdField);
        add(dateLabel);
        add(currentDateLabel);
        add(statusLabel);
        add(statusComboBox);
        add(new JLabel()); // Empty label for spacing
        add(markAttendanceButton);

        // Connect to the database
        connectToDatabase();

        // Add action listener to mark attendance button
        markAttendanceButton.addActionListener(e -> markAttendance());

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

    // Mark the attendance for the selected employee
    private void markAttendance() {
        String employeeId = employeeIdField.getText().trim();
        String status = (String) statusComboBox.getSelectedItem();

        if (employeeId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Employee ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Get the current date
            String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            // Insert attendance data into the database
            String query = "INSERT INTO attendance (employee_id, date, status) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(employeeId));
            pst.setString(2, currentDate);
            pst.setString(3, status);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Attendance marked successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error marking attendance.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Employee ID", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Employee_Attendance(); // Open the Take Attendance page
    }
}