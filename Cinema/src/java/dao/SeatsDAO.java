/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Seats;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PCASUS
 */
public class SeatsDAO extends DBContext {

    public List<Seats> getAllSeatA(int mid, int branchId, Date startDate, Time startTime, int roomId) {
        List<Seats> movieList = new ArrayList<>();

        // Câu SQL: So sánh `DATE` và kiểm tra `TIME` bằng cách lấy HOUR + MINUTE
        String sql = "SELECT \n"
                + "    se.seat_id, \n"
                + "    se.seat_number, \n"
                + "    se.seat_row, \n"
                + "    se.status \n"
                + "FROM movies m \n"
                + "JOIN showtimes s ON m.movie_id = s.movie_id\n"
                + "JOIN rooms r ON r.room_id = s.room_id\n"
                + "JOIN theaters t ON t.theater_id = r.theater_id\n"
                + "JOIN seats se ON se.room_id = r.room_id\n"
                + "WHERE m.movie_id = ?\n"
                + "    AND CONVERT(DATE, s.showtime) = ?\n"
                + "    AND DATEPART(HOUR, s.showtime) = DATEPART(HOUR, ?)\n"
                + "    AND DATEPART(MINUTE, s.showtime) = DATEPART(MINUTE, ?)\n"
                + "    AND t.theater_id = ?\n"
                + "    AND r.room_id = ?\n"
                + "    AND se.seat_row = 'A';";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, mid);
            st.setInt(5, branchId);
            st.setDate(2, startDate);

