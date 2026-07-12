package com.warungqu.view;

import com.warungqu.dao.ProdukDAO;
import com.warungqu.model.Produk;
import com.warungqu.util.FormatUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProdukPanel extends JPanel {
    private final ProdukDAO produkDAO;
    private final DefaultTableModel tableModel;
    private final JTable produkTable;
    private final JTextField namaField;
    private final JTextField hargaField;
    private final JTextField idField;
    private final JButton simpanButton;
    private final JButton hapusButton;
    private final JButton resetButton;

    public ProdukPanel() {
        this.produkDAO = new ProdukDAO();
        setLayout(new BorderLayout(12, 12));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel title = new JLabel("Manajemen Produk");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(new Color(46, 125, 50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        formPanel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        formPanel.add(new JLabel("ID"), gbc);
        idField = new JTextField(8);
        idField.setEditable(false);
        gbc.gridx = 1;
        formPanel.add(idField, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Nama Produk"), gbc);
        namaField = new JTextField(16);
        gbc.gridx = 3;
        formPanel.add(namaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Harga"), gbc);
        hargaField = new JTextField(16);
        gbc.gridx = 1;
        formPanel.add(hargaField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        simpanButton = new JButton("Simpan");
        simpanButton.setBackground(new Color(21, 101, 192));
        simpanButton.setForeground(Color.WHITE);
        formPanel.add(simpanButton, gbc);

        hapusButton = new JButton("Hapus");
        hapusButton.setBackground(new Color(198, 40, 40));
        hapusButton.setForeground(Color.WHITE);
        gbc.gridx = 3;
        formPanel.add(hapusButton, gbc);

        resetButton = new JButton("Reset");
        resetButton.setBackground(new Color(120, 120, 120));
        resetButton.setForeground(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(resetButton, gbc);

        String[] columns = {"ID", "Nama Produk", "Harga"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        produkTable = new JTable(tableModel);
        produkTable.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                if (value instanceof Number) {
                    setText(FormatUtil.formatRupiah(((Number) value).doubleValue()));
                } else {
                    super.setValue(value);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(produkTable);

        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        simpanButton.addActionListener(e -> simpanProduk());
        hapusButton.addActionListener(e -> hapusProduk());
        resetButton.addActionListener(e -> resetForm());
        produkTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && produkTable.getSelectedRow() != -1) {
                int row = produkTable.getSelectedRow();
                idField.setText(tableModel.getValueAt(row, 0).toString());
                namaField.setText(tableModel.getValueAt(row, 1).toString());
                hargaField.setText(tableModel.getValueAt(row, 2).toString());
            }
        });

        loadProduk();
    }

    private void simpanProduk() {
        String nama = namaField.getText().trim();
        String hargaText = hargaField.getText().trim();

        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama produk wajib diisi.");
            return;
        }

        if (!hargaText.matches("^\\d+(\\.\\d+)?$")) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka positif tanpa tanda koma atau huruf.");
            return;
        }

        try {
            double harga = Double.parseDouble(hargaText);
            if (harga <= 0) {
                JOptionPane.showMessageDialog(this, "Harga harus lebih besar dari nol.");
                return;
            }

            Produk produk = new Produk();
            produk.setNamaProduk(nama);
            produk.setHarga(harga);

            if (!idField.getText().isEmpty()) {
                produk.setIdProduk(Integer.parseInt(idField.getText()));
                if (produkDAO.update(produk)) {
                    JOptionPane.showMessageDialog(this, "Produk berhasil diubah.");
                }
            } else if (produkDAO.insert(produk)) {
                JOptionPane.showMessageDialog(this, "Produk berhasil ditambah.");
            }

            resetForm();
            loadProduk();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka positif.");
        }
    }

    private void hapusProduk() {
        if (idField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih produk dulu dari tabel.");
            return;
        }

        int id = Integer.parseInt(idField.getText());
        int confirm = JOptionPane.showConfirmDialog(this, "Hapus produk ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (produkDAO.delete(id)) {
                JOptionPane.showMessageDialog(this, "Produk berhasil dihapus.");
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menghapus produk.");
            }
            resetForm();
            loadProduk();
        }
    }

    private void resetForm() {
        idField.setText("");
        namaField.setText("");
        hargaField.setText("");
        produkTable.clearSelection();
    }

    private void loadProduk() {
        tableModel.setRowCount(0);
        List<Produk> list = produkDAO.getAll();
        for (Produk p : list) {
            tableModel.addRow(new Object[]{p.getIdProduk(), p.getNamaProduk(), p.getHarga()});
        }
    }
}
