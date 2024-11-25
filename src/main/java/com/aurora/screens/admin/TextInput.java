package com.aurora.screens.admin;

import com.aurora.database.DatabaseColumn;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class TextInput implements DatabaseColumn, TextInputComponent {
    private JTextComponent textField;
    private String columnName;
    private String label;

    public TextInput(JTextComponent textField, String label) {
        this.textField = textField;
        this.label = label;
    }

    public TextInput(JTextComponent textField, String columnName, String label) {
        this.textField = textField;
        this.columnName = columnName;
        this.label = label;
    }

    public JTextComponent getTextField() {
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
