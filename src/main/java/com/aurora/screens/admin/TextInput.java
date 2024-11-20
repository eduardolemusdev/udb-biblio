package com.aurora.screens.admin;

import javax.swing.*;

public class TextInput {
    private JTextField textField;
    private String columnName;
    private String label;

    public TextInput(JTextField textField, String columnName, String label) {
        this.textField = textField;
        this.columnName = columnName;
        this.label = label;
    }

    public JTextField getTextField() {
        return textField;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
