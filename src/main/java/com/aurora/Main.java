package com.aurora;

import com.aurora.screens.SigninPane;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Colegio Amigos De Don Bosco");
        frame.add(new SigninPane(frame));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(650, 600);
        frame.setVisible(true);
    }
}