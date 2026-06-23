import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;

public class MyForm implements java.awt.event.ActionListener {

    JTextField txtNama, txtNIM;
    JButton btnSubmit, btnCancel;

    public MyForm() {
        JFrame frame = new JFrame("Data Mahasiswa");
        frame.setSize(250, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel l1 = new JLabel("Nama");
        JLabel l2 = new JLabel("NIM");

        txtNama = new JTextField(10);
        txtNIM = new JTextField(10);

        btnSubmit = new JButton("Submit");
        btnCancel = new JButton("Cancel");

        JPanel panel = new JPanel();        panel.setBackground(Color.white);
        // 
        panel.add(l1);
        panel.add(txtNama);
        panel.add(l2);
        panel.add(txtNIM);
        panel.add(btnSubmit);
        panel.add(btnCancel);
        btnSubmit.addActionListener(this::actionPerformed);
        btnCancel.addActionListener(this::actionPerformed);
       

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        MyForm form = new MyForm();
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            String nama = txtNama.getText();
            String nim = txtNIM.getText();
            
            String data = "Nama: " + nama;
            data += "\nNIM: " + nim;

            JOptionPane.showMessageDialog(null, data, "Data Mahasiswa", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == btnCancel) {
            txtNama.setText("");
            txtNIM.setText("");
        }
    }
}
