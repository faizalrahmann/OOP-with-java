package com.warungqu.model;

public class DetailTransaksi {
    private int idDetail;
    private int idTransaksi;
    private int idProduk;
    private int jumlah;
    private double subtotal;

    public DetailTransaksi() {
    }

    public DetailTransaksi(int idDetail, int idTransaksi, int idProduk, int jumlah, double subtotal) {
        this.idDetail = idDetail;
        this.idTransaksi = idTransaksi;
        this.idProduk = idProduk;
        this.jumlah = jumlah;
        this.subtotal = subtotal;
    }

    public int getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(int idDetail) {
        this.idDetail = idDetail;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public int getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(int idProduk) {
        this.idProduk = idProduk;
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
