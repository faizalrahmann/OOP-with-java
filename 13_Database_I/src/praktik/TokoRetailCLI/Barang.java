package praktik.TokoRetailCLI;

public class Barang {
    private String kodeBarang;
    private String namaBarang;
    private int hargaBarang;
    private int stokBarang;

    public Barang(String kodeBarang, String namaBarang, int hargaBarang, int stokBarang) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.stokBarang = stokBarang;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(int hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public int getStokBarang() {
        return stokBarang;
    }

    public void setStokBarang(int stokBarang) {
        this.stokBarang = stokBarang;
    }

    @Override
    public String toString() {
        return String.format("%-12s %-25s %-10d %-6d", kodeBarang, namaBarang, hargaBarang, stokBarang);
    }
}