package com.warungqu.model;

public class Transaksi {
    private int idTransaksi;
    private String tipe;
    private String tanggal;
    private double total;

    public Transaksi() {
    }

    public Transaksi(int idTransaksi, String tipe, String tanggal, double total) {
        this.idTransaksi = idTransaksi;
        this.tipe = tipe;
        this.tanggal = tanggal;
        this.total = total;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
