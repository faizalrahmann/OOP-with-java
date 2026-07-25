package model;

public class Patient extends Person {
    private String birthDate;
    private String gender;
    private String medicalHistory;

    public Patient() {
    }

    public Patient(int id, String fullName, String phone, String address, String birthDate, String gender, String medicalHistory) {
        super(id, fullName, phone, address);
        this.birthDate = birthDate;
        this.gender = gender;
        this.medicalHistory = medicalHistory;
    }

    @Override
    public void displayInfo() {
        System.out.println("Pasien: " + getFullName() + " - " + getPhone());
    }

    public String getBirthDate() {
        return birthDate == null ? "" : birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate == null ? "" : birthDate.trim();
    }

    public String getGender() {
        return gender == null ? "" : gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? "" : gender.trim();
    }

    public String getMedicalHistory() {
        return medicalHistory == null ? "" : medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory == null ? "" : medicalHistory.trim();
    }
}
