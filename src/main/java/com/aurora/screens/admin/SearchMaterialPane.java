package com.aurora.screens.admin;

import com.aurora.database.models.MaterialDirection;
import com.aurora.database.repositories.SearchMaterialService;
import com.aurora.exceptions.MaterialEmptyPropertyException;
import com.aurora.screens.admin.create.MaterialDirectionPane;
import javafx.scene.control.RadioButton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class SearchMaterialPane extends JPanel {

    private  JTextField searchTextField;
    private MaterialDirectionPane materialDirectionPane;
    private  JButton searchButton;

    private ButtonGroup searchButtonGroup = new ButtonGroup();
    private JRadioButton materialIDButton, materialTitleButton;

    private JTable materialTable = new JTable();
    private JPanel queryPanel = new JPanel();

    private boolean isFirstSearch = true;

    GridBagConstraints gbc = new GridBagConstraints();
    public SearchMaterialPane() {
        setLayout(new GridBagLayout());
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
        gbc.gridy++;
        queryPanel.setLayout(new GridBagLayout());

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

               List<Map<String,Object>> results=  searchMaterialService.searchMaterial(selectedFilter, searctTarget, textInputs);



                createDynamicTable(results,gbc);
            }catch (MaterialEmptyPropertyException e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        });
    }
    public void createDynamicTable(List<Map<String, Object>> data, GridBagConstraints gbc) {
        // Obtener todas las claves únicas para las columnas
        Set<String> columnSet = new LinkedHashSet<>();
        for (Map<String, Object> row : data) {
            columnSet.addAll(row.keySet());
        }
        String[] columns = columnSet.toArray(new String[0]);
        DefaultTableModel model = new DefaultTableModel(columns,0);


        System.out.println(columnSet.toString());
        // Agregar las filas al modelo
        for (Map<String, Object> row : data) {
            Object[] rowData = new Object[columns.length];
            for (int i = 0; i < columns.length; i++) {
                rowData[i] = row.getOrDefault(columns[i], ""); // Valor por defecto si no existe la clave
            }
            model.addRow(rowData);
        }

        materialTable.setModel(model);
        JScrollPane scrollPane = new JScrollPane(materialTable);
        scrollPane.setPreferredSize(new Dimension(500, 150));

        queryPanel.removeAll();
        queryPanel.add(scrollPane,gbc);
        add(queryPanel, gbc);

        queryPanel.revalidate();
        queryPanel.repaint();

    }
}
