package financeproject;

import javax.swing.SwingUtilities;

public class Finance {
    public static void main(String[] args) {
    	
    	AuthManager.initDatabase();
        SwingUtilities.invokeLater(() -> {
            StartFrame startFrame = new StartFrame();
            startFrame.setVisible(true);
        });
    }
}
             

