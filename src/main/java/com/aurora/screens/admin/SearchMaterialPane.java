package com.aurora.screens.admin;

import com.aurora.database.models.MaterialDirection;
import com.aurora.database.repositories.SearchMaterialService;
import com.aurora.exceptions.MaterialEmptyPropertyException;
import com.aurora.screens.admin.create.MaterialDirectionPane;
import javafx.scene.control.RadioButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SearchMaterialPane extends JPanel {

    private  JTextField searchTextField;
    private MaterialDirectionPane materialDirectionPane;
    private  JButton searchButton;

    private ButtonGroup searchButtonGroup = new ButtonGroup();
    private JRadioButton materialIDButton, materialTitleButton;

    public SearchMaterialPane() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.insets = new Insets(5, 5, 5, 5);

        //build principal filters
        JPanel idTitleFilterPanel = new JPanel();
        searchButtonGroup.add(materialIDButton = new JRadioButton("Material ID"));
        materialIDButton.setActionCommand("materialID");
        searchButtonGroup.add(materialTitleButton = new JRadioButton("Material Title"));
        materialTitleButton.setActionCommand("materialTitle");

        idTitleFilterPanel.setLayout(new GridBagLayout());
        idTitleFilterPanel.setBorder(BorderFactory.createTitledBorder("Filtros"));
        idTitleFilterPanel.add(materialIDButton);
        idTitleFilterPanel.add(materialTitleButton);
        materialIDButton.setSelected(true);

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

        //add filters id title
        add(idTitleFilterPanel, gbc);
        gbc.gridy++;


        materialDirectionPane = new MaterialDirectionPane(gbc);
        gbc.gridx = 0;

        add(materialDirectionPane, gbc);

        handleSearchButton();

    }

    private void handleSearchButton(){
        this.searchButton.addActionListener(actionEvent -> {

            String selectedFilter = searchButtonGroup.getSelection().getActionCommand();
            String searctTarget = searchTextField.getText();

            try {
                MaterialDirection m = materialDirectionPane.getMaterialDirection(false);
                List<TextInput> textInputs = materialDirectionPane.getFilledTextInputs();

                SearchMaterialService searchMaterialService = new SearchMaterialService();

                searchMaterialService.searchMaterial(selectedFilter, searctTarget, textInputs);

            }catch (MaterialEmptyPropertyException e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        });
    }
}
