package controller.guest;

import dao.participantDAO;
import entity.participants;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DetailParticipantController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int movieId = Integer.parseInt(request.getParameter("mid"));

            participantDAO dao = new participantDAO();
            List<participants> directors = dao.getDirectorsByMovieId(movieId);
            List<participants> actors = dao.getActorsByMovieId(movieId);

            request.setAttribute("directors", directors);
            request.setAttribute("actors", actors);
            request.getRequestDispatcher("movie.jsp").forward(request, response);

        } catch (Exception e) {
            System.err.println("❌ Lỗi tại DetailParticipantController: " + e.getMessage());
            response.sendRedirect("error.jsp");
        }
    }
    
    
}
