package financeproject;

import javax.swing.*;
import static javax.swing.JOptionPane.showMessageDialog;
import java.awt.*;

public class LoginFrame extends JFrame {
	
	private StartFrame startFrame;

    public LoginFrame(StartFrame startFrame) {
    	this.startFrame = startFrame;
        setTitle("Finance Manager — Login");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centers on screen
        //setResizable(false);

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
        gbc.gridy = 2;
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton loginBtn = new JButton("Login");
        JButton backBtn = new JButton("<-");
        btnPanel.add(loginBtn, gbc);
        btnPanel.add(backBtn, gbc);
        panel.add(btnPanel, gbc);

      //Button Functions
        backBtn.addActionListener(_ -> {
            startFrame.setVisible(true);
            dispose();
        });
        
        // ── Login button ─────────────────────────────────────────────────────
        loginBtn.addActionListener(_ -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            AuthManager auth = new AuthManager();

            if (auth.login(username, password)) {
                // Login successful — close StartPage permanently, open MainFrame
                startFrame.dispose();
                dispose();
                new MainFrame(username).setVisible(true);
            } else {
                showMessageDialog(this, "Invalid username or password");
                passwordField.setText("");
            }
        });
        // ── Handle the X button the same as Back ────────────────────────────
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                startFrame.setVisible(true);
                dispose();
            }
        });

       
        add(panel);
    }
}