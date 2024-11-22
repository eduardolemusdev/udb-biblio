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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MaterialDirectionPane extends JPanel {

    private  JTextField buildingTextField, buildingFloorTextField, buildingFloorSectorTextField,
            shelfCodeTextField, shelfFloorTextField;
    private List<TextInput> textInputs = new ArrayList<>();
    public MaterialDirectionPane(GridBagConstraints gbc) {
        textInputs.add(new TextInput(new JTextField(5),"building","Edificio: "));
        textInputs.add(new TextInput(new JTextField(5),"building_floor","#Planta del Edificio: "));
        textInputs.add(new TextInput(new JTextField(5), "building_floor_sector","Sector: "));
        textInputs.add(new TextInput(new JTextField(5), "shelf_code","Codigo de Estante: "));
        textInputs.add(new TextInput(new JTextField(5), "shelf_level","#Nivel de estante: "));

        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Material Direction"));

        AtomicInteger columnCount = new AtomicInteger(0);
        textInputs.forEach(textInput -> {
           add(new JLabel(textInput.getLabel()), gbc);
           gbc.gridx++;
           add(textInput.getTextField(),gbc);
           gbc.gridx++;
           columnCount.set(columnCount.get()+1);
           if (columnCount.get() == 3) {
               gbc.gridx=0;
               gbc.gridy++;
           }
        });
    }

    public MaterialDirection getMaterialDirection(boolean needsAllProperties) throws MaterialEmptyPropertyException {


        if (needsAllProperties) {
            List<String> missingProperties = new ArrayList<>();

            boolean existEmptyValues = !textInputs.stream()
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

        String building = textInputs.get(0).getTextField().getText();
        String buildingFloor = textInputs.get(1).getTextField().getText();
        String buildingFloorSector = textInputs.get(2).getTextField().getText();
        String shelfCode = textInputs.get(3).getTextField().getText();
        String shelfFloor = textInputs.get(4).getTextField().getText();
        return new MaterialDirection(building,
                buildingFloor,
                buildingFloorSector,
                shelfCode,
                shelfFloor);
    }

    public List<TextInput> getFilledTextInputs() {
        return textInputs.stream().filter(textInput -> !textInput.getTextField().getText().isEmpty()).collect(Collectors.toList());
    }
}