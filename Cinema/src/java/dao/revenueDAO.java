/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.revenue_data;
import entity.showtimes;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GIGABYTE
 */
public class revenueDAO extends DBContext{
    
    public List<revenue_data> listRevenue(){
        List<revenue_data> revenueList = null;
        
        String sql = "SELECT DISTINCT "
                + "    s.movie_id, "
                + "    CAST(s.showtime AS DATE) AS show_date, "
                + "    s.showtime_id, "
                + "    s.status "
                + "FROM movies m "
                + "JOIN showtimes s ON m.movie_id = s.movie_id "
                + "JOIN rooms r ON r.room_id = s.room_id "
                + "JOIN theaters t ON t.theater_id = r.theater_id "
                + "WHERE s.movie_id = ? AND r.theater_id = ? "
                + "GROUP BY s.movie_id, CAST(s.showtime AS DATE), s.showtime_id, s.status;";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    
                }
            }
        } catch (Exception e) {
            Logger.getLogger(revenueDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return revenueList;
    }
    
}
