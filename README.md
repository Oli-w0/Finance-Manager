Personal Finance Tracker

A Java desktop application for managing personal finances. Built with Java Swing for the GUI and SQLite for local data storage. Users can create accounts, log in securely, track income and expenses, and generate financial reports.

Project Description
Personal Finance Tracker is a desktop application that allows users to:

Create a personal account with secure password hashing
Log in and out with a dedicated login screen
Add, edit and delete financial transactions
Categorise transactions as Income or Expense
Generate a financial report showing total income, total expenses and net balance
Switch between light and dark mode
View and update their profile details including name, age and password

All data is stored locally in a SQLite database file (finance.db) that is created automatically on first run. No internet connection or external server is required.

Technologies Used
TechnologyPurposeJava 17Core programming languageJava SwingDesktop GUI frameworkSQLiteLocal database for storing users and transactionsSQLite JDBC (sqlite-jdbc-3.51.3.0.jar)Java driver for connecting to SQLiteFlatLafModern light and dark theme library for SwingSHA-256 (Java MessageDigest)Password hashing for secure storage

Setup Instructions
Requirements

Java 11 or higher must be installed on your machine
Download Java from: https://adoptium.net if not already installed
No database setup required — SQLite is bundled inside the JAR

Running the Application
Option 1 — Double click
Simply double click FinanceTracker.jar to launch the application.
If double clicking does nothing, right click the JAR and select:

On Mac: Open With → Jar Launcher
On Windows: Open With → Java Platform SE Binary

Option 2 — Terminal or Command Prompt
Navigate to the folder containing the JAR file and run:
bashjava -jar FinanceTracker.jar
First Time Setup
The application creates the SQLite database automatically on first run. You will see the Start Page — click Create Account to register, then log in with your new credentials.

Usage
Start Page
When you launch the app you are presented with two options — Login or Create Account.

Creating an Account
Fill in your full name, username, password, confirm password and age. Passwords must be at least 6 characters. Usernames must be unique.

Logging In
Enter your username and password. If correct you are taken to the main application window.

Adding a Transaction
Click the Add Transaction tab. Select Income or Expense, enter a category (e.g. Food, Rent, Salary), enter an amount and a description, then click Add Transaction.

Viewing Transactions
The Transactions tab shows all your saved transactions in a table. You can select any row and click Edit Selected to update the amount or description, or Delete Selected to remove it.

Generating a Report
Click the Report tab and press Generate Report to see your total income, total expenses and net balance.

Logging Out
Click the Logout button to return to the Start Page.

AI Acknowledgment
This project was developed with assistance from Claude (by Anthropic) throughout the development process. The following describes specifically how AI was used:
Debugging
Claude was used extensively to diagnose runtime errors and exceptions encountered during development. Examples include:

Fixing a NullPointerException caused by TransactionManager being assigned to itself (this.manager = manager) instead of being properly initialised (this.manager = new TransactionManager(username))
Resolving a No suitable driver found error when switching from MySQL to SQLite caused by the JAR being added to Modulepath instead of Classpath in Eclipse
Identifying a bug where a semicolon after an if statement (if(password.length() < 6);) caused the password validation block to always execute regardless of the condition
Fixing duplicate transaction saving caused by the add transaction logic being written twice inside the same action listener

Code Generation and Structure
Claude provided guidance and code examples for:

Implementing the Swing GUI components including GridBagLayout forms, JTabbedPane, JTable with DefaultTableModel and JToolBar
Writing the SQLite database layer including CREATE TABLE statements, PreparedStatements for INSERT, SELECT, UPDATE and DELETE operations
Implementing SHA-256 password hashing using Java MessageDigest so passwords are never stored as plain text


Learning and Explanation
Claude was used as a learning resource to understand concepts including:

The difference between Modulepath and Classpath in Eclipse and why it matters for external JARs
Why DO_NOTHING_ON_CLOSE is used instead of EXIT_ON_CLOSE on child windows
The difference between SQLite and MySQL syntax including data types, AUTO_INCREMENT vs AUTOINCREMENT and connection handling
Why confirm password should not be stored in the database
How VARCHAR lengths in MySQL represent characters not bytes
What DECIMAL(10,2) means for storing monetary values

Database Migration
Claude assisted with migrating the project from MySQL to SQLite to make the application fully portable and self-contained, requiring no external database installation from the end user.
