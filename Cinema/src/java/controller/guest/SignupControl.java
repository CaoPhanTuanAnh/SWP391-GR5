/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.guest;

import dao.DAO;
import entity.User;
import controller.customer.SendEmailControl;
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
@WebServlet(name = "SignupControl", urlPatterns = {"/signup"})
public class SignupControl extends HttpServlet {

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
        String user = request.getParameter("sign-up-name");
        String pass = request.getParameter("sign-up-password");
        String re_pass = request.getParameter("sign-up-repassword");
        String fullname = request.getParameter("sign-up-fullname");
        String email = request.getParameter("sign-up-email");
        String phone = request.getParameter("sign-up-phone");
        String address = request.getParameter("sign-up-address");
        
        if (user == null || user.trim().isEmpty() ||
            pass == null || pass.trim().isEmpty() ||
            re_pass == null || re_pass.trim().isEmpty() ||
            fullname == null || fullname.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            phone == null || phone.trim().isEmpty() ||
            address == null || address.trim().isEmpty()) {

            request.setAttribute("mess", "All fields must be filled.");
            request.getRequestDispatcher("sign_up.jsp").forward(request, response);
            return;
        }else if(!pass.equals(re_pass)){
            request.setAttribute("mess", "Password do not match.");
            request.getRequestDispatcher("sign_up.jsp").forward(request, response);
        }else if (pass.contains(" ")) {
            request.setAttribute("mess", "Username should not contain spaces.");
            request.getRequestDispatcher("sign_up.jsp").forward(request, response);
        } else if (user.contains(" ")) {
            request.setAttribute("mess", "Password should not contain spaces.");
            request.getRequestDispatcher("sign_up.jsp").forward(request, response);
        } else if (!pass.matches("[a-zA-Z0-9]+")) {  // Chỉ cho phép chữ cái và số
            request.setAttribute("mess", "Password must not contain special characters.");
            request.getRequestDispatcher("sign_up.jsp").forward(request, response);
        }else{
            DAO dao = new DAO();
            User a = dao.checkUserExist(user);
            User b = dao.checkEmailExist(email);
            
            SendEmailControl sm= new SendEmailControl();
            String code1 = sm.getRandom();
            HttpSession session = request.getSession();
            session.setAttribute("authcode", code1);
            
            if(a==null&&b==null){
                User c = new User();
                boolean test =sm.sendEmail(c,code1);
                //if(test){

                    //response.sendRedirect("VerifyCode.jsp");
                //}
                //dao.signup(user, pass,fullname,email,phone,address);
                response.sendRedirect("VerifyCode.jsp");
                //response.sendRedirect("home");
            }else if(a!=null){
                request.setAttribute("mess", "Username already exists.");
                request.getRequestDispatcher("sign_up.jsp").forward(request, response);
            }else if(b!=null){
                request.setAttribute("mess", "Email already exists.");
                request.getRequestDispatcher("sign_up.jsp").forward(request, response);
                }
            else{
                request.setAttribute("mess", "error.");
                request.getRequestDispatcher("sign_up.jsp").forward(request, response);
            }
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
