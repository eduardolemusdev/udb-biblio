package com.aurora.screens.admin;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TextInputContainer extends JPanel {

    private List<TextInput> textInputs;
    private String labelContainer;
    private GridBagConstraints gbc;

    public TextInputContainer(List<TextInput> textInputs, String labelContainer, GridBagConstraints gbc) {
        this.textInputs = textInputs;
        this.labelContainer = labelContainer;
        this.gbc = gbc;

    }

    public void initContainer(){
        setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new TitledBorder(labelContainer), new EmptyBorder(12, 0, 0, 0)));
        Font font = new Font("Arial", Font.PLAIN, 12);

        AtomicInteger colCounter = new AtomicInteger(0);

        for (TextInput textInput : textInputs) {
            JLabel label = new JLabel(textInput.getLabel());
            label.setFont(font);
            add(label, gbc);
            gbc.gridx++;
            add(textInput.getTextField(), gbc);
            gbc.gridx++;

            colCounter.set(colCounter.get() + 1);
            if (colCounter.get() == 3) {
                System.out.println(colCounter.get());
                colCounter.set(0);
                gbc.gridx = 0;
                gbc.gridy++;
            }

        }
    }

    public List<TextInput> getTextInputs() {
        return textInputs;
    }
}
