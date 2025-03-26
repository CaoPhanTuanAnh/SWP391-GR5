/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import controller.admin.*;
import dao.couponDAO;
import entity.coupon;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;

/**
 *
 * @author PCASUS
 */
@WebServlet(name = "EditCoupon", urlPatterns = {"/EditCoupon"})
public class EditCoupon extends HttpServlet {

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
        try {
            // Lấy dữ liệu từ form
            int couponID = Integer.parseInt(request.getParameter("couponID"));
            String couponCode = request.getParameter("couponCode");
            double discountPercentage = Double.parseDouble(request.getParameter("discountPercentage"));
            Date expiryDate = Date.valueOf(request.getParameter("expiryDate"));

            // Tạo đối tượng Coupon
            coupon updatedCoupon = new coupon();
            updatedCoupon.setCoupon_code(couponCode);
            updatedCoupon.setUser_id(0); // Nếu user_id không cần thiết, có thể bỏ qua hoặc đặt giá trị mặc định
            updatedCoupon.setDiscount_percentage(discountPercentage);
            updatedCoupon.setExpiry_date(expiryDate);
            updatedCoupon.setCoupon_id(couponID);

            // Gọi DAO để cập nhật database
            couponDAO dao = new couponDAO();
            boolean success = dao.updateCoupon(updatedCoupon);

            // Chuyển hướng lại trang quản lý Coupon
            if (success) {
                response.sendRedirect("coupons");
            } else {
                response.sendRedirect("coupons");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("coupons");
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
