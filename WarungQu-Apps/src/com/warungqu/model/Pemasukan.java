package com.warungqu.model;

public class Pemasukan extends Transaksi {
    private double uangBayar;
    private double uangKembalian;

    public Pemasukan() {
    }

    public Pemasukan(int idTransaksi, String tipe, String tanggal, double total, double uangBayar, double uangKembalian) {
        super(idTransaksi, tipe, tanggal, total);
        this.uangBayar = uangBayar;
        this.uangKembalian = uangKembalian;
    }

    public double getUangBayar() {
        return uangBayar;
    }

    public void setUangBayar(double uangBayar) {
        this.uangBayar = uangBayar;
    }

    public double getUangKembalian() {
        return uangKembalian;
    }

    public void setUangKembalian(double uangKembalian) {
        this.uangKembalian = uangKembalian;
    }
}
