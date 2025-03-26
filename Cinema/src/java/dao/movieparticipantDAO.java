/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.movie_participant_name;
import entity.movie_participants;
import entity.movies;
import entity.participants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.sql.Date;

public class movieparticipantDAO extends DBContext {
    
    Connection conn = null; // kết nối vs sql
    PreparedStatement ps = null; // ném query sang sql
    ResultSet rs = null; // nhận kết quả trả về
    
    public List<movie_participants> listMovieParticipant(){
        ArrayList<movie_participants> movie_participantList = new ArrayList<>();
        String query = "SELECT DISTINCT\n" +
                "    m.*,\n" +
                "    p.*,\n" +
                "    mv.*\n" +
                "FROM movie_participants m\n" +
                "LEFT JOIN participants p ON m.participant_id = p.participant_id\n" +
                "LEFT JOIN movies mv ON m.movie_id = mv.movie_id;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                movie_participants movie_participant = new movie_participant_name(
                        rs.getInt("movie_id"),
                        rs.getString("title"),
                        rs.getInt("participant_id"),
                        rs.getString("participant_name"),
                        rs.getString("role_in_movie"));
                movie_participantList.add(movie_participant);
            }
            return movie_participantList;
        } catch (Exception ex) {
            Logger.getLogger(movieparticipantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return movie_participantList;
    }
    
    public boolean addMovieParticipant(int movie_id, int participant_id, String role_in_movie){
        String query = "INSERT INTO movie_participants (movie_id, participant_id, role_in_movie) \n" + "VALUES (?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, movie_id);
            ps.setInt(2, participant_id);
            ps.setString(3, role_in_movie);
            int check = ps.executeUpdate();
            if(check==1)return true;
        } catch (Exception ex) {
            Logger.getLogger(movieparticipantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean deleteMovieParticipant(int movie_id,int participant_id){
        String query = "delete from [dbo].[movie_participants] where movie_id=? and participant_id=?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, movie_id);
            ps.setInt(2, participant_id);
            int check = ps.executeUpdate();
            if(check==1)return true;
        } catch (Exception ex) {
            Logger.getLogger(movieparticipantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean editMovieParticipant(int new_movie_id, int new_participant_id, String role_in_movie ,int movie_id, int participant_id){
        String query = "update movie_participants set movie_id=?, participant_id = ?, role_in_movie = ? where movie_id=? and participant_id=?" ;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, new_movie_id);
            ps.setInt(2, new_participant_id);
            ps.setString(3, role_in_movie);
            ps.setInt(4, movie_id);
            ps.setInt(5, participant_id);
            int check = ps.executeUpdate();
            if(check==1)return true;
        } catch (Exception ex) {
            Logger.getLogger(movieparticipantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List<movies> listMovie() {
        List<movies> movieList = new ArrayList<>();
        String sql = "SELECT * FROM movies ";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int movie_id = rs.getInt("movie_id");
                    String title = rs.getString("title");
                    int duration = rs.getInt("duration");
                    String poster_url = rs.getString("poster_url");

                    movies movies = new movies();
                    movies.setMovie_id(movie_id);
                    movies.setTitle(title);
                    movies.setDuration(duration);
                    movies.setPoster_url(poster_url);
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
    
    public List<participants> listParticipant() {
        List<participants> participantList = new ArrayList<>();
        String sql = "SELECT * FROM participants ";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int participant_id = rs.getInt("participant_id");
                    String participant_name = rs.getString("participant_name");
                    String portrait_url = rs.getString("portrait_url");
                    Date birth_date = rs.getDate("birth_date");
                    String nationality = rs.getString("nationality");
                    String about = rs.getString("about");

                    participants participants = new participants();
                    participants.setParticipant_id(participant_id);
                    participants.setParticipant_name(participant_name);
                    participants.setPortrait_url(portrait_url);
                    participants.setBirth_date(birth_date);
                    participants.setNationality(nationality);
                    participants.setAbout(about);
                    participantList.add(participants);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn getAllParticipantsWithPaging: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi kết nối DB: " + e.getMessage());
        }
        return participantList;
    }
}
