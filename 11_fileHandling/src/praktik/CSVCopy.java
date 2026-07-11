package praktik;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVCopy {
    public static void main(String[] args) {
        String sourceFile = "src/praktik/Students.csv";
        String targetFile = "src/praktik/StudentsCopy.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }

            System.out.println("File berhasil disalin dari " + sourceFile + " ke " + targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
