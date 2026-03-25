package financeproject;

import javax.swing.*;
import java.awt.*;

public class CreateAccount extends JFrame {

	private StartFrame startFrame;

    public CreateAccount(StartFrame startFrame) {
    	this.startFrame = startFrame;
        setTitle("Finance Tracker — Create Account");
        setSize(500, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centers on screen
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx=0; gbc.gridy=0;
        panel.add(new JLabel("Full Name"), gbc);
        gbc.gridx=1;
        JTextField fullnameField = new JTextField(25);
        panel.add(fullnameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        JTextField usernameField = new JTextField(20);
        panel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(25);
        panel.add(passwordField, gbc);
        
        gbc.gridx=0; gbc.gridy =3;
        panel.add(new JLabel("Confirm Password:"), gbc);
        gbc.gridx=1;
        JPasswordField confirmField = new JPasswordField(25);
        panel.add(confirmField, gbc);
        
        gbc.gridx = 0; gbc.gridy=4;
        panel.add(new JLabel("Age:"), gbc);
        gbc.gridx=1;
        JTextField ageField = new JTextField(15);
        panel.add(ageField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        JLabel statusLabel = new JLabel(" ");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(statusLabel, gbc);
        
        gbc.gridy = 5;
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton createBtn = new JButton("Create Account");
        JButton backBtn = new JButton("⬅️");
        btnPanel.add(createBtn);
        btnPanel.add(backBtn);
        panel.add(btnPanel, gbc);
        
        //Button Functions
        backBtn.addActionListener(e -> {
            startFrame.setVisible(true);
            dispose();
        });
        
        createBtn.addActionListener(e ->
        {
        	
        });

        add(panel);

        
    }
}
        