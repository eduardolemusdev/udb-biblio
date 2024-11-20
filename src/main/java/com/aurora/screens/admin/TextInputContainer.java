package com.aurora.screens.admin;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TextInputContainer extends JPanel {

    List<TextInput> textInputs;
    private String labelContainer;

    public TextInputContainer(List<TextInput> textInputs, String labelContainer) {
        this.textInputs = textInputs;
        this.labelContainer = labelContainer;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Font font = new Font("Arial", Font.PLAIN, 12);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        AtomicInteger colCounter = new AtomicInteger(0);

        for (TextInput textInput : textInputs) {
            JLabel label = new JLabel(textInput.getLabel());
            label.setFont(font);
            add(label, gbc);
            gbc.gridx++;
            add(textInput.getTextField(), gbc);
            gbc.gridx++;

            if (colCounter.get() == 3) {
                colCounter.set(0);
                gbc.gridx = 0;
                gbc.gridy++;
            }

            colCounter.set(colCounter.get() + 1);
        }
    }
}
