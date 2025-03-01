/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.City;
import entity.Theater;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {

    Connection conn = null; // kết nối vs sql
    PreparedStatement ps = null; // ném query sang sql
    ResultSet rs = null; // nhận kết quả trả về

    public User login(String user, String pass) {
        try {
            String query = "select * from users where username = ? and password = ?";
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                User a = new User(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8));
                return a;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void signup(String username, String password, String fullName, String email, String phone, String address) {
        String query = "INSERT INTO users (username, password, role_id, full_name, email, phone, address) "
                + "VALUES (?, ?, 3, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection(); // Mở kết nối với SQL Server
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, fullName);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, address);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User checkUserExist(String user) {
        String query = "select * from users\n"
                + "where username = ?\n";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Theater> getAllTheater() {
        List<Theater> list = new ArrayList<>();
        String query = "select * from theaters";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Theater(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<City> getAllCity() {
        List<City> listC = new ArrayList<>();
        String query = "select top 5 c.*,count(t.theater_id) as numOfTheater \n"
                + "from cities c left join theaters t \n"
                + "on t.city_id=c.city_id group by c.city_id,c.city_name";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listC.add(new City(rs.getInt(1),
                        rs.getString(2), rs.getInt("numOfTheater")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listC;
    }

    public List<User> getAllUser() {
        List<User> listU = new ArrayList<>();
        String query = "select * from users";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listU.add(new User(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listU;
    }

    public void deleteTheater(String theaterid) {
        String query = "ALTER TABLE reviews NOCHECK CONSTRAINT FK__reviews__ticket___787EE5A0;\n"
                + "ALTER TABLE tickets NOCHECK CONSTRAINT FK__tickets__seat_id__71D1E811;\n"
                + "ALTER TABLE tickets NOCHECK CONSTRAINT FK__tickets__showtim__70DDC3D8;\n"
                + "ALTER TABLE theaters NOCHECK CONSTRAINT FK__theaters__direct__45F365D3;\n"
                + "ALTER TABLE seats NOCHECK CONSTRAINT FK__seats__room_id__5441852A;\n"
                + "ALTER TABLE users NOCHECK CONSTRAINT FK__users__theater_i__440B1D61;\n"
                + "ALTER TABLE rooms NOCHECK CONSTRAINT FK__rooms__manager_i__4CA06362;\n"
                + "ALTER TABLE showtimes NOCHECK CONSTRAINT FK__showtimes__room___6383C8BA;\n"
                + "ALTER TABLE coupons NOCHECK CONSTRAINT FK__coupons__user_id__68487DD7;\n"
                + "ALTER TABLE bookings NOCHECK CONSTRAINT FK__bookings__coupon__6D0D32F4;\n"
                + "ALTER TABLE bookings NOCHECK CONSTRAINT FK__bookings__user_i__6B24EA82;\n"
                + "ALTER TABLE tickets NOCHECK CONSTRAINT FK__tickets__booking__73BA3083;\n"
                + "ALTER TABLE reviews NOCHECK CONSTRAINT FK__reviews__user_id__778AC167;\n"
                + "BEGIN TRANSACTION;\n"
                + "DELETE FROM reviews\n"
                + "WHERE ticket_id IN (\n"
                + "    SELECT ticket_id\n"
                + "    FROM tickets\n"
                + "    WHERE showtime_id IN (\n"
                + "        SELECT showtime_id\n"
                + "        FROM showtimes\n"
                + "        WHERE room_id IN (\n"
                + "            SELECT room_id\n"
                + "            FROM rooms\n"
                + "            WHERE theater_id = ?\n"
                + "        )\n"
                + "    )\n"
                + ") OR user_id IN (\n"
                + "    SELECT user_id\n"
                + "    FROM users\n"
                + "    WHERE theater_id = ?\n"
                + ");\n"
                + "DELETE FROM tickets\n"
                + "WHERE seat_id IN (\n"
                + "    SELECT seat_id\n"
                + "    FROM seats\n"
                + "    WHERE room_id IN (\n"
                + "        SELECT room_id\n"
                + "        FROM rooms\n"
                + "        WHERE theater_id = ?\n"
                + "    )\n"
                + ") OR showtime_id IN (\n"
                + "    SELECT showtime_id\n"
                + "    FROM showtimes\n"
                + "    WHERE room_id IN (\n"
                + "        SELECT room_id\n"
                + "        FROM rooms\n"
                + "        WHERE theater_id = ?\n"
                + "    )\n"
                + ") OR booking_id IN (\n"
                + "    SELECT booking_id\n"
                + "    FROM bookings\n"
                + "    WHERE user_id IN (\n"
                + "        SELECT user_id\n"
                + "        FROM users\n"
                + "        WHERE theater_id = ?\n"
                + "    )\n"
                + ");\n"
                + "DELETE FROM bookings\n"
                + "WHERE coupon_id IN (\n"
                + "    SELECT coupon_id\n"
                + "    FROM coupons\n"
                + "    WHERE user_id IN (\n"
                + "        SELECT user_id\n"
                + "        FROM users\n"
                + "        WHERE theater_id = ?\n"
                + "    )\n"
                + ") OR user_id IN (\n"
                + "    SELECT user_id\n"
                + "    FROM users\n"
                + "    WHERE theater_id = ?\n"
                + ");\n"
                + "DELETE FROM coupons\n"
                + "WHERE user_id IN (\n"
                + "    SELECT user_id\n"
                + "    FROM users\n"
                + "    WHERE theater_id = ?\n"
                + ");\n"
                + "DELETE FROM users\n"
                + "WHERE theater_id = ?;\n"
                + "DELETE FROM showtimes\n"
                + "WHERE room_id IN (\n"
                + "    SELECT room_id\n"
                + "    FROM rooms\n"
                + "    WHERE theater_id = ?\n"
                + ");\n"
                + "DELETE FROM rooms\n"
                + "WHERE theater_id = ?;\n"
                + "DELETE FROM theaters\n"
                + "WHERE theater_id = ?;\n"
                + "COMMIT;\n"
                + "ALTER TABLE reviews CHECK CONSTRAINT FK__reviews__ticket___787EE5A0;\n"
                + "ALTER TABLE tickets CHECK CONSTRAINT FK__tickets__seat_id__71D1E811;\n"
                + "ALTER TABLE tickets CHECK CONSTRAINT FK__tickets__showtim__70DDC3D8;\n"
                + "ALTER TABLE theaters CHECK CONSTRAINT FK__theaters__direct__45F365D3;\n"
                + "ALTER TABLE seats CHECK CONSTRAINT FK__seats__room_id__5441852A;\n"
                + "ALTER TABLE users CHECK CONSTRAINT FK__users__theater_i__440B1D61;\n"
                + "ALTER TABLE rooms CHECK CONSTRAINT FK__rooms__manager_i__4CA06362;\n"
                + "ALTER TABLE showtimes CHECK CONSTRAINT FK__showtimes__room___6383C8BA;\n"
                + "ALTER TABLE coupons CHECK CONSTRAINT FK__coupons__user_id__68487DD7;\n"
                + "ALTER TABLE bookings CHECK CONSTRAINT FK__bookings__coupon__6D0D32F4;\n"
                + "ALTER TABLE bookings CHECK CONSTRAINT FK__bookings__user_i__6B24EA82;\n"
                + "ALTER TABLE tickets CHECK CONSTRAINT FK__tickets__booking__73BA3083;\n"
                + "ALTER TABLE reviews CHECK CONSTRAINT FK__reviews__user_id__778AC167;";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, theaterid);
            ps.setString(2, theaterid);
            ps.setString(3, theaterid);
            ps.setString(4, theaterid);
            ps.setString(5, theaterid);
            ps.setString(6, theaterid);
            ps.setString(7, theaterid);
            ps.setString(8, theaterid);
            ps.setString(9, theaterid);
            ps.setString(10, theaterid);
            ps.setString(11, theaterid);
            ps.setString(12, theaterid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertTheater(String city, String name, String address) {
        String query = "INSERT INTO theaters (city_id, theater_name, address)\n"
                + "VALUES (?,?,?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, city);
            ps.setString(2, name);
            ps.setString(3, address);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Theater getTheaterByID(String id) {
        String query = "select * from theaters\n"
                + "where theater_id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Theater(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void editTheater(String theaterid, String city, String name, String address, String directorid) {
        String query = "UPDATE theaters\n"
                + "SET \n"
                + "    city_id = ?, \n"
                + "    director_id = ?,\n"
                + "    theater_name = ?,\n"
                + "    address = ?\n"
                + "WHERE theater_id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, city);
            ps.setString(2, directorid);
            ps.setString(3, name);
            ps.setString(4, address);
            ps.setString(5, theaterid);

            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public User getUserByEmail(String email) {
        String query = "Select * from users where email = ?";
        try {
            try {
                conn = new DBContext().getConnection();//mo ket noi voi sql
            } catch (Exception ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

}
