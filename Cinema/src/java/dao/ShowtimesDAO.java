/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.extend_showtimes;
import entity.movies;
import entity.rooms;
import entity.showtimes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PCASUS
 */
public class showtimesDAO extends DBContext {

    public List<showtimes> getAllDateByBrand(int mid, int branchId) {
        List<showtimes> movieList = new ArrayList<>();
        String sql = "SELECT DISTINCT "
                + "    s.movie_id, "
                + "    CAST(s.showtime AS DATE) AS show_date, "
                + "    s.showtime_id, "
                + "    s.status "
                + "FROM movies m "
                + "JOIN showtimes s ON m.movie_id = s.movie_id "
                + "JOIN rooms r ON r.room_id = s.room_id "
                + "JOIN theaters t ON t.theater_id = r.theater_id "
                + "WHERE s.movie_id = ? AND r.theater_id = ? "
                + "GROUP BY s.movie_id, CAST(s.showtime AS DATE), s.showtime_id, s.status;";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, mid);
            st.setInt(2, branchId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int movie_id = rs.getInt("movie_id");
                    Date date = rs.getDate("show_date");
                    int showtime_id = rs.getInt("showtime_id");
                    String status = rs.getString("status");

                    showtimes movies = new showtimes();
                    movies.setDate(date);
                    movies.setMovie_id(movie_id);
                    movies.setStatus(status);
                    movies.setShowtime_id(showtime_id);

                    movieList.add(movies);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn getAllDateByBrand: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi kết nối DB: " + e.getMessage());
        }

        return movieList;
    }

    public List<showtimes> getAllTimeByBrand(int mid, int branchId) {
        List<showtimes> movieList = new ArrayList<>();
        String sql = "SELECT DISTINCT "
                + "    s.movie_id, "
                + "    CAST(s.showtime AS DATE) AS show_date, "
                + "    CAST(s.showtime AS TIME) AS show_time, "
                + "    s.showtime_id, "
                + "    s.status "
                + "FROM movies m "
                + "JOIN showtimes s ON m.movie_id = s.movie_id "
                + "JOIN rooms r ON r.room_id = s.room_id "
                + "JOIN theaters t ON t.theater_id = r.theater_id "
                + "WHERE s.movie_id = ? AND r.theater_id = ? "
                + "GROUP BY s.movie_id, CAST(s.showtime AS DATE), CAST(s.showtime AS TIME), s.showtime_id, s.status;";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, mid);
            st.setInt(2, branchId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int movie_id = rs.getInt("movie_id");
                    Date date = rs.getDate("show_date");
                    Time time = rs.getTime("show_time");
                    int showtime_id = rs.getInt("showtime_id");
                    String status = rs.getString("status");

                    showtimes movies = new showtimes();
                    movies.setDate(date);
                    movies.setMovie_id(movie_id);
                    movies.setTime(time);
                    movies.setStatus(status);
                    movies.setShowtime_id(showtime_id);

                    movieList.add(movies);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn getAllTimeByBrand: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi kết nối DB: " + e.getMessage());
        }

        return movieList;
    }

    public List<extend_showtimes> listShowtimeByRoom(int room_id, int movie_id, Date date, String status) {
        List<extend_showtimes> showtimeList = new ArrayList<>();
        String sql = "select st.*,r.room_name, m.title,m.poster_url,m.duration, CAST(st.showtime AS DATE) AS show_date, CAST(st.showtime AS TIME) AS show_time\n"
                + "from showtimes st\n"
                + "join movies m on m.movie_id = st.movie_id\n"
                + "join rooms r on r.room_id=st.room_id\n"
                + "where st.room_id = ? ";
        if (movie_id != 0) {
            sql += "and st.movie_id = ? ";
        }
        if (date != null) {
            sql += "and CAST(st.showtime AS DATE) like '" + date + "' ";
        }
        if (status != null && !status.isBlank()) {
            sql += "and st.status like '" + status + "' ";
        }
        sql +=" order by showtime desc ";
        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, room_id);
            if (movie_id != 0) {
                st.setInt(2, movie_id);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                showtimeList.add(new extend_showtimes(rs.getString("room_name"),
                        rs.getString("title"),
                        rs.getString("poster_url"),
                        rs.getInt("duration"),
                        rs.getInt("showtime_id"),
                        rs.getInt("movie_id"),
                        rs.getInt("room_id"),
                        rs.getDate("show_date"),
                        rs.getTime("show_time"),
                        rs.getString("status")));
            }
            return showtimeList;
        } catch (Exception ex) {
            Logger.getLogger(bookingsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<rooms> listRoomByDirector(int directorId) {
        List<rooms> roomList = new ArrayList<>();
        String query = "select * from rooms\n"
                + "where theater_id in (select theater_id from theaters where director_id=?)";
        try {
            Connection conn = new DBContext().getConnection();//mo ket noi voi sql
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, directorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                roomList.add(new rooms(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return roomList;
    }

    public List<rooms> listRoomByManager(int managerId) {
        List<rooms> roomList = new ArrayList<>();
        String query = "select * from rooms\n"
                + "where manager_id = ?";
        try {
            Connection conn = new DBContext().getConnection();//mo ket noi voi sql
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, managerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                roomList.add(new rooms(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return roomList;
    }

    public List<movies> listMovie() {
        List<movies> movieList = new ArrayList<>();
        String sql = "SELECT * FROM movies where status like 'Present'";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int movie_id = rs.getInt("movie_id");
                    String title = rs.getString("title");
                    int duration = rs.getInt("duration");
                    String poster_url = rs.getString("poster_url");

                    movies movies = new movies();
                    movies.setMovie_id(movie_id);
                    movies.setTitle(title);
                    movies.setDuration(duration);
                    movies.setPoster_url(poster_url);
                    movieList.add(movies);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn getAllMoviesWithPaging: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi kết nối DB: " + e.getMessage());
        }
        return movieList;
    }

    public boolean addShowtime(int room_id, int movie_id, Date date, Time time) {
        String query = "insert into showtimes(room_id,movie_id,showtime) values(?,?,?)";
        try {
            Connection conn = new DBContext().getConnection();//mo ket noi voi sql
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, room_id);
            ps.setInt(2, movie_id);
            ps.setString(3, date+" "+time);
            return ps.executeUpdate()==1;
        } catch (Exception ex) {
            Logger.getLogger(bookingsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean deleteShowtime(int showtime_id) {
        String query = "delete showtimes where showtime_id = ?";
        try {
            Connection conn = new DBContext().getConnection();//mo ket noi voi sql
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, showtime_id);
            return ps.executeUpdate()==1;
        } catch (Exception ex) {
            Logger.getLogger(bookingsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean submitShowtime(int showtime_id) {
        String query = "update showtimes set status='Submitted' where showtime_id = ?";
        try {
            Connection conn = new DBContext().getConnection();//mo ket noi voi sql
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, showtime_id);
            return ps.executeUpdate()==1;
        } catch (Exception ex) {
            Logger.getLogger(bookingsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
