package com.warungqu.model;

public class Transaksi {
    private int idTransaksi;
    private String tipe;
    private String tanggal;
    private double total;
    private double bayar;
    private double kembalian;
    private String keterangan;

    public Transaksi() {
    }

    public Transaksi(int idTransaksi, String tipe, String tanggal, double total) {
        this.idTransaksi = idTransaksi;
        this.tipe = tipe;
        this.tanggal = tanggal;
        this.total = total;
    }

    public Transaksi(int idTransaksi, String tipe, String tanggal, double total, double bayar, double kembalian, String keterangan) {
        this.idTransaksi = idTransaksi;
        this.tipe = tipe;
        this.tanggal = tanggal;
        this.total = total;
        this.bayar = bayar;
        this.kembalian = kembalian;
        this.keterangan = keterangan;
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

    public double getBayar() {
        return bayar;
    }

    public void setBayar(double bayar) {
        this.bayar = bayar;
    }

    public double getKembalian() {
        return kembalian;
    }

    public void setKembalian(double kembalian) {
        this.kembalian = kembalian;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
