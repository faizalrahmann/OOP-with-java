package project.perpustakaan.model;

public class BukuNonFiksi extends Buku {

    private String kategori;

    public BukuNonFiksi(String kode, String judul, String pengarang, int tahunTerbit, String kategori) {
        super(kode, judul, pengarang, tahunTerbit);
        this.kategori = kategori;
    }

    public String getKategori() { return kategori; }

    @Override
    public String getInfo() {
        return "Non-Fiksi | Kategori: " + kategori;
    }
}
