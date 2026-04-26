public abstract class Laptop {
    private String model;
    private int ram;
    private int storage;
    private boolean powerOn;
    private int volume;

    public Laptop(String model, int ram, int storage) {
        this.model = model;
        this.ram = ram;
        this.storage = storage;
        this.powerOn = false;
        this.volume = 50;
    }

    public String getModel() {
        return model;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public boolean isPowerOn() {
        return powerOn;
    }

    public int getVolume() {
        return volume;
    }

    public void turnOn() {
        if (powerOn) {
            System.out.println(getClass().getSimpleName() + " " + model + " is already ON.");
            return;
        }
        powerOn = true;
        boot();
        System.out.println(getClass().getSimpleName() + " " + model + " is now ON.");
    }

    public void turnOff() {
        if (!powerOn) {
            System.out.println(getClass().getSimpleName() + " " + model + " is already OFF.");
            return;
        }
        powerOn = false;
        System.out.println("Shutting down " + getClass().getSimpleName() + " " + model + ".");
    }

    public void volumeUp() {
        if (!powerOn) {
            System.out.println("Please turn on the laptop before changing the volume.");
            return;
        }
        if (volume >= 100) {
            System.out.println("Volume is already at maximum (" + volume + ").");
            return;
        }
        volume = Math.min(100, volume + 10);
        System.out.println("Volume increased to " + volume + ".");
    }

    public void volumeDown() {
        if (!powerOn) {
            System.out.println("Please turn on the laptop before changing the volume.");
            return;
        }
        if (volume <= 0) {
            System.out.println("Volume is already at minimum (" + volume + ").");
            return;
        }
        volume = Math.max(0, volume - 10);
        System.out.println("Volume decreased to " + volume + ".");
    }

    public abstract void boot();

    public void showSpecs() {
        System.out.println("Brand: " + getClass().getSimpleName());
        System.out.println("Model : " + model);
        System.out.println("RAM   : " + ram + " GB");
        System.out.println("Storage: " + storage + " GB");
    }
}
