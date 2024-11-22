package com.aurora.screens.admin.create;

import com.aurora.database.models.MaterialDirection;
import com.aurora.exceptions.MaterialEmptyPropertyException;
import com.aurora.screens.admin.TextInput;
import com.aurora.screens.admin.TextInputComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MaterialDirectionPane extends JPanel {
    private  JTextField buildingTextField, buildingFloorTextField, buildingFloorSectorTextField,
            shelfCodeTextField, shelfFloorTextField;
    public MaterialDirectionPane(GridBagConstraints gbc) {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Material Direction"));
        add(new JLabel("Edificio: "), gbc);
        gbc.gridx++;
        add(buildingTextField = new JTextField(5), gbc);
        gbc.gridx++;
        add(new JLabel("#Planta del Edificio: "), gbc);
        gbc.gridx++;
        add(buildingFloorTextField = new JTextField(5), gbc);
        gbc.gridx++;
        add(new JLabel("(SECTOR): "), gbc);
        gbc.gridx++;
        add(buildingFloorSectorTextField = new JTextField(6), gbc);

        gbc.gridx=0;
        gbc.gridy++;

        add(new JLabel("Codigo de Estante: "), gbc);
        gbc.gridx++;
        add(shelfCodeTextField = new JTextField(5), gbc);
        gbc.gridx++;
        add(new JLabel("#Nivel de Estante: "), gbc);
        gbc.gridx++;
        add(shelfFloorTextField = new JTextField(5), gbc);
    }

    public MaterialDirection getMaterialDirection(boolean needsAllProperties) throws MaterialEmptyPropertyException {
        List<TextInputComponent> formDirection = new ArrayList<>();

        formDirection.add(new TextInput( buildingTextField, "Edificio"));
        formDirection.add(new TextInput( buildingFloorTextField, "#Planta del Edificio"));
        formDirection.add(new TextInput( buildingFloorSectorTextField, "Sector"));
        formDirection.add(new TextInput( shelfCodeTextField, "Codigo de Estante"));
        formDirection.add(new TextInput( shelfFloorTextField, "#Nivel de Estante"));


        if (needsAllProperties) {
            List<String> missingProperties = new ArrayList<>();

            boolean existEmptyValues = !formDirection.stream()
                    .filter(input ->{
                        boolean isEmpty = input.getTextField().getText().isEmpty();
                        if (isEmpty){
                            missingProperties.add(input.getLabel());
                        }
                        return isEmpty;
                    }).collect(Collectors.toList()).isEmpty();
            if (existEmptyValues) {
                String missingPropertiesString = missingProperties.stream().map(missingLabel -> "- "+missingLabel).collect(Collectors.joining("\n"));
                throw new MaterialEmptyPropertyException("No se permiten campos vacíos en la ubicación del material.\n"+missingPropertiesString);
            }
        }

        return new MaterialDirection(buildingTextField.getText(),
                buildingFloorTextField.getText(),
                buildingFloorSectorTextField.getText(),
                shelfCodeTextField.getText(),
                shelfFloorTextField.getText());
    }
}
