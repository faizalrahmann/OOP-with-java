package praktik.TokoRetailCLI;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/toko_retail?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws Exception {
        Class.forName(JDBC_DRIVER);
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}