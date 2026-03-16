
package Employee_Management;


import javax.swing.*;

public class Add_Employee extends JFrame {
    Add_Employee() {
        setTitle("Add Employee");
        setSize(500, 400);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setVisible(true); 
    }

    public static void main(String[] args) {
        new Add_Employee(); 
    }
}
