public class MacBook extends Laptop {
    private String osVersion;

    public MacBook(String model, int ram, int storage, String osVersion) {
        super(model, ram, storage);
        this.osVersion = osVersion;
    }

    @Override
    public void boot() {
        System.out.println("MacBook " + getModel() + " boots into macOS " + osVersion + "...");
    }

    public void useTouchBar() {
        System.out.println("Using the Touch Bar on MacBook " + getModel() + ".");
    }

    public String getOsVersion() {
        return osVersion;
    }
}
