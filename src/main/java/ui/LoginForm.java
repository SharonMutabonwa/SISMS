/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author Odeth
 */

import model.User;
import service.UserService;
import util.Session;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private UserService userService = new UserService();

    public LoginForm() {
        setTitle("SISMS - Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Smart Inventory & Sales System", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        panel.add(new JLabel("Username:"));
        txtUsername = new JTextField();
        panel.add(txtUsername);

        panel.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> handleLogin());
        panel.add(new JLabel());
        panel.add(btnLogin);

        add(panel, BorderLayout.CENTER);
    }

    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        User user = userService.login(username, password);
        if (user != null) {
            Session.currentUser = user.getUsername();
            Session.currentRole = user.getRole();

            JOptionPane.showMessageDialog(this, "Welcome, " + user.getUsername());

            dispose(); // Close login
            if ("admin".equalsIgnoreCase(user.getRole())) {
                new AdminHomePage().setVisible(true);
            } else {
                new ClerkHomePage().setVisible(true);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials!", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}

