/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Rooms;
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
public class RoomsDAO extends DBContext {

    public List<Rooms> getAllRoom(int mid, int branchId, Date startDate, Time startTime) {
    List<Rooms> movieList = new ArrayList<>();
    
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

                Rooms room = new Rooms();
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

    public Rooms getRoom(int roomId) {
    Rooms room = null;
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

                room = new Rooms();
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



}
