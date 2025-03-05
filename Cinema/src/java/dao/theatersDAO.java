/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.theaters;
import context.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PCASUS
 */
public class theatersDAO extends DBContext {

    public List<theaters> getAllTheater() {
        List<theaters> movieList = new ArrayList<>();
        String sql = "SELECT DISTINCT\n"
                + "    t.address, t.img, t.theater_id, t.theater_name\n"
                + "FROM movies m \n"
                + "JOIN showtimes s ON m.movie_id = s.movie_id \n"
                + "JOIN rooms r ON r.room_id = s.room_id \n"
                + "JOIN theaters t ON t.theater_id = r.theater_id\n"
                + "\n"
                + "GROUP BY t.address, t.img, t.theater_id, t.theater_name";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int theater_id = rs.getInt("theater_id");
                    String theater_name = rs.getString("theater_name");

                    String img = rs.getString("img");
                    String address = rs.getString("address");

                    theaters movies = new theaters();
                    movies.setTheater_id(theater_id);
                    movies.setImg(img);
                    movies.setAddress(theater_name);
                    movies.setTheater_name(theater_name);

                    movieList.add(movies);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn getAllMoviesWithPaging: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi kết nối DB: " + e.getMessage());
        }
        return movieList;
    }

    public static void main(String[] args) {
        List<theaters> a = new theatersDAO().getAllTheater();
        for (theaters theater : a) {
            System.out.println(theater.getTheater_name());
        }

    }

    public theaters getBrand(int branchId) throws Exception {
        String sql = "SELECT theater_id, city_id, theater_name, img, address FROM theaters WHERE theater_id = ?";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, branchId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int idTheater = rs.getInt("theater_id");
                    int idCity = rs.getInt("city_id");
                    String theaterName = rs.getString("theater_name");
                    String img = rs.getString("img");
                    String theaterAddress = rs.getString("address");

                    return new theaters(idTheater, idCity, theaterName, theaterAddress, img);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn getBrand: " + e.getMessage());
        }
        return null;
    }

}
