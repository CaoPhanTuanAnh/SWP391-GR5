/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.bookings;
import entity.user_bookings;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GIGABYTE
 */
public class bookingsDAO {

    Connection conn = null; // kết nối vs sql
    PreparedStatement ps = null; // ném query sang sql
    ResultSet rs = null; // nhận kết quả trả về

    public List<user_bookings> listUserBooking(int user_id) {
        ArrayList<user_bookings> userBookingList = new ArrayList<>();
        String query = "select b.*, t.title, count(t.ticket_id) as num_of_tickets, count(bc.combo_id) as num_of_combos\n"
                + "from bookings b \n"
                + "left join (select tk.*, m.title from tickets tk \n"
                + "	join showtimes st on st.showtime_id = tk.showtime_id\n"
                + "	join movies m on m.movie_id = st.movie_id) as t on t.booking_id = b.booking_id \n"
                + "left join booking_combos bc on bc.booking_id = b.booking_id\n"
                + "where b.user_id = ? \n"
                + "group by b.booking_id,b.booking_date,b.coupon_id,b.status,b.sub_total_amount,b.total_amount,b.user_id, t.title\n"
                + "order by booking_date";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                user_bookings user_bookings;
                user_bookings = new user_bookings(rs.getInt("num_of_tickets"), rs.getInt("num_of_combos"),rs.getString("title"), rs.getInt("booking_id"), rs.getInt("user_id"), rs.getString("booking_date"), rs.getDouble("sub_total_amount"), rs.getInt("coupon_id"), rs.getDouble("total_amount"), rs.getString("status"));
                userBookingList.add(user_bookings);
            }
            return userBookingList;
        } catch (Exception ex) {
            Logger.getLogger(citiesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userBookingList;
    }
}
