package com.warungqu.model;

public class Transaksi {
    private int idTransaksi;
    private String tanggal;
    private double total;

    public Transaksi() {
    }

    public Transaksi(int idTransaksi, String tanggal, double total) {
        this.idTransaksi = idTransaksi;
        this.tanggal = tanggal;
        this.total = total;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
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
