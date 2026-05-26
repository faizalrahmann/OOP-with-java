import java.io.File;

public class InfoFile {
    public static void main(String[] args) {
     File f = new File ("file.txt");

     if (f.exists()) {
            System.out.println("File name: " + f.getName());
            System.out.println("Absolute path: " + f.getAbsolutePath());
            System.out.println("Writeable: " + f.canWrite());
            System.out.println("Readable: " + f.canRead());
            System.out.println("File size: " + f.length());
        } else {
            System.out.println("File tidak ada.");
     }
    }
}