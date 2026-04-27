package project.perpustakaan.model;

public class BukuFiksi extends Buku {

    private String genre;

    public BukuFiksi(String kode, String judul, String pengarang, int tahunTerbit, String genre) {
        super(kode, judul, pengarang, tahunTerbit);
        this.genre = genre;
    }

    public String getGenre() { return genre; }

    @Override
    public String getInfo() {
        return "Fiksi | Genre: " + genre;
    }
}
