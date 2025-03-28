/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class usersDAO extends DBContext {

    Connection conn = null; // kết nối vs sql
    PreparedStatement ps = null; // ném query sang sql
    ResultSet rs = null; // nhận kết quả trả về
    

    public users getAccountByUser(String username, String password) {
        String query = "select * from users where username = ? AND password = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new users(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getString(10));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean changePassword(int ID, String newPassword) {
        String query = "update users set password=? where user_id=?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, newPassword);
            ps.setInt(2, ID);
            return ps.executeUpdate() == 1;
        } catch (Exception ex) {
            Logger.getLogger(usersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean editProfile(int ID, String fullName, String phone, String birth_date, int theater_id) {
        String query = "update users set full_name=?, phone=?, birth_date=?, theater_id=? where user_id=?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, fullName);
            ps.setString(2, phone);
            ps.setString(3, birth_date);
            ps.setInt(4, theater_id);
            ps.setInt(5, ID);
            return ps.executeUpdate() == 1;
        } catch (Exception ex) {
            Logger.getLogger(usersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<users> getAllUser() {
        List<users> users = new ArrayList<>();
        String query = "SELECT user_id, role_id, username, [full_name], email, phone, address FROM users";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                users.add(new users(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getString(10))
                );
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách người dùng: " + e.getMessage());
        }
        return users;
    }

    public users getAccountById(int id) {
        String query = "SELECT user_id, role_id, username, full_name, email, phone, address FROM users WHERE user_id = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new users(rs.getInt(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getInt(9),
                            rs.getString(10));
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy tài khoản theo ID: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        usersDAO userDao = new usersDAO();
        users users = userDao.getAccountById(1);
        System.out.println(users);
    }

    public boolean createUser(users user) {
        String query = "INSERT INTO users (role_id, username, full_name, email, phone, birth_date, password) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, user.getRole_id());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getFullname());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getBirth_date());
            ps.setString(7, user.getPassword());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            System.out.println("Lỗi khi tạo người dùng: " + e.getMessage());
        }
        return false;
    }

    public boolean updateUserInDatabase(String fullname, String email, String phone, String address) {
        String query = "UPDATE users SET full_name = ?, phone = ?, address = ? WHERE email = ?";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, fullname);
            ps.setString(2, phone);
            ps.setString(3, address);
            ps.setString(4, email);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật người dùng: " + e.getMessage());
        }
        return false;
    }
    
    public boolean updatePassword(String email, String password) {
        String query = "update users set password=? where email = ?";
        try {
            try {
                conn = new DBContext().getConnection();//mo ket noi voi sql
                ps = conn.prepareStatement(query);
                
                ps.setString(1, password);
                ps.setString(2, email);
                return ps.executeUpdate() == 1;
            } catch (Exception ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
