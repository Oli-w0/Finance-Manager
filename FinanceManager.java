package financeproject;
import java.util.*;

class FinanceManager {
 private ArrayList<Transaction> transactions = new ArrayList<>();
 private int nextId = 1; // auto-incrementing transaction ID

 // Add a new transaction
 public void addTransaction(String type, String category, double amount, String description) {
	    Transaction t = new Transaction(nextId++, type, category, amount, description);
	    transactions.add(t);
	}

 // View all transactions
 public void viewTransactions() {
     if (transactions.isEmpty()) {
         System.out.println("No transactions found.");
         return;
     }
     System.out.println("==== All Transactions ====");
     for (Transaction t : transactions) {
         System.out.println(t);
     }
 }

 // Edit an existing transaction
 public void editTransaction(int id, double newAmount, String newDesc) {
	    Transaction t = findTransactionById(id);
	    if (t == null) return;
	    t.setAmount(newAmount);
	    t.setDescription(newDesc);
	}


 // Delete a transaction
 public void deleteTransaction(int id) {
	    Transaction t = findTransactionById(id);
	    if (t != null) transactions.remove(t);
	}

 // Generate simple report
 public String generateReport() {
	    double totalIncome = 0, totalExpense = 0;
	    for (Transaction t : transactions) {
	        if (t.getType().equalsIgnoreCase("income")) totalIncome += t.getAmount();
	        else if (t.getType().equalsIgnoreCase("expense")) totalExpense += t.getAmount();
	    }
	    double balance = totalIncome - totalExpense;
	    return String.format(
	        "Total Income: €%.2f\nTotal Expenses: €%.2f\nNet Savings: €%.2f",
	        totalIncome, totalExpense, balance
	    );
	}
 // Helper: find transaction by ID
 private Transaction findTransactionById(int id) {
     for (Transaction t : transactions) {
         if (t.getId() == id) {
             return t;
         }
     }
     return null;
 }
 public ArrayList<Transaction> getTransactions() {
	    return transactions;
	}
}
