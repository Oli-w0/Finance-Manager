package financeproject;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
	
	private StartFrame startFrame;

    public LoginFrame() {
        setTitle("Finance Tracker — Login");
        setSize(380, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centres on screen
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        //Username row
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        JTextField usernameField = new JTextField(15);
        panel.add(usernameField, gbc);

        // Password row
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(15);
        panel.add(passwordField, gbc);

        // Error label (hidden until needed)
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JLabel errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(errorLabel, gbc);

        // Login button
        gbc.gridy = 3;
        JButton loginBtn = new JButton("Login");
        panel.add(loginBtn, gbc);

        loginBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            // Simple hardcoded check for now — swap with AuthManager later
            if (username.equals("admin") && password.equals("1234")) {
                dispose(); // close login window
                //new MainFrame(username).setVisible(true);
            } else {
                errorLabel.setText("Invalid username or password.");
                passwordField.setText("");
            }
        });

        add(panel);
    }
}