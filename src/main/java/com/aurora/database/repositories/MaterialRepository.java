package com.aurora.database.repositories;

import com.aurora.database.DatabaseConnection;
import com.aurora.database.InputStatement;
import com.aurora.database.models.MaterialDirection;
import com.aurora.exceptions.ErrorCreatingDatabaseRecord;
import org.json.JSONObject;

import java.sql.*;
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

   public int saveDinamicMaterial(List<InputStatement> inputStatements, String fkMaterialType) throws Exception{
       JSONObject materialToSave = new JSONObject();

       int newId = -1;
       // construimos el objeto del material a guardar
       inputStatements.stream()
               .forEach(statement -> {
                    materialToSave.put( statement.getColumnName(), statement.getValue());
               });
       // preparamos la sentencia sql
       String query = "insert into material (properties, material_type_id) values (?::jsonb, ?)";

       //Hacemos la conección a la base de datos
       try(Connection conn = DatabaseConnection.getConnection()){
           PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
           stmt.setString(1, materialToSave.toString());
           stmt.setInt(2, Integer.parseInt(fkMaterialType));
           stmt.executeUpdate();
           // Obtener la clave generada
           try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
               if (generatedKeys.next()) { // No es necesario usar un bucle
                    newId = generatedKeys.getInt(1);
               } else {
                  throw  new ErrorCreatingDatabaseRecord("No se generó ningún ID para el material.");
               }
           }

       }catch (SQLException e){
           e.printStackTrace();
       }

       return newId;
   }

   public void saveMaterialLocation(MaterialDirection materialDirection, Integer materialId) throws ErrorCreatingDatabaseRecord {
        System.out.println(materialDirection);
       String query = "insert into material_location (building, building_floor, building_floor_sector, shelf_code, shelf_level, material_id) values (?,?,?,?,?,?)";

       try(Connection conn = DatabaseConnection.getConnection()){
           PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
           stmt.setString(1, materialDirection.getBuilding());
           stmt.setInt(2, Integer.parseInt(materialDirection.getFloorBuilding()));
           stmt.setString(3, materialDirection.getFloorSector());
           stmt.setString(4, materialDirection.getShelCode());
           stmt.setInt(5, Integer.parseInt(materialDirection.getShelFloor()));
           stmt.setInt(6, materialId);

           stmt.executeUpdate();

       }catch (SQLException e){
           e.printStackTrace();
           throw new ErrorCreatingDatabaseRecord("Error al crear la locación del material");
       }
   };

}
