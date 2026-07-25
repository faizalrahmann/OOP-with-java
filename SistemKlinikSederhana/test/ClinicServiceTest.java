import service.ClinicService;

import java.nio.file.Files;
import java.nio.file.Path;

public class ClinicServiceTest {
    public static void main(String[] args) throws Exception {
        ClinicService service = new ClinicService();
        String report = service.buildReport();
        Path file = service.exportReportToFile("out/laporan_test.txt");
        if (!Files.exists(file)) {
            throw new IllegalStateException("File laporan tidak dibuat");
        }
        System.out.println(report);
        System.out.println("report_file_created=" + file.toAbsolutePath());
    }
}
