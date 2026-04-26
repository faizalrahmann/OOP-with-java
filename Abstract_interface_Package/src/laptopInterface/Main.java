package laptopInterface;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Laptop thinkpad = new Lenovo();
        LaptopUser andri = new LaptopUser(thinkpad);

        andri.turnOnLaptop();
        andri.makeLaptopLouder();
        andri.makeLaptopLouder();
        andri.makeLaptopSilence();
        andri.turnOffLaptop();
    }
}
