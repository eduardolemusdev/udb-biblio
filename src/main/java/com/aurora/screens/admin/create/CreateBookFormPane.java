package com.aurora.screens.admin.create;

import javax.swing.*;
import java.awt.*;

public class CreateBookFormPane extends JPanel {
    JTextField titleTextField, authorTextField, detailsTextField,
            yearPublicationTextField, publisherTextField, isbnTextField,
            numberOfPagesTextField, coverURlTextField;
    JTextArea descriptionTextArea;
    JButton btnSave;

    public CreateBookFormPane() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        Font font = new Font("Arial", Font.PLAIN, 12);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);

        add(new JLabel("Título:"), c);
        c.gridx++;
        add(titleTextField = new JTextField(15), c);
        titleTextField.setFont(font);
        c.gridx++;
        add(new JLabel("Autor:"), c);
        c.gridx++;
        add(authorTextField = new JTextField(10), c);
        authorTextField.setFont(font);
        c.gridx =0;
        c.gridy++;

        add(new JLabel("Editorial:"), c);
        c.gridx++;
        add(publisherTextField  = new JTextField(10), c);
        publisherTextField.setFont(font);
        c.gridx++;

        add(new JLabel("ISBN:"), c);
        c.gridx++;
        add(isbnTextField= new JTextField(8), c);
        isbnTextField.setFont(font);
        c.gridx=0;
        c.gridy++;

        add(new JLabel("Número de páginas:"), c);
        c.gridx++;
        add(numberOfPagesTextField= new JTextField(7), c);
        numberOfPagesTextField.setFont(font);
        c.gridx++;
        add(new JLabel("URL de Portada:"), c);
        c.gridx++;
        add(coverURlTextField= new JTextField(7), c);
        coverURlTextField.setFont(font);
        c.gridx =0;
        c.gridy++;


        add(new JLabel("Detalles:"), c);
        c.gridx++;
        add(detailsTextField = new JTextField(10), c);
        detailsTextField.setFont(font);
        c.gridx++;
        add(new JLabel("Año de publicación:"), c);
        c.gridx++;
        add(yearPublicationTextField = new JTextField(15), c);
        yearPublicationTextField.setFont(font);

        c.gridx = 0;
        c.gridy++;
        add(new JLabel("Descripcion:"), c);
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
        MaterialCategoriesPane materialCategoriesPane = new MaterialCategoriesPane();
        add(materialCategoriesPane,c);

        c.gridy++;
        add(btnSave =new JButton("Listar categorias"), c);

        btnSave.addActionListener(e -> {
            System.out.println("Listar categorias");
            materialCategoriesPane.getSelectedCategoriesId().forEach(System.out::println);
        });
    }

}
