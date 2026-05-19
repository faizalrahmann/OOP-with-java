package model;

public abstract class Produk{
    public string nama;
    public int harga;
    public int qty;
    public int stok;

    public abstract int hitungTotal();

    public void tampil() {
        System.out.pritnln()

    }
}