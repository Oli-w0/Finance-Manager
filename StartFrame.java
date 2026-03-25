package financeproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame 
{
	
	public StartFrame()
	{
	setTitle("Finance Tracker");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);

    // Main panel with a vertical BoxLayout
    // BoxLayout stacks components top to bottom in a single column
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

    // App title label
    JLabel titleLabel = new JLabel("Personal Finance Tracker");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center in BoxLayout

    // Spacer — rigid area creates a fixed-height gap between components
    // BoxLayout doesn't have insets like GridBagLayout so you use these instead
    JPanel spacer1 = new JPanel();
    spacer1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

    // Login button 
    JButton loginBtn = new JButton("Login");
    loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    loginBtn.setMaximumSize(new Dimension(200, 40)); // cap the width
    loginBtn.setFont(new Font("Arial", Font.PLAIN, 14));

    JPanel spacer2 = new JPanel();
    spacer2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 12));

    // Create account button
    JButton createBtn = new JButton("Create Account");
    createBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    createBtn.setMaximumSize(new Dimension(200, 40));
    createBtn.setFont(new Font("Arial", Font.PLAIN, 14));

    // Add everything to the panel in order
    panel.add(titleLabel);
    panel.add(spacer1);
    panel.add(loginBtn);
    panel.add(spacer2);
    panel.add(createBtn);

    add(panel);

    // ── Button actions ───────────────────────────────────────────────────

    // Open LoginFrame, hide StartPage (don't dispose — user might come back)
    loginBtn.addActionListener(e -> 
    {
        LoginFrame loginFrame = new LoginFrame(this);
        loginFrame.setVisible(true);
        setVisible(false); // hide StartPage while login is open
    });

    // Open CreateAccount, hide StartPage
    createBtn.addActionListener(e -> 
    {
        CreateAccount createAccount = new CreateAccount(this);
        createAccount.setVisible(true);
        setVisible(false);
    });
}

}


