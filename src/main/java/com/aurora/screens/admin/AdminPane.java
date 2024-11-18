package com.aurora.screens.admin;

import javax.swing.*;
import java.awt.*;

public class AdminPane extends JPanel {
    private JTabbedPane tabbedPane;
    public AdminPane() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx  = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;

        tabbedPane = new JTabbedPane();

        tabbedPane.add("Materiales Registro", new MaterialPane());
        tabbedPane.add("Auth config", new AuthRegistryPane());

        add(tabbedPane,gbc);


    }
}
