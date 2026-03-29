package financeproject;

import javax.swing.*;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private TransactionManager manager;
    private DefaultTableModel tableModel;
    private String username;

    public MainFrame(String username) {
        this.username = username;
        this.manager  = new TransactionManager(username); 

        setTitle("Finance Tracker — Welcome, " + username);
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Transactions",    buildTransactionsPanel());
        tabs.addTab("Add Transaction", buildAddPanel());
        tabs.addTab("Report",          buildReportPanel());

        add(tabs);
    }

    // ── Tab 1: Transactions table ────────────────────────────────────────────
    private JPanel buildTransactionsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columns = {"ID", "Type", "Category", "Amount (€)", "Description"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        // Load saved transactions from database
        for (Transaction t : manager.getTransactions()) {
            tableModel.addRow(new Object[]{
                t.getId(), t.getType(), t.getCategory(),
                t.getAmount(), t.getDescription()
            });
        }

        JTable table = new JTable(tableModel);
        table.setRowHeight(24);
        table.getTableHeader().setReorderingAllowed(false);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton editBtn   = new JButton("Edit Selected");
        JButton deleteBtn = new JButton("Delete Selected");
        JButton logoutBtn = new JButton("LogOut");
        btnPanel.add(editBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(logoutBtn);
        panel.add(btnPanel, BorderLayout.SOUTH);

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a transaction first.");
                return;
            }
            int id = (int) tableModel.getValueAt(row, 0);
            manager.deleteTransaction(id);
            tableModel.removeRow(row);
        });
        
        logoutBtn.addActionListener(e -> {
        	dispose();
        	StartFrame frame = new StartFrame();
        	frame.setVisible(true);
        	
        });
        

        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a transaction first.");
                return;
            }
            int id = (int) tableModel.getValueAt(row, 0);

            JTextField amountField = new JTextField(10);
            JTextField descField   = new JTextField(20);

            JPanel editPanel = new JPanel(new GridLayout(2, 2, 5, 5));
            editPanel.add(new JLabel("New Amount (€):")); editPanel.add(amountField);
            editPanel.add(new JLabel("New Description:")); editPanel.add(descField);

            int result = JOptionPane.showConfirmDialog(this, editPanel,
                "Edit Transaction #" + id, JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    double newAmount = Double.parseDouble(amountField.getText().trim());
                    String newDesc   = descField.getText().trim();
                    manager.editTransaction(id, newAmount, newDesc);
                    tableModel.setValueAt(newAmount, row, 3);
                    tableModel.setValueAt(newDesc,   row, 4);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
                }
            }
        });

        return panel;
    }

    // ── Tab 2: Add transaction ───────────────────────────────────────────────
    private JPanel buildAddPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Type:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"Income", "Expense"});
        panel.add(typeBox, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Category (Food, Rent etc):"), gbc);
        gbc.gridx = 1;
        JTextField categoryField = new JTextField(20);
        panel.add(categoryField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Amount (€):"), gbc);
        gbc.gridx = 1;
        JTextField amountField = new JTextField(20);
        panel.add(amountField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        JTextField descField = new JTextField(20);
        panel.add(descField, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        JLabel statusLabel = new JLabel(" ");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(statusLabel, gbc);

        gbc.gridy = 5;
        JButton addBtn = new JButton("Add Transaction");
        panel.add(addBtn, gbc);

        addBtn.addActionListener(e -> {
            String type     = (String) typeBox.getSelectedItem();
            String category = categoryField.getText().trim();
            String desc     = descField.getText().trim();
            String amtText  = amountField.getText().trim();

            // Validation
            if (category.isEmpty() || desc.isEmpty() || amtText.isEmpty()) {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Please fill in all fields.");
                return;
            }

            double amount;
            try {
                amount = Double.parseDouble(amtText);
            } catch (NumberFormatException ex) {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Please enter a valid number for amount.");
                return;
            }

            if (amount <= 0) {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Amount must be greater than zero.");
                return;
            }

            // Save to database
            boolean success = manager.addTransaction(type, category, amount, desc);

            if (success) {
                // Get the real ID that MySQL assigned
                ArrayList<Transaction> all = manager.getTransactions();
                int newId = all.get(0).getId(); // ordered by date DESC so newest is first

                tableModel.addRow(new Object[]{newId, type, category, amount, desc});

                categoryField.setText("");
                amountField.setText("");
                descField.setText("");
                statusLabel.setForeground(new Color(0, 150, 0));
                statusLabel.setText("Transaction added successfully!");
            } else {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Failed to save transaction. Try again.");
            }
        });

        return panel;
    }

    // ── Tab 3: Report ────────────────────────────────────────────────────────
    private JPanel buildReportPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        reportArea.setText("Click 'Generate Report' to see your summary.");

        JButton reportBtn = new JButton("Generate Report");
        reportBtn.addActionListener(e -> reportArea.setText(manager.generateReport()));

        panel.add(new JScrollPane(reportArea), BorderLayout.CENTER);
        panel.add(reportBtn, BorderLayout.SOUTH);

        return panel;
    }
}