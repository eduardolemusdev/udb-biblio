package com.aurora.screens;

import com.aurora.screens.admin.AdminPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SigninPane extends JPanel {
    private JTextField emailTextField, passwordTextField;
    private JButton btnSignIn;

    private JPanel adminPane = new AdminPane();

    public SigninPane(JFrame mainFrame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        Font font = new Font("Verdana", Font.PLAIN, 16);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        JLabel emailLabel = new JLabel("Correo Electronico:");
        emailLabel.setFont(font);
        add(emailLabel, gbc);
        gbc.gridy++;
        emailTextField = new JTextField(10);
        emailTextField.setFont(font);
        emailTextField.setPreferredSize(new Dimension(150, 50));
        add(emailTextField, gbc);
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(font);
        add(passwordLabel, gbc);
        add(passwordLabel, gbc);
        gbc.gridy++;

        passwordTextField = new JPasswordField(10);
        passwordTextField.setFont(font);
        passwordTextField.setPreferredSize(new Dimension(150, 50));
        add(passwordTextField, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        btnSignIn = new JButton("Iniciar sesión");
        btnSignIn.setPreferredSize(new Dimension(100, 30));
        add(btnSignIn, gbc);

        btnSignIn.addActionListener((ActionEvent e) -> {
            String email = emailTextField.getText();
            String password = passwordTextField.getText();

            mainFrame.setVisible(false);
            mainFrame.dispose();

            JFrame appFrame = new JFrame();
            appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            appFrame.setTitle("Colegio Amigos De Don Bosco");
            appFrame.add(adminPane);
            appFrame.pack();
            appFrame.setLocationRelativeTo(null);
            appFrame.setSize(1080, 720);
            appFrame.setVisible(true);
        });
    }
}
