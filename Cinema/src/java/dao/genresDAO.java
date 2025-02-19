package dao;

import context.DBContext;
import entity.genres;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class genresDAO extends DBContext {
    public List<genres> getAllGenres() {
        List<genres> genreList = new ArrayList<>();
        String sql = "SELECT * FROM genres";

        try (Connection connection = getConnection();  // Gọi trực tiếp getConnection() từ DBContext
             PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                int genreId = rs.getInt("genre_id");
                String genreName = rs.getString("genre_name");

                genres genre = new genres(genreId, genreName);
                genreList.add(genre);
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách thể loại: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi kết nối DB: " + e.getMessage());
        }

        return genreList;
    }

//    public static void main(String[] args) {
//        genresDAO dao = new genresDAO();
//        List<genres> genres = dao.getAllGenres();
//
//        for (genres g : genres) {
//            System.out.println("ID: " + g.getGenre_id() + ", Name: " + g.getGenre_name());
//        }
//    }
}
