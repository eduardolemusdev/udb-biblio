package com.aurora.database.repositories;

import com.aurora.database.DatabaseConnection;
import com.aurora.database.models.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    public List<Category> getCategories(){
        List<Category> categories = new ArrayList<>();
        String query = "select * from categories order by name";
        try(Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs  = stmt.executeQuery();

            while(rs.next()){
                String name = rs.getString("name");
                int id = rs.getInt("id");
                categories.add(new Category(name, id));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return categories;
    }

    public void saveMaterialCategories(List<Integer> listCategoriesSelected, Integer newMaterialId){
     listCategoriesSelected.forEach(categoryId -> {
         String query = "insert into material_categories(material_id, category_id) values(?,?)";
         try(Connection conn = DatabaseConnection.getConnection()){
             PreparedStatement stmt = conn.prepareStatement(query);
             stmt.setInt(1, newMaterialId);
             stmt.setInt(2, categoryId);
             stmt.executeUpdate();
         }catch (SQLException e){
             e.printStackTrace();
         }
     });

     System.out.println("Material categories saved");
    }
}
