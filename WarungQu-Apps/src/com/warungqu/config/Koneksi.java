package com.warungqu.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static Koneksi instance;
    private Connection connection;

    private final String URL = "jdbc:mysql://localhost:3306/warungqu_db?useSSL=false&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "";

    private Koneksi() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Koneksi getInstance() {
        if (instance == null) {
            instance = new Koneksi();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}