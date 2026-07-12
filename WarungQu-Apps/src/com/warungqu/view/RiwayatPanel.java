package com.warungqu.view;

import com.warungqu.dao.TransaksiDAO;
import com.warungqu.model.DetailTransaksiReport;
import com.warungqu.model.Transaksi;
import com.warungqu.util.FormatUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RiwayatPanel extends JPanel {
    private final TransaksiDAO transaksiDAO;
    private final DefaultTableModel tableModel;
    private final JTable table;
    private final JComboBox<String> filterCombo;
    private final JTextArea detailArea;
    private final JLabel detailTitleLabel;

    public RiwayatPanel() {
        this.transaksiDAO = new TransaksiDAO();
        setLayout(new BorderLayout(12, 12));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JLabel title = new JLabel("Laporan Riwayat Transaksi");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(new Color(21, 101, 192));
        add(title, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBackground(new Color(245, 245, 245));
        topPanel.add(new JLabel("Filter Tipe:"));
        filterCombo = new JComboBox<>(new String[]{"Semua", "Pemasukan", "Pengeluaran"});
        topPanel.add(filterCombo);
        JButton refreshButton = new JButton("Refresh");
        topPanel.add(refreshButton);
        add(topPanel, BorderLayout.PAGE_START);

        String[] columns = {"ID", "Tanggal", "Tipe", "Total", "Bayar", "Kembalian", "Keterangan"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(700, 400));

        JPanel detailPanel = new JPanel(new BorderLayout(8, 8));
        detailPanel.setBackground(Color.WHITE);
        detailPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));
        detailTitleLabel = new JLabel("Detail Transaksi");
        detailTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        detailArea = new JTextArea();
        detailArea.setEditable(false);
        detailArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        detailArea.setLineWrap(true);
        detailArea.setWrapStyleWord(true);
        detailArea.setText("Pilih transaksi untuk melihat detail.");
        detailPanel.add(detailTitleLabel, BorderLayout.NORTH);
        detailPanel.add(new JScrollPane(detailArea), BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tableScroll, detailPanel);
        splitPane.setResizeWeight(0.65);
        add(splitPane, BorderLayout.CENTER);

        filterCombo.addActionListener(e -> loadRiwayat());
        refreshButton.addActionListener(e -> loadRiwayat());
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                showDetail();
            }
        });

        loadRiwayat();
    }

    private void loadRiwayat() {
        tableModel.setRowCount(0);
        String filter = filterCombo.getSelectedItem() != null ? filterCombo.getSelectedItem().toString() : "Semua";
        List<Transaksi> transaksiList = transaksiDAO.getAllTransaksi(filter);
        for (Transaksi transaksi : transaksiList) {
            tableModel.addRow(new Object[]{
                    transaksi.getIdTransaksi(),
                    transaksi.getTanggal(),
                    transaksi.getTipe(),
                    FormatUtil.formatRupiah(transaksi.getTotal()),
                    FormatUtil.formatRupiah(transaksi.getBayar()),
                    FormatUtil.formatRupiah(transaksi.getKembalian()),
                    transaksi.getKeterangan() == null ? "" : transaksi.getKeterangan()
            });
        }
        detailArea.setText("Pilih transaksi untuk melihat detail.");
        detailTitleLabel.setText("Detail Transaksi");
    }

    private void showDetail() {
        int row = table.getSelectedRow();
        if (row == -1) {
            detailArea.setText("Pilih transaksi untuk melihat detail.");
            detailTitleLabel.setText("Detail Transaksi");
            return;
        }

        int idTransaksi = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
        String tipe = tableModel.getValueAt(row, 2).toString();
        detailTitleLabel.setText("Detail Transaksi #" + idTransaksi + " (" + tipe + ")");

        if ("Pengeluaran".equalsIgnoreCase(tipe)) {
            String keterangan = tableModel.getValueAt(row, 6).toString();
            detailArea.setText("Tipe: Pengeluaran\n");
            detailArea.append("Keterangan: " + (keterangan.isBlank() ? "-" : keterangan) + "\n");
            detailArea.append("Total: " + tableModel.getValueAt(row, 3));
            return;
        }

        List<DetailTransaksiReport> detailList = transaksiDAO.getDetailTransaksi(idTransaksi);
        if (detailList.isEmpty()) {
            detailArea.setText("Tidak ada detail item untuk transaksi ini.");
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Daftar Item:\n\n");
        for (DetailTransaksiReport detail : detailList) {
            builder.append("- ")
                    .append(detail.getNamaProduk())
                    .append(" x")
                    .append(detail.getJumlah())
                    .append(" = ")
                    .append(FormatUtil.formatRupiah(detail.getSubtotal()))
                    .append("\n");
        }
        builder.append("\n");
        builder.append("Total: ").append(tableModel.getValueAt(row, 3)).append("\n");
        builder.append("Bayar: ").append(tableModel.getValueAt(row, 4)).append("\n");
        builder.append("Kembalian: ").append(tableModel.getValueAt(row, 5)).append("\n");
        detailArea.setText(builder.toString());
    }

    public void refreshRiwayat() {
        loadRiwayat();
    }
}
