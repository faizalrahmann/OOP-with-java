import javax.swing.JOptionPane;

public class Case2 {
    public static void main(String[] args) {

        // Pop-up pertama: Input
        String belajar = JOptionPane.showInputDialog(
                null,
                "Anda sedang belajar apa?",
                "Input",
                JOptionPane.QUESTION_MESSAGE
        );

        // Pop-up kedua: Pesan
        JOptionPane.showMessageDialog(
                null,
                "Belajar " + belajar + " sangat mudah",
                "Message",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
