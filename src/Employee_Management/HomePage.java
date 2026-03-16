package Employee_Management;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomePage extends JFrame implements ActionListener {
    JLabel l1;
    Font f1, f2, f3;
    JPanel p1;

    HomePage() {
        super("Employee Home Page");
        setLocation(0, 0);
        setSize(1550, 800);

        f1 = new Font("Lucida Fax", Font.BOLD, 20);
        f2 = new Font("Gadugi", Font.BOLD, 35);
        f3 = new Font("MS UI Gothic", Font.BOLD, 18);

        // Setting up the icon for the JLabel
        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("Employee_Management/Icon/employeeHP.jpg"));
        Image img = ic.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        ImageIcon ic1 = new ImageIcon(img);
        l1 = new JLabel(ic1);

        // Setting up the JMenuBar
        JMenuBar mb1 = new JMenuBar();
        mb1.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

        // Adding menus and menu items
        JMenu m1 = new JMenu("Profile");
        JMenuItem jmi1 = new JMenuItem("Complete Profile");
        JMenuItem jmi2 = new JMenuItem("View Profile");

        JMenu m2 = new JMenu("Manage");
        JMenuItem jmi3 = new JMenuItem("Update Details");

        JMenu m3 = new JMenu("Attendance");
        JMenuItem jmi4 = new JMenuItem("Take Attendance");
        JMenuItem jmi5 = new JMenuItem("View Attendance");

        JMenu m4 = new JMenu("Leave");
        JMenuItem jmi6 = new JMenuItem("Apply Leave");
        JMenuItem jmi7 = new JMenuItem("View Leave");

        JMenu m5 = new JMenu("Salary");
        JMenuItem jmi8 = new JMenuItem("Add Salary");
        JMenuItem jmi9 = new JMenuItem("Generate Salary Slip");

        JMenu m6 = new JMenu("Exit");
        JMenuItem jmi10 = new JMenuItem("Logout");

        JMenu m7 = new JMenu("Delete");
        JMenuItem jmi11 = new JMenuItem("Delete Employee");

        // Add items to menus
        m1.add(jmi1);
        m1.add(jmi2);
        m2.add(jmi3);
        m3.add(jmi4);
        m3.add(jmi5);
        m4.add(jmi6);
        m4.add(jmi7);
        m5.add(jmi8);
        m5.add(jmi9);
        m6.add(jmi10);
        m7.add(jmi11);

        // Add menus to the menu bar
        mb1.add(m1);
        mb1.add(m2);
        mb1.add(m3);
        mb1.add(m4);
        mb1.add(m5);
        mb1.add(m6);
        mb1.add(m7);

        // Set fonts
        m1.setFont(f1);
        m2.setFont(f1);
        m3.setFont(f1);
        m4.setFont(f1);
        m5.setFont(f1);
        m6.setFont(f1);
        m7.setFont(f1);

        jmi1.setFont(f1);
        jmi2.setFont(f1);
        jmi3.setFont(f1);
        jmi4.setFont(f1);
        jmi5.setFont(f1);
        jmi6.setFont(f1);
        jmi7.setFont(f1);
        jmi8.setFont(f1);
        jmi9.setFont(f1);
        jmi10.setFont(f1);
        jmi11.setFont(f1);

        mb1.setBackground(Color.BLACK);

        m1.setForeground(Color.GRAY);
        m2.setForeground(Color.GRAY);
        m3.setForeground(Color.GRAY);
        m4.setForeground(Color.GRAY);
        m5.setForeground(Color.GRAY);
        m6.setForeground(Color.GRAY);
        m7.setForeground(Color.RED);

        // Set background color for menu items
        jmi1.setBackground(Color.YELLOW);
        jmi2.setBackground(Color.YELLOW);
        jmi3.setBackground(Color.YELLOW);
        jmi4.setBackground(Color.YELLOW);
        jmi5.setBackground(Color.YELLOW);
        jmi6.setBackground(Color.YELLOW);
        jmi7.setBackground(Color.YELLOW);
        jmi8.setBackground(Color.YELLOW);
        jmi9.setBackground(Color.YELLOW);
        jmi10.setBackground(Color.YELLOW);
        jmi11.setBackground(Color.YELLOW);

        // Add action listeners for menu items
        jmi1.addActionListener(this);
        jmi2.addActionListener(this);
        jmi3.addActionListener(this);
        jmi4.addActionListener(this);
        jmi5.addActionListener(this);
        jmi6.addActionListener(this);
        jmi7.addActionListener(this);
        jmi8.addActionListener(this);
        jmi9.addActionListener(this);
        jmi10.addActionListener(this);
        jmi11.addActionListener(this);

        // Set the JMenuBar
        setJMenuBar(mb1);

        // Add the icon label to the window
        add(l1);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Complete Profile")) {
            new Add_Employee();
        } else if (command.equals("View Profile")) {
            new View_Employee();
        } else if (command.equals("Update Details")) {
            new Update_Details().setVisible(true);
        } else if (command.equals("Take Attendance")) {
            new Employee_Attendance().setVisible(true);
        } else if (command.equals("View Attendance")) {
            new View_Attendance().setVisible(true);
        } else if (command.equals("Apply Leave")) {
            new Apply_Leave().setVisible(true);
        } else if (command.equals("View Leave")) {
            new View_Leaves().setVisible(true);
        } else if (command.equals("Add Salary")) {
            new Salary().setVisible(true);
        } else if (command.equals("Generate Salary Slip")) {
            new Generate_PaySlip().setVisible(true);
        } else if (command.equals("Delete Employee")) {
            new Delete_Employee().setVisible(true);
        } else if (command.equals("Logout")) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new HomePage().setVisible(true); // Create and display the HomePage
    }
}