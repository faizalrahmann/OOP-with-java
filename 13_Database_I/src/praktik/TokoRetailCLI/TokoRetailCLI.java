package praktik.TokoRetailCLI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class TokoRetailCLI {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/toko_retail?useSSL=false&serverTimezone=UTC";
    static final String USERNAME = "root";
    static final String PASSWORD = "";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("Pilihan : ");
            String pilihan = sc.nextLine().trim();

            switch (pilihan) {
                case "1":
                    showAllData();
                    break;
                case "2":
                    addData(sc);
                    break;
                case "3":
                    searchData(sc);
                    break;
                case "4":
                    updateData(sc);
                    break;
                case "5":
                    deleteData(sc);
                    break;
                case "0":
                    System.out.println("Keluar program. Terima kasih.");
                    sc.close();
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }

            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("============================================");
        System.out.println("               MENU TOKO RETAIL             ");
        System.out.println("============================================");
        System.out.println("1. Tampil Semua Data");
        System.out.println("2. Tambah Data");
        System.out.println("3. Cari Data");
        System.out.println("4. Ubah Data");
        System.out.println("5. Hapus Data");
        System.out.println("0. Keluar");
        System.out.println("============================================");
    }

    private static Connection createConnection() throws Exception {
        Class.forName(JDBC_DRIVER);
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    private static void showAllData() {
        String query = "SELECT * FROM barang ORDER BY kode";

        try (Connection con = createConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\nDAFTAR BARANG TOKO RETAIL");
            printTableHeader();

            int nomor = 0;
            while (rs.next()) {
                nomor++;
                String kode = rs.getString("kode");
                String nama = rs.getString("nama_barang");
                int harga = rs.getInt("harga");
                int stok = rs.getInt("stok");
                printTableRow(nomor, kode, nama, harga, stok);
            }

            if (nomor == 0) {
                System.out.println("Tidak ada data barang.");
            } else {
                System.out.println("Total: " + nomor + " barang");
            }

        } catch (Exception ex) {
            System.out.println("Gagal menampilkan data: " + ex.getMessage());
        }
    }

    private static void addData(Scanner sc) {
        System.out.println("\nTAMBAH DATA BARANG");
        System.out.print("Kode Barang   : ");
        String kode = sc.nextLine().trim();
        System.out.print("Nama Barang   : ");
        String nama = sc.nextLine().trim();
        System.out.print("Harga Barang  : ");
        int harga = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Stok Barang   : ");
        int stok = Integer.parseInt(sc.nextLine().trim());

        String query = "INSERT INTO barang (kode, nama_barang, harga, stok) VALUES (?, ?, ?, ?)";

        try (Connection con = createConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, kode);
            ps.setString(2, nama);
            ps.setInt(3, harga);
            ps.setInt(4, stok);

            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Data berhasil ditambahkan.");
            } else {
                System.out.println("Gagal menambahkan data.");
            }

        } catch (Exception ex) {
            System.out.println("Gagal menambahkan data: " + ex.getMessage());
        }
    }

    private static void searchData(Scanner sc) {
        System.out.println("\nCARI DATA BARANG");
        System.out.print("Masukkan kode atau nama barang: ");
        String keyword = sc.nextLine().trim();
        String query = "SELECT * FROM barang WHERE kode LIKE ? OR nama_barang LIKE ? ORDER BY kode";

        try (Connection con = createConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            String filter = "%" + keyword + "%";
            ps.setString(1, filter);
            ps.setString(2, filter);

            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("\nHASIL PENCARIAN");
                printTableHeader();

                int nomor = 0;
                while (rs.next()) {
                    nomor++;
                    String kode = rs.getString("kode");
                    String nama = rs.getString("nama_barang");
                    int harga = rs.getInt("harga");
                    int stok = rs.getInt("stok");
                    printTableRow(nomor, kode, nama, harga, stok);
                }

                if (nomor == 0) {
                    System.out.println("Data tidak ditemukan.");
                } else {
                    System.out.println("Total: " + nomor + " barang");
                }
            }

        } catch (Exception ex) {
            System.out.println("Gagal mencari data: " + ex.getMessage());
        }
    }

    private static void updateData(Scanner sc) {
        System.out.println("\nUBAH DATA BARANG");
        System.out.print("Masukkan kode barang yang akan diubah: ");
        String kode = sc.nextLine().trim();

        String selectQuery = "SELECT * FROM barang WHERE kode = ?";
        try (Connection con = createConnection();
             PreparedStatement selectPs = con.prepareStatement(selectQuery)) {

            selectPs.setString(1, kode);
            try (ResultSet rs = selectPs.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("Kode barang tidak ditemukan.");
                    return;
                }

                System.out.println("Nama Lama : " + rs.getString("nama_barang"));
                System.out.println("Harga Lama: " + rs.getInt("harga"));
                System.out.println("Stok Lama : " + rs.getInt("stok"));
            }

            System.out.print("Nama Barang baru : ");
            String nama = sc.nextLine().trim();
            System.out.print("Harga Barang baru: ");
            int harga = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Stok Barang baru : ");
            int stok = Integer.parseInt(sc.nextLine().trim());

            String updateQuery = "UPDATE barang SET nama_barang = ?, harga = ?, stok = ? WHERE kode = ?";
            try (PreparedStatement updatePs = con.prepareStatement(updateQuery)) {
                updatePs.setString(1, nama);
                updatePs.setInt(2, harga);
                updatePs.setInt(3, stok);
                updatePs.setString(4, kode);

                int result = updatePs.executeUpdate();
                if (result > 0) {
                    System.out.println("Data berhasil diubah.");
                } else {
                    System.out.println("Gagal mengubah data.");
                }
            }

        } catch (Exception ex) {
            System.out.println("Gagal mengubah data: " + ex.getMessage());
        }
    }

    private static void deleteData(Scanner sc) {
        System.out.println("\nHAPUS DATA BARANG");
        System.out.print("Masukkan kode barang yang akan dihapus: ");
        String kode = sc.nextLine().trim();

        String query = "DELETE FROM barang WHERE kode = ?";

        try (Connection con = createConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, kode);
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Data berhasil dihapus.");
            } else {
                System.out.println("Kode barang tidak ditemukan atau gagal dihapus.");
            }

        } catch (Exception ex) {
            System.out.println("Gagal menghapus data: " + ex.getMessage());
        }
    }

    private static void printTableHeader() {
        System.out.printf("%-4s %-12s %-25s %-10s %-6s%n", "#", "Kode", "Nama Barang", "Harga", "Stok");
        System.out.println("--------------------------------------------------------------");
    }

    private static void printTableRow(int nomor, String kode, String nama, int harga, int stok) {
        System.out.printf("%-4d %-12s %-25s %-10d %-6d%n", nomor, kode, nama, harga, stok);
    }
}
