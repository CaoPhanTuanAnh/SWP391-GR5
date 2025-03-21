/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.cities;
import entity.theaters;
import entity.users;
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

    public users login(String user, String pass) {
        try {
            String query = "select * from users where username = ? and password = ?";
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                users a = new users(rs.getInt(1),
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

    public users checkUserExist(String user) {
        String query = "select * from users\n"
                + "where username = ?\n";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
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
        }
        return null;
    }

    public boolean checkExists(String column, String value) {
    String query = "SELECT 1 FROM users WHERE " + column + " = ?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, value);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}


    public List<theaters> getAllTheater() {
        List<theaters> list = new ArrayList<>();
        String query = "select * from theaters";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new theaters(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)));
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
                        rs.getString(2), rs.getInt("numOfTheater")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listC;
    }

    public List<users> getAllUser() {
        List<users> listU = new ArrayList<>();
        String query = "select * from users";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listU.add(new users(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getString(10)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listU;
    }

    public List<users> getUsersByPage(int start, int total) {
        List<users> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            String query = "SELECT * FROM users ORDER BY user_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            ps = conn.prepareStatement(query);
            ps.setInt(1, start);
            ps.setInt(2, total);
            System.out.println("Executing query: " + query + " with start=" + start + " and total=" + total);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new users(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getString(10)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalUserCount() {
        int count = 0;
        try {
            conn = new DBContext().getConnection();
            String query = "SELECT COUNT(*) FROM users";
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<users> searchUsers(String keyword, int start, int total) {
        List<users> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            String query = "SELECT * FROM users "
                    + "WHERE full_name LIKE ? "
                    + "OR email LIKE ? "
                    + "OR phone LIKE ? "
                    + "ORDER BY user_id "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            ps = conn.prepareStatement(query);
            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);
            ps.setInt(4, start);
            ps.setInt(5, total);

            System.out.println("Executing query: " + query + " with keyword = " + keyword);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new users(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getString(10)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalSearchCount(String keyword) {
        int count = 0;
        try {
            conn = new DBContext().getConnection();
            String query = "SELECT COUNT(*) FROM users "
                    + "WHERE full_name LIKE ? "
                    + "OR email LIKE ? "
                    + "OR phone LIKE ?";
            ps = conn.prepareStatement(query);
            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public void deleteTheater(String theaterid) {
        String query = "DELETE FROM [dbo].[theaters] WHERE theater_id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, theaterid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertTheater(String city, String manager, String name, String address) {
        String query = "INSERT INTO [dbo].[theaters] (city_id, director_id, theater_name, img, [address])\n"
                + "VALUES \n"
                + "(?, ?, ?, NULL, ?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, city);
            ps.setString(2, manager);
            ps.setString(3, name);
            ps.setString(4, address);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertAccount(String role_id, String username, String password, String full_name, String email, String phone, String birth_date) {
        String query = "INSERT INTO [dbo].[users] (role_id, username, password, full_name, email, phone, birth_date, theater_id, status)  \n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, NULL, 'Active');";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, role_id);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, full_name);
            ps.setString(5, email);
            ps.setString(6, phone);
            ps.setString(7, birth_date);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public theaters getTheaterByID(String id) {
        String query = "select * from theaters\n"
                + "where theater_id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new theaters(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
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

    public void editAccount(String user_id, String fullname, String email, String phone, String role_id, String status) {
        String query = "UPDATE [dbo].[users]\n"
                + "SET \n"
                + "    full_name = ?, \n"
                + "    email = ?, \n"
                + "    phone = ?, \n"
                + "    role_id = ?, \n"
                + "    status = ?\n"
                + "WHERE user_id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, fullname);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, role_id);
            ps.setString(5, status);
            ps.setString(6, user_id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public users getUserByEmail(String email) {
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
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public users getUserById(String id) {
        String query = "Select * from users where user_id = ?";
        try {
            try {
                conn = new DBContext().getConnection();//mo ket noi voi sql
            } catch (Exception ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public users checkEmailExist(String email) {
        String query = "select * from users\n"
                + "where email = ?\n";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
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

    public static void main(String[] args) {
        System.out.println("dpopodp");
    }

}
