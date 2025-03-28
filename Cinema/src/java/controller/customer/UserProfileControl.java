/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dao.DAO;
import dao.bookingsDAO;
import dao.usersDAO;
import entity.theaters;
import entity.user_bookings;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import service.MaHoa;
import service.TypeValidator;

/**
 *
 * @author GIGABYTE
 */
@WebServlet(name = "ChangePasswordControl", urlPatterns = {"/user_profile"})
public class UserProfileControl extends HttpServlet {

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
            HttpSession session = request.getSession();
            users user = (users) session.getAttribute("acc");
            String mess = null;
            String service = request.getParameter("service");
            if (user == null) {
                response.sendRedirect("sign_in.jsp");
            } else if (service.equals("editProfile")) {
                editProfile(request, response, session, user, mess);
            } else if (service.equals("changePassword")) {
                changePassword(request, response, session, user, mess);
            } else if (service.equals("listUserBooking")) {
                listUserBooking(request, response, session, user, mess);
            } else if (service.equals("viewBookingHistory")) {
                viewBookingDetail(request, response, session, user, mess);
            } else {
                response.sendRedirect("error.html");
            }
        }
    }

    private void editProfile(HttpServletRequest request, HttpServletResponse response,
            HttpSession session, users user, String mess)
            throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String birth_date = request.getParameter("birth_date");
        String phone = request.getParameter("phone");
        String theater_id = request.getParameter("theater_id");
        if (fullName != null && birth_date != null && phone != null && theater_id != null) {
            try {
                if (TypeValidator.validateFullName(fullName)
                        && TypeValidator.validatePhone(phone)
                        && TypeValidator.validateBirthDate(birth_date)) {
                    usersDAO dao = new usersDAO();
                    if (!dao.editProfile(user.getUser_id(), fullName, phone, birth_date, Integer.parseInt(theater_id))) {
                        mess = "Something go wrong!";
                    } else {
                        mess = "Profile have been updated successfully!";
                        user.setFullname(fullName);
                        user.setPhone(phone);
                        user.setBirth_date(birth_date);
                        user.setTheater_id(Integer.parseInt(theater_id));
                    }
                }
            } catch (Exception e) {
                Logger.getLogger(usersDAO.class.getName()).log(Level.SEVERE, null, e);
                mess = e.getMessage();
            }
        }
        DAO dao = new DAO();
        List<theaters> theaterList = dao.getAllTheater();
        request.setAttribute("theaterList", theaterList);
        request.setAttribute("mess", mess);
        request.getRequestDispatcher("user_profile.jsp").forward(request, response);
    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response,
            HttpSession session, users user, String mess)
            throws ServletException, IOException {
        String oldPassword = request.getParameter("oldPassword");
        oldPassword = MaHoa.toSHA1(oldPassword);
        
        if (!user.getPassword().equals(oldPassword)) {
            mess = "Incorrect old password!";
        } else {
            String newPassword = request.getParameter("newPassword");
            String reNewPassword = request.getParameter("reNewPassword");
            if (!newPassword.equals(reNewPassword)) {
                mess = "Incorrect re-enter new password!";
            } else {
                try {
                    if (TypeValidator.validatePassword(newPassword)) {
                        newPassword = MaHoa.toSHA1(newPassword);
                        usersDAO dao = new usersDAO();
                        if (!dao.changePassword(user.getUser_id(), newPassword)) {
                            mess = "Something go wrong!";
                        } else {
                            mess = "Change password successfully!";
                            user.setPassword(newPassword);
                        }
                    }
                } catch (Exception e) {
                    Logger.getLogger(UserProfileControl.class.getName()).log(Level.SEVERE, null, e);
                    mess = e.getMessage();
                }
            }
        }
        request.setAttribute("mess", mess);
        request.getRequestDispatcher("change_password.jsp").forward(request, response);
    }

    private void listUserBooking(HttpServletRequest request, HttpServletResponse response,
            HttpSession session, users user, String mess)
            throws ServletException, IOException {
        bookingsDAO dao = new bookingsDAO();
        List<user_bookings> userBookingList = dao.listUserBooking(user.getUser_id());
        request.setAttribute("userBookingList", userBookingList);
        request.getRequestDispatcher("list_user_booking.jsp").forward(request, response);
    }
    
    private void viewBookingDetail(HttpServletRequest request, HttpServletResponse response,
            HttpSession session, users user, String mess)
            throws ServletException, IOException {
        int booking_id = Integer.parseInt(request.getParameter("booking_id"));
        bookingsDAO dao = new bookingsDAO();
        user_bookings userBooking = dao.viewBookingDetail(booking_id);
        request.setAttribute("userBooking", userBooking);
        request.getRequestDispatcher("view_booking_detail.jsp").forward(request, response);
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
