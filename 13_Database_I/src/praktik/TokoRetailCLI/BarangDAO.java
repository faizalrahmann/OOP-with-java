package praktik.TokoRetailCLI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BarangDAO {

    public List<Barang> getAll() {
        List<Barang> barangList = new ArrayList<>();
        String query = "SELECT * FROM barang ORDER BY kode";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                barangList.add(mapResultSet(rs));
            }
        } catch (Exception ex) {
            System.out.println("Gagal mengambil data: " + ex.getMessage());
        }

        return barangList;
    }

    public boolean insert(Barang barang) {
        String query = "INSERT INTO barang (kode, nama_barang, harga, stok) VALUES (?, ?, ?, ?)";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, barang.getKodeBarang());
            ps.setString(2, barang.getNamaBarang());
            ps.setInt(3, barang.getHargaBarang());
            ps.setInt(4, barang.getStokBarang());

            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println("Gagal menambahkan data: " + ex.getMessage());
            return false;
        }
    }

    public List<Barang> search(String keyword) {
        List<Barang> barangList = new ArrayList<>();
        String query = "SELECT * FROM barang WHERE kode LIKE ? OR nama_barang LIKE ? ORDER BY kode";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            String filter = "%" + keyword + "%";
            ps.setString(1, filter);
            ps.setString(2, filter);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    barangList.add(mapResultSet(rs));
                }
            }
        } catch (Exception ex) {
            System.out.println("Gagal mencari data: " + ex.getMessage());
        }

        return barangList;
    }

    public Barang findByKode(String kodeBarang) {
        String query = "SELECT * FROM barang WHERE kode = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, kodeBarang);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        } catch (Exception ex) {
            System.out.println("Gagal mencari kode barang: " + ex.getMessage());
        }

        return null;
    }

    public boolean update(Barang barang) {
        String query = "UPDATE barang SET nama_barang = ?, harga = ?, stok = ? WHERE kode = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, barang.getNamaBarang());
            ps.setInt(2, barang.getHargaBarang());
            ps.setInt(3, barang.getStokBarang());
            ps.setString(4, barang.getKodeBarang());

            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println("Gagal mengubah data: " + ex.getMessage());
            return false;
        }
    }

    public boolean delete(String kodeBarang) {
        String query = "DELETE FROM barang WHERE kode = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, kodeBarang);
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println("Gagal menghapus data: " + ex.getMessage());
            return false;
        }
    }

    private Barang mapResultSet(ResultSet rs) throws Exception {
        return new Barang(
                rs.getString("kode"),
                rs.getString("nama_barang"),
                rs.getInt("harga"),
                rs.getInt("stok")
        );
    }
}