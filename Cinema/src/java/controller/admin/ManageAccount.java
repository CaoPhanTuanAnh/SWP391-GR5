/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.DAO;
import dao.rolesDAO;
import entity.roles;
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
 * @author 84912
 */
@WebServlet(name = "ManageAccount", urlPatterns = {"/ManageAccount"})
public class ManageAccount extends HttpServlet {

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

// Kiểm tra session và quyền admin
        HttpSession session = request.getSession(false);
        users user = (session != null) ? (users) session.getAttribute("acc") : null;
        if (user == null || (user.getRole_id() != 1)) {
            response.sendRedirect("AccessDenied.jsp");
            return;
        }

        DAO dao = new DAO();
        rolesDAO rolesDAO = new rolesDAO();

// Lấy thông tin phân trang
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int start = (page - 1) * recordsPerPage;

// Lấy từ khóa tìm kiếm
        String keyword = request.getParameter("search");
        if (keyword == null) {
            keyword = "";
        }

// Lấy danh sách user theo tìm kiếm hoặc toàn bộ
        List<users> listU;
        int totalRecords;
        if (keyword.isEmpty()) {
            listU = dao.getUsersByPage(start, recordsPerPage);
            totalRecords = dao.getTotalUserCount();
        } else {
            listU = dao.searchUsers(keyword, start, recordsPerPage);
            totalRecords = dao.getTotalSearchCount(keyword);
        }

        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

// Gửi dữ liệu đến JSP
        request.setAttribute("listU", listU);
        request.setAttribute("listRole", rolesDAO.getAllRole());
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("search", keyword);
        if (listU.isEmpty()) {
            request.setAttribute("message", "Not found");
        }
        request.getRequestDispatcher("ManageAccount.jsp").forward(request, response);

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
