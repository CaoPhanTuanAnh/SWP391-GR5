/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.citiesDAO;
import entity.cities;
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
 * @author GIGABYTE
 */
@WebServlet(name = "CityControl", urlPatterns = {"/city_control"})
public class CityControl extends HttpServlet {

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

        // Nếu chưa đăng nhập hoặc không phải Admin
        if (user == null || (user.getRole_id() != 1)) {
            response.sendRedirect("AccessDenied.jsp");
            return;
        }
        try (PrintWriter out = response.getWriter()) {
            users admin = (users) session.getAttribute("acc");
            if (admin != null && admin.getRole_id() == 1) {
                String service = request.getParameter("service");
                if (service == null) {
                    service = "listCity";
                }
                if (service.equals("addCity")) {
                    addCity(request);
                } else if (service.equals("deleteCity")) {
                    deleteCity(request);
                } else if (service.equals("editCity")) {
                    editCity(request);
                }
                listCity(request);
            }
            request.getRequestDispatcher("list_city.jsp").forward(request, response);
        }
    }

    private void editCity(HttpServletRequest request) {
        String mess = "";
        try {
            int cityID = Integer.parseInt(request.getParameter("cityID"));
            String cityName = request.getParameter("cityName").trim();
            citiesDAO dao = new citiesDAO();
            if (cityName == null) {
                mess = "City name can't be null!";
            } else if (cityName.isBlank()) {
                mess = "City name can't be empty!";
            } else if (cityName.isBlank()) {
                mess = "City name can't be empty!";
            } else {
                List<cities> cityList = dao.listCity();
                boolean used = false;
                for (cities city : cityList) {
                    if (city.getCity_id()!=cityID&&city.getCity_name().toLowerCase().equals(cityName.toLowerCase())) {
                        used = true;
                    }
                }
                if (used) {
                    mess = "City name should be unique!";
                } else {
                    if (!dao.editCity(cityID, cityName)) {
                        mess = "Something go wrong!";
                    } else {
                        mess = "City edited successfully!";
                    }
                }
            }
        } catch (NumberFormatException e) {
            mess = "Id not int ?????";
        }
        request.setAttribute("mess", mess);
    }

    private void deleteCity(HttpServletRequest request) {
        String mess = "";
        try {
            int cityID = Integer.parseInt(request.getParameter("cityID"));
            citiesDAO dao = new citiesDAO();
            if (!dao.deleteCity(cityID)) {
                mess = "Can not delete city that currently used!";
            } else {
                mess = "City deleted successfully!";
            }
        } catch (NumberFormatException e) {
            mess = "Id not int ?????";
        }
        request.setAttribute("mess", mess);
    }

    private void addCity(HttpServletRequest request) {
        String cityName = request.getParameter("cityName").trim();
        String mess = "";
        citiesDAO dao = new citiesDAO();
        if (cityName == null) {
            mess = "City name can't be null!";
        } else if (cityName.isBlank()) {
            mess = "City name can't be empty!";
        } else {
            List<cities> cityList = dao.listCity();
            boolean used = false;
            for (cities city : cityList) {
                if (city.getCity_name().toLowerCase().equals(cityName.toLowerCase())) {
                    used = true;
                }
            }
            if (used) {
                mess = "City name should be unique!";
            } else {
                if (!dao.addCity(cityName)) {
                    mess = "Something go wrong!";
                } else {
                    mess = "City added successfully!";
                }
            }
        }
        request.setAttribute("mess", mess);
    }

    private void listCity(HttpServletRequest request) {
        citiesDAO dao = new citiesDAO();
        List<cities> cityList = dao.listCity();
        request.setAttribute("cityList", cityList);
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
