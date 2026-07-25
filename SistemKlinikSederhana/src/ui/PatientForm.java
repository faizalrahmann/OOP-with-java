package ui;

import model.Patient;
import service.ClinicService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PatientForm extends JDialog {
    private final ClinicService clinicService = new ClinicService();
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> patientList = new JList<>(listModel);
    private final JTextField txtName = new JTextField();
    private final JTextField txtPhone = new JTextField();
    private final JTextField txtAddress = new JTextField();
    private final JTextField txtBirthDate = new JTextField();
    private final JTextField txtGender = new JTextField();
    private final JTextArea txtHistory = new JTextArea();
    private int selectedId = 0;

    public PatientForm(Frame owner) {
        super(owner, "Data Pasien", true);
        setSize(700, 500);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.add(new JLabel("Nama"));
        formPanel.add(txtName);
        formPanel.add(new JLabel("Telepon"));
        formPanel.add(txtPhone);
        formPanel.add(new JLabel("Alamat"));
        formPanel.add(txtAddress);
        formPanel.add(new JLabel("Tanggal Lahir"));
        formPanel.add(txtBirthDate);
        formPanel.add(new JLabel("Gender"));
        formPanel.add(txtGender);
        formPanel.add(new JLabel("Riwayat"));
        formPanel.add(new JScrollPane(txtHistory));

        JPanel buttonPanel = new JPanel();
        JButton btnSave = new JButton("Simpan");
        JButton btnDelete = new JButton("Hapus");
        JButton btnSearch = new JButton("Cari");
        buttonPanel.add(btnSave);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnSearch);

        JPanel left = new JPanel(new BorderLayout());
        left.add(formPanel, BorderLayout.CENTER);
        left.add(buttonPanel, BorderLayout.SOUTH);

        JPanel right = new JPanel(new BorderLayout());
        right.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
        JTextField txtSearch = new JTextField();
        right.add(txtSearch, BorderLayout.NORTH);
        right.add(new JScrollPane(patientList), BorderLayout.CENTER);

        add(left, BorderLayout.CENTER);
        add(right, BorderLayout.EAST);

        btnSave.addActionListener(e -> savePatient());
        btnDelete.addActionListener(e -> deletePatient());
        btnSearch.addActionListener(e -> searchPatient(txtSearch.getText()));
        patientList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String value = patientList.getSelectedValue();
                if (value != null) {
                    String[] parts = value.split("\\|");
                    if (parts.length > 0) {
                        selectedId = Integer.parseInt(parts[0].trim());
                    }
                }
            }
        });

        loadPatients();
    }

    private void savePatient() {
        try {
            Patient patient = new Patient(selectedId, txtName.getText(), txtPhone.getText(), txtAddress.getText(), txtBirthDate.getText(), txtGender.getText(), txtHistory.getText());
            clinicService.savePatient(patient);
            JOptionPane.showMessageDialog(this, "Data pasien disimpan");
            loadPatients();
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletePatient() {
        try {
            if (selectedId > 0) {
                clinicService.deletePatient(selectedId);
                JOptionPane.showMessageDialog(this, "Data pasien dihapus");
                loadPatients();
                clearForm();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchPatient(String keyword) {
        try {
            List<Patient> patients = clinicService.searchPatients(keyword);
            listModel.clear();
            for (Patient patient : patients) {
                listModel.addElement(patient.getId() + " | " + patient.getFullName() + " | " + patient.getPhone());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void loadPatients() {
        searchPatient("");
    }

    private void clearForm() {
        selectedId = 0;
        txtName.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtBirthDate.setText("");
        txtGender.setText("");
        txtHistory.setText("");
    }
}
