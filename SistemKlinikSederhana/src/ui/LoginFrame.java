package ui;

import database.DatabaseConnection;
import model.User;
import service.ClinicService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    private final ClinicService clinicService = new ClinicService();

    public LoginFrame() {
        setTitle("Login Sistem Klinik");
        setSize(420, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblUser = new JLabel("Username");
        JTextField txtUser = new JTextField();
        JLabel lblPass = new JLabel("Password");
        JPasswordField txtPass = new JPasswordField();
        JButton btnLogin = new JButton("Login");

        panel.add(lblUser);
        panel.add(txtUser);
        panel.add(lblPass);
        panel.add(txtPass);
        panel.add(new JLabel());
        panel.add(btnLogin);

        btnLogin.addActionListener(e -> {
            try {
                User user = clinicService.login(txtUser.getText(), new String(txtPass.getPassword()));
                JOptionPane.showMessageDialog(this, "Selamat datang " + user.getFullName());
                new MainFrame(user).setVisible(true);
                dispose();
            } catch (IllegalArgumentException | SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Login Gagal", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(panel);
    }
}
