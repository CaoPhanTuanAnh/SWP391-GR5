/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.City;
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
public class CityDAO {
    Connection conn = null; // kết nối vs sql
    PreparedStatement ps = null; // ném query sang sql
    ResultSet rs = null; // nhận kết quả trả về
    
    public List<City> listCity(){
        ArrayList<City> cityList = new ArrayList<>();
        String query = "select c.*,count(t.theater_id) as numOfTheater "
                + "from cities c left join theaters t "
                + "on t.city_id=c.city_id group by c.city_id,c.city_name";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                City city = new City(rs.getInt("city_id"),rs.getString("city_name"),rs.getInt("numOfTheater"));
                cityList.add(city);
            }
            return cityList;
        } catch (Exception ex) {
            Logger.getLogger(CityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cityList;
    }
    
    public boolean addCity(String cityName){
        String query = "insert into cities(city_name) values (?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, cityName);
            int check = ps.executeUpdate();
            if(check==1)return true;
        } catch (Exception ex) {
            Logger.getLogger(CityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean deleteCity(int cityID){
        String query = "delete from cities where city_id=?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, cityID);
            int check = ps.executeUpdate();
            if(check==1)return true;
        } catch (Exception ex) {
            Logger.getLogger(CityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean editCity(int cityID, String cityName){
        String query = "update cities set city_name=? where city_id=?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, cityName);
            ps.setInt(2, cityID);
            int check = ps.executeUpdate();
            if(check==1)return true;
        } catch (Exception ex) {
            Logger.getLogger(CityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
