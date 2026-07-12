package com.warungqu.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private final JTextField usernameField;
    private final JPasswordField passwordField;

    public LoginForm() {
        setTitle("WarungQU - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 320);
        setLocationRelativeTo(null);
        setResizable(false);

        Color bg = new Color(245, 245, 245);
        Color primaryGreen = new Color(46, 125, 50);
        Color accentBlue = new Color(21, 101, 192);
        Color textDark = new Color(33, 33, 33);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(bg);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(bg);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("WarungQU");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(primaryGreen);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(titleLabel, gbc);

        JLabel subtitleLabel = new JLabel("Masuk ke sistem kasir");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitleLabel.setForeground(textDark);
        gbc.gridy = 1;
        formPanel.add(subtitleLabel, gbc);

        JLabel userLabel = new JLabel("Username");
        userLabel.setForeground(textDark);
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(userLabel, gbc);

        usernameField = new JTextField(18);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        JLabel passLabel = new JLabel("Password");
        passLabel.setForeground(textDark);
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(passLabel, gbc);

        passwordField = new JPasswordField(18);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(accentBlue);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(16, 8, 8, 8);
        formPanel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());

                if ("admin".equals(username) && "admin123".equals(password)) {
                    JOptionPane.showMessageDialog(LoginForm.this, "Login berhasil!");
                    new DashboardForm().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this, "Username atau password salah.", "Login Gagal", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mainPanel.add(formPanel, BorderLayout.CENTER);
        setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm().setVisible(true));
    }
}
