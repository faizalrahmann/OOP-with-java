import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Toshiba toshiba = new Toshiba("Satellite Pro", 8, 512, true);
        MacBook macBook = new MacBook("MacBook Air", 16, 256, "Ventura");

        System.out.println("=== Laptop Collection ===");
        System.out.println("1. Toshiba");
        toshiba.showSpecs();
        System.out.println();
        System.out.println("2. MacBook");
        macBook.showSpecs();
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        Laptop selectedLaptop = null;

        while (selectedLaptop == null) {
            System.out.print("Pilih laptop (1 = Toshiba, 2 = MacBook): ");
            if (!scanner.hasNextLine()) {
                System.out.println("Tidak ada input. Program dihentikan.");
                scanner.close();
                return;
            }
            String pilihan = scanner.nextLine().trim();
            if (pilihan.equals("1")) {
                selectedLaptop = toshiba;
            } else if (pilihan.equals("2")) {
                selectedLaptop = macBook;
            } else {
                System.out.println("Pilihan tidak valid. Silakan pilih 1 atau 2.");
            }
        }

        System.out.println("\nMasukkan perintah:");
        System.out.println("  ON   -> Menyalakan laptop");
        System.out.println("  OFF  -> Mematikan laptop");
        System.out.println("  UP   -> Menambah volume");
        System.out.println("  DOWN -> Mengurangi volume");
        System.out.println("  EXIT -> Keluar program");

        while (true) {
            System.out.print("Perintah: ");
            if (!scanner.hasNextLine()) {
                System.out.println("Tidak ada input lagi. Program dihentikan.");
                scanner.close();
                return;
            }
            String command = scanner.nextLine().trim().toUpperCase();
            switch (command) {
                case "ON":
                    selectedLaptop.turnOn();
                    break;
                case "OFF":
                    selectedLaptop.turnOff();
                    break;
                case "UP":
                    selectedLaptop.volumeUp();
                    break;
                case "DOWN":
                    selectedLaptop.volumeDown();
                    break;
                case "EXIT":
                    System.out.println("Keluar dari program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Perintah tidak dikenal. Gunakan ON, OFF, UP, DOWN, atau EXIT.");
            }
        }
    }
}
