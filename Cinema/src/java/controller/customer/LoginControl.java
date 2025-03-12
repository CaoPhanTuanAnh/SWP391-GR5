/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.customer;

import dao.DAO;
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

@WebServlet(name = "LoginControl", urlPatterns = {"/login"})
public class LoginControl extends HttpServlet {

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
        try {
            String user = request.getParameter("username");
            String pass = request.getParameter("password");
            DAO dao = new DAO();
            users a = dao.login(user, pass);
            if(a == null){
                request.setAttribute("mess", "Wrong username or password");
                request.getRequestDispatcher("sign_in.jsp").forward(request, response);
            }else{
                HttpSession session = request.getSession();
                session.setAttribute("acc", a);
                session.setAttribute("managerId", a.getUser_id()); // Lưu ID của manager
                response.sendRedirect("home");
            }
        }catch(Exception e){
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
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        DAO dao = new DAO();
        users a = dao.login(user, pass);
        if(a == null){
                request.setAttribute("mess", "Wrong username or password");
                request.getRequestDispatcher("sign_in.jsp").forward(request, response);
            }else{
                if (a.getStatus().equals("Active")) {
                    HttpSession session = request.getSession();
                    session.setAttribute("acc", a);
                    session.setAttribute("managerId", a.getUser_id()); // Lưu ID của manager
                    response.sendRedirect("home");
                }else{
                    request.setAttribute("mess", "Account has been banned");
                    request.getRequestDispatcher("sign_in.jsp").forward(request, response);
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