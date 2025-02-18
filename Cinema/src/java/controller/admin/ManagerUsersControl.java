/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.UserDAO;
import entity.User;
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
 * @author trung
 */
@WebServlet(name = "ManagerUsersControl", urlPatterns = {"/admin/users"})
public class ManagerUsersControl extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManagerUsersControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerUsersControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        UserDAO userDAO = new UserDAO();
        // list user
        List<User> listUser = userDAO.getAllUser();
        request.setAttribute("listUser", listUser);

        // view detail
        if (request.getParameter("action") != null) {
            String action = request.getParameter("action");
            if (action.equals("view-detail")) {

                int id = Integer.parseInt(request.getParameter("id"));
                User user = userDAO.getAccountById(id);
                request.setAttribute("userDetail", user);
                request.getRequestDispatcher("manager_users_detail.jsp").forward(request, response);
                return;
            }
        }

        request.getRequestDispatcher("manager_users.jsp").forward(request, response);
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

        String action = request.getParameter("action");
        if (action.equals("create")) {
            String fullname = request.getParameter("fullname");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String role = request.getParameter("role");
            int roleid = 2;
            if (role.equals("admin")) {
                roleid = 1;
            }
            String password = request.getParameter("password");

            // Gọi DAO để lưu user vào database
            UserDAO userDao = new UserDAO();
            boolean success = userDao.createUser(new User(roleid, username, password, fullname, email, phone, address));

            if (success) {
                response.sendRedirect("users?action=list"); // Chuyển hướng lại danh sách user
            } else {
                request.setAttribute("error", "Failed to create user!");
                request.getRequestDispatcher("users?action=list").forward(request, response);
            }
        }
        if (action.equals("edit")) {

            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            UserDAO userDao = new UserDAO();

            boolean isUpdated = userDao.updateUserInDatabase(fullname, email, phone, address);
            if (isUpdated) {
                HttpSession session = request.getSession();
                session.setAttribute("message", "Create success");

                response.sendRedirect("users?action=list"); // Chuyển hướng lại danh sách user

            } else {
                request.setAttribute("error", "Failed to create user!");
                request.getRequestDispatcher("users?action=list").forward(request, response);
            }
        }

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
