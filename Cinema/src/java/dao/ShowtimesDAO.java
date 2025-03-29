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
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PCASUS
 */
public class showtimesDAO extends DBContext {

    private int rows_of_page = 9;

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
                + "WHERE s.movie_id = ? AND r.theater_id = ? AND s.status = 'Submitted'"
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
                + "WHERE s.movie_id = ? AND r.theater_id = ?  AND s.status = 'Submitted' "
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

    public int getMaxPage(int room_id, int movie_id, Date date, String status) {
        float maxpage = 1;
        String sql = "select count(st.showtime_id)\n"
                + "from showtimes st\n"
                + "join movies m on m.movie_id = st.movie_id\n"
                + "join rooms r on r.room_id=st.room_id\n"
                + "where ";
        if (status == null) {
            status = "";
        }
        sql += "st.status like '%" + status + "%' ";
        if (room_id != 0) {
            sql += "and st.room_id = " + room_id + " ";
        }
        if (movie_id != 0) {
            sql += "and st.movie_id = " + movie_id + " ";
        }
        if (date != null) {
            sql += "and CAST(st.showtime AS DATE) like '" + date + "' ";
        }
        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                maxpage = rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(showtimesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (int) Math.ceil(maxpage / rows_of_page);
    }

    public List<extend_showtimes> listShowtimeByRoom(int room_id, int movie_id, Date date, String status, int page) {

        int max_page = getMaxPage(room_id, movie_id, date, status);
        if (page < 1) {
            page = 1;
        } else if (page > max_page) {
            page = max_page;
        }

        List<extend_showtimes> showtimeList = new ArrayList<>();
        String sql = "select st.*, r.room_name, m.title, m.poster_url, m.duration, CAST(st.showtime AS DATE) AS show_date, CAST(st.showtime AS TIME) AS show_time\n"
                + "from showtimes st\n"
                + "join movies m on m.movie_id = st.movie_id\n"
                + "join rooms r on r.room_id=st.room_id\n"
                + "where ";
        if (status == null) {
            status = "";
        }
        sql += "st.status like '%" + status + "%' ";
        if (room_id != 0) {
            sql += "and st.room_id = " + room_id + " ";
        }
        if (movie_id != 0) {
            sql += "and st.movie_id = " + movie_id + " ";
        }
        if (date != null) {
            sql += "and CAST(st.showtime AS DATE) like '" + date + "' ";
        }
        sql += " order by showtime desc ";
        sql += " OFFSET ? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY";
        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, page == 0 ? 0 : (page - 1) * rows_of_page);
            st.setInt(2, rows_of_page);
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
            Logger.getLogger(showtimesDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public int addShowtime(int room_id, int movie_id, LocalDateTime showtime) {
        if (isPassed(showtime)) {
            return -2;
        }
        int year = showtime.get(ChronoField.YEAR);
        int month = showtime.get(ChronoField.MONTH_OF_YEAR);
        int date = showtime.get(ChronoField.DAY_OF_MONTH);
        int hour = showtime.get(ChronoField.HOUR_OF_DAY);
        int minute = showtime.get(ChronoField.MINUTE_OF_HOUR);
        String showt = year
                + "-"
                + ((10 > month) ? "0" : "") + month
                + "-"
                + ((10 > date) ? "0" : "") + date
                + " "
                + ((10 > hour) ? "0" : "") + hour
                + ":"
                + ((10 > minute) ? "0" : "") + minute
                + ":00";
        if (checkOverlaped(room_id, movie_id, showtime, -1)) {
            return -1;
        }

        String query = "insert into showtimes(room_id,movie_id,showtime) values(?,?,?)";
        try {
            Connection conn = new DBContext().getConnection();//mo ket noi voi sql
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, room_id);
            ps.setInt(2, movie_id);
            ps.setString(3, showt);
            return ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(showtimesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean deleteShowtime(int showtime_id) {
        String query = "delete showtimes where showtime_id = ?";
        try {
            Connection conn = new DBContext().getConnection();//mo ket noi voi sql
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, showtime_id);
            return ps.executeUpdate() == 1;
        } catch (Exception ex) {
            Logger.getLogger(showtimesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int submitShowtime(int showtime_id) {
        String sql = "select showtime from showtimes where showtime_id = ?";
        try {
            Connection conn = new DBContext().getConnection();//mo ket noi voi sql
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, showtime_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime showtime = LocalDateTime.parse(rs.getString("showtime").substring(0, 19), formatter);
                if (isPassed(showtime)) {
                    return -1;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(showtimesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = "update showtimes set status='Submitted' where showtime_id = ?";
        try {
            Connection conn = new DBContext().getConnection();//mo ket noi voi sql
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, showtime_id);
            return ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(showtimesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int editShowtime(int showtime_id, int room_id, int movie_id, LocalDateTime showtime) {
        if (isPassed(showtime)) {
            return -2;
        }
        int year = showtime.get(ChronoField.YEAR);
        int month = showtime.get(ChronoField.MONTH_OF_YEAR);
        int date = showtime.get(ChronoField.DAY_OF_MONTH);
        int hour = showtime.get(ChronoField.HOUR_OF_DAY);
        int minute = showtime.get(ChronoField.MINUTE_OF_HOUR);
        String showt = year
                + "-"
                + ((10 > month) ? "0" : "") + month
                + "-"
                + ((10 > date) ? "0" : "") + date
                + " "
                + ((10 > hour) ? "0" : "") + hour
                + ":"
                + ((10 > minute) ? "0" : "") + minute
                + ":00";
        if (checkOverlaped(room_id, movie_id, showtime, showtime_id)) {
            return -1;
        }
        String query = "update showtimes set room_id=?,movie_id=?,showtime=? where showtime_id = ?";
        try {
            Connection conn = new DBContext().getConnection();//mo ket noi voi sql
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, room_id);
            ps.setInt(2, movie_id);
            ps.setString(3, showt);
            ps.setInt(4, showtime_id);
            return ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(showtimesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private LocalDateTime prevdatetime = null;
    private int prevduration = 0;

    public boolean checkOverlaped(int room_id, int movie_id, LocalDateTime showtime, int id) {

        int duration = 0;
        String getMovieDuration = "select duration from movies where movie_id = ?";
        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(getMovieDuration)) {
            st.setInt(1, movie_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                duration = rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(showtimesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select st.*, r.room_name, m.title, m.poster_url, m.duration, CAST(st.showtime AS TIME) AS show_time\n"
                + "from showtimes st\n"
                + "join movies m on m.movie_id = st.movie_id\n"
                + "join rooms r on r.room_id=st.room_id\n"
                + "where st.room_id = ? \n"
                + "order by showtime desc ";
        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, room_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                extend_showtimes show = new extend_showtimes(rs.getString("room_name"),
                        rs.getString("title"),
                        rs.getString("poster_url"),
                        rs.getInt("duration"),
                        rs.getInt("showtime_id"),
                        rs.getInt("movie_id"),
                        rs.getInt("room_id"),
                        rs.getDate("showtime"),
                        rs.getTime("show_time"),
                        rs.getString("status"));
                LocalDate date = show.getDate().toLocalDate();
                LocalTime time = show.getTime().toLocalTime();
                LocalDateTime datetime = LocalDateTime.of(date, time);
                if (id != show.getShowtime_id()) {
                    if (showtime.isBefore(datetime)) {
                        prevdatetime = datetime;
                        prevduration = show.getDuration();
                    } else {
                        return isOverlap(showtime, duration, prevdatetime, prevduration) || isOverlap(showtime, duration, datetime, show.getDuration());
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(showtimesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isOverlap(showtime, duration, prevdatetime, prevduration) || isOverlap(showtime, duration, null, 0);
    }

    private boolean isOverlap(LocalDateTime showtime, int duration, LocalDateTime datetime, int oldduration) {
        if (datetime == null) {
            return false;
        }
        LocalDateTime end = showtime.plusMinutes(duration);
        LocalDateTime checkend = datetime.plusMinutes(oldduration + 15);
        datetime = datetime.minusMinutes(15);
        if ((datetime.isBefore(showtime) && checkend.isAfter(showtime)) || (datetime.isBefore(end) && checkend.isAfter(end))) {
            return true;
        }
        return false;
    }

    private boolean isPassed(LocalDateTime showtime) {
        LocalDateTime now = LocalDateTime.now();
        if (showtime.isBefore(now)) {
            return true;
        }
        return false;
    }

    public int endShowtime(int showtime_id) {
        String sql = "select showtime, status from showtimes where showtime_id = ?";
        try {
            Connection conn = new DBContext().getConnection();//mo ket noi voi sql
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, showtime_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (!rs.getString("status").equals("Submitted")) {
                    return -2;
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime showtime = LocalDateTime.parse(rs.getString("showtime").substring(0, 19), formatter);
                System.out.println(showtime);
                System.out.println(LocalDateTime.now());
                if (!isPassed(showtime)) {
                    return -1;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(showtimesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = "update showtimes set status='Ended' where showtime_id = ?";
        try {
            Connection conn = new DBContext().getConnection();//mo ket noi voi sql
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, showtime_id);
            return ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(showtimesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public showtimes getShowTimebyDateTime(Date startDate, Time startTime, int mid, int branch) {
        showtimes showtime = null;
        String sql = "SELECT * FROM showtimes WHERE showtime = ? AND movie_id = ? AND room_id = ?";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            // Chuyển startDate (java.sql.Date) thành LocalDate
            LocalDate localDate = startDate.toLocalDate();

            // Chuyển startTime (java.sql.Time) thành LocalTime
            LocalTime localTime = startTime.toLocalTime();

            // Hợp nhất thành LocalDateTime rồi chuyển sang Timestamp
            LocalDateTime dateTime = LocalDateTime.of(localDate, localTime);
            Timestamp sqlTimestamp = Timestamp.valueOf(dateTime);

            // Set giá trị tham số
            st.setTimestamp(1, sqlTimestamp);
            st.setInt(2, mid);
            st.setInt(3, branch);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    showtime = new showtimes();
                    showtime.setShowtime_id(rs.getInt("showtime_id"));
                    showtime.setMovie_id(rs.getInt("movie_id"));
                    showtime.setRoom_id(rs.getInt("room_id"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn getShowTimebyDateTime: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi kết nối DB: " + e.getMessage());
        }
        return showtime;
    }
    
    public int getShowTime(Date date, Time showtime, int room_id) {
        String sql = "SELECT showtime_id FROM showtimes WHERE showtime = ? AND room_id = ?";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            // Hợp nhất thành LocalDateTime rồi chuyển sang Timestamp
            // Chuyển startDate (java.sql.Date) thành LocalDate
            LocalDate localDate = date.toLocalDate();

            // Chuyển startTime (java.sql.Time) thành LocalTime
            LocalTime localTime = showtime.toLocalTime();

            // Hợp nhất thành LocalDateTime rồi chuyển sang Timestamp
            LocalDateTime dateTime = LocalDateTime.of(localDate, localTime);
            Timestamp sqlTimestamp = Timestamp.valueOf(dateTime);

            // Set giá trị tham số
            st.setTimestamp(1, sqlTimestamp);
            st.setInt(2, room_id);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn getShowTimebyDateTime: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi kết nối DB: " + e.getMessage());
        }
        return 0;
    }
    
}
