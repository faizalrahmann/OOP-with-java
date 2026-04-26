public class App {
    public static void main(String[] args) throws Exception {
        Toshiba toshiba = new Toshiba("Satellite Pro", 8, 512, true);
        MacBook macBook = new MacBook("MacBook Air", 16, 256, "Ventura");

        System.out.println("=== Toshiba ===");
        toshiba.showSpecs();
        toshiba.boot();
        toshiba.openApp("Microsoft Word");
        toshiba.printKeyboardInfo();

        System.out.println();
        System.out.println("=== MacBook ===");
        macBook.showSpecs();
        macBook.boot();
        macBook.useTouchBar();
    }
}
