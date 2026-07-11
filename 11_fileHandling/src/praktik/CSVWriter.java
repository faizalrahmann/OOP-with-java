package praktik;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CSVWriter {
    public static void main(String[] args) {
        String csvFile = "src/praktik/Students.csv";
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();

        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();

        System.out.print("Masukkan Umur: ");
        String umur = scanner.nextLine();

        System.out.print("Masukkan Prodi: ");
        String prodi = scanner.nextLine();

        String data = nim + "," + nama + "," + umur + "," + prodi;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile, true))) {
            bw.write(data);
            bw.newLine();
            System.out.println("Data berhasil ditambahkan ke " + csvFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