            Timestamp startTimestamp = new Timestamp(startTime.getTime());
            st.setTimestamp(3, startTimestamp);
            st.setTimestamp(4, startTimestamp);
            st.setInt(6, roomId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int seat_id = rs.getInt("seat_id");
                    int seat_number = rs.getInt("seat_number");
                    String seat_row = rs.getString("seat_row");
                    String status = rs.getString("status");

                    Seats room = new Seats();

                    room.setSeat_id(seat_id);
                    room.setSeat_number(seat_number);
                    room.setSeat_row(seat_row);
                    room.setStatus(status);
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
    
    public static void main(String[] args) {
        // Tạo đối tượng DAO
        SeatsDAO seatsDAO = new SeatsDAO();

        // Khai báo các tham số test
        int movieId = 4;  // Thay bằng ID phim thực tế trong DB
        int branchId = 4; // Thay bằng ID chi nhánh thực tế
        Date startDate = Date.valueOf("2024-10-18"); // Thay bằng ngày thực tế có suất chiếu
        Time startTime = Time.valueOf("21:00:00"); // Thay bằng giờ có suất chiếu thực tế
        int roomId = 4; // Thay bằng ID phòng thực tế

        // Gọi phương thức getAllSeatA
        List<Seats> seatsList = seatsDAO.getAllSeatA(movieId, branchId, startDate, startTime, roomId);

        // Kiểm tra kết quả
        if (seatsList.isEmpty()) {
            System.out.println("Không tìm thấy ghế nào cho suất chiếu này.");
        } else {
            System.out.println("Danh sách ghế hàng A:");
            for (Seats seat : seatsList) {
                System.out.println("ID: " + seat.getSeat_id() +
                        ", Số ghế: " + seat.getSeat_number() +
                        ", Hàng: " + seat.getSeat_row() +
                        ", Trạng thái: " + seat.getStatus());
            }
        }
    }
    
    

    public List<Seats> getAllSeatB(int mid, int branchId, Date startDate, Time startTime, int roomId) {
        List<Seats> movieList = new ArrayList<>();

        // Câu SQL: So sánh `DATE` và kiểm tra `TIME` bằng cách lấy HOUR + MINUTE
        String sql = "SELECT \n"
                + "    se.seat_id, \n"
                + "    se.seat_number, \n"
                + "    se.seat_row, \n"
                + "    se.status \n"
                + "FROM movies m \n"
                + "JOIN showtimes s ON m.movie_id = s.movie_id\n"
                + "JOIN rooms r ON r.room_id = s.room_id\n"
                + "JOIN theaters t ON t.theater_id = r.theater_id\n"
                + "JOIN seats se ON se.room_id = r.room_id\n"
                + "WHERE m.movie_id = ?\n"
                + "    AND CONVERT(DATE, s.showtime) = ?\n"
                + "    AND DATEPART(HOUR, s.showtime) = DATEPART(HOUR, ?)\n"
                + "    AND DATEPART(MINUTE, s.showtime) = DATEPART(MINUTE, ?)\n"
                + "    AND t.theater_id = ?\n"
                + "    AND r.room_id = ?\n"
                + "    AND se.seat_row = 'B';";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, mid);
            st.setInt(5, branchId);
            st.setDate(2, startDate);

            Timestamp startTimestamp = new Timestamp(startTime.getTime());
            st.setTimestamp(3, startTimestamp);
            st.setTimestamp(4, startTimestamp);
            st.setInt(6, roomId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int seat_id = rs.getInt("seat_id");
                    int seat_number = rs.getInt("seat_number");
                    String seat_row = rs.getString("seat_row");
                    String status = rs.getString("status");

                    Seats room = new Seats();

                    room.setSeat_id(seat_id);
                    room.setSeat_number(seat_number);
                    room.setSeat_row(seat_row);
                    room.setStatus(status);
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

    public List<Seats> getAllSeatC(int mid, int branchId, Date startDate, Time startTime, int roomId) {
        List<Seats> movieList = new ArrayList<>();

        // Câu SQL: So sánh `DATE` và kiểm tra `TIME` bằng cách lấy HOUR + MINUTE
        String sql = "SELECT \n"
                + "    se.seat_id, \n"
                + "    se.seat_number, \n"
                + "    se.seat_row, \n"
                + "    se.status \n"
                + "FROM movies m \n"
                + "JOIN showtimes s ON m.movie_id = s.movie_id\n"
                + "JOIN rooms r ON r.room_id = s.room_id\n"
                + "JOIN theaters t ON t.theater_id = r.theater_id\n"
                + "JOIN seats se ON se.room_id = r.room_id\n"
                + "WHERE m.movie_id = ?\n"
                + "    AND CONVERT(DATE, s.showtime) = ?\n"
                + "    AND DATEPART(HOUR, s.showtime) = DATEPART(HOUR, ?)\n"
                + "    AND DATEPART(MINUTE, s.showtime) = DATEPART(MINUTE, ?)\n"
                + "    AND t.theater_id = ?\n"
                + "    AND r.room_id = ?\n"
                + "    AND se.seat_row = 'C';";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, mid);
            st.setInt(5, branchId);
            st.setDate(2, startDate);

            Timestamp startTimestamp = new Timestamp(startTime.getTime());
            st.setTimestamp(3, startTimestamp);
            st.setTimestamp(4, startTimestamp);
            st.setInt(6, roomId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                  int seat_id = rs.getInt("seat_id");
                    int seat_number = rs.getInt("seat_number");
                    String seat_row = rs.getString("seat_row");
                    String status = rs.getString("status");

                    Seats room = new Seats();

                    room.setSeat_id(seat_id);
                    room.setSeat_number(seat_number);
                    room.setSeat_row(seat_row);
                    room.setStatus(status);
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

    public List<Seats> getAllSeatD(int mid, int branchId, Date startDate, Time startTime, int roomId) {
        List<Seats> movieList = new ArrayList<>();

        // Câu SQL: So sánh `DATE` và kiểm tra `TIME` bằng cách lấy HOUR + MINUTE
        String sql = "SELECT \n"
                + "    se.seat_id, \n"
                + "    se.seat_number, \n"
                + "    se.seat_row, \n"
                + "    se.status \n"
                + "FROM movies m \n"
                + "JOIN showtimes s ON m.movie_id = s.movie_id\n"
                + "JOIN rooms r ON r.room_id = s.room_id\n"
                + "JOIN theaters t ON t.theater_id = r.theater_id\n"
                + "JOIN seats se ON se.room_id = r.room_id\n"
                + "WHERE m.movie_id = ?\n"
                + "    AND CONVERT(DATE, s.showtime) = ?\n"
                + "    AND DATEPART(HOUR, s.showtime) = DATEPART(HOUR, ?)\n"
                + "    AND DATEPART(MINUTE, s.showtime) = DATEPART(MINUTE, ?)\n"
                + "    AND t.theater_id = ?\n"
                + "    AND r.room_id = ?\n"
                + "    AND se.seat_row = 'D';";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, mid);
            st.setInt(5, branchId);
            st.setDate(2, startDate);

            Timestamp startTimestamp = new Timestamp(startTime.getTime());
            st.setTimestamp(3, startTimestamp);
            st.setTimestamp(4, startTimestamp);
            st.setInt(6, roomId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
           int seat_id = rs.getInt("seat_id");
                    int seat_number = rs.getInt("seat_number");
                    String seat_row = rs.getString("seat_row");
                    String status = rs.getString("status");

                    Seats room = new Seats();

                    room.setSeat_id(seat_id);
                    room.setSeat_number(seat_number);
                    room.setSeat_row(seat_row);
                    room.setStatus(status);
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

    public List<Seats> getAllSeatE(int mid, int branchId, Date startDate, Time startTime, int roomId) {
        List<Seats> movieList = new ArrayList<>();

        // Câu SQL: So sánh `DATE` và kiểm tra `TIME` bằng cách lấy HOUR + MINUTE
        String sql = "SELECT \n"
                + "    se.seat_id, \n"
                + "    se.seat_number, \n"
                + "    se.seat_row, \n"
                + "    se.status \n"
                + "FROM movies m \n"
                + "JOIN showtimes s ON m.movie_id = s.movie_id\n"
                + "JOIN rooms r ON r.room_id = s.room_id\n"
                + "JOIN theaters t ON t.theater_id = r.theater_id\n"
                + "JOIN seats se ON se.room_id = r.room_id\n"
                + "WHERE m.movie_id = ?\n"
                + "    AND CONVERT(DATE, s.showtime) = ?\n"
                + "    AND DATEPART(HOUR, s.showtime) = DATEPART(HOUR, ?)\n"
                + "    AND DATEPART(MINUTE, s.showtime) = DATEPART(MINUTE, ?)\n"
                + "    AND t.theater_id = ?\n"
                + "    AND r.room_id = ?\n"
                + "    AND se.seat_row = 'E';";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, mid);
            st.setInt(5, branchId);
            st.setDate(2, startDate);

            Timestamp startTimestamp = new Timestamp(startTime.getTime());
            st.setTimestamp(3, startTimestamp);
            st.setTimestamp(4, startTimestamp);
            st.setInt(6, roomId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                  int seat_id = rs.getInt("seat_id");
                    int seat_number = rs.getInt("seat_number");
                    String seat_row = rs.getString("seat_row");
                    String status = rs.getString("status");

                    Seats room = new Seats();

                    room.setSeat_id(seat_id);
                    room.setSeat_number(seat_number);
                    room.setSeat_row(seat_row);
                    room.setStatus(status);
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

}
