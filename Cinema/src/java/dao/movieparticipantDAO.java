/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.movie_participant_name;
import entity.movie_participants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class movieparticipantDAO {
    
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
}
