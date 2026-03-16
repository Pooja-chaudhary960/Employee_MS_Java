
package Employee_Management;


import javax.swing.*;

public class Salary extends JFrame {
    Salary() {
        setTitle("Salary");
        setSize(500, 400);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setVisible(true); 
    }

    public static void main(String[] args) {
        new Salary(); 
    }
}