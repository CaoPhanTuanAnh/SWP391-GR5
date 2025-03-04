/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dao.DAO;
import dao.roomsDAO;
import entity.City;
import entity.Theater;
import entity.User;
import entity.rooms;
import entity.types;
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
 * @author 84912
 */
@WebServlet(name = "ManageRoomDetail", urlPatterns = {"/ManageRoomDetail"})
public class ManageRoomDetail extends HttpServlet {

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
        // Lấy session và kiểm tra user
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("acc") : null;

        // Nếu chưa đăng nhập hoặc không phải Manager thì chặn
        if (user == null || (user.getRole() != 2)) {
            response.sendRedirect("AccessDenied.jsp");
            return;
        }
        roomsDAO roomsDAO = new roomsDAO();

        // Lấy theaterId từ request
        String theaterIdParam = request.getParameter("theaterId");
        if (theaterIdParam != null) {
            int theaterId = Integer.parseInt(theaterIdParam);
            DAO dao = new DAO();
            List<types> type = roomsDAO.getAllTypes();
            request.setAttribute("type", type);
            List<User> listU = dao.getAllUser();
            List<Theater> listT = dao.getAllTheater();
            request.setAttribute("listT", listT);
            request.setAttribute("listU", listU);
            // Lấy danh sách phòng của rạp
            List<rooms> listR = roomsDAO.getAllRoomByManagerIDAndTheaterID(user.getID(), theaterId);
            request.setAttribute("listR", listR);
            // Lấy thông tin rạp
            Theater selectedTheater = roomsDAO.getTheaterById(theaterId);
            request.setAttribute("selectedTheater", selectedTheater);

        }
        request.getRequestDispatcher("ManageRoomDetail.jsp").forward(request, response);

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
