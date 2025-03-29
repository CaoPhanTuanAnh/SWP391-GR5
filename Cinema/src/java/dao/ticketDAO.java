package dao;

import context.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ticketDAO extends DBContext {

    public static void createTicket(int showtime_id, int seat, int user_id, int booking_id, double price, String status) {
        Connection conn = null;
        PreparedStatement ps = null;

        String query = "INSERT INTO tickets (showtime_id, seat_id, user_id, booking_id, price, status) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);

            ps.setInt(1, showtime_id);
            ps.setInt(2, seat);  // Đảm bảo seat là số nguyên
            ps.setInt(3, user_id);
            ps.setInt(4, booking_id);
            ps.setDouble(5, price);
            ps.setString(6, status);

            ps.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(bookingsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(bookingsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
// Phương thức mới: Lấy danh sách seatId theo bookingId
    public List<String> getSeatIdsByBookingId(int bookingId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> seatIds = new ArrayList<>();
        String query = "SELECT seat_id FROM tickets WHERE booking_id = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, bookingId);
            rs = ps.executeQuery();
            while (rs.next()) {
                seatIds.add(rs.getString("seat_id")); // Chuyển seat_id thành String nếu cần
            }
        } catch (Exception ex) {
            Logger.getLogger(ticketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                Logger.getLogger(ticketDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return seatIds;
    }

    // Phương thức mới: Cập nhật trạng thái ticket
    public void updateTicketStatus(int bookingId, String seatId, String status) {
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE tickets SET status = ? WHERE booking_id = ? AND seat_id = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2, bookingId);
            ps.setString(3, seatId); // seatId là String để đồng bộ với seatsDAO
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ticketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                Logger.getLogger(ticketDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public List<Integer> getticket(String date,String time,int seat_id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String datetime = date+" "+time;
        List<Integer> ticketIds = new ArrayList<>();
        String query = "SELECT t.ticket_id FROM tickets t join showtimes s on t.showtime_id=s.showtime_id WHERE s.showtime = ? and seat_id=?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, datetime);
            ps.setInt(2, seat_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ticketIds.add(rs.getInt("ticket_id")); // Chuyển seat_id thành String nếu cần
            }
        } catch (Exception ex) {
            Logger.getLogger(ticketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                Logger.getLogger(ticketDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ticketIds;
    }
    
    
    public void updateTicket(int booking_id,int ticket_id) {
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE tickets SET status = 'Booked', booking_id=? WHERE ticket_id=?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, booking_id);
            ps.setInt(2, ticket_id);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ticketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                Logger.getLogger(ticketDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void backTicket(int booking_id) {
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE tickets SET status = 'Available' WHERE booking_id=?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, booking_id);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ticketDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                Logger.getLogger(ticketDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
