/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dao.showtimesDAO;
import entity.extend_showtimes;
import entity.movies;
import entity.rooms;
import entity.users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author GIGABYTE
 */
@WebServlet(name = "ShowtimeController", urlPatterns = {"/ShowtimeURL"})
public class ShowtimeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        users user = (session != null) ? (users) session.getAttribute("acc") : null;

        // Nếu chưa đăng nhập hoặc không phải Manager thì chặn
        if (user == null || (user.getRole_id()!= 2)) {
            response.sendRedirect("AccessDenied.jsp");
            return;
        }
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service.equals("listShowtimeByRoom")) {
                listShowtimeByRoom(request, response, session, user);
            }
        }
    }

    private void listShowtimeByRoom(HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            users user)
            throws ServletException, IOException {
        showtimesDAO dao = new showtimesDAO();
        String submit = request.getParameter("submit");
        if (submit != null) {
            int room_id = Integer.parseInt(request.getParameter("room_id"));
            int movie_id = Integer.parseInt(request.getParameter("movie_id"));
            request.setAttribute("room_id", room_id);
            request.setAttribute("movie_id", movie_id);
            List<extend_showtimes> showtimeList = dao.listShowtimeByRoom(room_id,movie_id);
            request.setAttribute("showtimeList", showtimeList);
        }
        List<rooms> roomList = dao.listRoomByDirector(user.getUser_id());
        if(roomList.isEmpty())roomList = dao.listRoomByManager(user.getUser_id());
        List<movies> movieList = dao.listMovie();
        request.setAttribute("roomList", roomList);
        request.setAttribute("movieList", movieList);
        request.getRequestDispatcher("list_showtime.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
