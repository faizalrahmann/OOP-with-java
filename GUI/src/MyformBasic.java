import javax.swing.*;
import java.awt.*;

public class MyformBasic extends JFrame {

        MyformBasic() {
            setTitle("Belajar GUI");
            setSize(800, 500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            getContentPane().setBackground(Color.WHITE);
            setLocationRelativeTo(null);
            setVisible(true);
        }

        public static void main(String[] args) {
            MyformBasic form = new MyformBasic ();
        }
}
