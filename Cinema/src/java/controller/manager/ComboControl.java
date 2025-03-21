/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dao.combosDAO;
import entity.combos;
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
@WebServlet(name = "ComboControl", urlPatterns = {"/combo_control"})
public class ComboControl extends HttpServlet {

    
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
                    service = "listCombo";
                }
                if (service.equals("addCombo")) {
                    addCombo(request);
                } else if (service.equals("deleteCombo")) {
                    deleteCombo(request);
                } else if (service.equals("editCombo")) {
                    editCombo(request);
                }
                listCombo(request);
            }
            request.getRequestDispatcher("list_combo.jsp").forward(request, response);
        }
    }

    private void editCombo(HttpServletRequest request) {
        String mess = "";
        try {
            int comboID = Integer.parseInt(request.getParameter("comboID"));
            String comboName = request.getParameter("comboName");
            String detail = request.getParameter("detail");
            double comboPrice = Double.parseDouble(request.getParameter("comboPrice"));
            combosDAO dao = new combosDAO();
            if (comboName == null) {
                mess = "Combo name can't be null!";
            } else if (comboName.isBlank()) {
                mess = "Combo name can't be empty!";
            } else if (!dao.editCombo(comboID, comboName, detail, comboPrice)) {
                mess = "Something go wrong!";
            } else {
                mess = "Combo edited successfully!";
            }
        } catch (NumberFormatException e) {
            mess = "Id not int ?????";
        }
        request.setAttribute("mess", mess);
    }

    private void deleteCombo(HttpServletRequest request) {
        String mess = "";
        try {
            int comboID = Integer.parseInt(request.getParameter("comboID"));
            combosDAO dao = new combosDAO();
            if (!dao.deleteCombo(comboID)) {
                mess = "Something go wrong!";
            } else {
                mess = "Combo added successfully!";
            }
        } catch (NumberFormatException e) {
            mess = "Id not int ?????";
        }
        request.setAttribute("mess", mess);
    }

    private void addCombo(HttpServletRequest request) {
        String comboName = request.getParameter("comboName");
        String detail = request.getParameter("detail");
        double comboPrice = Double.parseDouble(request.getParameter("comboPrice"));
        String mess = "";
        if (comboName == null) {
            mess = "Combo name can't be null!";
        } else if (comboName.isBlank()) {
            mess = "Combo name can't be empty!";
        } else {
            //check name policy
            combosDAO dao = new combosDAO();
            if (!dao.addCombo(comboName,detail,comboPrice)) {
                mess = "Something go wrong!";
            } else {
                mess = "Combo added successfully!";
            }
        }
        request.setAttribute("mess", mess);
    }

    private void listCombo(HttpServletRequest request) {
        combosDAO dao = new combosDAO();
        List<combos> comboList = dao.listCombo();
        request.setAttribute("comboList", comboList);
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
