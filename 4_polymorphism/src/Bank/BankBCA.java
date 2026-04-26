package Bank;

public class BankBCA extends Bank {
    public BankBCA(String namaNasabah, String rekening) {
        super(namaNasabah, rekening);
    }

    @Override
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        if (bankTujuan != null && bankTujuan.equalsIgnoreCase("BCA")) {
            int biaya = hitungBiayaTransfer(bankTujuan);
            System.out.println("[BCA] Transfer Rp" + jumlah + " ke rekening " + rekeningTujuan + " sukses.");
            System.out.println("Bank tujuan: BCA");
            System.out.println("Biaya transfer: Rp" + biaya + "\n");
        } else {
            System.out.println("[BCA] Bank tujuan tidak valid untuk metode override BCA. Gunakan 'BCA'.\n");
        }
    }

    @Override
    public void sukuBunga() {
        System.out.println("Suku Bunga dari BCA adalah : 4.5%\n");
    }
}
