package financeproject;

import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;

public class TransactionManager 
{

	private static final String DB_URL = "jdbc:sqlite:user.db";
    
    private String username;
    
    public TransactionManager(String username)
    {
    	this.username = username;
    }
    
    public boolean addTransaction(String type, String category, double amount, String description)
    {
    	String sql = """
                INSERT INTO transactions (username, type, category, amount, description)
                VALUES (?, ?, ?, ?, ?)
                """;
    	
    	 try (Connection conn = DriverManager.getConnection(DB_URL);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, username);
                stmt.setString(2, type);
                stmt.setString(3, category);
                stmt.setDouble(4, amount);
                stmt.setString(5, description);
                stmt.executeUpdate();
                return true;
    }
    	 catch(SQLException e)
    	 {
    		 e.printStackTrace();
    		 return false;
    	 }
    }
    public ArrayList<Transaction> getTransactions() {
        ArrayList<Transaction> list = new ArrayList<>();
        String sql = """
            SELECT id, type, category, amount, description 
            FROM transactions 
            WHERE username = ?
            ORDER BY date DESC
            """;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transaction t = new Transaction(
                    rs.getInt("id"),
                    rs.getString("type"),
                    rs.getString("category"),
                    rs.getDouble("amount"),
                    rs.getString("description")
                );
                list.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //edit a transaction
    public boolean editTransaction(int id, double newAmount, String newDescription) {
        String sql = """
            UPDATE transactions 
            SET amount = ?, description = ? 
            WHERE id = ? AND username = ?
            """;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, newAmount);
            stmt.setString(2, newDescription);
            stmt.setInt(3, id);
            stmt.setString(4, username); // safety check — only edit your own transactions
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Delete a transaction
    public boolean deleteTransaction(int id) {
        String sql = "DELETE FROM transactions WHERE id = ? AND username = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setString(2, username); // safety check — only delete your own transactions
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Generate report data
    public String generateReport() {
        String sql = """
            SELECT 
                SUM(CASE WHEN type = 'Income'  THEN amount ELSE 0 END) AS totalIncome,
                SUM(CASE WHEN type = 'Expense' THEN amount ELSE 0 END) AS totalExpense
            FROM transactions
            WHERE username = ?
            """;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double income  = rs.getDouble("totalIncome");
                double expense = rs.getDouble("totalExpense");
                double balance = income - expense;

                return String.format(
                    "Total Income:    €%.2f%n" +
                    "Total Expenses:  €%.2f%n" +
                    "Net Balance:     €%.2f",
                    income, expense, balance
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Could not generate report.";
    }
    
    

}
