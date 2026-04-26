public class MacBook {
    private boolean powerOn;
    private int volume = 50;

    public void hidupkanLaptop() {
        if (!powerOn) {
            powerOn = true;
            System.out.println("MacBook menyala.");
        } else {
            System.out.println("MacBook sudah dalam keadaan ON.");
        }
    }

    public void matikanLaptop() {
        if (powerOn) {
            powerOn = false;
            System.out.println("MacBook dimatikan.");
        } else {
            System.out.println("MacBook sudah dalam keadaan OFF.");
        }
    }

    public void tambahVolume() {
        if (powerOn) {
            volume = Math.min(volume + 10, 100);
            System.out.println("Volume MacBook: " + volume);
        } else {
            System.out.println("Nyalakan MacBook terlebih dahulu.");
        }
    }

    public void kurangiVolume() {
        if (powerOn) {
            volume = Math.max(volume - 10, 0);
            System.out.println("Volume MacBook: " + volume);
        } else {
            System.out.println("Nyalakan MacBook terlebih dahulu.");
        }
    }
}
