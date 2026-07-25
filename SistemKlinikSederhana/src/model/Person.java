package model;

public abstract class Person {
    protected int id;
    protected String fullName;
    protected String phone;
    protected String address;

    public Person() {
    }

    public Person(int id, String fullName, String phone, String address) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
    }

    public abstract void displayInfo();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName == null ? "" : fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? "" : fullName.trim();
    }

    public String getPhone() {
        return phone == null ? "" : phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? "" : phone.trim();
    }

    public String getAddress() {
        return address == null ? "" : address;
    }

    public void setAddress(String address) {
        this.address = address == null ? "" : address.trim();
    }

    public String getSummary() {
        return getFullName() + " | " + getPhone();
    }
}
