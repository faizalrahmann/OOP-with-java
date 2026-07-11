import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Insert {

    static final String jdbc = "com.mysql.cj.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost/toko_retail";
    static final String username = "root";
    static final String password = "";

    static Connection con;
    static ResultSet rs;
    static PreparedStatement ps;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Kode Barang:");
        String kode_barang = sc.nextLine();

        System.out.print("Nama Barang:");
        String nama_barang = sc.nextLine();

        System.out.print("Harga Barang:");
        int harga_barang = Integer.parseInt(sc.nextLine());

        System.out.print("Stok Barang:");
        int stok_barang = Integer.parseInt(sc.nextLine());

        String query = "insert into tbl_barang values (?,?,?,?)";

        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, username, password);
            ps = con.prepareStatement(query);

            ps.setString(1, kode_barang);
            ps.setString(2, nama_barang);
            ps.setInt(3, harga_barang);
            ps.setInt(4, stok_barang);

            if (ps.executeUpdate() > 0) {
                System.out.println("Proses Berhasil");
            } else {
                System.out.println("Proses Gagal");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}