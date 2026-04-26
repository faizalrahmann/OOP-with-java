package model;

public abstract class Produk {
    public String nama;
    public int harga;
    public int qty;
    public int stok;

    public abstract int hitungTotal();

    public void tampil() {
        System.out.println("Nama: " + nama);
        System.out.println("Harga: " + harga);
        System.out.println("Qty: " + qty);
        System.out.println("Stok: " + stok);
    }
}
