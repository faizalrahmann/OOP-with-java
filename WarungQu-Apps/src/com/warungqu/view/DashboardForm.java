package com.warungqu.view;

import javax.swing.*;
import java.awt.*;

public class DashboardForm extends JFrame {
    private final Color bg = new Color(245, 245, 245);
    private final Color primaryGreen = new Color(46, 125, 50);
    private final Color dangerRed = new Color(198, 40, 40);
    private final Color accentBlue = new Color(21, 101, 192);
    private final Color textDark = new Color(33, 33, 33);

    public DashboardForm() {
        setTitle("WarungQU - Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 760);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(bg);
        tabbedPane.setForeground(textDark);

        tabbedPane.addTab("Dashboard", createDashboardPanel());
        tabbedPane.addTab("Produk", new ProdukPanel());
        tabbedPane.addTab("Kasir", new KasirPanel());
        tabbedPane.addTab("Pengeluaran", new PengeluaranPanel());
        tabbedPane.addTab("Riwayat", new RiwayatPanel());

        setContentPane(tabbedPane);
    }

    private JPanel createDashboardPanel() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(bg);
        root.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel titleLabel = new JLabel("Dashboard WarungQU");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(primaryGreen);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));
        root.add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(12, 12));
        centerPanel.setBackground(bg);

        JPanel cardsPanel = new JPanel(new GridLayout(1, 3, 12, 0));
        cardsPanel.setBackground(bg);

        cardsPanel.add(createSummaryCard("Pemasukan Hari Ini", "Rp 0", primaryGreen));
        cardsPanel.add(createSummaryCard("Pengeluaran Hari Ini", "Rp 0", dangerRed));
        cardsPanel.add(createSummaryCard("Saldo Bersih", "Rp 0", accentBlue));

        centerPanel.add(cardsPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        bottomPanel.setBackground(bg);

        bottomPanel.add(createActivityPanel("Recent Activity", textDark));
        bottomPanel.add(createActivityPanel("Menu Utama", textDark));

        centerPanel.add(bottomPanel, BorderLayout.CENTER);
        root.add(centerPanel, BorderLayout.CENTER);
        return root;
    }

    private JPanel createSummaryCard(String title, String value, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 2),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titleLabel.setForeground(textDark);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        valueLabel.setForeground(color);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        return card;
    }

    private JPanel createActivityPanel(String title, Color textColor) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(textColor);
        panel.add(titleLabel, BorderLayout.NORTH);

        JTextArea area = new JTextArea("Belum ada data\n");
        area.setEditable(false);
        area.setBackground(Color.WHITE);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(new JScrollPane(area), BorderLayout.CENTER);
        return panel;
    }
}
