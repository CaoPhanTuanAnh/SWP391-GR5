/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import context.DBContext;
import entity.news;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author default
 */
public class NewsDAO extends DBContext {

    public List<news> getAllNews() throws Exception {
        List<news> list = new ArrayList<>();
        String sql = "SELECT * FROM posts ORDER BY created_date DESC";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new news(
                        rs.getInt("post_id"),
                        rs.getString("user_id"),
                        rs.getString("title"),
                        rs.getString("photo_url"),
                        rs.getString("content"),
                        rs.getDate("created_date"),
                        rs.getString("content_type")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean deleteNews(int postId) throws Exception {
        String sql = "DELETE FROM posts WHERE post_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, postId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu có bản ghi bị xóa
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertNews(news news) throws Exception {
        String sql = "INSERT INTO posts (user_id, title, photo_url, content, created_date, content_type) VALUES (?, ?, ?, ?, GETDATE(), ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, news.getUserId());
            ps.setString(2, news.getTitle());
            ps.setString(3, news.getPhotoUrl());
            ps.setString(4, news.getContent());
            ps.setString(5, news.getContentType());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public news getNewsById(int id) throws Exception {
        String sql = "SELECT * FROM posts WHERE post_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new news(
                        rs.getInt("post_id"),
                        rs.getString("user_id"),
                        rs.getString("title"),
                        rs.getString("photo_url"),
                        rs.getString("content"),
                        rs.getDate("created_date"),
                        rs.getString("content_type")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateNews(news news) throws Exception {
        String sql = "UPDATE posts SET title=?, photo_url=?, content=?, content_type=? WHERE post_id=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, news.getTitle());
            ps.setString(2, news.getPhotoUrl());
            ps.setString(3, news.getContent());
            ps.setString(4, news.getContentType());
            ps.setInt(5, news.getPostId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<news> getAdNews() {
        List<news> list = new ArrayList<news>();
        String sql = "SELECT top 4 * FROM posts where content_type like 'Advertisement' ORDER BY created_date DESC";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new news(
                        rs.getInt("post_id"),
                        rs.getString("user_id"),
                        rs.getString("title"),
                        rs.getString("photo_url"),
                        rs.getString("content"),
                        rs.getDate("created_date"),
                        rs.getString("content_type")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(NewsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
