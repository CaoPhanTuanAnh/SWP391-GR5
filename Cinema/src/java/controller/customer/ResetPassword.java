/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dao.DAO;
import dao.usersDAO;
import entity.users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.EmailService;
import service.MaHoa;
import service.TypeValidator;

/**
 *
 * @author default
 */
@WebServlet(name = "ResetPassword", urlPatterns = {"/reset_password"})
public class ResetPassword extends HttpServlet {

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
            out.println("<title>Servlet ResetPassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPassword at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("new_password.jsp").forward(request, response);
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

        HttpSession session = request.getSession();
        String email_change_password = (String) session.getAttribute("email_change_password");
        String newPassword = request.getParameter("new_password");
        String reNewPassword = request.getParameter("re_new_password");
        String mess = "";
        if (newPassword == null || !newPassword.equals(reNewPassword)) {
            mess = "Passwords do not match!";
            request.setAttribute("mess", mess);
            request.getRequestDispatcher("new_password.jsp").forward(request, response);
            return;
        }
        try {
            
            if (TypeValidator.validatePassword(newPassword)) {
                newPassword = MaHoa.toSHA1(newPassword);
                usersDAO dao = new usersDAO();
                boolean updateResult = dao.updatePassword(email_change_password, newPassword);
                if (updateResult) {
                    mess = "Password changed successfully!";
                    // Xoá các thông tin liên quan đến OTP khỏi session
                    session.removeAttribute("otp2");
                    session.removeAttribute("otpExpireTime2");
                    session.removeAttribute("email_change_password");
                    // Chuyển hướng về trang đăng nhập
                    response.sendRedirect("sign_in.jsp");
                    return;
                } else {
                    mess = "Error: Unable to update password. Please try again.";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("new_password.jsp").forward(request, response);
                }
            } else {
                mess = "New password does not meet security requirements.";
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("new_password.jsp").forward(request, response);
            }
        } catch (Exception e) {
            Logger.getLogger(ResetPassword.class.getName()).log(Level.SEVERE, null, e);
            mess = "Password must have 8-16 characters,\n" +
"                                 1 special character, 1 digit,\n" +
"                                 1 uppercase and 1 lowercase charater!";
            request.setAttribute("mess", mess);
            request.getRequestDispatcher("new_password.jsp").forward(request, response);
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
