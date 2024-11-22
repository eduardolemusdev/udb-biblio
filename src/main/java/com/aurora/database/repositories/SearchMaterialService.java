package com.aurora.database.repositories;

import com.aurora.database.DatabaseConnection;
import com.aurora.exceptions.ErrorCreatingDatabaseRecord;
import com.aurora.screens.admin.TextInput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SearchMaterialService {

    private final Logger logger = LogManager.getLogger();


    public void searchMaterial( String filterStrategy, String title, List<TextInput> materialLocationTextInputs){

      switch (filterStrategy) {
            case "materialID":
                break;
            case "materialTitle":
                searchByTitle(title, materialLocationTextInputs);
                break;
        }
    }

    private  void searchByTitle(String title, List<TextInput> materialLocationTextInputs) {

        SearchBuilderResult searchBuilderResult = buildSearcByTitleQuery(title, materialLocationTextInputs);
        try(Connection conn = DatabaseConnection.getConnection()){

            logger.info(searchBuilderResult.getQuery());
            PreparedStatement stmt = conn.prepareStatement(searchBuilderResult.getQuery());
            stmt.setString(1, "%"+title+"%");

            if (materialLocationTextInputs.size() > 0) {
                AtomicInteger stmtCounter = new AtomicInteger(2);

                for (TextInput textInput : searchBuilderResult.getInputsWithValues()) {
                    String buildTextValue = textInput.getTextField().getText();
                    if (!isInteger(buildTextValue)) {
                        stmt.setString(stmtCounter.get(), buildTextValue);
                        return;
                    }
                    stmt.setInt(stmtCounter.get(), Integer.parseInt(buildTextValue));
                    stmtCounter.set(stmtCounter.get() + 1);
                }
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
               logger.info(rs.getString("properties"));
            }


        }catch (SQLException e){
            e.printStackTrace();
            logger.error(e);
        }

    }

    private SearchBuilderResult buildSearcByTitleQuery(String title, List<TextInput> materialLocationTextInputs) {

        String searchByTitleBaseQuery = "SELECT * FROM material m JOIN material_location ml ON m.id = ml.material_id WHERE m.properties->>'title' ILIKE ?";
        StringBuffer sb = new StringBuffer(searchByTitleBaseQuery);

        List<TextInput> columsWithValues = materialLocationTextInputs.stream()
                .filter( textInput -> !textInput.getTextField().getText().isEmpty())
                .collect(Collectors.toList());

        // se agregan dinamicmente las columnas
        // AND ml.building '?'
        // AND ml.buildingg_flor '?'
        // etc
        columsWithValues.forEach(col -> sb.append(String.format(" AND ml.%s = '?'",col.getColumnName())));
        sb.append(";");

        logger.info(sb.toString());

        return new SearchBuilderResult(sb.toString(), columsWithValues);
    }

    private boolean isInteger(String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private  class SearchBuilderResult {
        private String query;
        private List<TextInput> InputsWithValues;

        public SearchBuilderResult(String query, List<TextInput> inputsWithValues) {
            this.query = query;
            InputsWithValues = inputsWithValues;
        }

        public String getQuery() {
            return query;
        }

        public List<TextInput> getInputsWithValues() {
            return InputsWithValues;
        }
    }
}
