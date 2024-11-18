package com.aurora.screens.admin;

import com.aurora.database.DatabaseConnection;
import com.aurora.database.repositories.MaterialRepository;
import com.aurora.screens.admin.create.CreateBookFormPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.sql.*;
import java.util.List;

public class MaterialPane extends JPanel {
    private MaterialRepository materialRepository = new MaterialRepository();
    private JComboBox<String> materialTypes = new JComboBox<String>();
    private JPanel formContainer = new JPanel(new GridBagLayout());

    public MaterialPane(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel materialPanel = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0; // No ocupa espacio vertical adicional
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        materialPanel.add(new JLabel("Material "), gbc);
        materialPanel.add(materialTypes, gbc);
        add(materialPanel, gbc);

        gbc.gridy = 1; // Siguiente fila
        gbc.weightx = 1;
        gbc.weighty = 1; // Ocupa el espacio restante
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(formContainer, BorderLayout.NORTH);
        add(wrapperPanel, gbc);
        // Inicializar contenido inicial
        formContainer.add(new CreateBookFormPane());

        initMaterialTypes();
        materialTypes.addItemListener((ItemEvent e) -> {
            if(e.getStateChange() == ItemEvent.SELECTED){
                String materialType = (String) materialTypes.getSelectedItem();
                System.out.println(materialType);
                formContainer.removeAll();
                switch(materialType){
                    case "libro":
                        formContainer.add(new CreateBookFormPane());
                    break;
                }
                formContainer.revalidate();
                formContainer.repaint();
            }

        });
    }

    private void initMaterialTypes(){
        List<String> types = this.materialRepository.materialTypes();
        types.forEach(t -> materialTypes.addItem(t));
    }
}
