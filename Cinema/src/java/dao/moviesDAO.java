/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.movies;
import entity.participants;
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
                + "    STRING_AGG(p.participant_name, ', ') AS actors,\n"
                + "    STRING_AGG(g.genre_name, ', ') AS genres,\n"
                + "    p.participant_id,\n"
                + "    p.participant_name,\n"
                + "    p.portrait_url,\n"
                + "    p.birth_date,\n"
                + "    p.nationality,\n"
                + "    p.about,\n"
                + "    mp.role_in_movie  -- Lấy trực tiếp vai trò từ bảng movie_participants\n"
                + "FROM movies m\n"
                + "LEFT JOIN movie_participants mp ON m.movie_id = mp.movie_id\n"
                + "LEFT JOIN participants p ON mp.participant_id = p.participant_id\n"
                + "LEFT JOIN movie_genres mg ON m.movie_id = mg.movie_id\n"
                + "LEFT JOIN genres g ON mg.genre_id = g.genre_id\n"
                + "GROUP BY \n"
                + "    m.movie_id, \n"
                + "    m.title, \n"
                + "    m.poster_url, \n"
                + "    m.description, \n"
                + "    m.duration, \n"
                + "    m.release_date, \n"
                + "    p.participant_id, \n"
                + "    p.participant_name, \n"
                + "    p.portrait_url, \n"
                + "    p.birth_date, \n"
                + "    p.nationality, \n"
                + "    p.about, \n"
                + "    mp.role_in_movie  -- Lấy vai trò trực tiếp từ bảng movie_participants\n"
                + "ORDER BY m.movie_id ASC \n"
                + "OFFSET ((? - 1) * ?) ROWS FETCH NEXT ? ROWS ONLY;";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, page);
            st.setInt(2, PAGE_SIZE);
            st.setInt(3, PAGE_SIZE);

            try (ResultSet rs = st.executeQuery()) {
                int currentMovieId = -1;
                movies currentMovie = null;
                List<participants> participantsList = new ArrayList<>();

                while (rs.next()) {
                    int movie_id = rs.getInt("movie_id");
                    if (movie_id != currentMovieId) {
                        // Nếu gặp bộ phim mới, lưu bộ phim cũ và khởi tạo lại danh sách
                        if (currentMovie != null) {
                            currentMovie.setParts(participantsList);
                            movieList.add(currentMovie);
                        }

                        // Tạo đối tượng movies mới cho bộ phim hiện tại
                        currentMovieId = movie_id;
                        currentMovie = new movies();
                        currentMovie.setMovie_id(movie_id);
                        currentMovie.setTitle(rs.getString("title"));
                        currentMovie.setPoster_url(rs.getString("poster_url"));
                        currentMovie.setDescription(rs.getString("description"));
                        currentMovie.setDuration(rs.getInt("duration"));
                        currentMovie.setRelease_date(rs.getDate("release_date"));
                        currentMovie.setGenres(rs.getString("genres")); // Set genres

                        // Reset danh sách người tham gia cho bộ phim mới
                        participantsList = new ArrayList<>();
                    }

                    // Tạo đối tượng participants và thêm vào danh sách
                    participants participant = new participants();
                    participant.setParticipant_id(rs.getInt("participant_id"));
                    participant.setParticipant_name(rs.getString("participant_name"));
                    participant.setPortrait_url(rs.getString("portrait_url"));
                    participant.setBirth_date(rs.getDate("birth_date"));
                    participant.setNationality(rs.getString("nationality"));
                    participant.setAbout(rs.getString("about"));
                    participant.setRole(rs.getString("role_in_movie"));  // Sử dụng role_in_movie trực tiếp

                    participantsList.add(participant);
                }

                // Thêm bộ phim cuối cùng vào danh sách
                if (currentMovie != null) {
                    currentMovie.setParts(participantsList);
                    movieList.add(currentMovie);
                }

            }
        } catch (SQLException e) {
            System.err.println("Lỗi trong truy vấn getAllMoviesWithPaging: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi kết nối DB: " + e.getMessage());
        }

        return movieList;
    }

