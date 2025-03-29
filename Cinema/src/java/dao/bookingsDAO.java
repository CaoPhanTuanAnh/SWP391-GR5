package dao;

import context.DBContext;
import entity.bookings;
import entity.user_bookings;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class bookingsDAO {

    Connection conn = null; // kết nối vs sql
    PreparedStatement ps = null; // ném query sang sql
    ResultSet rs = null; // nhận kết quả trả về

    public List<user_bookings> listUserBooking(int user_id) {
        ArrayList<user_bookings> userBookingList = new ArrayList<>();
        String query = "select distinct b.*, t.*\n"
                + "from bookings b \n"
                + "left join (select distinct tk.booking_id, th.theater_name, r.room_name, st.showtime, m.title, m.poster_url from tickets tk \n"
                + "	join showtimes st on st.showtime_id = tk.showtime_id\n"
                + "	join rooms r on r.room_id = st.room_id\n"
                + "	join theaters th on th.theater_id = r.theater_id\n"
                + "	join movies m on m.movie_id = st.movie_id\n"
                + ") as t on t.booking_id = b.booking_id\n"
                + "left join booking_combos bc on bc.booking_id = b.booking_id\n"
                + "where b.user_id = ?\n"
                + "order by booking_date desc";
//        String query_seat = "select s.seat_row,s.seat_number from tickets t\n"
//                + "join seats s on s.seat_id = t.seat_id\n"
//                + "where t.booking_id = ?";
//        String query_combo = "select c.combo_name,bc.quantity from booking_combos bc\n"
//                + "join combos c on c.combo_id = bc.combo_id\n"
//                + "where bc.booking_id=?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
//                int num_of_tickets = 0;
//                String seats_info = "";
//                int num_of_combos = 0;
//                String combos_info = "";
//                PreparedStatement ps1 = conn.prepareStatement(query_seat);
//                PreparedStatement ps2 = conn.prepareStatement(query_combo);
//                ps1.setInt(1, rs.getInt("booking_id"));
//                ps2.setInt(1, rs.getInt("booking_id"));
//                ResultSet rs1 = ps1.executeQuery();
//                ResultSet rs2 = ps2.executeQuery();
//                while (rs1.next()) {
//                    seats_info += rs1.getString(1) + rs1.getString(2) + ", ";
//                    num_of_tickets++;
//                }
//                while (rs2.next()) {
//                    combos_info += rs2.getString(1) + " x " + rs2.getInt(2) + ", ";
//                    num_of_combos += rs2.getInt(2);
//                }
                user_bookings user_bookings;
                user_bookings = new user_bookings(
                        rs.getString("title"),
                        rs.getString("poster_url"),
                        rs.getString("showtime"),
                        rs.getString("theater_name"),
                        rs.getString("room_name"),
                        rs.getInt("booking_id"),
                        rs.getInt("user_id"),
                        rs.getString("booking_date"),
                        rs.getDouble("sub_total_amount"),
                        rs.getInt("coupon_id"),
                        rs.getDouble("total_amount"),
                        rs.getString("status"));
                userBookingList.add(user_bookings);
            }
            return userBookingList;
        } catch (Exception ex) {
            Logger.getLogger(bookingsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userBookingList;
    }

    public user_bookings viewBookingDetail(int booking_id) {
        String query = "select distinct b.*, t.*, cp.coupon_code, cp.discount_percentage\n"
                + "from bookings b \n"
                + "left join (select distinct tk.booking_id, th.theater_name, r.room_name, st.showtime_id, st.showtime, m.title, m.poster_url from tickets tk \n"
                + "	join showtimes st on st.showtime_id = tk.showtime_id\n"
                + "	join rooms r on r.room_id = st.room_id\n"
                + "	join theaters th on th.theater_id = r.theater_id\n"
                + "	join movies m on m.movie_id = st.movie_id\n"
                + ") as t on t.booking_id = b.booking_id\n"
                + "left join booking_combos bc on bc.booking_id = b.booking_id\n"
                + "left join coupons cp on cp.coupon_id = b.coupon_id\n"
                + "where b.booking_id = ?\n";
        String query_seat = "select s.seat_row,s.seat_number from tickets t\n"
                + "join seats s on s.seat_id = t.seat_id\n"
                + "where t.booking_id = ?";
        String query_combo = "select c.combo_name,bc.quantity from booking_combos bc\n"
                + "join combos c on c.combo_id = bc.combo_id\n"
                + "where bc.booking_id=?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, booking_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int num_of_tickets = 0;
                String seats_info = "";
                int num_of_combos = 0;
                String combos_info = "";
                PreparedStatement ps1 = conn.prepareStatement(query_seat);
                PreparedStatement ps2 = conn.prepareStatement(query_combo);
                ps1.setInt(1, rs.getInt("booking_id"));
                ps2.setInt(1, rs.getInt("booking_id"));
                ResultSet rs1 = ps1.executeQuery();
                ResultSet rs2 = ps2.executeQuery();
                boolean first = true;
                while (rs1.next()) {
                    if (first) {
                        seats_info += rs1.getString(1) + rs1.getString(2);
                        first = false;
                    } else {
                        seats_info += ", " + rs1.getString(1) + rs1.getString(2);
                    }
                    num_of_tickets++;
                }
                first = true;
                while (rs2.next()) {
                    if (first) {
                        combos_info += rs2.getString(1) + " x " + rs2.getInt(2);
                        first = false;
                    } else {
                        combos_info += ", " + rs2.getString(1) + " x " + rs2.getInt(2);
                    }
                    num_of_combos += rs2.getInt(2);
                }
                user_bookings user_bookings;
                user_bookings = new user_bookings(num_of_tickets,
                        seats_info,
                        num_of_combos,
                        combos_info,
                        rs.getString("coupon_code"),
                        rs.getDouble("discount_percentage"),
                        rs.getString("title"),
                        rs.getString("poster_url"),
                        rs.getInt("showtime_id"),
                        rs.getString("showtime"),
                        rs.getString("theater_name"),
                        rs.getString("room_name"),
                        rs.getInt("booking_id"),
                        rs.getInt("user_id"),
                        rs.getString("booking_date"),
                        rs.getDouble("sub_total_amount"),
                        rs.getInt("coupon_id"),
                        rs.getDouble("total_amount"),
                        rs.getString("status"));
                return user_bookings;
            }
        } catch (Exception ex) {
            Logger.getLogger(bookingsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
 public static bookings createBooking(int user_id, String date, double totalAmount, String status) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    bookings booking = null;
    LocalDate now = LocalDate.now();

    String query = "INSERT INTO bookings (user_id, booking_date, total_amount, status) VALUES (?, ?, ?, ?)";
    
    try {
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        
        ps.setInt(1, user_id);
        ps.setString(2, now.toString());
        ps.setDouble(3, totalAmount);
        ps.setString(4, status);

        int affectedRows = ps.executeUpdate();
        
        if (affectedRows > 0) {
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int booking_id = rs.getInt(1); // Lấy booking_id vừa tạo
                booking = new bookings(booking_id, user_id, now.toString(), 0.0, 0, totalAmount, status); 
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    return booking;
}
 public void updateBookingStatus(int bookingId, String status) {
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE bookings SET status = ? WHERE booking_id = ?";
        
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2, bookingId);
            ps.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(bookingsDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                Logger.getLogger(bookingsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
