/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PCASUS
 */
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

}
