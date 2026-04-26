package app;
import model.Makanan;
import model.Minuman;

public class KasirApp {
    public static void main(String[] args) {
        System.out.println("=== SISTEM KASIR ===");

        Makanan makanana = new Makanan();
        makanana.nama = "Nasi Goreng";
        makanann.harga = 20000;
        makanan.qty = 2;

        Minuman minuman = new Minuman ();
        minuman.nama = "Teh Botol";
        minuman.harga = 5000;
        minuman.qty = 10;
        int totalMinuman = 0;

        System.out.println("\n=== Item1 ===");
        makanan.tapil();
        System.out.println("Diskon: " + makanan.hitungDiskon());
        int totalMakanan = makanan.hitungTotal();

        System.out.println("\n=== Item2 ===");
        minuman.tampil();
        if (minuman.cekStok()) {
            System.out.println("Diskon: " + minuman.hitungDiskon());
            System.out.println("Total: " + minuman.hitungTotal());
            totalMinuman = minuman.hitungTotal();
            minuman.kurangiStok();
            System.out.println("Sisa Stok: " + minuman.stok);
        }
        else {
            System.out.println("Stok tidak cukup!");
        }

        int grandTotal = totalMakanan + totalMinuman;
        System.out.println("\n==================");
        System.out.println("Grand Total: " + grandTotal);
    }

    
}
