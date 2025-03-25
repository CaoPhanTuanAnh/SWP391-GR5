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

        try (Connection connection = getConnection(); // Gọi trực tiếp getConnection() từ DBContext
                 PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

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

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<genres> getAllGenre() {
        List<genres> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            String sql = "SELECT genre_id, genre_name FROM genres";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                genres g = new genres();
                g.setGenre_id(rs.getInt("genre_id"));
                g.setGenre_name(rs.getString("genre_name"));
                list.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
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
