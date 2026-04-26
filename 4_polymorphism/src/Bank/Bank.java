package Bank;

public class Bank {
    private String namaNasabah;
    private String rekening;

    public Bank(String namaNasabah, String rekening) {
        this.namaNasabah = namaNasabah;
        this.rekening = rekening;
    }

    public void transferUang(int jumlah, String rekeningTujuan) {
        System.out.println("Transfer Rp" + jumlah + " ke rekening " + rekeningTujuan + " sukses.");
        System.out.println("Bank tujuan: di dalam bank yang sama.");
        System.out.println("Biaya transfer: Rp0\n");
    }

    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        int biaya = hitungBiayaTransfer(bankTujuan);
        System.out.println("Transfer Rp" + jumlah + " ke rekening " + rekeningTujuan + " di bank " + bankTujuan + " sukses.");
        System.out.println("Bank tujuan: " + bankTujuan + ".");
        System.out.println("Biaya transfer: Rp" + biaya + "\n");
    }

    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan, String berita) {
        int biaya = hitungBiayaTransfer(bankTujuan);
        System.out.println("Transfer Rp" + jumlah + " ke rekening " + rekeningTujuan + " di bank " + bankTujuan + " sukses.");
        System.out.println("Berita: " + berita);
        System.out.println("Bank tujuan: " + bankTujuan + ".");
        System.out.println("Biaya transfer: Rp" + biaya + "\n");
    }

    public void sukuBunga() {
        System.out.println("Suku Bunga standar adalah 3%\n");
    }

    protected int hitungBiayaTransfer(String bankTujuan) {
        if (bankTujuan == null) {
            return 0;
        }
        switch (bankTujuan.toUpperCase()) {
            case "BNI":
                return 6500;
            case "BCA":
                return 7500;
            default:
                return 10000;
        }
    }

    public String getNamaNasabah() {
        return namaNasabah;
    }

    public String getRekening() {
        return rekening;
    }
}
