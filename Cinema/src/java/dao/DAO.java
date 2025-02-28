/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.cities;
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
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getString(10));
                return a;
            }
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
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
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getString(10));
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
                        rs.getString(3),
                        rs.getString(4)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<cities> getAllCity() {
        List<cities> listC = new ArrayList<>();
        String query = "select top 5 c.*,count(t.theater_id) as numOfTheater \n" 
                + "from cities c left join theaters t \n"
                + "on t.city_id=c.city_id group by c.city_id,c.city_name";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listC.add(new cities(rs.getInt(1),
                        rs.getString(2),rs.getInt("numOfTheater")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listC;
    }

    public void deleteTheater(String theaterid) {
        String query = "DELETE FROM booking_items \n"
                + "WHERE ticket_id IN (\n"
                + "    SELECT ticket_id FROM tickets \n"
                + "    WHERE showtime_id IN (\n"
                + "        SELECT showtime_id FROM showtimes \n"
                + "        WHERE room_id IN (\n"
                + "            SELECT room_id FROM rooms WHERE theater_id = ?\n"
                + "        )\n"
                + "    )\n"
                + ");\n"
                + "DELETE FROM tickets \n"
                + "WHERE showtime_id IN (\n"
                + "    SELECT showtime_id FROM showtimes \n"
                + "    WHERE room_id IN (\n"
                + "        SELECT room_id FROM rooms WHERE theater_id = ?\n"
                + "    )\n"
                + ");\n"
                + "DELETE FROM showtimes \n"
                + "WHERE room_id IN (\n"
                + "    SELECT room_id FROM rooms WHERE theater_id = ?\n"
                + ");\n"
                + "DELETE FROM seats \n"
                + "WHERE room_id IN (\n"
                + "    SELECT room_id FROM rooms WHERE theater_id = ?\n"
                + ");\n"
                + "DELETE FROM rooms WHERE theater_id = ?;\n"
                + "DELETE FROM theaters WHERE theater_id = ?; ";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, theaterid);
            ps.setString(2, theaterid);
            ps.setString(3, theaterid);
            ps.setString(4, theaterid);
            ps.setString(5, theaterid);
            ps.setString(6, theaterid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void insertTheater( String city,String name, String address) {
        String query = "INSERT INTO theaters (city_id, theater_name, address)\n" +
                        "VALUES (?,?,?)";
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
        String query = "select * from theaters\n" +
                       "where theater_id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Theater(rs.getInt(1),
                        rs.getInt(2), 
                        rs.getString(3),
                        rs.getString(4));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public void editTheater(String theaterid, String city, String name, String address) {
        String query = "UPDATE theaters\n" +
                       "SET city_id = ?,\n" +
                       "theater_name = ?,\n" +
                       "address = ?\n" +
                       "WHERE theater_id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, city);
            ps.setString(2, name);
            ps.setString(3, address);
            ps.setString(4, theaterid);
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
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getString(10));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public ResultSet select(String sql){
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            return ps.executeQuery();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
