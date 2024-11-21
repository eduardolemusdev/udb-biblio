package com.aurora.screens.admin.create;

import com.aurora.database.repositories.InputStatement;
import com.aurora.database.repositories.MaterialRepository;
import com.aurora.screens.admin.TextInput;
import com.aurora.screens.admin.TextInputContainer;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AbstractMaterialForm extends JPanel {
    List<TextInput> textInputs = new ArrayList<>();
    private String materialLabel;
    private TextInputContainer textInputContainer;
    private JButton btnSave;
    private String foreignKeyMaterialType;
    private JTextArea descriptionTextArea;

    public AbstractMaterialForm(List<TextInput> textInputs, String materialLabel,  String foreignKeyMaterialType) {
        this.materialLabel = materialLabel;
        this.textInputs = textInputs;
        this.foreignKeyMaterialType = foreignKeyMaterialType;

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        Font font = new Font("Arial", Font.PLAIN, 12);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);

        textInputContainer = new TextInputContainer(textInputs, materialLabel,c);
        textInputContainer.initContainer();
        add(textInputContainer, c);

        c.gridy++;
        c.gridwidth = 4; // Ocupa todo el ancho
        descriptionTextArea = new JTextArea();
        descriptionTextArea.setFont(font);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(descriptionTextArea);
        scrollPane.setPreferredSize(new Dimension(0, 100)); // Altura fija de 200 píxeles
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, c);

        c.gridy++;
        JScrollPane scrollCategories = getjScrollPane();

        add(scrollCategories,c);

        c.gridy++;
        add(btnSave =new JButton("Listar categorias"), c);

        btnSave.addActionListener(e -> {
           saveMaterial();
        });
    }

    private static JScrollPane getjScrollPane() {
        MaterialCategoriesPane materialCategoriesPane = new MaterialCategoriesPane();
        JScrollPane scrollCategories = new JScrollPane(materialCategoriesPane);
        scrollCategories.setBorder(new CompoundBorder(new TitledBorder("Categorías"), new EmptyBorder(12, 0, 0, 0)));
        scrollCategories.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollCategories.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollCategories.setPreferredSize(new Dimension(400, 200)); // Ajusta el tamaño según lo que necesites
        return scrollCategories;
    }

    public void saveMaterial(){
        MaterialRepository materialRepository = new MaterialRepository();
        textInputs.add(new TextInput(descriptionTextArea,"description", "Descripción"));
        List<InputStatement> inputStatements = textInputs.stream()
                .filter(input -> !input.getTextField().getText().isEmpty())
                .map(input -> new InputStatement(input.getColumnName(),input.getTextField().getText()))
                .collect(Collectors.toList());
        materialRepository.saveDinamicMaterial(inputStatements, foreignKeyMaterialType);
    }

}
