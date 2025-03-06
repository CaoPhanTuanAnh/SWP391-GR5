/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.movies;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PCASUS
 */
public class moviesDAO extends DBContext {

    public List<movies> getAllMoviesWithPaging(int page, int PAGE_SIZE) {
        List<movies> movieList = new ArrayList<>();
        String sql = "SELECT \n"
                + "    m.movie_id,\n"
                + "    m.title,\n"
                + "    m.poster_url,\n"
                + "    m.description,\n"
                + "    m.duration,\n"
                + "    m.release_date,\n"
                + "    STRING_AGG(p.participant_name, ', ') AS actors\n"
                + "FROM movies m\n"
                + "LEFT JOIN movie_participants mp ON m.movie_id = mp.movie_id\n"
                + "LEFT JOIN participants p ON mp.participant_id = p.participant_id\n"
                + "GROUP BY m.movie_id, m.title, m.duration, m.poster_url, m.description, m.release_date\n"
                + "ORDER BY m.movie_id ASC \n"
                + "OFFSET ((? - 1) * ?) ROWS FETCH NEXT ? ROWS ONLY;";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, page);
            st.setInt(2, PAGE_SIZE);
            st.setInt(3, PAGE_SIZE);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int movie_id = rs.getInt("movie_id");
                    String title = rs.getString("title");
                    int duration = rs.getInt("duration");
                    String poster_url = rs.getString("poster_url");
                    String description = rs.getString("description");
                    Date release_date = rs.getDate("release_date");
                    String actorsString = rs.getString("actors");
                    String[] actorsArray = actorsString != null ? actorsString.split(", ") : new String[0];

                    movies movies = new movies();
                    movies.setMovie_id(movie_id);
                    movies.setTitle(title);
                    movies.setDuration(duration);
                    movies.setPoster_url(poster_url);
                    movies.setDescription(description);
                    movies.setRelease_date(release_date);
                    movies.setParticipants(actorsArray);

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

    public int getTotalMovies() {
        int total = 0;
        String sql = "SELECT COUNT(movie_id) AS total FROM movies";
        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn getTotalMovies: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi kết nối DB: " + e.getMessage());
        }
        return total;
    }

    public List<movies> getPopular() {
        List<movies> movieList = new ArrayList<>();
        String sql = "select top 10  m.movie_id, m.title, m.duration, m.poster_url, count(s.movie_id) as popular from movies m join showtimes s on m.movie_id = s.movie_id\n"
                + "group by  m.director,m.duration, \n"
                + "m.movie_id, m.poster_url, m.title, s.movie_id\n"
                + "ORDER BY popular DESC";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

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

        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn getPopular: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi kết nối DB: " + e.getMessage());
        }

        return movieList;
    }

    public List<movies> getNew() {
        List<movies> movieList = new ArrayList<>();
        String sql = "select top 7 movie_id, title, duration, poster_url from movies order by release_date desc";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

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

        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn getNew: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi kết nối DB: " + e.getMessage());
        }
        return movieList;
    }

    public List<movies> searchMovies(int[] cid, String showDate, String showTimeFrom, String showTimeTo, int page, int PAGE_SIZE, String name) {
        List<movies> movieList = new ArrayList<>();
        String sql = buildQuery(showDate, showTimeFrom, showTimeTo, cid, name);

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {
            int paramIndex = 1;

            // Set category IDs if provided
            if (cid != null && cid.length > 0) {
                for (int id : cid) {
                    st.setInt(paramIndex++, id);
                }
            }

            // Set name keyword if provided (actors OR director)
            if (name != null && !name.isEmpty()) {
                st.setString(paramIndex++, "%" + name + "%");
                st.setString(paramIndex++, "%" + name + "%");
            }

            // Set show date if provided
            if (showDate != null && !showDate.isEmpty()) {
                st.setString(paramIndex++, showDate);
            }

            // Set show time range if provided
            if (showTimeFrom != null && !showTimeFrom.isEmpty()) {
                st.setString(paramIndex++, showTimeFrom);
            }
            if (showTimeTo != null && !showTimeTo.isEmpty()) {
                st.setString(paramIndex++, showTimeTo);
            }

            // Set pagination parameters
            st.setInt(paramIndex++, (page - 1) * PAGE_SIZE); // OFFSET
            st.setInt(paramIndex++, PAGE_SIZE); // FETCH NEXT

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int movie_id = rs.getInt("movie_id");
                    String title = rs.getString("title");
                    int duration = rs.getInt("duration");
                    String poster_url = rs.getString("poster_url");

                    movies movie = new movies();
                    movie.setMovie_id(movie_id);
                    movie.setTitle(title);
                    movie.setDuration(duration);
                    movie.setPoster_url(poster_url);
                    movieList.add(movie);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn searchMovies: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi kết nối DB: " + e.getMessage());
        }
        return movieList;
    }

    public static String buildQuery(String showDate, String showTimeFrom, String showTimeTo, int[] cid, String name) {
        StringBuilder sql = new StringBuilder("SELECT m.movie_id, "
                + "m.title, m.description, "
                + "m.trailer_url, m.duration, m.poster_url, m.genre_id, m.release_date "
                + "FROM movies AS m "
                + "JOIN showtimes AS s ON m.movie_id = s.movie_id "
                + "WHERE 1=1 ");

        // Category filtering
        if (cid != null && cid.length > 0) {
            sql.append("AND m.genre_id IN (");
            for (int i = 0; i < cid.length; i++) {
                sql.append("?");
                if (i < cid.length - 1) {
                    sql.append(", ");
                }
            }
            sql.append(") ");
        }

        // Name keyword filtering (search in actors OR director)
        if (name != null && !name.isEmpty()) {
            sql.append("AND (m.title LIKE ? ) ");
        }

        // Show date filtering (Sửa lỗi DATE() trong SQL Server)
        if (showDate != null && !showDate.isEmpty()) {
            sql.append("AND CAST(s.showtime AS DATE) = ? ");
        }

        // Show time range filtering
        if (showTimeFrom != null && !showTimeFrom.isEmpty()) {
            sql.append("AND CONVERT(TIME, s.showtime) >= ? ");
        }
        if (showTimeTo != null && !showTimeTo.isEmpty()) {
            sql.append("AND CONVERT(TIME, s.showtime) <= ? ");
        }

        // Pagination (for SQL Server)
        sql.append("ORDER BY m.movie_id ASC "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;");

        return sql.toString();
    }

  public movies getMovie(int mid) {
    movies movie = null;
    String sql = "SELECT * FROM movies WHERE movie_id = ?";

    try (Connection connection = getConnection(); 
         PreparedStatement st = connection.prepareStatement(sql)) {

        st.setInt(1, mid);
        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                // Khởi tạo đối tượng movies trước khi sử dụng
                movie = new movies();  

                int movie_id = rs.getInt("movie_id");
                String title = rs.getString("title");
                int duration = rs.getInt("duration");
                String poster_url = rs.getString("poster_url");

                movie.setMovie_id(movie_id);
                movie.setTitle(title);
                movie.setDuration(duration);
                movie.setPoster_url(poster_url);
            }
        }

    } catch (SQLException e) {
        System.err.println("Lỗi truy vấn getMovie: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Lỗi kết nối DB: " + e.getMessage());
    }

    return movie;
}

   
    public static void main(String[] args) {
       
        moviesDAO b = new moviesDAO();
         movies a= b.getMovie(4);
    }




}
