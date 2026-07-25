import java.sql.*;
public class TestDb {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_klinik?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM users");
        ResultSet rs = stmt.executeQuery();
        rs.next();
        System.out.println("users_count=" + rs.getInt(1));
        conn.close();
    }
}
