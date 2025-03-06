/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

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
import tool.TypeValidator;

/**
 *
 * @author default
 */
@WebServlet(name = "VerifyOTPChange_password", urlPatterns = {"/verify_otp_change_password"})
public class VerifyOTPChange_password extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String inputOtp2 = request.getParameter("otp2");
        String generatedOtp2 = (String) session.getAttribute("otp2");
        Long otpExpireTime2 = (Long) session.getAttribute("otpExpireTime2");
        
        

        if (System.currentTimeMillis() > otpExpireTime2) {
            // OTP hết hạn, quay về trang đăng ký
            session.setAttribute("msg", "Baby2");
            session.invalidate();
            response.sendRedirect("ResetPassword.jsp?mess=OTP expired. Please sign up again.");
            return;
        }

        if (inputOtp2.equals(generatedOtp2)) {
            // OTP đúng, chuyển sang nhập password mới
            response.sendRedirect("new_password.jsp");
            
            
        } else {
            // OTP sai, giữ lại trang xác thực
            request.setAttribute("mess", "Invalid OTP. Please try again.");
            request.getRequestDispatcher("otp_verification2.jsp").forward(request, response);
        }
    }
}
