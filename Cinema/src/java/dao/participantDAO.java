package dao;

import context.DBContext;
import entity.participants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class participantDAO extends DBContext {

    public List<participants> getDirectorsByMovieId(int movieId) throws Exception {
        List<participants> list = new ArrayList<>();
        String sql = "SELECT p.participant_id, p.participant_name, p.portrait_url " +
                     "FROM participants p " +
                     "JOIN movie_participants mp ON p.participant_id = mp.participant_id " +
                     "WHERE mp.movie_id = ? AND mp.role_in_movie = 'Director'";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, movieId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    participants p = new participants();
                    p.setParticipant_id(rs.getInt("participant_id"));
                    p.setParticipant_name(rs.getString("participant_name"));
                    p.setPortrait_url(rs.getString("portrait_url"));
                    list.add(p);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi truy vấn getDirectorsByMovieId: " + e.getMessage());
        }
        return list;
    }

    public List<participants> getActorsByMovieId(int movieId) throws Exception {
        List<participants> list = new ArrayList<>();
        String sql = "SELECT p.participant_id, p.participant_name, p.portrait_url " +
                     "FROM participants p " +
                     "JOIN movie_participants mp ON p.participant_id = mp.participant_id " +
                     "WHERE mp.movie_id = ? AND mp.role_in_movie = 'Actor'";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, movieId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    participants p = new participants();
                    p.setParticipant_id(rs.getInt("participant_id"));
                    p.setParticipant_name(rs.getString("participant_name"));
                    p.setPortrait_url(rs.getString("portrait_url"));
                    list.add(p);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi truy vấn getActorsByMovieId: " + e.getMessage());
        }
        return list;
    }
    
    public static void main(String[] args) {
        try {
            participantDAO dao = new participantDAO();
            List<participants> directors = dao.getDirectorsByMovieId(1);  // Thay ID bộ phim cần kiểm tra
            List<participants> actors = dao.getActorsByMovieId(1);
            
            System.out.println("Đạo diễn:");
            for (participants d : directors) {
                System.out.println(d.getParticipant_name());
            }
            
            System.out.println("Diễn viên:");
            for (participants a : actors) {
                System.out.println(a.getParticipant_name());
            }   } catch (Exception ex) {
            Logger.getLogger(participantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
}

}
