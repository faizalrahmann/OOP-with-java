package com.warungqu.dao;

import com.warungqu.config.Koneksi;
import com.warungqu.model.DetailTransaksi;
import com.warungqu.model.Pemasukan;
import com.warungqu.model.Pengeluaran;
import com.warungqu.model.Transaksi;
import com.warungqu.model.DetailTransaksiReport;
import com.warungqu.util.FormatUtil;

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
        return getAllTransaksi(null);
    }

    public List<Transaksi> getAllTransaksi(String tipeFilter) {
        List<Transaksi> list = new ArrayList<>();
        String sql = "SELECT t.id, t.tipe, t.tanggal, t.total, t.bayar, t.kembalian, p.keterangan " +
                "FROM tabel_transaksi t " +
                "LEFT JOIN tabel_pengeluaran p ON t.id = p.id_transaksi ";
        if (tipeFilter != null && !tipeFilter.isBlank() && !"Semua".equalsIgnoreCase(tipeFilter)) {
            sql += "WHERE t.tipe = ? ";
        }
        sql += "ORDER BY t.id DESC";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            if (tipeFilter != null && !tipeFilter.isBlank() && !"Semua".equalsIgnoreCase(tipeFilter)) {
                ps.setString(1, tipeFilter);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Transaksi transaksi = new Transaksi();
                    transaksi.setIdTransaksi(rs.getInt("id"));
                    transaksi.setTipe(rs.getString("tipe"));
                    transaksi.setTanggal(rs.getString("tanggal"));
                    transaksi.setTotal(rs.getDouble("total"));
                    transaksi.setBayar(rs.getDouble("bayar"));
                    transaksi.setKembalian(rs.getDouble("kembalian"));
                    transaksi.setKeterangan(rs.getString("keterangan"));
                    list.add(transaksi);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<DetailTransaksiReport> getDetailTransaksi(int idTransaksi) {
        List<DetailTransaksiReport> detailList = new ArrayList<>();
        String sql = "SELECT p.nama_produk, dt.jumlah, dt.subtotal " +
                "FROM tabel_detail_transaksi dt " +
                "LEFT JOIN tabel_produk p ON dt.id_produk = p.id " +
                "WHERE dt.id_transaksi = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idTransaksi);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DetailTransaksiReport detail = new DetailTransaksiReport();
                    detail.setNamaProduk(rs.getString("nama_produk"));
                    detail.setJumlah(rs.getInt("jumlah"));
                    detail.setSubtotal(rs.getDouble("subtotal"));
                    detailList.add(detail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detailList;
    }

    public double getTodayPemasukan() {
        String sql = "SELECT COALESCE(SUM(total), 0) AS total FROM tabel_transaksi WHERE tipe = 'Pemasukan' AND DATE(tanggal) = CURDATE()";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getTodayPengeluaran() {
        String sql = "SELECT COALESCE(SUM(total), 0) AS total FROM tabel_transaksi WHERE tipe = 'Pengeluaran' AND DATE(tanggal) = CURDATE()";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getTodaySaldoBersih() {
        return getTodayPemasukan() - getTodayPengeluaran();
    }

    public List<String> getRecentActivity(int limit) {
        List<String> activity = new ArrayList<>();
        String sql = "SELECT tipe, tanggal, total FROM tabel_transaksi ORDER BY tanggal DESC LIMIT ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String tipe = rs.getString("tipe");
                    String tanggal = rs.getString("tanggal");
                    double total = rs.getDouble("total");
                    activity.add(tipe + " - " + tanggal + " - " + FormatUtil.formatRupiah(total));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activity;
    }
}

