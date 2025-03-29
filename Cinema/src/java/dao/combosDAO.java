/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.combos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class combosDAO {
    
    Connection conn = null; // kết nối vs sql
    PreparedStatement ps = null; // ném query sang sql
    ResultSet rs = null; // nhận kết quả trả về
    
    public List<combos> listCombo(){
        ArrayList<combos> comboList = new ArrayList<>();
        String query = "select c.* from combos c ";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                combos combo = new combos(rs.getInt("combo_id"),rs.getString("combo_name"),rs.getString("detail"),rs.getDouble("combo_price"));
                comboList.add(combo);
            }
            return comboList;
        } catch (Exception ex) {
            Logger.getLogger(combosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comboList;
    }
    
    public boolean addCombo(String comboName, String detail, double comboPrice){
        String query = "INSERT INTO combos (combo_name, detail, combo_price) \n" + "VALUES (?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, comboName);
            ps.setString(2, detail);
            ps.setDouble(3, comboPrice);
            int check = ps.executeUpdate();
            if(check==1)return true;
        } catch (Exception ex) {
            Logger.getLogger(combosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean deleteCombo(int comboID){
        String query = "delete from combos where combo_id=?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, comboID);
            int check = ps.executeUpdate();
            if(check==1)return true;
        } catch (Exception ex) {
            Logger.getLogger(combosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean editCombo(int comboID, String comboName, String detail, double comboPrice){
        String query = "update combos set combo_name=?, detail = ?, combo_price = ? where combo_id=?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, comboName);
            ps.setString(2,detail);
            ps.setDouble(3, comboPrice);
            ps.setInt(4, comboID);
            int check = ps.executeUpdate();
            if(check==1)return true;
        } catch (Exception ex) {
            Logger.getLogger(combosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int addBookingCombo(int booking_id, int combo_id, int quantity){
        String query = "insert into booking_combos values(?,?,?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, booking_id);
            ps.setInt(2,combo_id);
            ps.setInt(3, quantity);
            return ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(combosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
