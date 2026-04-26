package Task;

public class Mahasiswa {
    private String nim;
    private String nama;
    private int nilai;
    private String grade;
    private String status;

    public Mahasiswa(String nim, String nama, int nilai) {
        this.nim = nim;
        this.nama = nama;
        this.nilai = nilai;
        setGradeDanStatus();
    }

    private void setGradeDanStatus() {
        if (nilai < 0 || nilai > 100) {
            this.grade = "Invalid";
            this.status = "Input nilai anda salah";
        } else if (nilai >= 80) {
            this.grade = "A";
            this.status = "lulus";
        } else if (nilai >= 70) {
            this.grade = "B";
            this.status = "lulus";
        } else if (nilai >= 60) {
            this.grade = "C";
            this.status = "lulus";
        } else if (nilai >= 50) {
            this.grade = "D";
            this.status = "tidak lulus";
        } else {
            this.grade = "E";
            this.status = "tidak lulus";
        }
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public int getNilai() {
        return nilai;
    }

    public String getGrade() {
        return grade;
    }

    public String getStatus() {
        return status;
    }

    public boolean isValid() {
        return nilai >= 0 && nilai <= 100;
    }

    public boolean isLulus() {
        return isValid() && (grade.equals("A") || grade.equals("B") || grade.equals("C"));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("NIM : ").append(nim).append("\n");
        builder.append("Nama : ").append(nama).append("\n");
        builder.append("Nilai : ").append(nilai).append("\n");
        builder.append("Grade : ").append(grade);
        return builder.toString();
    }
}