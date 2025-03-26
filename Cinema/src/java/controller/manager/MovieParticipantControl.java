/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dao.movieparticipantDAO;
import entity.movie_participants;
import entity.movies;
import entity.participants;
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
 * @author default
 */
@WebServlet(name = "MovieParticipantControl", urlPatterns = {"/movie_participant_control"})
public class MovieParticipantControl extends HttpServlet {

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

        // Nếu chưa đăng nhập hoặc không phải Admin
        if (user == null || (user.getRole_id()!= 1)) {
            response.sendRedirect("AccessDenied.jsp");
            return;
        }
        try (PrintWriter out = response.getWriter()) {
            users admin = (users) session.getAttribute("acc");
            if (admin != null && admin.getRole_id()== 1) {
                String service = request.getParameter("service");
                if (service == null) {
                    service = "listMovie_participant";
                }
                if (service.equals("addMovie_participant")) {
                    addMovieParticipant(request);
                } else if (service.equals("deleteMovieParticipant")) {
                    deleteMovieParticipant(request);
                } else if (service.equals("editMovie_participant")) {
                    editMovieParticipant(request);
                }
                listMovieParticipant(request);
            }
            request.getRequestDispatcher("list_movie_participant.jsp").forward(request, response);
        }
    }

    private void editMovieParticipant(HttpServletRequest request) {
        String mess = "";
        try {
            int MovieID = Integer.parseInt(request.getParameter("MovieID"));
            int ParticipantID = Integer.parseInt(request.getParameter("ParticipantID"));
            String role = request.getParameter("role_type");
            int newMovieID = Integer.parseInt(request.getParameter("newMovieID"));
            int newParticipantID = Integer.parseInt(request.getParameter("newParticipantID"));
            movieparticipantDAO dao = new movieparticipantDAO();
            if (!dao.editMovieParticipant( newMovieID,newParticipantID, role, MovieID, ParticipantID)) {
                mess = "Something go wrong!";
                System.out.println("loi cho ni");
            } else {
                mess = "edited successfully!";
                System.out.println("loi cho kia");
            }
        } catch (NumberFormatException e) {
            mess = "Id not int ?????";
            System.out.println("loi cho nay");
        }
        request.setAttribute("mess", mess);
    }

    private void deleteMovieParticipant(HttpServletRequest request) {
        String mess = "";
        try {
            int MovieID = Integer.parseInt(request.getParameter("movie_participantMovieID"));
            int ParticipantID = Integer.parseInt(request.getParameter("movie_participantParticipantID"));
            movieparticipantDAO dao = new movieparticipantDAO();
            if (!dao.deleteMovieParticipant(MovieID,ParticipantID)) {
                mess = "Something go wrong!";
            } else {
                mess = "added successfully!";
            }
        } catch (NumberFormatException e) {
            mess = "Id not int ?????";
        }
        request.setAttribute("mess", mess);
    }

    private void addMovieParticipant(HttpServletRequest request) {
        int MovieID = Integer.parseInt(request.getParameter("movie_participantMovieID"));
        int ParticipantID = Integer.parseInt(request.getParameter("movie_participantParticipantID"));
        String role = request.getParameter("role_type");
        String mess = "";
        
        movieparticipantDAO dao = new movieparticipantDAO();
        if (!dao.addMovieParticipant(MovieID, ParticipantID, role)) {
            mess = "Something go wrong!";
        } else {
            mess = "added successfully!";
        }
        request.setAttribute("mess", mess);
    }

    private void listMovieParticipant(HttpServletRequest request) {
        movieparticipantDAO dao = new movieparticipantDAO();
        List<movie_participants> MovieParticipantList = dao.listMovieParticipant();
        request.setAttribute("movie_participantList", MovieParticipantList);
        
        List<participants> participantList = dao.listParticipant();
        List<movies> movieList = dao.listMovie();
        request.setAttribute("participantList", participantList);
        request.setAttribute("movieList", movieList);
        //request.getRequestDispatcher("list_movie_participant.jsp").forward(request, response);
        
        
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
