package model;

public class Appointment {
    private int id;
    private int patientId;
    private int doctorId;
    private String appointmentDate;
    private String status;
    private String notes;

    public Appointment() {
        this.status = "Baru";
    }

    public Appointment(int id, int patientId, int doctorId, String appointmentDate, String status, String notes) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getAppointmentDate() {
        return appointmentDate == null ? "" : appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate == null ? "" : appointmentDate.trim();
    }

    public String getStatus() {
        return status == null ? "Baru" : status;
    }

    public void setStatus(String status) {
        this.status = status == null ? "Baru" : status.trim();
    }

    public String getNotes() {
        return notes == null ? "" : notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? "" : notes.trim();
    }
}
