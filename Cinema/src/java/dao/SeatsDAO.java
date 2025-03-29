/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.seats;
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
public class seatsDAO extends DBContext {

    public List<seats> getAllSeatA(int mid, int branchId, Date startDate, Time startTime, int roomId, int showtimeID) {
        List<seats> movieList = new ArrayList<>();

        // Câu SQL: So sánh `DATE` và kiểm tra `TIME` bằng cách lấy HOUR + MINUTE
        String sql = "SELECT \n"
                + "se.seat_id, \n"
                + "se.seat_number, \n"
                + "se.seat_row, \n"
                + "t.status\n"
                + "FROM showtimes s \n"
                + "join tickets t on t.showtime_id=s.showtime_id\n"
                + "join seats se on se.seat_id=t.seat_id\n"
                + "where s.showtime_id=?\n"
                + "AND se.seat_row = 'A';";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, showtimeID);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int seat_id = rs.getInt("seat_id");
                    int seat_number = rs.getInt("seat_number");
                    String seat_row = rs.getString("seat_row");
                    String status = rs.getString("status");

                    seats room = new seats();

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
        seatsDAO seatsDAO = new seatsDAO();

        // Khai báo các tham số test
        int movieId = 4;  // Thay bằng ID phim thực tế trong DB
        int branchId = 4; // Thay bằng ID chi nhánh thực tế
        Date startDate = Date.valueOf("2024-10-18"); // Thay bằng ngày thực tế có suất chiếu
        Time startTime = Time.valueOf("21:00:00"); // Thay bằng giờ có suất chiếu thực tế
        int roomId = 4; // Thay bằng ID phòng thực tế

        // Gọi phương thức getAllSeatA
        List<seats> seatsList = seatsDAO.getAllSeatA(movieId, branchId, startDate, startTime, roomId,1);

        // Kiểm tra kết quả
        if (seatsList.isEmpty()) {
            System.out.println("Không tìm thấy ghế nào cho suất chiếu này.");
        } else {
            System.out.println("Danh sách ghế hàng A:");
            for (seats seat : seatsList) {
                System.out.println("ID: " + seat.getSeat_id()
                        + ", Số ghế: " + seat.getSeat_number()
                        + ", Hàng: " + seat.getSeat_row()
                        + ", Trạng thái: " + seat.getStatus());
            }
        }
    }

    public List<seats> getAllSeatB(int mid, int branchId, Date startDate, Time startTime, int roomId, int showtimeID) {
        List<seats> movieList = new ArrayList<>();

        // Câu SQL: So sánh `DATE` và kiểm tra `TIME` bằng cách lấy HOUR + MINUTE
        String sql = "SELECT \n"
                + "se.seat_id, \n"
                + "se.seat_number, \n"
                + "se.seat_row, \n"
                + "t.status\n"
                + "FROM showtimes s \n"
                + "join tickets t on t.showtime_id=s.showtime_id\n"
                + "join seats se on se.seat_id=t.seat_id\n"
                + "where s.showtime_id=?\n"
                + "AND se.seat_row = 'B';";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, showtimeID);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int seat_id = rs.getInt("seat_id");
                    int seat_number = rs.getInt("seat_number");
                    String seat_row = rs.getString("seat_row");
                    String status = rs.getString("status");

                    seats room = new seats();

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

    public List<seats> getAllSeatC(int mid, int branchId, Date startDate, Time startTime, int roomId, int showtimeID) {
        List<seats> movieList = new ArrayList<>();

        // Câu SQL: So sánh `DATE` và kiểm tra `TIME` bằng cách lấy HOUR + MINUTE
        String sql = "SELECT \n"
                + "se.seat_id, \n"
                + "se.seat_number, \n"
                + "se.seat_row, \n"
                + "t.status\n"
                + "FROM showtimes s \n"
                + "join tickets t on t.showtime_id=s.showtime_id\n"
                + "join seats se on se.seat_id=t.seat_id\n"
                + "where s.showtime_id=?\n"
                + "AND se.seat_row = 'C';";
        
        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, showtimeID);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int seat_id = rs.getInt("seat_id");
                    int seat_number = rs.getInt("seat_number");
                    String seat_row = rs.getString("seat_row");
                    String status = rs.getString("status");

                    seats room = new seats();

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

    public List<seats> getAllSeatD(int mid, int branchId, Date startDate, Time startTime, int roomId, int showtimeID) {
        List<seats> movieList = new ArrayList<>();

        // Câu SQL: So sánh `DATE` và kiểm tra `TIME` bằng cách lấy HOUR + MINUTE
        String sql = "SELECT \n"
                + "se.seat_id, \n"
                + "se.seat_number, \n"
                + "se.seat_row, \n"
                + "t.status\n"
                + "FROM showtimes s \n"
                + "join tickets t on t.showtime_id=s.showtime_id\n"
                + "join seats se on se.seat_id=t.seat_id\n"
                + "where s.showtime_id=?\n"
                + "AND se.seat_row = 'D';";
        
        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, showtimeID);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int seat_id = rs.getInt("seat_id");
                    int seat_number = rs.getInt("seat_number");
                    String seat_row = rs.getString("seat_row");
                    String status = rs.getString("status");

                    seats room = new seats();

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

    public List<seats> getAllSeatE(int mid, int branchId, Date startDate, Time startTime, int roomId, int showtimeID) {
        List<seats> movieList = new ArrayList<>();

        // Câu SQL: So sánh `DATE` và kiểm tra `TIME` bằng cách lấy HOUR + MINUTE
        String sql = "SELECT \n"
                + "se.seat_id, \n"
                + "se.seat_number, \n"
                + "se.seat_row, \n"
                + "t.status\n"
                + "FROM showtimes s \n"
                + "join tickets t on t.showtime_id=s.showtime_id\n"
                + "join seats se on se.seat_id=t.seat_id\n"
                + "where s.showtime_id=?\n"
                + "AND se.seat_row = 'E';";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, showtimeID);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int seat_id = rs.getInt("seat_id");
                    int seat_number = rs.getInt("seat_number");
                    String seat_row = rs.getString("seat_row");
                    String status = rs.getString("status");

                    seats room = new seats();

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

    Connection conn = null; // kết nối vs sql
    PreparedStatement ps = null; // ném query sang sql
    ResultSet rs = null; // nhận kết quả trả về

    public List<seats> getSeatsByRoom(String room_id) {
        List<seats> seats = new ArrayList<>();
        String query = "SELECT * FROM [dbo].[seats]\n"
                + "WHERE [room_id] = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, room_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                seats.add(new seats(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return seats;
    }

    public void updateSeatStatus(String seatId, String status) {
        String query = "UPDATE seats SET status = ? WHERE seat_id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, status);
            ps.setString(2, seatId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

}
