package com.warungqu.view;

import com.warungqu.dao.ProdukDAO;
import com.warungqu.dao.TransaksiDAO;
import com.warungqu.model.DetailTransaksi;
import com.warungqu.model.Pemasukan;
import com.warungqu.model.Produk;
import com.warungqu.util.FormatUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class KasirPanel extends JPanel {
    private final JComboBox<Produk> produkComboBox;
    private final JSpinner jumlahSpinner;
    private final DefaultTableModel keranjangModel;
    private final JTable keranjangTable;
    private final JLabel grandTotalLabel;
    private final JTextField uangBayarField;
    private final JLabel kembalianLabel;
    private double grandTotal = 0;

    public KasirPanel() {
        setLayout(new BorderLayout(12, 12));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel title = new JLabel("Mesin Kasir");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(new Color(46, 125, 50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        topPanel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        topPanel.add(new JLabel("Produk"), gbc);
        produkComboBox = new JComboBox<>();
        gbc.gridx = 1;
        topPanel.add(produkComboBox, gbc);

        gbc.gridx = 2;
        topPanel.add(new JLabel("Jumlah"), gbc);
        jumlahSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        gbc.gridx = 3;
        topPanel.add(jumlahSpinner, gbc);

        JButton tambahButton = new JButton("Tambah ke Keranjang");
        tambahButton.setBackground(new Color(21, 101, 192));
        tambahButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        topPanel.add(tambahButton, gbc);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(12, 12));
        centerPanel.setBackground(new Color(245, 245, 245));

        String[] columns = {"Produk", "Jumlah", "Subtotal"};
        keranjangModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        keranjangTable = new JTable(keranjangModel);
        keranjangTable.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                if (value instanceof Number) {
                    setText(FormatUtil.formatRupiah(((Number) value).doubleValue()));
                } else {
                    super.setValue(value);
                }
            }
        });
        centerPanel.add(new JScrollPane(keranjangTable), BorderLayout.CENTER);

        JPanel summaryPanel = new JPanel(new GridLayout(4, 2, 8, 8));
        summaryPanel.setBackground(Color.WHITE);
        summaryPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));

        summaryPanel.add(new JLabel("Grand Total"));
        grandTotalLabel = new JLabel("Rp 0");
        grandTotalLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        summaryPanel.add(grandTotalLabel);

        summaryPanel.add(new JLabel("Uang Bayar"));
        uangBayarField = new JTextField();
        summaryPanel.add(uangBayarField);

        summaryPanel.add(new JLabel("Uang Kembalian"));
        kembalianLabel = new JLabel("Rp 0");
        kembalianLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        summaryPanel.add(kembalianLabel);

        JButton selesaiButton = new JButton("Simpan Transaksi");
        selesaiButton.setBackground(new Color(46, 125, 50));
        selesaiButton.setForeground(Color.WHITE);
        summaryPanel.add(selesaiButton);
        summaryPanel.add(new JLabel(""));

        centerPanel.add(summaryPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        tambahButton.addActionListener(e -> tambahKeKeranjang());
        selesaiButton.addActionListener(e -> simpanTransaksi());
        uangBayarField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { hitungKembalian(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { hitungKembalian(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { hitungKembalian(); }
        });

        loadProduk();
    }

    private void loadProduk() {
        produkComboBox.removeAllItems();
        List<Produk> list = new ProdukDAO().getAll();
        for (Produk produk : list) {
            produkComboBox.addItem(produk);
        }
    }

    public void refreshProdukList() {
        loadProduk();
    }

    private void tambahKeKeranjang() {
        Produk produk = (Produk) produkComboBox.getSelectedItem();
        if (produk == null) {
            JOptionPane.showMessageDialog(this, "Tidak ada produk yang dipilih.");
            return;
        }

        int jumlah = (Integer) jumlahSpinner.getValue();
        double subtotal = produk.getHarga() * jumlah;
        grandTotal += subtotal;

        keranjangModel.addRow(new Object[]{produk.getNamaProduk(), jumlah, subtotal});
        grandTotalLabel.setText(FormatUtil.formatRupiah(grandTotal));
        hitungKembalian();
    }

    private void simpanTransaksi() {
        if (keranjangModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Keranjang masih kosong.");
            return;
        }

        try {
            String bayarText = uangBayarField.getText().trim();
            if (bayarText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Masukkan jumlah uang bayar.");
                return;
            }
            double bayar = Double.parseDouble(bayarText);
            if (bayar < 0) {
                JOptionPane.showMessageDialog(this, "Uang bayar tidak boleh negatif.");
                return;
            }
            if (bayar < grandTotal) {
                JOptionPane.showMessageDialog(this, "Uang bayar kurang dari total belanja.");
                return;
            }

            Pemasukan pemasukan = new Pemasukan();
            pemasukan.setTotal(grandTotal);
            pemasukan.setUangBayar(bayar);
            pemasukan.setUangKembalian(bayar - grandTotal);

            List<DetailTransaksi> detailList = new ArrayList<>();
            for (int i = 0; i < keranjangModel.getRowCount(); i++) {
                String namaProduk = keranjangModel.getValueAt(i, 0).toString();
                int jumlah = Integer.parseInt(keranjangModel.getValueAt(i, 1).toString());
                double subtotal = Double.parseDouble(keranjangModel.getValueAt(i, 2).toString());

                Produk produk = cariProdukByNama(namaProduk);
                if (produk != null) {
                    DetailTransaksi detail = new DetailTransaksi();
                    detail.setIdProduk(produk.getIdProduk());
                    detail.setJumlah(jumlah);
                    detail.setSubtotal(subtotal);
                    detailList.add(detail);
                }
            }

            TransaksiDAO transaksiDAO = new TransaksiDAO();
            int idTransaksi = transaksiDAO.simpanPemasukan(pemasukan, detailList);
            if (idTransaksi > 0) {
                JOptionPane.showMessageDialog(this, "Transaksi berhasil disimpan.");
                keranjangModel.setRowCount(0);
                grandTotal = 0;
                grandTotalLabel.setText(FormatUtil.formatRupiah(0));
                uangBayarField.setText("");
                kembalianLabel.setText(FormatUtil.formatRupiah(0));
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan transaksi.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Input uang bayar harus angka.");
        }
    }

    private Produk cariProdukByNama(String nama) {
        for (int i = 0; i < produkComboBox.getItemCount(); i++) {
            Produk produk = produkComboBox.getItemAt(i);
            if (produk.getNamaProduk().equalsIgnoreCase(nama)) {
                return produk;
            }
        }
        return null;
    }

    private void hitungKembalian() {
        try {
            double bayar = Double.parseDouble(uangBayarField.getText().trim());
            double kembalian = bayar - grandTotal;
            kembalianLabel.setText(FormatUtil.formatRupiah(Math.max(kembalian, 0)));
        } catch (NumberFormatException ex) {
            kembalianLabel.setText("Rp 0");
        }
    }
}
