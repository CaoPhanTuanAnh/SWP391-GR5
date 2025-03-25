/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dao.participantDAO;
import entity.participants;
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
 * @author default
 */
@WebServlet(name = "ManageParticipant", urlPatterns = {"/participant_control"})
public class ManageParticipant extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        users user = (session != null) ? (users) session.getAttribute("acc") : null;

        // Nếu chưa đăng nhập hoặc không phải Manager
        if (user == null || (user.getRole_id()!= 2)) {
            response.sendRedirect("AccessDenied.jsp");
            return;
        }
        try (PrintWriter out = response.getWriter()) {
            users admin = (users) session.getAttribute("acc");
            if (admin != null && admin.getRole_id()== 2) {
                String service = request.getParameter("service");
                if (service == null) {
                    service = "listParticipant";
                }
                if (service.equals("addParticipant")) {
                    addParticipant(request);
                } else if (service.equals("deleteParticipant")) {
                    deleteParticipant(request);
                } else if (service.equals("editParticipant")) {
                    editParticipant(request);
                }
                listParticipant(request);
            }
            request.getRequestDispatcher("list_participant.jsp").forward(request, response);
        }
    }

    private void editParticipant(HttpServletRequest request) {
        String mess = "";
        try {
            int participantID = Integer.parseInt(request.getParameter("participantID"));
            String participantName = request.getParameter("participantName");
            String portrait = request.getParameter("portrait");
            String birth_date = request.getParameter("birth_date");
            String nationality = request.getParameter("nationality");
            String about = request.getParameter("about");
            participantDAO dao = new participantDAO();
            if (participantName == null) {
                mess = "participant name can't be null!";
            } else if (participantName.isBlank()) {
                mess = "participant can't be empty!";
            } else if (!dao.editParticipant(participantID, participantName, portrait, birth_date, nationality, about)) {
                mess = "Something go wrong!";
            } else {
                mess = "participant edited successfully!";
            }
        } catch (NumberFormatException e) {
            mess = "Id not int ?????";
        }
        request.setAttribute("mess", mess);
    }

    private void deleteParticipant(HttpServletRequest request) {
        String mess = "";
        try {
            int participantID = Integer.parseInt(request.getParameter("participantID"));
            participantDAO dao = new participantDAO();
            if (!dao.deleteParticipant(participantID)) {
                mess = "Something go wrong!";
            } else {
                mess = " deleted successfully!";
            }
        } catch (NumberFormatException e) {
            mess = "Id not int ?????";
        }
        request.setAttribute("mess", mess);
    }

    private void addParticipant(HttpServletRequest request) {
        String participantName = request.getParameter("participantName");
        String portrait = request.getParameter("portrait");
        String birth_date = request.getParameter("birth_date");
        String nationality = request.getParameter("nationality");
        String about = request.getParameter("about");
        String mess = "";
        if (participantName == null) {
            mess = "participant name can't be null!";
        } else if (participantName.isBlank()) {
            mess = "participant name can't be empty!";
        } else {
            //check name policy
            participantDAO dao = new participantDAO();
            if (!dao.addParticipant(participantName, portrait, birth_date, nationality, about)) {
                mess = "Something go wrong!";
            } else {
                mess = "participant added successfully!";
            }
        }
        request.setAttribute("mess", mess);
    }

    private void listParticipant(HttpServletRequest request) {
        participantDAO dao = new participantDAO();
        List<participants> participantList = dao.listParticipant();
        request.setAttribute("participantList", participantList);
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
