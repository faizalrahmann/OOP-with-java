package com.warungqu.model;

public class Pengeluaran extends Transaksi {
    private String keteranganOperasional;

    public Pengeluaran() {
    }

    public Pengeluaran(int idTransaksi, String tanggal, double total, String keteranganOperasional) {
        super(idTransaksi, tanggal, total);
        this.keteranganOperasional = keteranganOperasional;
    }

    public String getKeteranganOperasional() {
        return keteranganOperasional;
    }

    public void setKeteranganOperasional(String keteranganOperasional) {
        this.keteranganOperasional = keteranganOperasional;
    }
}
