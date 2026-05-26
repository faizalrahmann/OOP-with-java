import java.io.FileWriter;

public class TulisFile {
    public static void main(String[] args) {
        try {
            FileWriter f = new FileWriter("file.txt");
            f.write("Belajar pemrograman file handling");
            f.close();
            System.out.println("Proses menulis ke file selesai.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }
}
