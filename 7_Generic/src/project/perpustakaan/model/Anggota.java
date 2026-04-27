package project.perpustakaan.model;

import java.util.ArrayList;

public class Anggota {

    private String id;
    private String nama;
    private String noTelp;
    private ArrayList<String> daftarPinjaman;

    public Anggota(String id, String nama, String noTelp) {
        this.id             = id;
        this.nama           = nama;
        this.noTelp         = noTelp;
        this.daftarPinjaman = new ArrayList<>();
    }

    // Getter & Setter
    public String getId()                        { return id; }
    public String getNama()                      { return nama; }
    public String getNoTelp()                    { return noTelp; }
    public ArrayList<String> getDaftarPinjaman() { return daftarPinjaman; }

    public void tambahPinjaman(String kodeBuku)  { daftarPinjaman.add(kodeBuku); }
    public void hapusPinjaman(String kodeBuku)   { daftarPinjaman.remove(kodeBuku); }

    @Override
    public String toString() {
        return "[" + id + "] " + nama + " | Telp: " + noTelp
                + " | Meminjam: " + daftarPinjaman.size() + " buku";
    }
}
