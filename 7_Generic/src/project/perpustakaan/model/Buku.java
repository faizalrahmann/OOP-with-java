package project.perpustakaan.model;

public abstract class Buku {

    private String kode;
    private String judul;
    private String pengarang;
    private int tahunTerbit;
    private boolean tersedia;

    public Buku(String kode, String judul, String pengarang, int tahunTerbit) {
        this.kode        = kode;
        this.judul       = judul;
        this.pengarang   = pengarang;
        this.tahunTerbit = tahunTerbit;
        this.tersedia    = true;
    }

    // Getter & Setter
    public String getKode()                  { return kode; }
    public String getJudul()                 { return judul; }
    public String getPengarang()             { return pengarang; }
    public int getTahunTerbit()              { return tahunTerbit; }
    public boolean isTersedia()              { return tersedia; }
    public void setTersedia(boolean tersedia){ this.tersedia = tersedia; }

    // Abstract method - wajib diimplementasikan child class
    public abstract String getInfo();

    @Override
    public String toString() {
        return "[" + kode + "] " + judul + " - " + pengarang
                + " (" + tahunTerbit + ")"
                + " | " + (tersedia ? "Tersedia" : "Dipinjam");
    }
}
