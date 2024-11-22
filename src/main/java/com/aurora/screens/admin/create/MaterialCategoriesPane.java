package com.aurora.screens.admin.create;

import com.aurora.database.models.Category;
import com.aurora.database.repositories.CategoryRepository;
import com.aurora.exceptions.NoCategoriesSelected;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MaterialCategoriesPane extends JPanel {
    private List<JCheckBox> checkBoxes = new ArrayList<>();
    private Map<String, Integer> checkBoxMap = new HashMap<>();
    public MaterialCategoriesPane() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(5, 5, 5, 5);

        addCategoriesCheckbox(c);
    }


    private void addCategoriesCheckbox(GridBagConstraints gbc){
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        CategoryRepository materialRepository = new CategoryRepository();
        List<Category> categoriesList = materialRepository.getCategories();
        AtomicInteger rowCount = new AtomicInteger();
        int initialRowCount = gbc.gridy;
        categoriesList.forEach(category ->{
            JCheckBox checkBox = new JCheckBox(category.getName());
            checkBoxMap.put(category.getName(), category.getId());
            checkBoxes.add(checkBox);
            add(checkBox, gbc);
            gbc.gridx++;
            rowCount.set(rowCount.get() + 1);
            if(rowCount.get() == 3){
                gbc.gridx = initialRowCount;
                gbc.gridy++;
                rowCount.set(0);
            }
        });
    }

    public List<Integer> getSelectedCategoriesId(boolean needAtLeastOneCategory) throws NoCategoriesSelected {

        if(needAtLeastOneCategory){
            boolean isAtLeastOneCategorySelected = false;
            isAtLeastOneCategorySelected = checkBoxes.stream().anyMatch(checkBox -> checkBox.isSelected());
            if(!isAtLeastOneCategorySelected){
              throw new NoCategoriesSelected("Necesitas seleccionar almenos una categoría");
            }
        }

        return checkBoxes.stream().filter(AbstractButton::isSelected).map( cb -> checkBoxMap.get(cb.getText())).collect(Collectors.toList());
    }

    public void saveMaterialCategories(int newMaterialId){

    }


}
