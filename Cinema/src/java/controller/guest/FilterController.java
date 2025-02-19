/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.guest;

import dao.genresDAO;
import dao.moviesDAO;
import entity.genres;
import entity.movies;
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
 * @author PCASUS
 */
@WebServlet(name = "FilterController", urlPatterns = {"/FilterController"})
public class FilterController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try {
            moviesDAO mv = new moviesDAO();
            genresDAO gr = new genresDAO();
            List<genres> genres = gr.getAllGenres();
            String name = request.getParameter("name");
         String showDate = request.getParameter("showDate");
            String showTimeFrom = request.getParameter("showTimeFrom");
            String showTimeTo = request.getParameter("showTimeTo");
            String[] cid_raw = request.getParameterValues("cid");

         int[] cid;
            if (cid_raw != null && cid_raw.length > 0) {
                cid = new int[cid_raw.length];
                for (int i = 0; i < cid_raw.length; i++) {
                    cid[i] = Integer.parseInt(cid_raw[i]);
                }
            } else {
                cid = new int[0];
            }
       
                final int PAGE_SIZE = 5;
            HttpSession session = request.getSession();
            session.setAttribute("destPage", "movies");
            
            String pagenumber = request.getParameter("pagenumber");
            int page = 1;
            if (pagenumber != null) {
                page = Integer.parseInt(pagenumber);
            }

            List<movies> listMovies = mv.searchMovies(cid, showDate, showTimeFrom, showTimeTo, page, PAGE_SIZE, name);
            int totalMovies = mv.getTotalMovies();
            int totalPage = totalMovies / PAGE_SIZE;
            if (totalMovies % PAGE_SIZE != 0) {
                totalPage += 1;
            }

            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("listMovies", listMovies);
            request.setAttribute("categorys", genres);

            session.setAttribute("urlHistory", "SearchMovieController"
                    + ((pagenumber != null) ? ("?pagenumber=" + pagenumber) : ""));
            request.getRequestDispatcher("movies.jsp").forward(request, response);

        } catch (Exception e) {
            log("Error at ViewAllController: " + e.toString());
        }
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
