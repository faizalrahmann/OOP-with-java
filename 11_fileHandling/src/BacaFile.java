import java.util.Scanner;

public class BacaFile {
    public static void main(String[] args) {
        try {
            File f = new File ("file.txt");
            Scanner r = new Scanner(f);
            while (r.hasNextLine()) {
                String data = r.nextLine();
                System.out.println(data);
            }
            r.close();
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan; File tidak ditemukan.");
            } 
    }  
}
