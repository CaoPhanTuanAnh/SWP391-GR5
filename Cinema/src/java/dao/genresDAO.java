/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.genres;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PCASUS
 */
public class genresDAO extends DBContext{
 Connection connection = null;
    public List<genres> getAllGenres() {
        List<genres> productList = new ArrayList<>();
        String sql = "SELECT * FROM genres";

        try ( PreparedStatement st = connection.prepareStatement(sql);  ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                int productId = rs.getInt("genre_id");
                String productName = rs.getString("genre_name");
               

              
                genres product = new genres(productId, productName);
                productList.add(product);
            }

        } catch (SQLException e) {
            // Handle the exception appropriately, e.g., log or rethrow
            System.err.println("Error retrieving products: " + e.getMessage());
        }

        return productList;
    }
    
}
