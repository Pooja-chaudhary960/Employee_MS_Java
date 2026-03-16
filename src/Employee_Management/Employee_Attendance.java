
package Employee_Management;


import javax.swing.*;

public class Employee_Attendance extends JFrame {
    Employee_Attendance(){
        setTitle("Employee_Attendance");
        setSize(500, 400);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setVisible(true); 
    }

    public static void main(String[] args) {
        new Employee_Attendance(); 
    }
}