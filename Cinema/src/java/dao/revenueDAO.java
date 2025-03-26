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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GIGABYTE
 */
public class revenueDAO extends DBContext {

    public List<revenue_data> viewRevenueByMovie() {
        List<revenue_data> revenueList = new ArrayList<>();

        String sql = "select m.title,sum(t.price) as revenue from movies m\n"
                + "join showtimes st on st.movie_id=m.movie_id\n"
                + "join tickets t on t.showtime_id=st.showtime_id\n"
                + "where t.booking_id is not null\n"
                + "group by m.title";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    revenueList.add(new revenue_data(rs.getString(1), rs.getDouble(2)));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(revenueDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return revenueList;
    }

    public List<revenue_data> viewRevenueByDay(int month, int year) {
        List<revenue_data> revenueList = new ArrayList<>();

        String sql = """
                     select DAY(st.showtime),sum(t.price) as revenue from showtimes st
                     join tickets t on t.showtime_id=st.showtime_id
                     where t.booking_id is not null and Month(st.showtime) = ? and Year(st.showtime) = ?
                     group by st.showtime""";
//--MONTH(GETDATE())
        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, month);
            st.setInt(2, year);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    revenueList.add(new revenue_data(rs.getString(1), rs.getDouble(2)));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(revenueDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return revenueList;
    }

}
