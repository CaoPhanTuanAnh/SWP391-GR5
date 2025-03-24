package dao;

import context.DBContext;
import entity.movie_genres;
import entity.movies;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class movie_genresDAO {

    Connection conn = null; // kết nối vs sql
    PreparedStatement ps = null; // ném query sang sql
    ResultSet rs = null; // nhận kết quả trả về

    public List<movie_genres> getMovieGenres(String search, int page, int pageSize) {
        List<movie_genres> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            String sql = "WITH PaginatedResults AS ( "
                    + "SELECT mg.movie_genre_id, mg.movie_id, m.title, g.genre_name, "
                    + "ROW_NUMBER() OVER (ORDER BY mg.movie_genre_id) AS row_num "
                    + "FROM movie_genres mg "
                    + "JOIN movies m ON mg.movie_id = m.movie_id "
                    + "JOIN genres g ON mg.genre_id = g.genre_id "
                    + "WHERE (? IS NULL OR m.title LIKE ?) "
                    + ") "
                    + "SELECT movie_genre_id, movie_id, title, genre_name FROM PaginatedResults "
                    + "WHERE row_num BETWEEN (? - 1) * ? + 1 AND ? * ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, search);
            ps.setString(2, "%" + search + "%");
            ps.setInt(3, page);
            ps.setInt(4, pageSize);
            ps.setInt(5, page);
            ps.setInt(6, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                movie_genres mg = new movie_genres();
                mg.setMovie_genre_id(rs.getInt("movie_genre_id"));
                mg.setMovie_id(rs.getInt("movie_id"));
                mg.setGenre_id(0); // Không cần genre_id, có thể bỏ
                mg.setTitle(rs.getString("title"));
                mg.setGenre_name(rs.getString("genre_name"));
                list.add(mg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public int getTotalRecords(String search) {
        int total = 0;
        try {
            conn = new DBContext().getConnection();
            String sql = "SELECT COUNT(*) FROM movie_genres mg "
                    + "JOIN movies m ON mg.movie_id = m.movie_id "
                    + "JOIN genres g ON mg.genre_id = g.genre_id "
                    + "WHERE (? IS NULL OR m.title LIKE ?)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, search);
            ps.setString(2, "%" + search + "%");
            rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return total;
    }

    public movie_genres getMovieGenreById(int movieGenreId) {
        movie_genres mg = null;
        try {
            conn = new DBContext().getConnection();
            String sql = "SELECT mg.movie_genre_id, mg.movie_id, mg.genre_id, m.title, g.genre_name "
                    + "FROM movie_genres mg "
                    + "JOIN movies m ON mg.movie_id = m.movie_id "
                    + "JOIN genres g ON mg.genre_id = g.genre_id "
                    + "WHERE mg.movie_genre_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, movieGenreId);
            rs = ps.executeQuery();
            if (rs.next()) {
                mg = new movie_genres();
                mg.setMovie_genre_id(rs.getInt("movie_genre_id"));
                mg.setMovie_id(rs.getInt("movie_id"));
                mg.setGenre_id(rs.getInt("genre_id"));  // Quan trọng!
                mg.setTitle(rs.getString("title"));
                mg.setGenre_name(rs.getString("genre_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mg;
    }

    public void updateMovieGenre(int movieGenreId, int newGenreId) {
        try {
            conn = new DBContext().getConnection();
            String sql = "UPDATE movie_genres SET genre_id = ? WHERE movie_genre_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, newGenreId);
            ps.setInt(2, movieGenreId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addMovieGenre(int movieId, int genreId) {
        try {
            conn = new DBContext().getConnection();
            String sql = "INSERT INTO movie_genres (movie_id, genre_id) VALUES (?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, movieId);
            ps.setInt(2, genreId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<movies> getMovies(String search) {
        List<movies> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            String sql = "SELECT movie_id, title FROM movies WHERE ? IS NULL OR title LIKE ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, search);
            ps.setString(2, "%" + search + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                movies movie = new movies();
                movie.setMovie_id(rs.getInt("movie_id"));
                movie.setTitle(rs.getString("title"));
                list.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public void deleteMovieGenre(int movieGenreId) {
        String query = "DELETE FROM movie_genres WHERE movie_genre_id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, movieGenreId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

}
