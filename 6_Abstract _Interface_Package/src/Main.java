import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Toshiba toshiba = new Toshiba();
        MacBook macBook = new MacBook();

        System.out.println("=== PILIH LAPTOP ===");
        System.out.println("1. Toshiba");
        System.out.println("2. MacBook");
        System.out.print("Masukkan pilihan (1/2): ");
        String pilihan = scanner.nextLine().trim();

        Object laptop;
        if ("2".equals(pilihan) || "MacBook".equalsIgnoreCase(pilihan)) {
            laptop = macBook;
            System.out.println("MacBook siap digunakan.");
        } else {
            laptop = toshiba;
            System.out.println("Toshiba siap digunakan.");
        }

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("ON   - Nyalakan laptop");
            System.out.println("OFF  - Matikan laptop");
            System.out.println("UP   - Tambah volume");
            System.out.println("DOWN - Kurangi volume");
            System.out.println("EXIT - Keluar program");
            System.out.print("Input: ");

            String input = scanner.nextLine().trim().toUpperCase();
            if ("EXIT".equals(input)) {
                System.out.println("Program selesai.");
                break;
            }

            switch (input) {
                case "ON":
                    if (laptop instanceof Toshiba) {
                        ((Toshiba) laptop).hidupkanLaptop();
                    } else {
                        ((MacBook) laptop).hidupkanLaptop();
                    }
                    break;
                case "OFF":
                    if (laptop instanceof Toshiba) {
                        ((Toshiba) laptop).matikanLaptop();
                    } else {
                        ((MacBook) laptop).matikanLaptop();
                    }
                    break;
                case "UP":
                    if (laptop instanceof Toshiba) {
                        ((Toshiba) laptop).tambahVolume();
                    } else {
                        ((MacBook) laptop).tambahVolume();
                    }
                    break;
                case "DOWN":
                    if (laptop instanceof Toshiba) {
                        ((Toshiba) laptop).kurangiVolume();
                    } else {
                        ((MacBook) laptop).kurangiVolume();
                    }
                    break;
                default:
                    System.out.println("Perintah tidak valid. Gunakan ON, OFF, UP, DOWN, atau EXIT.");
            }
        }

        scanner.close();
    }
}
