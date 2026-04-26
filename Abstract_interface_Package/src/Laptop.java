public abstract class Laptop {
    private String model;
    private int ram;
    private int storage;

    public Laptop(String model, int ram, int storage) {
        this.model = model;
        this.ram = ram;
        this.storage = storage;
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

    public abstract void boot();

    public void showSpecs() {
        System.out.println("Brand: " + getClass().getSimpleName());
        System.out.println("Model : " + model);
        System.out.println("RAM   : " + ram + " GB");
        System.out.println("Storage: " + storage + " GB");
    }
}
