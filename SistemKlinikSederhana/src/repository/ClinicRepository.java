package repository;

import database.DatabaseConnection;
import model.Appointment;
import model.Doctor;
import model.Patient;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClinicRepository {
    public User login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"), rs.getString("full_name"), rs.getString("username"), rs.getString("password"), rs.getString("role"), rs.getString("phone"), rs.getString("address"));
                }
            }
        }
        return null;
    }

    public void savePatient(Patient patient) throws SQLException {
        if (patient.getId() > 0) {
            String sql = "UPDATE patients SET full_name=?, phone=?, address=?, birth_date=?, gender=?, medical_history=? WHERE id=?";
            try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, patient.getFullName());
                stmt.setString(2, patient.getPhone());
                stmt.setString(3, patient.getAddress());
                stmt.setString(4, patient.getBirthDate());
                stmt.setString(5, patient.getGender());
                stmt.setString(6, patient.getMedicalHistory());
                stmt.setInt(7, patient.getId());
                stmt.executeUpdate();
            }
        } else {
            String sql = "INSERT INTO patients(full_name, phone, address, birth_date, gender, medical_history) VALUES(?,?,?,?,?,?)";
            try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, patient.getFullName());
                stmt.setString(2, patient.getPhone());
                stmt.setString(3, patient.getAddress());
                stmt.setString(4, patient.getBirthDate());
                stmt.setString(5, patient.getGender());
                stmt.setString(6, patient.getMedicalHistory());
                stmt.executeUpdate();
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        patient.setId(keys.getInt(1));
                    }
                }
            }
        }
    }

    public List<Patient> getAllPatients() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                patients.add(new Patient(rs.getInt("id"), rs.getString("full_name"), rs.getString("phone"), rs.getString("address"), rs.getString("birth_date"), rs.getString("gender"), rs.getString("medical_history")));
            }
        }
        return patients;
    }

    public List<Patient> searchPatients(String keyword) throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients WHERE full_name LIKE ? OR phone LIKE ? OR address LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            String pattern = "%" + keyword + "%";
            stmt.setString(1, pattern);
            stmt.setString(2, pattern);
            stmt.setString(3, pattern);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    patients.add(new Patient(rs.getInt("id"), rs.getString("full_name"), rs.getString("phone"), rs.getString("address"), rs.getString("birth_date"), rs.getString("gender"), rs.getString("medical_history")));
                }
            }
        }
        return patients;
    }

    public void deletePatient(int id) throws SQLException {
        String sql = "DELETE FROM patients WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void saveDoctor(Doctor doctor) throws SQLException {
        if (doctor.getId() > 0) {
            String sql = "UPDATE doctors SET full_name=?, phone=?, address=?, specialty=? WHERE id=?";
            try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, doctor.getFullName());
                stmt.setString(2, doctor.getPhone());
                stmt.setString(3, doctor.getAddress());
                stmt.setString(4, doctor.getSpecialty());
                stmt.setInt(5, doctor.getId());
                stmt.executeUpdate();
            }
        } else {
            String sql = "INSERT INTO doctors(full_name, phone, address, specialty) VALUES(?,?,?,?)";
            try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, doctor.getFullName());
                stmt.setString(2, doctor.getPhone());
                stmt.setString(3, doctor.getAddress());
                stmt.setString(4, doctor.getSpecialty());
                stmt.executeUpdate();
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        doctor.setId(keys.getInt(1));
                    }
                }
            }
        }
    }

    public List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                doctors.add(new Doctor(rs.getInt("id"), rs.getString("full_name"), rs.getString("phone"), rs.getString("address"), rs.getString("specialty")));
            }
        }
        return doctors;
    }

    public void saveAppointment(Appointment appointment) throws SQLException {
        if (appointment.getId() > 0) {
            String sql = "UPDATE appointments SET patient_id=?, doctor_id=?, appointment_date=?, status=?, notes=? WHERE id=?";
            try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, appointment.getPatientId());
                stmt.setInt(2, appointment.getDoctorId());
                stmt.setString(3, appointment.getAppointmentDate());
                stmt.setString(4, appointment.getStatus());
                stmt.setString(5, appointment.getNotes());
                stmt.setInt(6, appointment.getId());
                stmt.executeUpdate();
            }
        } else {
            String sql = "INSERT INTO appointments(patient_id, doctor_id, appointment_date, status, notes) VALUES(?,?,?,?,?)";
            try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, appointment.getPatientId());
                stmt.setInt(2, appointment.getDoctorId());
                stmt.setString(3, appointment.getAppointmentDate());
                stmt.setString(4, appointment.getStatus());
                stmt.setString(5, appointment.getNotes());
                stmt.executeUpdate();
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        appointment.setId(keys.getInt(1));
                    }
                }
            }
        }
    }

    public List<Appointment> getAllAppointments() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                appointments.add(new Appointment(rs.getInt("id"), rs.getInt("patient_id"), rs.getInt("doctor_id"), rs.getString("appointment_date"), rs.getString("status"), rs.getString("notes")));
            }
        }
        return appointments;
    }

    public List<Appointment> searchAppointments(String keyword) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE notes LIKE ? OR status LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            String pattern = "%" + keyword + "%";
            stmt.setString(1, pattern);
            stmt.setString(2, pattern);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    appointments.add(new Appointment(rs.getInt("id"), rs.getInt("patient_id"), rs.getInt("doctor_id"), rs.getString("appointment_date"), rs.getString("status"), rs.getString("notes")));
                }
            }
        }
        return appointments;
    }
}
