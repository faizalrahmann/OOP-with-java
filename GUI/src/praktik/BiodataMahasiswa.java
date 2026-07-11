package praktik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BiodataMahasiswa extends JFrame {
    private JTextField txtNIM;
    private JTextField txtNama;
    private JTextField txtProgramStudi;
    private JTextArea txtOutput;
    private JButton btnTampilkan;
    private JButton btnReset;

    public BiodataMahasiswa() {
        // Set up frame properties
        setTitle("Aplikasi Biodata Mahasiswa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Create input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Data"));

        // NIM field
        JLabel lblNIM = new JLabel("NIM");
        txtNIM = new JTextField();
        inputPanel.add(lblNIM);
        inputPanel.add(txtNIM);

        // Nama field
        JLabel lblNama = new JLabel("Nama");
        txtNama = new JTextField();
        inputPanel.add(lblNama);
        inputPanel.add(txtNama);

        // Program Studi field
        JLabel lblProgramStudi = new JLabel("Program Studi");
        txtProgramStudi = new JTextField();
        inputPanel.add(lblProgramStudi);
        inputPanel.add(txtProgramStudi);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnTampilkan = new JButton("Tampilkan");
        btnReset = new JButton("Reset");

        btnTampilkan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilkanData();
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetData();
            }
        });

        buttonPanel.add(btnTampilkan);
        buttonPanel.add(btnReset);

        // Create output panel
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Output"));

        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        txtOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        outputPanel.add(scrollPane, BorderLayout.CENTER);

        // Add panels to main panel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(outputPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);

        setVisible(true);
    }

    private void tampilkanData() {
        String nim = txtNIM.getText().trim();
        String nama = txtNama.getText().trim();
        String programStudi = txtProgramStudi.getText().trim();

        if (nim.isEmpty() || nama.isEmpty() || programStudi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder output = new StringBuilder();
        output.append("========== BIODATA MAHASISWA ==========\n\n");
        output.append("NIM             : ").append(nim).append("\n");
        output.append("Nama            : ").append(nama).append("\n");
        output.append("Program Studi   : ").append(programStudi).append("\n");

        txtOutput.setText(output.toString());
    }

    private void resetData() {
        txtNIM.setText("");
        txtNama.setText("");
        txtProgramStudi.setText("");
        txtOutput.setText("");
        txtNIM.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BiodataMahasiswa());
    }
}
