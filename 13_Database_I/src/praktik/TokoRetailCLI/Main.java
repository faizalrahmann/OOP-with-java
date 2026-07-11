package praktik.TokoRetailCLI;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final BarangDAO barangDAO = new BarangDAO();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("Pilihan : ");
            String pilihan = sc.nextLine().trim();

            switch (pilihan) {
                case "1":
                    showAllData();
                    break;
                case "2":
                    addData(sc);
                    break;
                case "3":
                    searchData(sc);
                    break;
                case "4":
                    updateData(sc);
                    break;
                case "5":
                    deleteData(sc);
                    break;
                case "0":
                    System.out.println("Keluar program. Terima kasih.");
                    sc.close();
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }

            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("============================================");
        System.out.println("               MENU TOKO RETAIL             ");
        System.out.println("============================================");
        System.out.println("1. Tampil Semua Data");
        System.out.println("2. Tambah Data");
        System.out.println("3. Cari Data");
        System.out.println("4. Ubah Data");
        System.out.println("5. Hapus Data");
        System.out.println("0. Keluar");
        System.out.println("============================================");
    }

    private static void showAllData() {
        List<Barang> barangList = barangDAO.getAll();
        printResult("DAFTAR BARANG TOKO RETAIL", barangList);
    }

    private static void addData(Scanner sc) {
        System.out.println("\nTAMBAH DATA BARANG");
        System.out.print("Kode Barang   : ");
        String kode = sc.nextLine().trim();
        System.out.print("Nama Barang   : ");
        String nama = sc.nextLine().trim();
        System.out.print("Harga Barang  : ");
        int harga = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Stok Barang   : ");
        int stok = Integer.parseInt(sc.nextLine().trim());

        Barang barang = new Barang(kode, nama, harga, stok);
        if (barangDAO.insert(barang)) {
            System.out.println("Data berhasil ditambahkan.");
        } else {
            System.out.println("Gagal menambahkan data.");
        }
    }

    private static void searchData(Scanner sc) {
        System.out.println("\nCARI DATA BARANG");
        System.out.print("Masukkan kode atau nama barang: ");
        String keyword = sc.nextLine().trim();

        List<Barang> barangList = barangDAO.search(keyword);
        printResult("HASIL PENCARIAN", barangList);
    }

    private static void updateData(Scanner sc) {
        System.out.println("\nUBAH DATA BARANG");
        System.out.print("Masukkan kode barang yang akan diubah: ");
        String kode = sc.nextLine().trim();

        Barang existing = barangDAO.findByKode(kode);
        if (existing == null) {
            System.out.println("Kode barang tidak ditemukan.");
            return;
        }

        System.out.println("Nama Lama : " + existing.getNamaBarang());
        System.out.println("Harga Lama: " + existing.getHargaBarang());
        System.out.println("Stok Lama : " + existing.getStokBarang());

        System.out.print("Nama Barang baru : ");
        String nama = sc.nextLine().trim();
        System.out.print("Harga Barang baru: ");
        int harga = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Stok Barang baru : ");
        int stok = Integer.parseInt(sc.nextLine().trim());

        Barang barang = new Barang(kode, nama, harga, stok);
        if (barangDAO.update(barang)) {
            System.out.println("Data berhasil diubah.");
        } else {
            System.out.println("Gagal mengubah data.");
        }
    }

    private static void deleteData(Scanner sc) {
        System.out.println("\nHAPUS DATA BARANG");
        System.out.print("Masukkan kode barang yang akan dihapus: ");
        String kode = sc.nextLine().trim();

        if (barangDAO.delete(kode)) {
            System.out.println("Data berhasil dihapus.");
        } else {
            System.out.println("Kode barang tidak ditemukan atau gagal dihapus.");
        }
    }

    private static void printResult(String title, List<Barang> barangList) {
        System.out.println();
        System.out.println(title);
        printTableHeader();

        if (barangList.isEmpty()) {
            System.out.println("Tidak ada data yang ditampilkan.");
            return;
        }

        int nomor = 0;
        for (Barang barang : barangList) {
            nomor++;
            System.out.printf("%-4d %s%n", nomor, barang.toString());
        }
        System.out.println("Total: " + barangList.size() + " barang");
    }

    private static void printTableHeader() {
        System.out.printf("%-4s %-12s %-25s %-10s %-6s%n", "#", "Kode", "Nama Barang", "Harga", "Stok");
        System.out.println("--------------------------------------------------------------");
    }
}