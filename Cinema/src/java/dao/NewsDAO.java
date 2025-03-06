/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import context.DBContext;
import entity.News;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author default
 */
public class NewsDAO extends DBContext {

    public List<News> getAllNews() throws Exception {
        List<News> list = new ArrayList<>();
        String sql = "SELECT * FROM posts ORDER BY created_date DESC";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new News(
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

    public boolean insertNews(News news) throws Exception {
        String sql = "INSERT INTO posts (user_id, title, photo_url, content, created_date, content_type) VALUES (?, ?, ?, ?, GETDATE(), ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
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

    public News getNewsById(int id) throws Exception {
        String sql = "SELECT * FROM posts WHERE post_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new News(
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

    public boolean updateNews(News news) throws Exception {
        String sql = "UPDATE posts SET title=?, photo_url=?, content=?, content_type=? WHERE post_id=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
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
}
