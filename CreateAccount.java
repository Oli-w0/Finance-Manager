package financeproject;

import javax.swing.*;
import java.awt.*;

public class CreateAccount extends JFrame {


    public CreateAccount() {
        setTitle("Finance Tracker — Create Account");
        setSize(380, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centres on screen
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
    }
}
        