//    public static void main(String[] args) {
//        moviesDAO a = new moviesDAO();
//        List<movies> b = a.getAllMoviesWithPaging(3, 3);
//        for (movies movie : b) {
//            System.out.println("Movie Title: " + movie.getTitle());
//            System.out.println("Actors:");
//            for (String actorName : movie.getParticipants()) {
//                System.out.println("- " + actorName);
//            }
//        }
//    }
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
        String sql = "SELECT \n"
                + "    m.movie_id,\n"
                + "    m.title,\n"
                + "    m.poster_url,\n"
                + "    m.trailer_url, \n"
                + "    m.description,\n"
                + "    m.duration,\n"
                + "    m.release_date,\n"
                + "    STRING_AGG(p.participant_name, ', ') AS actors,\n"
                + "    STRING_AGG(g.genre_name, ', ') AS genres,\n"
                + "    p.participant_id,\n"
                + "    p.participant_name,\n"
                + "    p.portrait_url,\n"
                + "    p.birth_date,\n"
                + "    p.nationality,\n"
                + "    p.about,\n"
                + "    mp.role_in_movie  -- Lấy trực tiếp vai trò từ bảng movie_participants\n"
                + "FROM movies m\n"
                + "JOIN showtimes s ON m.movie_id = s.movie_id \n"
                + "LEFT JOIN movie_participants mp ON m.movie_id = mp.movie_id\n"
                + "LEFT JOIN participants p ON mp.participant_id = p.participant_id\n"
                + "LEFT JOIN movie_genres mg ON m.movie_id = mg.movie_id\n"
                + "LEFT JOIN genres g ON mg.genre_id = g.genre_id\n"
                + "WHERE m.status = 'Present' "
                + "GROUP BY \n"
                + "    m.movie_id, \n"
                + "    m.title, \n"
                + "    m.poster_url, \n"
                + "    m.trailer_url, \n"
                + "    m.description, \n"
                + "    m.duration, \n"
                + "    m.release_date, \n"
                + "    p.participant_id, \n"
                + "    p.participant_name, \n"
                + "    p.portrait_url, \n"
                + "    p.birth_date, \n"
                + "    p.nationality, \n"
                + "    p.about, \n"
                + "    mp.role_in_movie";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            try (ResultSet rs = st.executeQuery()) {
                int currentMovieId = -1;
                movies currentMovie = null;
                List<participants> participantsList = new ArrayList<>();

                while (rs.next()) {
                    int movie_id = rs.getInt("movie_id");
                    if (movie_id != currentMovieId) {
                        // Nếu gặp bộ phim mới, lưu bộ phim cũ và khởi tạo lại danh sách
                        if (currentMovie != null) {
                            currentMovie.setParts(participantsList);
                            movieList.add(currentMovie);
                        }

                        // Tạo đối tượng movies mới cho bộ phim hiện tại
                        currentMovieId = movie_id;
                        currentMovie = new movies();
                        currentMovie.setMovie_id(movie_id);
                        currentMovie.setTitle(rs.getString("title"));
                        currentMovie.setPoster_url(rs.getString("poster_url"));
                        currentMovie.setTrailer_url(rs.getString("trailer_url"));
                        currentMovie.setDescription(rs.getString("description"));
                        currentMovie.setDuration(rs.getInt("duration"));
                        currentMovie.setRelease_date(rs.getDate("release_date"));
                        currentMovie.setGenres(rs.getString("genres")); // Set genres

                        // Reset danh sách người tham gia cho bộ phim mới
                        participantsList = new ArrayList<>();
                    }

                    // Tạo đối tượng participants và thêm vào danh sách
                    participants participant = new participants();
                    participant.setParticipant_id(rs.getInt("participant_id"));
                    participant.setParticipant_name(rs.getString("participant_name"));
                    participant.setPortrait_url(rs.getString("portrait_url"));
                    participant.setBirth_date(rs.getDate("birth_date"));
                    participant.setNationality(rs.getString("nationality"));
                    participant.setAbout(rs.getString("about"));
                    participant.setRole(rs.getString("role_in_movie"));  // Sử dụng role_in_movie trực tiếp

                    participantsList.add(participant);
                }

                // Thêm bộ phim cuối cùng vào danh sách
                if (currentMovie != null) {
                    currentMovie.setParts(participantsList);
                    movieList.add(currentMovie);
                }

            }
        } catch (SQLException e) {
            System.err.println("Lỗi trong truy vấn getAllMoviesWithPaging: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi kết nối DB: " + e.getMessage());
        }
        return movieList;
    }

    public List<movies> getNew() {
        List<movies> movieList = new ArrayList<>();
        String sql = "SELECT \n"
                + "    m.movie_id,\n"
                + "    m.title,\n"
                + "    m.poster_url,\n"
                + "    m.trailer_url, \n"
                + "    m.description,\n"
                + "    m.duration,\n"
                + "    m.release_date,\n"
                + "    STRING_AGG(p.participant_name, ', ') AS actors,\n"
                + "    STRING_AGG(g.genre_name, ', ') AS genres,\n"
                + "    p.participant_id,\n"
                + "    p.participant_name,\n"
                + "    p.portrait_url,\n"
                + "    p.birth_date,\n"
                + "    p.nationality,\n"
                + "    p.about,\n"
                + "    mp.role_in_movie  -- Lấy trực tiếp vai trò từ bảng movie_participants\n"
                + "FROM movies m\n"
                + "LEFT JOIN movie_participants mp ON m.movie_id = mp.movie_id\n"
                + "LEFT JOIN participants p ON mp.participant_id = p.participant_id\n"
                + "LEFT JOIN movie_genres mg ON m.movie_id = mg.movie_id\n"
                + "LEFT JOIN genres g ON mg.genre_id = g.genre_id\n"
                + "WHERE m.status = 'Future' "
                + "GROUP BY \n"
                + "    m.movie_id, \n"
                + "    m.title, \n"
                + "    m.poster_url, \n"
                + "    m.trailer_url, \n"
                + "    m.description, \n"
                + "    m.duration, \n"
                + "    m.release_date, \n"
                + "    p.participant_id, \n"
                + "    p.participant_name, \n"
                + "    p.portrait_url, \n"
                + "    p.birth_date, \n"
                + "    p.nationality, \n"
                + "    p.about, \n"
                + "    mp.role_in_movie";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

            try (ResultSet rs = st.executeQuery()) {
                int currentMovieId = -1;
                movies currentMovie = null;
                List<participants> participantsList = new ArrayList<>();

                while (rs.next()) {
                    int movie_id = rs.getInt("movie_id");
                    if (movie_id != currentMovieId) {
                        // Nếu gặp bộ phim mới, lưu bộ phim cũ và khởi tạo lại danh sách
                        if (currentMovie != null) {
                            currentMovie.setParts(participantsList);
                            movieList.add(currentMovie);
                        }

                        // Tạo đối tượng movies mới cho bộ phim hiện tại
                        currentMovieId = movie_id;
                        currentMovie = new movies();
                        currentMovie.setMovie_id(movie_id);
                        currentMovie.setTitle(rs.getString("title"));
                        currentMovie.setPoster_url(rs.getString("poster_url"));
                        currentMovie.setTrailer_url(rs.getString("trailer_url"));
                        currentMovie.setDescription(rs.getString("description"));
                        currentMovie.setDuration(rs.getInt("duration"));
                        currentMovie.setRelease_date(rs.getDate("release_date"));
                        currentMovie.setGenres(rs.getString("genres")); // Set genres

                        // Reset danh sách người tham gia cho bộ phim mới
                        participantsList = new ArrayList<>();
                    }

                    // Tạo đối tượng participants và thêm vào danh sách
                    participants participant = new participants();
                    participant.setParticipant_id(rs.getInt("participant_id"));
                    participant.setParticipant_name(rs.getString("participant_name"));
                    participant.setPortrait_url(rs.getString("portrait_url"));
                    participant.setBirth_date(rs.getDate("birth_date"));
                    participant.setNationality(rs.getString("nationality"));
                    participant.setAbout(rs.getString("about"));
                    participant.setRole(rs.getString("role_in_movie"));  // Sử dụng role_in_movie trực tiếp

                    participantsList.add(participant);
                }

                // Thêm bộ phim cuối cùng vào danh sách
                if (currentMovie != null) {
                    currentMovie.setParts(participantsList);
                    movieList.add(currentMovie);
                }

            }
        } catch (SQLException e) {
            System.err.println("Lỗi trong truy vấn getAllMoviesWithPaging: " + e.getMessage());
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

            // Thiết lập tham số thể loại (cid)
            if (cid != null && cid.length > 0) {
                for (int id : cid) {
                    st.setInt(paramIndex++, id);
                }
            }

            // Thiết lập tham số tên phim hoặc mô tả (name)
            if (name != null && !name.isEmpty()) {
                st.setString(paramIndex++, "%" + name + "%");
                st.setString(paramIndex++, "%" + name + "%");
            }

            // Thiết lập tham số ngày chiếu nếu có
            if (showDate != null && !showDate.isEmpty()) {
                st.setString(paramIndex++, showDate);
            }

            // Thiết lập tham số khoảng thời gian chiếu nếu có
            if (showTimeFrom != null && !showTimeFrom.isEmpty()) {
                st.setString(paramIndex++, showTimeFrom);
            }
            if (showTimeTo != null && !showTimeTo.isEmpty()) {
                st.setString(paramIndex++, showTimeTo);
            }

            // Thiết lập tham số phân trang
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
        StringBuilder sql = new StringBuilder("SELECT DISTINCT m.movie_id, "
                + "m.title, m.duration, m.poster_url "
                + "FROM movies AS m "
                + "JOIN showtimes AS s ON m.movie_id = s.movie_id "
                + "LEFT JOIN movie_genres AS mg ON m.movie_id = mg.movie_id "
                + "WHERE 1=1 ");

        // Lọc theo thể loại
        if (cid != null && cid.length > 0) {
            sql.append("AND mg.genre_id IN (");
            for (int i = 0; i < cid.length; i++) {
                sql.append("?");
                if (i < cid.length - 1) {
                    sql.append(", ");
                }
            }
            sql.append(") ");
        }

        // Lọc theo tên phim
        if (name != null && !name.isEmpty()) {
            sql.append("AND (m.title LIKE ? OR m.description LIKE ?) ");
        }

        // Lọc theo ngày chiếu
        if (showDate != null && !showDate.isEmpty()) {
            sql.append("AND CAST(s.showtime AS DATE) = ? ");
        }

        // Lọc theo khoảng thời gian chiếu
        if (showTimeFrom != null && !showTimeFrom.isEmpty()) {
            sql.append("AND CONVERT(TIME, s.showtime) >= ? ");
        }
        if (showTimeTo != null && !showTimeTo.isEmpty()) {
            sql.append("AND CONVERT(TIME, s.showtime) <= ? ");
        }

        // Phân trang (dành cho SQL Server)
        sql.append("ORDER BY m.movie_id ASC "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        return sql.toString();
    }

    public movies getMovie(int mid) {
        movies movie = null;
        String sql = "SELECT * FROM movies WHERE movie_id = ?";

        try (Connection connection = getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {

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

    public List<movies> getAllMovies() throws Exception {
        Connection conn = new DBContext().getConnection();
        List<movies> movies = new ArrayList<>();
        String sql = "WITH GenreCTE AS (\n"
                + "    SELECT DISTINCT mg.movie_id, g.genre_name\n"
                + "    FROM movie_genres mg\n"
                + "    JOIN genres g ON mg.genre_id = g.genre_id\n"
                + "),\n"
                + "ActorCTE AS (\n"
                + "    SELECT DISTINCT mp.movie_id, p.participant_name\n"
                + "    FROM movie_participants mp\n"
                + "    JOIN participants p ON mp.participant_id = p.participant_id\n"
                + "    WHERE mp.role_in_movie = 'Actor'\n"
                + "),\n"
                + "DirectorCTE AS (\n"
                + "    SELECT DISTINCT mp.movie_id, p.participant_name\n"
                + "    FROM movie_participants mp\n"
                + "    JOIN participants p ON mp.participant_id = p.participant_id\n"
                + "    WHERE mp.role_in_movie = 'Director'\n"
                + ")\n"
                + "SELECT \n"
                + "    m.movie_id, \n"
                + "    m.title, \n"
                + "    m.description, \n"
                + "    m.duration, \n"
                + "    m.release_date, \n"
                + "    m.status, \n"
                + "    (SELECT STRING_AGG(genre_name, ', ') FROM GenreCTE WHERE GenreCTE.movie_id = m.movie_id) AS genres,\n"
                + "    (SELECT STRING_AGG(participant_name, ', ') FROM ActorCTE WHERE ActorCTE.movie_id = m.movie_id) AS actors,\n"
                + "    (SELECT STRING_AGG(participant_name, ', ') FROM DirectorCTE WHERE DirectorCTE.movie_id = m.movie_id) AS directors\n"
                + "FROM movies m\n"
                + "ORDER BY m.movie_id ASC;";

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                movies movie = new movies();
                movie.setMovie_id(rs.getInt("movie_id"));
                movie.setTitle(rs.getString("title"));
                movie.setDescription(rs.getString("description"));
                movie.setDuration(rs.getInt("duration"));
                movie.setRelease_date(rs.getDate("release_date"));
                movie.setStatus(rs.getString("status"));
                movie.setGenres(rs.getString("genres"));
                // Chuyển chuỗi thành mảng
                String actorsString = rs.getString("actors");
                movie.setActors(actorsString != null ? actorsString.split(", ") : new String[0]);
                String directorsString = rs.getString("directors");
                movie.setDirectors(directorsString != null ? directorsString.split(", ") : new String[0]);
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public movies getMovieById(String movieId) throws Exception {
        Connection conn = new DBContext().getConnection();
        movies movie = null;
        String sql = "WITH GenreCTE AS ("
                + "    SELECT DISTINCT mg.movie_id, g.genre_name "
                + "    FROM movie_genres mg "
                + "    JOIN genres g ON mg.genre_id = g.genre_id "
                + "), "
                + "ActorCTE AS ("
                + "    SELECT DISTINCT mp.movie_id, p.participant_name "
                + "    FROM movie_participants mp "
                + "    JOIN participants p ON mp.participant_id = p.participant_id "
                + "    WHERE mp.role_in_movie = 'Actor' "
                + "), "
                + "DirectorCTE AS ("
                + "    SELECT DISTINCT mp.movie_id, p.participant_name "
                + "    FROM movie_participants mp "
                + "    JOIN participants p ON mp.participant_id = p.participant_id "
                + "    WHERE mp.role_in_movie = 'Director' "
                + ") "
                + "SELECT "
                + "    m.movie_id, m.title, m.description, m.trailer_url, m.poster_url, "
                + "    m.duration, m.release_date, m.status, "
                + "    (SELECT STRING_AGG(genre_name, ', ') FROM GenreCTE WHERE GenreCTE.movie_id = m.movie_id) AS genres, "
                + "    (SELECT STRING_AGG(participant_name, ', ') FROM ActorCTE WHERE ActorCTE.movie_id = m.movie_id) AS actors, "
                + "    (SELECT STRING_AGG(participant_name, ', ') FROM DirectorCTE WHERE DirectorCTE.movie_id = m.movie_id) AS directors "
                + "FROM movies m "
                + "WHERE m.movie_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, movieId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    movie = new movies();
                    movie.setMovie_id(rs.getInt("movie_id"));
                    movie.setTitle(rs.getString("title"));
                    movie.setDescription(rs.getString("description"));
                    movie.setTrailer_url(rs.getString("trailer_url"));
                    movie.setPoster_url(rs.getString("poster_url"));
                    movie.setDuration(rs.getInt("duration"));
                    movie.setRelease_date(rs.getDate("release_date"));
                    movie.setStatus(rs.getString("status"));
                    movie.setGenres(rs.getString("genres"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

    Connection conn = null; // kết nối vs sql
    PreparedStatement ps = null; // ném query sang sql
    ResultSet rs = null; // nhận kết quả trả về

    public void editMovie(String movieId, String title, String description, String trailerUrl,
            String posterUrl, String duration, String releaseDate, String status) {
        String query = "UPDATE movies SET title = ?, description = ?, trailer_url = ?, poster_url = ?, "
                + "duration = ?, release_date = ?, status = ? WHERE movie_id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, trailerUrl);
            ps.setString(4, posterUrl);
            ps.setString(5, duration);
            ps.setString(6, releaseDate);
            ps.setString(7, status);
            ps.setString(8, movieId);

            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertMovie(String title, String description, String trailerUrl,
            String posterUrl, String duration, String releaseDate, String status) {
        String query = "INSERT INTO movies (title, description, trailer_url, poster_url, duration, release_date, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, trailerUrl);
            ps.setString(4, posterUrl);
            ps.setString(5, duration);
            ps.setString(6, releaseDate);
            ps.setString(7, status);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

}
