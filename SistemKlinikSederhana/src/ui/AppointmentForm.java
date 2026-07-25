package ui;

import model.Appointment;
import service.ClinicService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class AppointmentForm extends JDialog {
    private final ClinicService clinicService = new ClinicService();
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> appointmentList = new JList<>(listModel);
    private final JTextField txtPatientId = new JTextField();
    private final JTextField txtDoctorId = new JTextField();
    private final JTextField txtDate = new JTextField();
    private final JTextField txtStatus = new JTextField();
    private final JTextArea txtNotes = new JTextArea();
    private int selectedId = 0;

    public AppointmentForm(Frame owner) {
        super(owner, "Transaksi Janji Temu", true);
        setSize(680, 450);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.add(new JLabel("ID Pasien"));
        formPanel.add(txtPatientId);
        formPanel.add(new JLabel("ID Dokter"));
        formPanel.add(txtDoctorId);
        formPanel.add(new JLabel("Tanggal"));
        formPanel.add(txtDate);
        formPanel.add(new JLabel("Status"));
        formPanel.add(txtStatus);
        formPanel.add(new JLabel("Catatan"));
        formPanel.add(new JScrollPane(txtNotes));

        JPanel buttonPanel = new JPanel();
        JButton btnSave = new JButton("Simpan");
        JButton btnRefresh = new JButton("Refresh");
        buttonPanel.add(btnSave);
        buttonPanel.add(btnRefresh);

        JPanel right = new JPanel(new BorderLayout());
        right.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
        right.add(new JScrollPane(appointmentList), BorderLayout.CENTER);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(right, BorderLayout.EAST);

        btnSave.addActionListener(e -> saveAppointment());
        btnRefresh.addActionListener(e -> loadAppointments());

        loadAppointments();
    }

    private void saveAppointment() {
        try {
            Appointment appointment = new Appointment(selectedId, Integer.parseInt(txtPatientId.getText()), Integer.parseInt(txtDoctorId.getText()), txtDate.getText(), txtStatus.getText(), txtNotes.getText());
            clinicService.saveAppointment(appointment);
            JOptionPane.showMessageDialog(this, "Janji temu disimpan");
            loadAppointments();
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadAppointments() {
        try {
            List<Appointment> appointments = clinicService.getAllAppointments();
            listModel.clear();
            for (Appointment appointment : appointments) {
                listModel.addElement(appointment.getId() + " | " + appointment.getPatientId() + " | " + appointment.getDoctorId() + " | " + appointment.getStatus());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void clearForm() {
        selectedId = 0;
        txtPatientId.setText("");
        txtDoctorId.setText("");
        txtDate.setText("");
        txtStatus.setText("");
        txtNotes.setText("");
    }
}
