public class Toshiba {
    private boolean powerOn;
    private int volume = 50;

    public void hidupkanLaptop() {
        if (!powerOn) {
            powerOn = true;
            System.out.println("Toshiba menyala.");
        } else {
            System.out.println("Toshiba sudah dalam keadaan ON.");
        }
    }

    public void matikanLaptop() {
        if (powerOn) {
            powerOn = false;
            System.out.println("Toshiba dimatikan.");
        } else {
            System.out.println("Toshiba sudah dalam keadaan OFF.");
        }
    }

    public void tambahVolume() {
        if (powerOn) {
            volume = Math.min(volume + 10, 100);
            System.out.println("Volume Toshiba: " + volume);
        } else {
            System.out.println("Nyalakan Toshiba terlebih dahulu.");
        }
    }

    public void kurangiVolume() {
        if (powerOn) {
            volume = Math.max(volume - 10, 0);
            System.out.println("Volume Toshiba: " + volume);
        } else {
            System.out.println("Nyalakan Toshiba terlebih dahulu.");
        }
    }
}
