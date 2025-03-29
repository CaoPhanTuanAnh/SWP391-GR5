/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.guest;

import dao.combosDAO;
import dao.roomsDAO;
import dao.theatersDAO;
import dao.moviesDAO;
import entity.combos;
import entity.rooms;
import entity.theaters;
import entity.movies;
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
 * @author PCASUS
 */
@WebServlet(name = "BillController", urlPatterns = {"/BillController"})
public class BillController extends HttpServlet {

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
        // Lấy session và kiểm tra user
        HttpSession session = request.getSession(false);
        users user = (session != null) ? (users) session.getAttribute("acc") : null;
        // Nếu chưa đăng nhập hoặc không phải Manager thì chặn
        if (user == null || (user.getRole_id() != 2) || (user.getRole_id() != 1) || (user.getRole_id() != 3)) {
            response.sendRedirect("sign_in.jsp");
            return;
        }
        String mid_raw = request.getParameter("mid");
        String branchId_raw = request.getParameter("branchId");
        String startDate_raw = request.getParameter("startDate");
        String startTime_raw = request.getParameter("startTime");
        String roomId_raw = request.getParameter("roomId");
        String[] selectedSeats = request.getParameterValues("seats");
        moviesDAO a = new moviesDAO();
        theatersDAO b = new theatersDAO();
        roomsDAO c = new roomsDAO();
        combosDAO d = new combosDAO();

        try {
            int mid = Integer.parseInt(mid_raw);
            int branchId = Integer.parseInt(branchId_raw);
            int roomId = Integer.parseInt(roomId_raw);

            movies movie = a.getMovie(mid);
            theaters brand = b.getBrand(branchId);
            rooms room = c.getRoom(roomId);
            List<combos> e = d.listCombo();

            request.setAttribute("room", room);
            request.setAttribute("movie", movie);
            request.setAttribute("brand", brand);
            request.setAttribute("startDate", startDate_raw);
            request.setAttribute("startTime", startTime_raw);
            request.setAttribute("mid", mid);
            request.setAttribute("branchId", branchId);
            request.setAttribute("roomId", roomId);
            request.setAttribute("e", e);
            request.setAttribute("selectedSeats", selectedSeats);

        } catch (Exception e) {
            log("Error at HomeController: " + e.toString());
        } finally {
            request.getRequestDispatcher("bill.jsp").forward(request, response);
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