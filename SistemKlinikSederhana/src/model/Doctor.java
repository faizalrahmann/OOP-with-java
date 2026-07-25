package model;

public class Doctor extends Person {
    private String specialty;

    public Doctor() {
    }

    public Doctor(int id, String fullName, String phone, String address, String specialty) {
        super(id, fullName, phone, address);
        this.specialty = specialty;
    }

    @Override
    public void displayInfo() {
        System.out.println("Dokter: " + getFullName() + " - " + specialty);
    }

    public String getSpecialty() {
        return specialty == null ? "" : specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty == null ? "" : specialty.trim();
    }
}
