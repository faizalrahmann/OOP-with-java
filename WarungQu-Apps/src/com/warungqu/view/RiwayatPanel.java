package com.warungqu.view;

import com.warungqu.dao.TransaksiDAO;
import com.warungqu.model.Transaksi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RiwayatPanel extends JPanel {
    private final TransaksiDAO transaksiDAO;
    private final DefaultTableModel tableModel;

    public RiwayatPanel() {
        this.transaksiDAO = new TransaksiDAO();
        setLayout(new BorderLayout(12, 12));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JLabel title = new JLabel("Riwayat Transaksi");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(new Color(21, 101, 192));
        add(title, BorderLayout.NORTH);

        String[] columns = {"ID", "Tanggal", "Total"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadRiwayat();
    }

    private void loadRiwayat() {
        tableModel.setRowCount(0);
        List<Transaksi> transaksiList = transaksiDAO.getAllTransaksi();
        for (Transaksi transaksi : transaksiList) {
            tableModel.addRow(new Object[]{
                    transaksi.getIdTransaksi(),
                    transaksi.getTanggal(),
                    transaksi.getTotal()
            });
        }
    }
}
