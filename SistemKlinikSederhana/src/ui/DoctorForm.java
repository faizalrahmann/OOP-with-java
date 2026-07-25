package ui;

import model.Doctor;
import service.ClinicService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class DoctorForm extends JDialog {
    private final ClinicService clinicService = new ClinicService();
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> doctorList = new JList<>(listModel);
    private final JTextField txtName = new JTextField();
    private final JTextField txtPhone = new JTextField();
    private final JTextField txtAddress = new JTextField();
    private final JTextField txtSpecialty = new JTextField();
    private int selectedId = 0;

    public DoctorForm(Frame owner) {
        super(owner, "Data Dokter", true);
        setSize(650, 420);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.add(new JLabel("Nama"));
        formPanel.add(txtName);
        formPanel.add(new JLabel("Telepon"));
        formPanel.add(txtPhone);
        formPanel.add(new JLabel("Alamat"));
        formPanel.add(txtAddress);
        formPanel.add(new JLabel("Spesialis"));
        formPanel.add(txtSpecialty);

        JPanel buttonPanel = new JPanel();
        JButton btnSave = new JButton("Simpan");
        JButton btnRefresh = new JButton("Refresh");
        buttonPanel.add(btnSave);
        buttonPanel.add(btnRefresh);

        JPanel right = new JPanel(new BorderLayout());
        right.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
        right.add(new JScrollPane(doctorList), BorderLayout.CENTER);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(right, BorderLayout.EAST);

        btnSave.addActionListener(e -> saveDoctor());
        btnRefresh.addActionListener(e -> loadDoctors());

        loadDoctors();
    }

    private void saveDoctor() {
        try {
            Doctor doctor = new Doctor(selectedId, txtName.getText(), txtPhone.getText(), txtAddress.getText(), txtSpecialty.getText());
            clinicService.saveDoctor(doctor);
            JOptionPane.showMessageDialog(this, "Data dokter disimpan");
            loadDoctors();
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadDoctors() {
        try {
            List<Doctor> doctors = clinicService.getAllDoctors();
            listModel.clear();
            for (Doctor doctor : doctors) {
                listModel.addElement(doctor.getId() + " | " + doctor.getFullName() + " | " + doctor.getSpecialty());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void clearForm() {
        selectedId = 0;
        txtName.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtSpecialty.setText("");
    }
}
