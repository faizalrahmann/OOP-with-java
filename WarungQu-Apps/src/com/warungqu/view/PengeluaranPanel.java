package com.warungqu.view;

import com.warungqu.dao.TransaksiDAO;
import com.warungqu.model.Pengeluaran;

import javax.swing.*;
import java.awt.*;

public class PengeluaranPanel extends JPanel {
    private final JTextField nominalField;
    private final JTextArea keteranganArea;
    private final TransaksiDAO transaksiDAO;

    public PengeluaranPanel() {
        this.transaksiDAO = new TransaksiDAO();
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
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel title = new JLabel("Input Pengeluaran");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(new Color(198, 40, 40));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Nominal"), gbc);
        nominalField = new JTextField(18);
        gbc.gridx = 1;
        formPanel.add(nominalField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Keterangan"), gbc);
        keteranganArea = new JTextArea(4, 18);
        JScrollPane scrollPane = new JScrollPane(keteranganArea);
        gbc.gridx = 1;
        formPanel.add(scrollPane, gbc);

        JButton simpanButton = new JButton("Simpan Pengeluaran");
        simpanButton.setBackground(new Color(198, 40, 40));
        simpanButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(simpanButton, gbc);

        add(formPanel, BorderLayout.CENTER);

        simpanButton.addActionListener(e -> simpanPengeluaran());
    }

    private void simpanPengeluaran() {
        try {
            double nominal = Double.parseDouble(nominalField.getText().trim());
            String keterangan = keteranganArea.getText().trim();

            if (keterangan.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Keterangan pengeluaran wajib diisi.");
                return;
            }

            Pengeluaran pengeluaran = new Pengeluaran();
            pengeluaran.setTotal(nominal);
            pengeluaran.setKeteranganOperasional(keterangan);

            boolean success = transaksiDAO.simpanPengeluaran(pengeluaran);
            if (success) {
                JOptionPane.showMessageDialog(this, "Pengeluaran berhasil disimpan.");
                nominalField.setText("");
                keteranganArea.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan pengeluaran.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Nominal harus berupa angka.");
        }
    }
}
