package model;
import interfacekasir.BisaDiskon;
import interfacekasir.BisaStok;

public class Makanan extends Produk implements BisaDiskon, BisaStok {

    @Override
    public int hitungDiskon() {
        return (harga * qty) * PERSEN_DISKON / 100;
    }

    @Override
    public int hitungTotal() {
        return (harga * qty) - hitungDiskon();
    }

    @Override
    public boolean cekStok() {
        return stok >= qty;
    }

    @Override
    public void kurangiStok() {
        stok -= qty;
    }
}
