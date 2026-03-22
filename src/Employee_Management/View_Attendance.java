package Employee_Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class View_Attendance extends JFrame {
    private JLabel startDateLabel, endDateLabel;
    private JTextField startDateField, endDateField;
    private JButton viewAttendanceButton;
    private JTable attendanceTable;
    private JScrollPane scrollPane;
    private Connection con;

    public View_Attendance() {
        setTitle("View Employee Attendance");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        // Add components
        startDateLabel = new JLabel("Start Date (YYYY-MM-DD):");
        startDateField = new JTextField(10);
        endDateLabel = new JLabel("End Date (YYYY-MM-DD):");
        endDateField = new JTextField(10);

        viewAttendanceButton = new JButton("View Attendance");

        // Add components to the frame
        add(startDateLabel);
        add(startDateField);
        add(endDateLabel);
        add(endDateField);
        add(viewAttendanceButton);

        // Table to display attendance
        attendanceTable = new JTable();
        scrollPane = new JScrollPane(attendanceTable);
        add(scrollPane);

        // Connect to the database
        connectToDatabase();

        // Add action listener for view attendance button
        viewAttendanceButton.addActionListener(e -> viewAttendance());

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

    // View attendance based on the date range
    private void viewAttendance() {
        String startDate = startDateField.getText().trim();
        String endDate = endDateField.getText().trim();

        if (startDate.isEmpty() || endDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both start and end dates", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Query to get attendance records within the specified date range
            String query = "SELECT e.id, e.name, a.date, a.status FROM employees e " +
                           "JOIN attendance a ON e.id = a.employee_id " +
                           "WHERE a.date BETWEEN ? AND ? ORDER BY a.date";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, startDate);
            pst.setString(2, endDate);
            ResultSet rs = pst.executeQuery();

            // Populate the table with the fetched data
            Vector<Vector<Object>> data = new Vector<>();
            Vector<String> columnNames = new Vector<>();
            columnNames.add("Employee ID");
            columnNames.add("Employee Name");
            columnNames.add("Date");
            columnNames.add("Attendance Status");

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("id"));
                row.add(rs.getString("name"));
                row.add(rs.getString("date"));
                row.add(rs.getString("status"));
                data.add(row);
            }

            // Set the table model
            attendanceTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching attendance data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new View_Attendance(); // Open the View Attendance page
    }
}