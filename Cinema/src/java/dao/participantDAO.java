package dao;

import context.DBContext;
import entity.participants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class participantDAO extends DBContext {

    Connection conn = null; // kết nối vs sql
    PreparedStatement ps = null; // ném query sang sql
    ResultSet rs = null; // nhận kết quả trả về

    public List<participants> getDirectorsByMovieId(int movieId) throws Exception {
        List<participants> list = new ArrayList<>();
        String sql = "SELECT p.participant_id, p.participant_name, p.portrait_url "
                + "FROM participants p "
                + "JOIN movie_participants mp ON p.participant_id = mp.participant_id "
                + "WHERE mp.movie_id = ? AND mp.role_in_movie = 'Director'";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
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
        String sql = "SELECT p.participant_id, p.participant_name, p.portrait_url "
                + "FROM participants p "
                + "JOIN movie_participants mp ON p.participant_id = mp.participant_id "
                + "WHERE mp.movie_id = ? AND mp.role_in_movie = 'Actor'";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
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

    public List<participants> getAllActors() throws Exception {
        List<participants> actors = new ArrayList<>();
        String sql = "SELECT participant_id, participant_name FROM participants "
                + "WHERE participant_id IN (SELECT participant_id FROM movie_participants WHERE role_in_movie = 'Actor')";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                participants actor = new participants();
                actor.setParticipant_id(rs.getInt("participant_id"));
                actor.setParticipant_name(rs.getString("participant_name"));
                actors.add(actor);
            }
        }
        return actors;
    }

    public List<participants> getAllDirectors() throws Exception {
        List<participants> directors = new ArrayList<>();
        String sql = "SELECT participant_id, participant_name FROM participants "
                + "WHERE participant_id IN (SELECT participant_id FROM movie_participants WHERE role_in_movie = 'Director')";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                participants director = new participants();
                director.setParticipant_id(rs.getInt("participant_id"));
                director.setParticipant_name(rs.getString("participant_name"));
                directors.add(director);
            }
        }
        return directors;
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
            }
        } catch (Exception ex) {
            Logger.getLogger(participantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<participants> listParticipant() {
        ArrayList<participants> participantList = new ArrayList<>();
        String query = "select p.* from participants p ";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                participants participant = new participants();

// Sử dụng setter để thiết lập giá trị cho các thuộc tính
                participant.setParticipant_id(rs.getInt("participant_id"));
                participant.setParticipant_name(rs.getString("participant_name"));
                participant.setPortrait_url(rs.getString("portrait_url"));
                participant.setBirth_date(rs.getDate("birth_date"));
                participant.setNationality(rs.getString("nationality"));
                participant.setAbout(rs.getString("about"));

// Thêm đối tượng vào danh sách
                participantList.add(participant);
            }
            return participantList;
        } catch (Exception ex) {
            Logger.getLogger(participantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return participantList;
    }

    public boolean addParticipant(String participantName, String portrait, String birth_date, String nationality, String about) {
        String query = "INSERT INTO participants (participant_name, portrait_url, birth_date, nationality, about) \n" + "VALUES (?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, participantName);
            ps.setString(2, portrait);
            ps.setString(3, birth_date);
            ps.setString(4, nationality);
            ps.setString(5, about);
            int check = ps.executeUpdate();
            if (check == 1) {
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(participantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteParticipant(int participantID) {
        String query = "delete from participants where participant_id=?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, participantID);
            int check = ps.executeUpdate();
            if (check == 1) {
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(participantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean editParticipant(int participantID, String participantName, String portrait, String birth_date, String nationality, String about) {
        String query = "update participants set participant_name=?, portrait_url = ?, birth_date = ?, nationality = ?, about = ? where participant_id=?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, participantName);
            ps.setString(2, portrait);
            ps.setString(3, birth_date);
            ps.setString(4, nationality);
            ps.setString(5, about);
            ps.setInt(6, participantID);
            int check = ps.executeUpdate();
            if (check == 1) {
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(participantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
