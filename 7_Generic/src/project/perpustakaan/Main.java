package project.perpustakaan;

import java.util.Scanner;

import project.perpustakaan.model.Anggota;
import project.perpustakaan.model.BukuFiksi;
import project.perpustakaan.model.BukuNonFiksi;
import project.perpustakaan.service.Perpustakaan;
import project.perpustakaan.service.PerpustakaanImpl;

public class Main {

    public static void main(String[] args) {

        Perpustakaan perpus = new PerpustakaanImpl();
        Scanner scanner     = new Scanner(System.in);

        // Data awal buku
        perpus.tambahBuku(new BukuFiksi("F001", "Laskar Pelangi", "Andrea Hirata", 2005, "Drama"));
        perpus.tambahBuku(new BukuFiksi("F002", "Bumi Manusia", "Pramoedya Ananta Toer", 1980, "Sejarah"));
        perpus.tambahBuku(new BukuFiksi("F003", "Negeri 5 Menara", "Ahmad Fuadi", 2009, "Inspirasi"));
        perpus.tambahBuku(new BukuNonFiksi("N001", "Clean Code", "Robert C. Martin", 2008, "Pemrograman"));
        perpus.tambahBuku(new BukuNonFiksi("N002", "Atomic Habits", "James Clear", 2018, "Pengembangan Diri"));

        // Data awal anggota
        perpus.tambahAnggota(new Anggota("A001", "Budi Santoso", "081234567890"));
        perpus.tambahAnggota(new Anggota("A002", "Siti Rahayu", "089876543210"));
        perpus.tambahAnggota(new Anggota("A003", "Ferdi Gunawan", "082345678901"));

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║   SISTEM MANAJEMEN PERPUSTAKAAN      ║");
        System.out.println("║   Universitas Nusa Putra             ║");
        System.out.println("╚══════════════════════════════════════╝");

        boolean running = true;
        while (running) {
            System.out.println("\n========== MENU UTAMA ==========");
            System.out.println("1. Lihat Semua Buku");
            System.out.println("2. Cari Buku");
            System.out.println("3. Pinjam Buku");
            System.out.println("4. Kembalikan Buku");
            System.out.println("5. Lihat Semua Anggota");
            System.out.println("0. Keluar");
            System.out.println("================================");
            System.out.print("Pilih menu: ");

            String pilihan = scanner.nextLine().trim();

            switch (pilihan) {
                case "1":
                    perpus.tampilkanSemuaBuku();
                    break;

                case "2":
                    System.out.print("Masukkan judul buku: ");
                    String judul = scanner.nextLine();
                    perpus.cariBuku(judul);
                    break;

                case "3":
                    System.out.print("Masukkan ID anggota : ");
                    String idPinjam = scanner.nextLine();
                    System.out.print("Masukkan kode buku  : ");
                    String kodePinjam = scanner.nextLine();
                    perpus.pinjamBuku(idPinjam, kodePinjam);
                    break;

                case "4":
                    System.out.print("Masukkan ID anggota : ");
                    String idKembali = scanner.nextLine();
                    System.out.print("Masukkan kode buku  : ");
                    String kodeKembali = scanner.nextLine();
                    perpus.kembalikanBuku(idKembali, kodeKembali);
                    break;

                case "5":
                    perpus.tampilkanSemuaAnggota();
                    break;

                case "0":
                    System.out.println("\nTerima kasih! Sampai jumpa. 👋");
                    running = false;
                    break;

                default:
                    System.out.println("✘ Pilihan tidak valid. Silakan coba lagi.");
            }
        }

        scanner.close();
    }
}
