public class Toshiba extends Laptop {
    private boolean hasNumericPad;

    public Toshiba(String model, int ram, int storage, boolean hasNumericPad) {
        super(model, ram, storage);
        this.hasNumericPad = hasNumericPad;
    }

    @Override
    public void boot() {
        System.out.println("Toshiba " + getModel() + " booting...");
    }

    public void openApp(String appName) {
        System.out.println("Opening " + appName + " on Toshiba " + getModel() + ".");
    }

    public void printKeyboardInfo() {
        System.out.println("Numeric pad: " + (hasNumericPad ? "Yes" : "No"));
    }
}
