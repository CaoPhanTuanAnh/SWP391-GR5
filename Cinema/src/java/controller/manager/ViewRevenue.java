/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dao.revenueDAO;
import entity.revenue_data;
import entity.users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GIGABYTE
 */
@WebServlet(name = "ViewRevenue", urlPatterns = {"/ViewRevenue"})
public class ViewRevenue extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        users user = (session != null) ? (users) session.getAttribute("acc") : null;

        // Nếu chưa đăng nhập hoặc không phải Manager thì chặn
        if (user == null || (user.getRole_id() != 1)) {
            response.sendRedirect("AccessDenied.jsp");
            return;
        }
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if ("viewRevenueByDay".equals(service)) {
                viewRevenueByDay(request, response, session, user);
            } else if ("viewRevenueByMovie".equals(service)) {
                viewRevenueByMovie(request, response, session, user);
            } else {
                viewRevenueByMovie(request, response, session, user);
            }
        }
    }

    private void viewRevenueByMovie(HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            users user)
            throws ServletException, IOException {
        revenueDAO dao = new revenueDAO();
        List<revenue_data> revenueView = dao.viewRevenueByMovie();
        List<String> xValue = new ArrayList<>();
        List<Double> yValue = new ArrayList<>();
        double total = 0;
        for (revenue_data x : revenueView) {
            xValue.add("'" + x.getXValue() + "'");
            yValue.add(x.getYValue());
            total+=x.getYValue();
        }
        request.setAttribute("service", "viewRevenueByMovie");
        request.setAttribute("xValue", xValue);
        request.setAttribute("yValue", yValue);
        request.setAttribute("total", total);
        request.getRequestDispatcher("ViewRevenue.jsp").forward(request, response);
    }

    private void viewRevenueByDay(HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            users user)
            throws ServletException, IOException {
        revenueDAO dao = new revenueDAO();
        String smonth = request.getParameter("month");
        String syear = request.getParameter("year");
        int month, year;
        try {
            month = Integer.parseInt(smonth);
        } catch (Exception e) {
            month = LocalDateTime.now().getMonthValue();
        }
        try {
            year = Integer.parseInt(syear);
        } catch (Exception e) {
            year = LocalDateTime.now().getYear();
        }
        List<revenue_data> revenueView = dao.viewRevenueByDay(month, year);
        List<String> xValue = new ArrayList<>();
        List<Double> yValue = new ArrayList<>();
        double total = 0;
        for (revenue_data x : revenueView) {
            xValue.add("'" + x.getXValue() + "'");
            yValue.add(x.getYValue());
            total+=x.getYValue();
        }
        request.setAttribute("service", "viewRevenueByDay");
        request.setAttribute("month", month);
        request.setAttribute("year", year);
        request.setAttribute("xValue", xValue);
        request.setAttribute("yValue", yValue);
        request.setAttribute("total", total);
        request.getRequestDispatcher("ViewRevenue.jsp").forward(request, response);
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
