 package financeproject;
import java.util.*;

class FinanceManager {
 private ArrayList<Transaction> transactions = new ArrayList<>();
 private int nextId = 1; // auto-incrementing transaction ID

 // Add a new transaction
 public void addTransaction(Scanner scanner) {
     System.out.println("Enter type (Income/Expense): ");
     String type = scanner.next();
     scanner.nextLine(); // clear newline

     System.out.println("Enter category (e.g., Food, Rent, Salary): ");
     String category = scanner.nextLine();

     System.out.println("Enter amount: ");
     double amount = scanner.nextDouble();
     scanner.nextLine();

     System.out.println("Enter description: ");
     String description = scanner.nextLine();

     Transaction t = new Transaction(nextId++, type, category, amount, description);
     transactions.add(t);

     System.out.println("Transaction added successfully!");
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
 public void editTransaction(Scanner scanner) {
     System.out.println("Enter transaction ID to edit: ");
     int id = scanner.nextInt();
     scanner.nextLine();

     Transaction t = findTransactionById(id);
     if (t == null) {
         System.out.println("Transaction not found.");
         return;
     }

     System.out.println("Editing: " + t);
     System.out.println("Enter new amount: ");
     double newAmount = scanner.nextDouble();
     scanner.nextLine();

     System.out.println("Enter new description: ");
     String newDesc = scanner.nextLine();

     t.setAmount(newAmount);
     t.setDescription(newDesc);

     System.out.println("  Transaction updated successfully!");
 }

 // Delete a transaction
 public void deleteTransaction(Scanner scanner) {
     System.out.println("Enter transaction ID to delete: ");
     int id = scanner.nextInt();
     scanner.nextLine();

     Transaction t = findTransactionById(id);
     if (t == null) {
         System.out.println("Transaction not found.");
         return;
     }

     transactions.remove(t);
     System.out.println("Transaction deleted successfully!");
 }

 // Generate simple report
 public void generateReport() {
     double totalIncome = 0, totalExpense = 0;

     for (Transaction t : transactions) {
         if (t.getType().equalsIgnoreCase("income")) {
             totalIncome += t.getAmount();
         } else if (t.getType().equalsIgnoreCase("expense")) {
             totalExpense += t.getAmount();
         }
     }

     double balance = totalIncome - totalExpense;

     System.out.println("==== Financial Report ====");
     System.out.printf("Total Income: €%.2f%n", totalIncome);
     System.out.printf("Total Expenses: €%.2f%n", totalExpense);
     System.out.printf("Net Savings: €%.2f%n", balance);
     System.out.println("===========================");
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
}
