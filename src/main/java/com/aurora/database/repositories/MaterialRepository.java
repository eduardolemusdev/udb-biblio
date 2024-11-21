package com.aurora.database.repositories;

import com.aurora.database.DatabaseConnection;
import com.aurora.screens.admin.TextInput;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class MaterialRepository {

    public List<String> materialTypes(){
        List<String> materialTypes = new ArrayList<>();
        String query = "select * from material_type";
        try(Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs  = stmt.executeQuery();

            while(rs.next()){
                materialTypes.add(rs.getString("name"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return materialTypes;
    }

   public void saveDinamicMaterial(List<InputStatement> inputStatements, String fkMaterialType){
       JSONObject materialToSave = new JSONObject();

       // construimos el objeto del material a guardar
       inputStatements.stream()
               .forEach(statement -> {
                    materialToSave.put( statement.getColumnName(), statement.getValue());
               });
       // preparamos la sentencia sql
       String query = "insert into material (properties, material_type_id) values (?::jsonb, ?)";

       //Hacemos la conección a la base de datos
       try(Connection conn = DatabaseConnection.getConnection()){
           PreparedStatement stmt = conn.prepareStatement(query);
           stmt.setString(1, materialToSave.toString());
           stmt.setInt(2, Integer.parseInt(fkMaterialType));
           stmt.executeUpdate();
           System.out.println("Material guardado sastifactoriamente.");
       }catch (SQLException e){
           e.printStackTrace();
       }

   }

}
