/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Showtimes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.sql.Time;

/**
 *
 * @author PCASUS
 */
public class ShowtimesDAO extends DBContext {

   public List<Showtimes> getAllDateByBrand(int mid, int branchId) {
    List<Showtimes> movieList = new ArrayList<>();
    String sql = "SELECT DISTINCT " +
                 "    s.movie_id, " +
                 "    CAST(s.showtime AS DATE) AS show_date, " +
                 "    s.showtime_id, " +
                 "    s.status " +
                 "FROM movies m " +
                 "JOIN showtimes s ON m.movie_id = s.movie_id " +
                 "JOIN rooms r ON r.room_id = s.room_id " +
                 "JOIN theaters t ON t.theater_id = r.theater_id " +
                 "WHERE s.movie_id = ? AND r.theater_id = ? " +
                 "GROUP BY s.movie_id, CAST(s.showtime AS DATE), s.showtime_id, s.status;";

    try (Connection connection = getConnection();
         PreparedStatement st = connection.prepareStatement(sql)) {

        st.setInt(1, mid);
        st.setInt(2, branchId);

        try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                int movie_id = rs.getInt("movie_id");
                Date date = rs.getDate("show_date");
                int showtime_id = rs.getInt("showtime_id");
                String status = rs.getString("status");

                Showtimes movies = new Showtimes();
                movies.setDate(date);
                movies.setMovie_id(movie_id);
                movies.setStatus(status);
                movies.setShowtime_id(showtime_id);

                movieList.add(movies);
            }
        }
    } catch (SQLException e) {
        System.err.println("Lỗi truy vấn getAllDateByBrand: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Lỗi kết nối DB: " + e.getMessage());
    }

    return movieList;
}

public List<Showtimes> getAllTimeByBrand(int mid, int branchId) {
    List<Showtimes> movieList = new ArrayList<>();
    String sql = "SELECT DISTINCT " +
                 "    s.movie_id, " +
                 "    CAST(s.showtime AS DATE) AS show_date, " +
                 "    CAST(s.showtime AS TIME) AS show_time, " +
                 "    s.showtime_id, " +
                 "    s.status " +
                 "FROM movies m " +
                 "JOIN showtimes s ON m.movie_id = s.movie_id " +
                 "JOIN rooms r ON r.room_id = s.room_id " +
                 "JOIN theaters t ON t.theater_id = r.theater_id " +
                 "WHERE s.movie_id = ? AND r.theater_id = ? " +
                 "GROUP BY s.movie_id, CAST(s.showtime AS DATE), CAST(s.showtime AS TIME), s.showtime_id, s.status;";

    try (Connection connection = getConnection();
         PreparedStatement st = connection.prepareStatement(sql)) {

        st.setInt(1, mid);
        st.setInt(2, branchId);

        try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                int movie_id = rs.getInt("movie_id");
                Date date = rs.getDate("show_date");
                Time time = rs.getTime("show_time");
                int showtime_id = rs.getInt("showtime_id");
                String status = rs.getString("status");

                Showtimes movies = new Showtimes();
                movies.setDate(date);
                movies.setMovie_id(movie_id);
                movies.setTime(time);
                movies.setStatus(status);
                movies.setShowtime_id(showtime_id);

                movieList.add(movies);
            }
        }
    } catch (SQLException e) {
        System.err.println("Lỗi truy vấn getAllTimeByBrand: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Lỗi kết nối DB: " + e.getMessage());
    }

    return movieList;
}


}
