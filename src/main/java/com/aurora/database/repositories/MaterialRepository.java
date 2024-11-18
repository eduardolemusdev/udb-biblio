package com.aurora.database.repositories;

import com.aurora.database.DatabaseConnection;

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

}
