package com.aurora.screens.admin;

import com.aurora.database.models.MaterialDirection;
import com.aurora.exceptions.MaterialEmptyPropertyException;
import com.aurora.screens.admin.create.MaterialDirectionPane;

import javax.swing.*;
import java.awt.*;

public class SearchMaterialPane extends JPanel {

    private  JTextField searchTextField;
    private MaterialDirectionPane materialDirectionPane;
    private  JButton searchButton;

    public SearchMaterialPane() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.insets = new Insets(5, 5, 5, 5);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridBagLayout());


        searchPanel.add(new JLabel("Buscar Material: "), gbc);
        gbc.gridx++;
        searchPanel.add(searchTextField = new JTextField(10), gbc);
        gbc.gridx++;
        searchPanel.add(searchButton = new JButton("Buscar"), gbc);
        gbc.gridx=0;
        add(searchPanel, gbc);

        gbc.gridy++;

        materialDirectionPane = new MaterialDirectionPane(gbc);
        gbc.gridx = 0;

        add(materialDirectionPane, gbc);

        handleSearchButton();

    }

    private void handleSearchButton(){
        this.searchButton.addActionListener(actionEvent -> {

            try {
                MaterialDirection m = materialDirectionPane.getMaterialDirection(false);
                System.out.println(m);
            }catch (MaterialEmptyPropertyException e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        });
    }
}
