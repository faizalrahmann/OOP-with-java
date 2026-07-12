package com.warungqu.dao;

import com.warungqu.config.Koneksi;
import com.warungqu.model.Produk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdukDAO {
    private final Connection connection;

    public ProdukDAO() {
        this.connection = Koneksi.getInstance().getConnection();
    }

    public boolean insert(Produk produk) {
        String sql = "INSERT INTO tabel_produk (nama, harga) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, produk.getNamaProduk());
            ps.setDouble(2, produk.getHarga());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Produk> getAll() {
        List<Produk> list = new ArrayList<>();
        String sql = "SELECT * FROM tabel_produk ORDER BY id ASC";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Produk produk = new Produk();
                produk.setIdProduk(rs.getInt("id"));
                produk.setNamaProduk(rs.getString("nama"));
                produk.setHarga(rs.getDouble("harga"));
                list.add(produk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Produk getById(int id) {
        String sql = "SELECT * FROM tabel_produk WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Produk produk = new Produk();
                    produk.setIdProduk(rs.getInt("id"));
                    produk.setNamaProduk(rs.getString("nama"));
                    produk.setHarga(rs.getDouble("harga"));
                    return produk;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Produk produk) {
        String sql = "UPDATE tabel_produk SET nama = ?, harga = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, produk.getNamaProduk());
            ps.setDouble(2, produk.getHarga());
            ps.setInt(3, produk.getIdProduk());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM tabel_produk WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}