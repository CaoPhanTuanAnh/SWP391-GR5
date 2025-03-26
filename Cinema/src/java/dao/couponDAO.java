/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.coupon;
import java.util.List;

/**
 *
 * @author PCASUS
 */
import context.DBContext;
import entity.coupon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class couponDAO extends DBContext {

    Connection conn = null; // kết nối với database
    PreparedStatement ps = null; // thực thi truy vấn SQL
    ResultSet rs = null; // nhận kết quả trả về

    public List<coupon> getAllCoupon() {
        List<coupon> couponList = new ArrayList<>();
        String query = "SELECT * FROM coupons";

        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                coupon c = new coupon(
                        rs.getInt("coupon_id"),
                        rs.getString("coupon_code"),
                        rs.getInt("user_id"),
                        rs.getDouble("discount_percentage"),
                        rs.getDate("expiry_date")
                );
                couponList.add(c);
            }
        } catch (Exception ex) {
            Logger.getLogger(couponDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return couponList;
    }

    public void addCoupon(coupon c) {
        String query = "INSERT INTO coupons (coupon_code, user_id, discount_percentage, expiry_date) VALUES (?, ?, ?, ?)";

        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, c.getCoupon_code());
            ps.setInt(2, c.getUser_id());
            ps.setDouble(3, c.getDiscount_percentage());
            ps.setDate(4, c.getExpiry_date());

            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(couponDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean updateCoupon(coupon coupon) {
        String sql = "UPDATE coupons SET coupon_code = ?, discount_percentage = ?, expiry_date = ? WHERE coupon_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, coupon.getCoupon_code());
            ps.setDouble(2, coupon.getDiscount_percentage());
            ps.setDate(3, coupon.getExpiry_date());
            ps.setInt(4, coupon.getCoupon_id());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCoupon(int couponID) {
        String sql = "DELETE FROM coupons WHERE coupon_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, couponID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
