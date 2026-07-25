package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String BASE_URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "db_klinik";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver tidak ditemukan", e);
        }
        String url = BASE_URL + DATABASE_NAME + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        return DriverManager.getConnection(url, USER, PASSWORD);
    }

    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(BASE_URL, USER, PASSWORD); Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME);
            stmt.execute("USE " + DATABASE_NAME);
            String[] sqlStatements = {
                "CREATE TABLE IF NOT EXISTS users (id INT AUTO_INCREMENT PRIMARY KEY, full_name VARCHAR(100), username VARCHAR(50) UNIQUE, password VARCHAR(100), role VARCHAR(20), phone VARCHAR(20), address VARCHAR(255))",
                "CREATE TABLE IF NOT EXISTS patients (id INT AUTO_INCREMENT PRIMARY KEY, full_name VARCHAR(100), phone VARCHAR(20), address VARCHAR(255), birth_date VARCHAR(20), gender VARCHAR(20), medical_history TEXT)",
                "CREATE TABLE IF NOT EXISTS doctors (id INT AUTO_INCREMENT PRIMARY KEY, full_name VARCHAR(100), phone VARCHAR(20), address VARCHAR(255), specialty VARCHAR(100))",
                "CREATE TABLE IF NOT EXISTS appointments (id INT AUTO_INCREMENT PRIMARY KEY, patient_id INT, doctor_id INT, appointment_date VARCHAR(20), status VARCHAR(30), notes TEXT)",
                "INSERT IGNORE INTO users (full_name, username, password, role, phone, address) VALUES ('Admin Klinik', 'admin', 'admin123', 'admin', '08123456789', 'Bandung')"
            };

            for (String sql : sqlStatements) {
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            System.err.println("Gagal menginisialisasi database: " + e.getMessage());
        }
    }
}
