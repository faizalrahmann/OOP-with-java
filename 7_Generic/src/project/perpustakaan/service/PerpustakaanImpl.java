package project.perpustakaan.service;

import java.util.ArrayDeque;

import project.perpustakaan.generic.Koleksi;
import project.perpustakaan.model.Anggota;
import project.perpustakaan.model.Buku;

public class PerpustakaanImpl implements Perpustakaan {

    private Koleksi<Buku>    daftarBuku;
    private Koleksi<Anggota> daftarAnggota;
    private ArrayDeque<String> antrianPengembalian;

    public PerpustakaanImpl() {
        this.daftarBuku           = new Koleksi<>();
        this.daftarAnggota        = new Koleksi<>();
        this.antrianPengembalian  = new ArrayDeque<>();
    }

    @Override
    public void tambahBuku(Buku buku) {
        daftarBuku.tambah(buku);
        System.out.println("✔ Buku berhasil ditambahkan: " + buku.getJudul());
    }

    @Override
    public void tampilkanSemuaBuku() {
        if (daftarBuku.kosong()) {
            System.out.println("Belum ada buku di perpustakaan.");
            return;
        }
        System.out.println("\n========== DAFTAR BUKU ==========");
        for (Buku b : daftarBuku.getAll()) {
            System.out.println(b);
            System.out.println("   Info : " + b.getInfo());
        }
        System.out.println("==================================");
    }

    @Override
    public void cariBuku(String judul) {
        System.out.println("\n=== HASIL PENCARIAN: \"" + judul + "\" ===");
        boolean ditemukan = false;
        for (Buku b : daftarBuku.getAll()) {
            if (b.getJudul().toLowerCase().contains(judul.toLowerCase())) {
                System.out.println(b);
                System.out.println("   Info : " + b.getInfo());
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Buku tidak ditemukan.");
    }

    @Override
    public void pinjamBuku(String idAnggota, String kodeBuku) {
        Anggota anggota = cariAnggota(idAnggota);
        Buku    buku    = cariBukuByKode(kodeBuku);

        if (anggota == null) {
            System.out.println("✘ Anggota dengan ID " + idAnggota + " tidak ditemukan.");
            return;
        }
        if (buku == null) {
            System.out.println("✘ Buku dengan kode " + kodeBuku + " tidak ditemukan.");
            return;
        }
        if (!buku.isTersedia()) {
            System.out.println("✘ Buku \"" + buku.getJudul() + "\" sedang dipinjam.");
            return;
        }

        buku.setTersedia(false);
        anggota.tambahPinjaman(kodeBuku);
        System.out.println("✔ " + anggota.getNama() + " berhasil meminjam \"" + buku.getJudul() + "\"");
    }

    @Override
    public void kembalikanBuku(String idAnggota, String kodeBuku) {
        Anggota anggota = cariAnggota(idAnggota);
        Buku    buku    = cariBukuByKode(kodeBuku);

        if (anggota == null) {
            System.out.println("✘ Anggota dengan ID " + idAnggota + " tidak ditemukan.");
            return;
        }
        if (buku == null) {
            System.out.println("✘ Buku dengan kode " + kodeBuku + " tidak ditemukan.");
            return;
        }
        if (!anggota.getDaftarPinjaman().contains(kodeBuku)) {
            System.out.println("✘ Anggota ini tidak meminjam buku tersebut.");
            return;
        }

        buku.setTersedia(true);
        anggota.hapusPinjaman(kodeBuku);
        antrianPengembalian.offer(buku.getJudul());
        System.out.println("✔ \"" + buku.getJudul() + "\" berhasil dikembalikan.");
        System.out.println("   Antrian pengembalian hari ini: " + antrianPengembalian);
    }

    @Override
    public void tambahAnggota(Anggota anggota) {
        daftarAnggota.tambah(anggota);
        System.out.println("✔ Anggota berhasil didaftarkan: " + anggota.getNama());
    }

    @Override
    public void tampilkanSemuaAnggota() {
        if (daftarAnggota.kosong()) {
            System.out.println("Belum ada anggota terdaftar.");
            return;
        }
        System.out.println("\n========== DAFTAR ANGGOTA ==========");
        for (Anggota a : daftarAnggota.getAll()) {
            System.out.println(a);
            if (!a.getDaftarPinjaman().isEmpty()) {
                System.out.println("   Buku dipinjam: " + a.getDaftarPinjaman());
            }
        }
        System.out.println("=====================================");
    }

    // Helper methods
    private Anggota cariAnggota(String id) {
        for (Anggota a : daftarAnggota.getAll()) {
            if (a.getId().equalsIgnoreCase(id)) return a;
        }
        return null;
    }

    private Buku cariBukuByKode(String kode) {
        for (Buku b : daftarBuku.getAll()) {
            if (b.getKode().equalsIgnoreCase(kode)) return b;
        }
        return null;
    }
}
