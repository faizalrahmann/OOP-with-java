package Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Mahasiswa> mahasiswas = new ArrayList<>();
        int totalNilai = 0;
        int jumlahMahasiswa = 0;

        System.out.print("Masukkan jumlah mahasiswa: ");
        int n = scanner.nextInt();
        scanner.nextLine(); 

        int jumlahLulus = 0;
        int jumlahTidakLulus = 0;
        ArrayList<String> namaLulus = new ArrayList<>();
        ArrayList<String> namaTidakLulus = new ArrayList<>();
        ArrayList<String> nilaiA = new ArrayList<>();
        ArrayList<String> nilaiB = new ArrayList<>();
        ArrayList<String> nilaiC = new ArrayList<>();
        ArrayList<String> nilaiD = new ArrayList<>();
        ArrayList<String> nilaiE = new ArrayList<>();
        StringBuilder formulaNilai = new StringBuilder();

        for (int i = 0; i < n; i++) {
            System.out.println("Masukkan data mahasiswa ke-" + (i + 1) + ":");
            System.out.print("NIM: ");
            String nim = scanner.nextLine();
            System.out.print("Nama: ");
            String nama = scanner.nextLine();
            System.out.print("Nilai: ");
            int nilai = scanner.nextInt();
            scanner.nextLine(); 

            Mahasiswa mhs = new Mahasiswa(nim, nama, nilai);
            mahasiswas.add(mhs);

            if (mhs.isValid()) {
                totalNilai += nilai;
                jumlahMahasiswa++;

                if (formulaNilai.length() > 0) {
                    formulaNilai.append("+");
                }
                formulaNilai.append(nilai);

                if (mhs.isLulus()) {
                    jumlahLulus++;
                    namaLulus.add(nama);
                } else {
                    if (!mhs.getGrade().equals("Invalid")) {
                        jumlahTidakLulus++;
                        namaTidakLulus.add(nama);
                    }
                }

                switch (mhs.getGrade()) {
                    case "A" -> nilaiA.add(nama);
                    case "B" -> nilaiB.add(nama);
                    case "C" -> nilaiC.add(nama);
                    case "D" -> nilaiD.add(nama);
                    case "E" -> nilaiE.add(nama);
                }
            } else {
                System.out.println("Input nilai anda salah");
            }
        }

        System.out.println();
        for (Mahasiswa mhs : mahasiswas) {
            System.out.println(mhs);
            System.out.println("============================================");
        }

        System.out.println("Jumlah Mahasiswa : " + n);
        System.out.println("Jumlah Mahasiswa yg Lulus : " + jumlahLulus + (namaLulus.isEmpty() ? "" : " yaitu " + String.join(", ", namaLulus)));
        System.out.println("Jumlah Mahasiswa yg Tidak Lulus : " + jumlahTidakLulus + (namaTidakLulus.isEmpty() ? "" : " yaitu " + String.join(", ", namaTidakLulus)));

        if (!nilaiA.isEmpty()) {
            System.out.println("Jumlah Mahasiswa dengan Nilai A : " + nilaiA.size() + " yaitu " + String.join(", ", nilaiA));
        }
        if (!nilaiB.isEmpty()) {
            System.out.println("Jumlah Mahasiswa dengan Nilai B : " + nilaiB.size() + " yaitu " + String.join(", ", nilaiB));
        }
        if (!nilaiC.isEmpty()) {
            System.out.println("Jumlah Mahasiswa dengan Nilai C : " + nilaiC.size() + " yaitu " + String.join(", ", nilaiC));
        }
        if (!nilaiD.isEmpty()) {
            System.out.println("Jumlah Mahasiswa dengan Nilai D : " + nilaiD.size() + " yaitu " + String.join(", ", nilaiD));
        }
        if (!nilaiE.isEmpty()) {
            System.out.println("Jumlah Mahasiswa dengan Nilai E : " + nilaiE.size() + " yaitu " + String.join(", ", nilaiE));
        }

        if (jumlahMahasiswa > 0) {
            double rataRata = (double) totalNilai / jumlahMahasiswa;
            System.out.println("Rata-rata nilai mahasiswa adalah : " + formulaNilai + " / " + jumlahMahasiswa + " = " + String.format("%.2f", rataRata));
        } else {
            System.out.println("Tidak ada nilai valid untuk menghitung rata-rata.");
        }

        scanner.close();
    }
}