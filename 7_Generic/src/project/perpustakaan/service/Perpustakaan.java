package project.perpustakaan.service;

import project.perpustakaan.model.Anggota;
import project.perpustakaan.model.Buku;

public interface Perpustakaan {

    void tambahBuku(Buku buku);
    void tampilkanSemuaBuku();
    void cariBuku(String judul);
    void pinjamBuku(String idAnggota, String kodeBuku);
    void kembalikanBuku(String idAnggota, String kodeBuku);
    void tambahAnggota(Anggota anggota);
    void tampilkanSemuaAnggota();
}
