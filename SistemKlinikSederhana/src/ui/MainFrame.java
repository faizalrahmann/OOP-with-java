package ui;

import model.User;
import service.ClinicService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class MainFrame extends JFrame {
    private final User currentUser;
    private final ClinicService clinicService = new ClinicService();

    public MainFrame(User currentUser) {
        this.currentUser = currentUser;
        setTitle("Sistem Klinik Sederhana");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuData = new JMenu("Data");
        JMenuItem itemPasien = new JMenuItem("Data Pasien");
        JMenuItem itemDokter = new JMenuItem("Data Dokter");
        JMenuItem itemJanji = new JMenuItem("Transaksi Janji Temu");
        JMenuItem itemLaporan = new JMenuItem("Laporan");
        menuData.add(itemPasien);
        menuData.add(itemDokter);
        menuData.add(itemJanji);
        menuData.add(itemLaporan);
        menuBar.add(menuData);
        setJMenuBar(menuBar);

        JTextArea area = new JTextArea();
        area.setText("Selamat datang " + currentUser.getFullName() + "\nRole: " + currentUser.getRole());
        area.setEditable(false);
        add(new JScrollPane(area), BorderLayout.CENTER);

        itemPasien.addActionListener(e -> new PatientForm(this).setVisible(true));
        itemDokter.addActionListener(e -> new DoctorForm(this).setVisible(true));
        itemJanji.addActionListener(e -> new AppointmentForm(this).setVisible(true));
        itemLaporan.addActionListener(e -> {
            try {
                JOptionPane.showMessageDialog(this, clinicService.buildReport());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });
    }
}
