package collection;

import java.util.ArrayList;

public class ContohArrayList {

    public static void main(String[] args) {

        // Membuat ArrayList
        ArrayList<String> mataKuliah = new ArrayList<>();

        // Menambahkan data - add()
        mataKuliah.add("Pemrograman Berorientasi Objek");
        mataKuliah.add("Basis Data");
        mataKuliah.add("Jaringan Komputer");
        mataKuliah.add("Algoritma dan Struktur Data");
        mataKuliah.add("Kecerdasan Buatan");

        System.out.println("=== DATA AWAL ===");
        System.out.println("Jumlah mata kuliah : " + mataKuliah.size());
        System.out.println("Isi ArrayList      : " + mataKuliah);

        // Mengakses elemen - get()
        System.out.println("\n=== AKSES ELEMEN ===");
        System.out.println("Elemen index 0 : " + mataKuliah.get(0));
        System.out.println("Elemen index 2 : " + mataKuliah.get(2));

        // Mengubah elemen - set()
        System.out.println("\n=== UBAH ELEMEN ===");
        mataKuliah.set(1, "Basis Data Lanjut");
        System.out.println("Setelah set index 1 : " + mataKuliah);

        // Menghapus elemen - remove()
        System.out.println("\n=== HAPUS ELEMEN ===");
        mataKuliah.remove("Kecerdasan Buatan");
        System.out.println("Setelah remove      : " + mataKuliah);

        // Cek apakah elemen ada - contains()
        System.out.println("\n=== CEK ELEMEN ===");
        System.out.println("Mengandung 'Basis Data Lanjut' : " + mataKuliah.contains("Basis Data Lanjut"));
        System.out.println("Mengandung 'Kecerdasan Buatan' : " + mataKuliah.contains("Kecerdasan Buatan"));

        // Iterasi dengan for-each
        System.out.println("\n=== ITERASI FOR-EACH ===");
        for (String mk : mataKuliah) {
            System.out.println("- " + mk);
        }

        // Menghapus semua elemen - clear()
        mataKuliah.clear();
        System.out.println("\n=== SETELAH CLEAR ===");
        System.out.println("Jumlah mata kuliah : " + mataKuliah.size());
        System.out.println("ArrayList kosong   : " + mataKuliah.isEmpty());
    }
}
