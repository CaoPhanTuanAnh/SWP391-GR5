/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Theater;
import entity.rooms;
import entity.types;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 84912
 */
public class roomsDAO {

    Connection conn = null; // kết nối vs sql
    PreparedStatement ps = null; // ném query sang sql
    ResultSet rs = null; // nhận kết quả trả về

    public rooms getRoomByRoomID(String id) {
        String query = "select * from rooms\n"
                + "where room_id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new rooms(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Theater getTheaterById(int theaterId) {
        String query = "SELECT theater_id, theater_name, director_id FROM theaters WHERE theater_id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, theaterId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Theater(rs.getInt("theater_id"), rs.getString("theater_name"),rs.getInt("director_id") );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Theater> getTheaterByManagerID(int id) {
        List<Theater> listT = new ArrayList<>();
        String query = "select * from theaters\n"
                + "where director_id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                listT.add(new Theater(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listT;
    }

    public List<rooms> getAllRoomByManagerIDAndTheaterID(int managerId, int theaterId) {
        List<rooms> listR = new ArrayList<>();
        String query = "select * from rooms\n"
                + "where manager_id = ? and theater_id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, managerId);
            ps.setInt(2, theaterId);
            rs = ps.executeQuery();
            while (rs.next()) {
                listR.add(new rooms(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listR;
    }

    public List<types> getAllTypes() {
        List<types> type = new ArrayList<>();
        String query = "select * from types";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                type.add(new types(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return type;
    }

    public void editRoom(String room_name, String capacity, String type_id, String room_id) {
        String query = "UPDATE rooms\n"
                + "SET \n"
                + "    room_name = ?, \n"
                + "    capacity = ?, \n"
                + "    type_id = ?\n"
                + "WHERE \n"
                + "    room_id = ?;";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, room_name);
            ps.setString(2, capacity);
            ps.setString(3, type_id);
            ps.setString(4, room_id);

            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertRoom(int theater_id, int manager_id, String room_name, String capacity, String type_id) {
        String query = "INSERT INTO rooms (theater_id, manager_id, room_name, img, capacity, type_id)  \n"
                + "VALUES (?, ?, ?, NULL, ?, ?);";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, theater_id);
            ps.setInt(2, manager_id);
            ps.setString(3, room_name);
            ps.setString(4, capacity);
            ps.setString(5, type_id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
        public void deleteRoom(String roomid) {
        String query = "DELETE FROM [dbo].[rooms] WHERE room_id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, roomid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

}
