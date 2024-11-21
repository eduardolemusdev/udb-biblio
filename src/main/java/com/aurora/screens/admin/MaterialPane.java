package com.aurora.screens.admin;

import com.aurora.database.repositories.MaterialRepository;
import com.aurora.screens.admin.create.AbstractMaterialForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
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

        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(formContainer, BorderLayout.NORTH);
        add(wrapperPanel, gbc);
        // Inicializar contenido inicial
        List<TextInput> bookInputs = buildBookInputs();
        formContainer.add(new AbstractMaterialForm(bookInputs,"Libro", "1"));

        initMaterialTypes();
        materialTypes.addItemListener((ItemEvent e) -> {
            if(e.getStateChange() == ItemEvent.SELECTED){
                String materialType = (String) materialTypes.getSelectedItem();
                System.out.println(materialType);
                formContainer.removeAll();
                switch(materialType){
                    case "libro":
                        List<TextInput> textInputs = buildBookInputs();
                        formContainer.add(new AbstractMaterialForm(textInputs, "Libro", "1"));
                    break;
                    case "cd":
                        List<TextInput> cdInputs = buildCDInputs();
                        formContainer.add(new AbstractMaterialForm(cdInputs, "CD", "2"));
                        break;
                    case "dvd":
                        List<TextInput> dvdInputs = buildDVDInputs();
                        formContainer.add(new AbstractMaterialForm(dvdInputs, "DVD", "3"));
                        break;
                    case "vhs":
                        List<TextInput> vhsInputs = buildVHSInputs();
                        formContainer.add(new AbstractMaterialForm(vhsInputs, "VHS","4"));
                        break;
                    case "tesis":
                        List<TextInput> thesisInputs = buildThesisInputs();
                        formContainer.add(new AbstractMaterialForm(thesisInputs, "Tesis", "5"));
                        break;
                    case "articulo":
                        List<TextInput> articleInputs = buildNewspaperArticleInputs();
                        formContainer.add(new AbstractMaterialForm(articleInputs, "Artículo",  "34"));
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

    private List<TextInput> buildBookInputs(){
        List<TextInput> textInputs = new ArrayList<>();

        textInputs.add(new TextInput(new JTextField(15), "title", "Título"));
        textInputs.add(new TextInput(new JTextField(10), "author", "Autor"));
        textInputs.add(new TextInput(new JTextField(12), "details", "Detalles"));
        textInputs.add(new TextInput(new JTextField(6), "year_publication", "Año de Publicación"));
        textInputs.add(new TextInput(new JTextField(10), "publisher", "Editorial"));
        textInputs.add(new TextInput(new JTextField(13), "isbn", "ISBN"));
        textInputs.add(new TextInput(new JTextField(7), "number_of_pages", "Número de Páginas"));
        textInputs.add(new TextInput(new JTextField(10), "cover_url", "URL de la Portada"));

        return textInputs;
    }

    private List<TextInput> buildThesisInputs() {
        List<TextInput> textInputs = new ArrayList<>();

        textInputs.add(new TextInput(new JTextField(20), "title", "Título"));
        textInputs.add(new TextInput(new JTextField(15), "author", "Autor"));
        textInputs.add(new TextInput(new JTextField(15), "advisor", "Asesor"));
        textInputs.add(new TextInput(new JTextField(20), "university", "Universidad"));
        textInputs.add(new TextInput(new JTextField(10), "faculty", "Facultad"));
        textInputs.add(new TextInput(new JTextField(15), "degree", "Grado Académico"));
        textInputs.add(new TextInput(new JTextField(6), "year_publication", "Año de Publicación"));
        textInputs.add(new TextInput(new JTextField(10), "keywords", "Palabras Clave"));
        textInputs.add(new TextInput(new JTextField(15), "abstract", "Resumen"));
        textInputs.add(new TextInput(new JTextField(20), "subject", "Tema Principal"));
        textInputs.add(new TextInput(new JTextField(10), "location", "Ubicación Física"));
        textInputs.add(new TextInput(new JTextField(15), "identifier", "Identificador (ID)"));
        textInputs.add(new TextInput(new JTextField(10), "language", "Idioma"));
        textInputs.add(new TextInput(new JTextField(20), "external_link", "Enlace Externo (URL)"));
        textInputs.add(new TextInput(new JTextField(15), "department", "Departamento"));

        return textInputs;
    }


    private List<TextInput> buildDVDInputs() {
        List<TextInput> textInputs = new ArrayList<>();

        textInputs.add(new TextInput(new JTextField(15), "title", "Título"));
        textInputs.add(new TextInput(new JTextField(10), "director", "Director"));
        textInputs.add(new TextInput(new JTextField(6), "year_release", "Año de Lanzamiento"));
        textInputs.add(new TextInput(new JTextField(10), "genre", "Género"));
        textInputs.add(new TextInput(new JTextField(8), "duration", "Duración (min)"));
        textInputs.add(new TextInput(new JTextField(12), "studio", "Estudio"));
        textInputs.add(new TextInput(new JTextField(15), "format", "Formato"));

        return textInputs;
    }

    private List<TextInput> buildNewspaperArticleInputs() {
        List<TextInput> textInputs = new ArrayList<>();

        textInputs.add(new TextInput(new JTextField(15), "title", "Título"));
        textInputs.add(new TextInput(new JTextField(10), "author", "Autor"));
        textInputs.add(new TextInput(new JTextField(10), "newspaper", "Periódico"));
        textInputs.add(new TextInput(new JTextField(6), "date_publication", "Fecha de Publicación"));
        textInputs.add(new TextInput(new JTextField(10), "section", "Sección"));
        textInputs.add(new TextInput(new JTextField(15), "url", "URL del Artículo"));
        textInputs.add(new TextInput(new JTextField(12), "keywords", "Palabras Clave"));

        return textInputs;
    }
    private List<TextInput> buildVHSInputs() {
        List<TextInput> textInputs = new ArrayList<>();

        textInputs.add(new TextInput(new JTextField(20), "title", "Título"));
        textInputs.add(new TextInput(new JTextField(15), "director", "Director"));
        textInputs.add(new TextInput(new JTextField(6), "release_year", "Año de Lanzamiento"));
        textInputs.add(new TextInput(new JTextField(10), "duration", "Duración (min)"));
        textInputs.add(new TextInput(new JTextField(15), "genre", "Género"));
        textInputs.add(new TextInput(new JTextField(15), "location", "Ubicación Física"));
        textInputs.add(new TextInput(new JTextField(10), "identifier", "Identificador"));

        return textInputs;
    }

    private List<TextInput> buildCDInputs() {
        List<TextInput> textInputs = new ArrayList<>();

        textInputs.add(new TextInput(new JTextField(20), "title", "Título del CD"));
        textInputs.add(new TextInput(new JTextField(15), "artist", "Artista/Grupo"));
        textInputs.add(new TextInput(new JTextField(10), "genre", "Género Musical"));
        textInputs.add(new TextInput(new JTextField(6), "release_year", "Año de Lanzamiento"));
        textInputs.add(new TextInput(new JTextField(10), "duration", "Duración Total (min)"));
        textInputs.add(new TextInput(new JTextField(15), "location", "Ubicación Física"));
        textInputs.add(new TextInput(new JTextField(10), "identifier", "Identificador"));

 //       textInputs.add(new TextInput(new JTextField(20), "building", "Edificio"));
   //     textInputs.add(new TextInput(new JTextField(10), "building_floor", "Piso del Edificio"));
     //   textInputs.add(new TextInput(new JTextField(15), "shelf_code", "Código de Estantería"));
       // textInputs.add(new TextInput(new JTextField(10), "shelf_level", "Nivel de Estantería"));

        return textInputs;
    }

}
