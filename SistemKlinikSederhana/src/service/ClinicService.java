package service;

import model.Appointment;
import model.Doctor;
import model.Patient;
import model.User;
import repository.ClinicRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClinicService {
    private final ClinicRepository repository = new ClinicRepository();

    public User login(String username, String password) throws SQLException {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username tidak boleh kosong");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password tidak boleh kosong");
        }

        User user = repository.login(username.trim(), password);
        if (user == null) {
            throw new IllegalArgumentException("Username atau password salah");
        }
        return user;
    }

    public void savePatient(Patient patient) throws SQLException {
        if (patient == null) {
            throw new IllegalArgumentException("Data pasien tidak boleh kosong");
        }
        if (patient.getFullName() == null || patient.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama pasien wajib diisi");
        }
        if (patient.getPhone() == null || patient.getPhone().trim().isEmpty()) {
            throw new IllegalArgumentException("Nomor telepon wajib diisi");
        }
        repository.savePatient(patient);
    }

    public List<Patient> getAllPatients() throws SQLException {
        return repository.getAllPatients();
    }

    public List<Patient> searchPatients(String keyword) throws SQLException {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllPatients();
        }
        return repository.searchPatients(keyword.trim());
    }

    public void deletePatient(int id) throws SQLException {
        repository.deletePatient(id);
    }

    public void saveDoctor(Doctor doctor) throws SQLException {
        if (doctor == null) {
            throw new IllegalArgumentException("Data dokter tidak boleh kosong");
        }
        if (doctor.getFullName() == null || doctor.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama dokter wajib diisi");
        }
        if (doctor.getSpecialty() == null || doctor.getSpecialty().trim().isEmpty()) {
            throw new IllegalArgumentException("Spesialis wajib diisi");
        }
        repository.saveDoctor(doctor);
    }

    public List<Doctor> getAllDoctors() throws SQLException {
        return repository.getAllDoctors();
    }

    public void saveAppointment(Appointment appointment) throws SQLException {
        if (appointment == null) {
            throw new IllegalArgumentException("Data janji temu tidak boleh kosong");
        }
        if (appointment.getPatientId() <= 0 || appointment.getDoctorId() <= 0) {
            throw new IllegalArgumentException("Pasien dan dokter harus dipilih");
        }
        if (appointment.getAppointmentDate() == null || appointment.getAppointmentDate().trim().isEmpty()) {
            throw new IllegalArgumentException("Tanggal janji temu wajib diisi");
        }
        repository.saveAppointment(appointment);
    }

    public List<Appointment> getAllAppointments() throws SQLException {
        return repository.getAllAppointments();
    }

    public List<Appointment> searchAppointments(String keyword) throws SQLException {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllAppointments();
        }
        return repository.searchAppointments(keyword.trim());
    }

    public String buildReport() throws SQLException {
        List<Patient> patients = getAllPatients();
        List<Doctor> doctors = getAllDoctors();
        List<Appointment> appointments = getAllAppointments();
        return "Laporan Klinik\nPasien: " + patients.size() + "\nDokter: " + doctors.size() + "\nJanji Temu: " + appointments.size();
    }

    public Path exportReportToFile(String fileName) throws SQLException, IOException {
        String report = buildReport();
        Path path = Path.of(fileName);
        Files.createDirectories(path.getParent());
        Files.writeString(path, report);
        return path;
    }

    public List<String> getPatientSummaryList() throws SQLException {
        List<String> summaries = new ArrayList<>();
        for (Patient patient : getAllPatients()) {
            summaries.add(patient.getId() + " | " + patient.getFullName().toUpperCase() + " | " + patient.getPhone());
        }
        return summaries;
    }
}
