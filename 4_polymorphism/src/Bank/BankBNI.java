package Bank;

public class BankBNI extends Bank {
    public BankBNI(String namaNasabah, String rekening) {
        super(namaNasabah, rekening);
    }

    @Override
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        if (bankTujuan != null && bankTujuan.equalsIgnoreCase("BNI")) {
            int biaya = hitungBiayaTransfer(bankTujuan);
            System.out.println("[BNI] Transfer Rp" + jumlah + " ke rekening " + rekeningTujuan + " sukses.");
            System.out.println("Bank tujuan: BNI");
            System.out.println("Biaya transfer: Rp" + biaya + "\n");
        } else {
            System.out.println("[BNI] Bank tujuan tidak valid untuk metode override BNI. Gunakan 'BNI'.\n");
        }
    }

    @Override
    public void sukuBunga() {
        System.out.println("Suku Bunga dari BNI adalah : 4%\n");
    }
}
