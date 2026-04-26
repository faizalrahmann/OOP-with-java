package Bank;

public class App {
    public static void main(String[] args) {
        Bank bankUmum = new Bank("Dian", "123-456");
        BankBNI nasabahBNI = new BankBNI("Aldi", "987-654");
        BankBCA nasabahBCA = new BankBCA("Rina", "321-789");

        System.out.println("=== Contoh method overloading pada Bank ===");
        bankUmum.transferUang(250000, "555-111");
        bankUmum.transferUang(500000, "222-333", "BNI");
        bankUmum.transferUang(750000, "777-888", "BCA", "Bayar tagihan listrik");
        bankUmum.sukuBunga();

        System.out.println("=== Contoh method overriding pada BankBNI ===");
        nasabahBNI.transferUang(1000000, "123-999", "BNI");
        nasabahBNI.sukuBunga();

        System.out.println("=== Contoh method overriding pada BankBCA ===");
        nasabahBCA.transferUang(1200000, "456-000", "BCA");
        nasabahBCA.sukuBunga();

        System.out.println("=== Biaya transfer beda bank ===");
        bankUmum.transferUang(300000, "888-777", "Mandiri", "Transfer antar bank umum");
    }
}
