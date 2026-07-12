package com.warungqu.dao;

import com.warungqu.config.Koneksi;
import com.warungqu.model.DetailTransaksi;
import com.warungqu.model.Pemasukan;
import com.warungqu.model.Pengeluaran;
import com.warungqu.model.Transaksi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDAO {
    private final Connection connection;

    public TransaksiDAO() {
        this.connection = Koneksi.getInstance().getConnection();
    }

    public int simpanPemasukan(Pemasukan pemasukan, List<DetailTransaksi> detailList) {
        String sqlTransaksi = "INSERT INTO tabel_transaksi (tipe, total, bayar, kembalian) VALUES (?, ?, ?, ?)";
        String sqlDetail = "INSERT INTO tabel_detail_transaksi (id_transaksi, id_produk, jumlah, subtotal) VALUES (?, ?, ?, ?)";

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement(sqlTransaksi, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, "Pemasukan");
                ps.setDouble(2, pemasukan.getTotal());
                ps.setDouble(3, pemasukan.getUangBayar());
                ps.setDouble(4, pemasukan.getUangKembalian());
                ps.executeUpdate();

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idTransaksi = generatedKeys.getInt(1);
                        try (PreparedStatement psDetail = connection.prepareStatement(sqlDetail)) {
                            for (DetailTransaksi detail : detailList) {
                                psDetail.setInt(1, idTransaksi);
                                psDetail.setInt(2, detail.getIdProduk());
                                psDetail.setInt(3, detail.getJumlah());
                                psDetail.setDouble(4, detail.getSubtotal());
                                psDetail.addBatch();
                            }
                            psDetail.executeBatch();
                        }
                        connection.commit();
                        return idTransaksi;
                    }
                }
            }
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public boolean simpanPengeluaran(Pengeluaran pengeluaran) {
        String sqlTransaksi = "INSERT INTO tabel_transaksi (tipe, total, bayar, kembalian) VALUES (?, ?, ?, ?)";
        String sqlPengeluaran = "INSERT INTO tabel_pengeluaran (id_transaksi, keterangan) VALUES (?, ?)";

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement(sqlTransaksi, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, "Pengeluaran");
                ps.setDouble(2, pengeluaran.getTotal());
                ps.setDouble(3, 0);
                ps.setDouble(4, 0);
                ps.executeUpdate();

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idTransaksi = generatedKeys.getInt(1);
                        try (PreparedStatement psOut = connection.prepareStatement(sqlPengeluaran)) {
                            psOut.setInt(1, idTransaksi);
                            psOut.setString(2, pengeluaran.getKeteranganOperasional());
                            psOut.executeUpdate();
                        }
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<Transaksi> getAllTransaksi() {
        List<Transaksi> list = new ArrayList<>();
        String sql = "SELECT * FROM tabel_transaksi ORDER BY id DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Transaksi transaksi = new Transaksi();
                transaksi.setIdTransaksi(rs.getInt("id"));
                transaksi.setTanggal(rs.getString("tanggal"));
                transaksi.setTotal(rs.getDouble("total"));
                list.add(transaksi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
