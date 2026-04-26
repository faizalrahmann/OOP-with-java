package model;

 import interfacekasir.BisaDiskon;
 import interfacekasir.BisaStok;

 public class Minuman extends Produk implements BisaDiskon, BisaStok {

    @Override
    public void hitungDiskon() {
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
     public void kurangiStok(){
        stok -= qty;
     }

    @Override
     public void tampil () {
        super.tampil();
        System.out.println("Stok: " + stok);
     }
 }