
package Employee_Management;

import javax.swing.*;

public class View_Attendance extends JFrame {
    View_Attendance() {
        setTitle("View_Attendance");
        setSize(500, 400);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setVisible(true); 
    }

    public static void main(String[] args) {
        new View_Attendance(); 
    }
}
