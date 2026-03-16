
package Employee_Management;

import javax.swing.*;

public class Update_Details extends JFrame {
    Update_Details() {
        setTitle("Add Employee");
        setSize(500, 400);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close the window without exiting the program
        setVisible(true); // Make the frame visible
    }

    public static void main(String[] args) {
        new Update_Details(); 
    }
}