package financeproject;

import java.security.MessageDigest;
import java.sql.*;

public class AuthManager {

    private static final String DB_URL  = "jdbc:mysql://localhost:3306/userDB";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "@ppLIe1280"; 

    // Run once at startup to create the table if it doesn't exist
    public static void initDatabase() {
        String sql = """
            CREATE TABLE IF NOT EXISTS users (
                id       INT          PRIMARY KEY AUTO_INCREMENT,
                fullname VARCHAR(100) NOT NULL,
                username VARCHAR(50)  NOT NULL UNIQUE,
                password VARCHAR(255) NOT NULL,
                age      INT          NOT NULL
            )
            """;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create a new user — returns false if username already taken
    public boolean createAccount(String fullname, String username, String password, int age) {
        String hashed = hashPassword(password);
        String sql = "INSERT INTO users (fullname, username, password, age) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fullname);
            stmt.setString(2, username);
            stmt.setString(3, hashed);
            stmt.setInt(4, age);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) { // MySQL's duplicate error message
                return false;
            }
            e.printStackTrace();
            return false;
        }
    }

    // Check username and password against database
    public boolean login(String username, String password) {
        String hashed = hashPassword(password);
        String sql = "SELECT id FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, hashed);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true if a matching row was found

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // SHA-256 hash — password is never stored as plain text
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}