/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Theater;
import entity.rooms;
import entity.types;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PCASUS
 */
public class roomsDAO extends DBContext {

    public List<rooms> getAllRoom(int mid, int branchId, Date startDate, Time startTime) {
    List<rooms> movieList = new ArrayList<>();
    
    // Câu SQL: So sánh `DATE` và kiểm tra `TIME` bằng cách lấy HOUR + MINUTE
    String sql = "SELECT DISTINCT " +
                 "r.room_id, r.room_name, r.capacity, r.img " +
                 "FROM movies m " +
                 "JOIN showtimes s ON m.movie_id = s.movie_id " +
                 "JOIN rooms r ON r.room_id = s.room_id " +
                 "JOIN theaters t ON t.theater_id = r.theater_id " +
                 "WHERE m.movie_id = ? " +
                 "AND t.theater_id = ? " +
                 "AND CONVERT(DATE, s.showtime) = ? " +
                 "AND DATEPART(HOUR, s.showtime) = DATEPART(HOUR, ?) " +
                 "AND DATEPART(MINUTE, s.showtime) = DATEPART(MINUTE, ?) " +
                 "GROUP BY r.room_id, r.room_name, r.capacity, r.img";

    try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

        st.setInt(1, mid);
        st.setInt(2, branchId);
        st.setDate(3, startDate);
        
        // Chuyển `startTime` thành `Timestamp` để lấy `HOUR` và `MINUTE`
        Timestamp startTimestamp = new Timestamp(startTime.getTime());
        st.setTimestamp(4, startTimestamp);
        st.setTimestamp(5, startTimestamp);

        try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                int room_id = rs.getInt("room_id");
                String room_name = rs.getString("room_name");
                int capacity = rs.getInt("capacity");
                String img = rs.getString("img");

                rooms room = new rooms();
                room.setRoom_id(room_id);
                room.setRoom_name(room_name);
                room.setImg(img);
                room.setCapacity(capacity);
                movieList.add(room);
            }
        }
    } catch (SQLException e) {
        System.err.println("Lỗi truy vấn getAllRoom: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Lỗi kết nối DB: " + e.getMessage());
    }

    return movieList;
}

    public rooms getRoom(int roomId) {
    rooms room = null;
    String sql = "SELECT room_id, theater_id, manager_id, room_name, type_id, capacity, img FROM rooms WHERE room_id = ?";

    try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {
        st.setInt(1, roomId);
        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                int theater_id = rs.getInt("theater_id");
                int manager_id = rs.getInt("manager_id");
                String room_name = rs.getString("room_name");
                int type_id = rs.getInt("type_id");
                int capacity = rs.getInt("capacity");
                String img = rs.getString("img");

                room = new rooms();
                room.setRoom_id(roomId);
                room.setTheater_id(theater_id);
                room.setManager_id(manager_id);
                room.setRoom_name(room_name);
                room.setType_id(type_id);
                room.setCapacity(capacity);
                room.setImg(img);
            }
        }
    } catch (SQLException e) {
        System.err.println("Lỗi truy vấn getRoom: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Lỗi kết nối DB: " + e.getMessage());
    }
    return room;
}
    
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
