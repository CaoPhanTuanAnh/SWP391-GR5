/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.guest;

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

/**
 *
 * @author default
 */
@WebServlet(name = "VerifyOTPSignUp", urlPatterns = {"/verify_otp"})
public class VerifyOTPSignUp extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String inputOtp = request.getParameter("otp");
        String generatedOtp = (String) session.getAttribute("otp");
        Long otpExpireTime = (Long) session.getAttribute("otpExpireTime");

        if (System.currentTimeMillis() > otpExpireTime) {
            // OTP hết hạn, quay về trang đăng ký
             session.setAttribute("msg", "Baby");
            session.invalidate();
           
            response.sendRedirect("sign_in.jsp?mess=OTP expired. Please sign up again.");
            return;
        }

        if (inputOtp.equals(generatedOtp)) {
            // OTP đúng, tạo tài khoản trong database
            users newUser = (users) session.getAttribute("signupUser");
            usersDAO usersDAO = new usersDAO();
            usersDAO.createUser(newUser);

            session.invalidate();
            response.sendRedirect("sign_in.jsp?mess=Account created successfully. Please log in.");
        } else {
            // OTP sai, giữ lại trang xác thực
            request.setAttribute("mess", "Invalid OTP. Please try again.");
            request.getRequestDispatcher("otp_verification.jsp").forward(request, response);
        }
    }
}
