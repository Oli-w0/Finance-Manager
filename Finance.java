package financeproject;

import java.util.Scanner;

public class Finance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FinanceManager manager = new FinanceManager();

        int choice = 0;
        System.out.println("==== WELCOME TO YOUR PERSONAL FINANCE TRACKER ====");

        // Main menu loop
        while (choice != 6) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Transaction");
            System.out.println("2. View Transactions");
            System.out.println("3. Edit Transaction");
            System.out.println("4. Delete Transaction");
            System.out.println("5. Generate Report");
            System.out.println("6. Exit");
            System.out.print("Choice: ");

            // Read input safely
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number!");
                scanner.next(); // discard invalid input
            }

            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> manager.addTransaction(scanner);
                case 2 -> manager.viewTransactions();
                case 3 -> manager.editTransaction(scanner);
                case 4 -> manager.deleteTransaction(scanner);
                case 5 -> manager.generateReport();
                case 6 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice. Try again!");
            }
        }

        scanner.close();
    }
}
             
//Stuff to add
//Add a login system?
//Tracking system

