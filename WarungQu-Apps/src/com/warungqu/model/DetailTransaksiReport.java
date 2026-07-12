package com.warungqu.model;

public class DetailTransaksiReport {
    private String namaProduk;
    private int jumlah;
    private double subtotal;

    public DetailTransaksiReport() {
    }

    public DetailTransaksiReport(String namaProduk, int jumlah, double subtotal) {
        this.namaProduk = namaProduk;
        this.jumlah = jumlah;
        this.subtotal = subtotal;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
