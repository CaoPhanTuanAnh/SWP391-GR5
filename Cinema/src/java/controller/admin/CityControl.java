/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.CityDAO;
import entity.City;
import entity.User;
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
        User user = (session != null) ? (User) session.getAttribute("acc") : null;

        // Nếu chưa đăng nhập hoặc không phải Admin
        if (user == null || (user.getRole() != 1)) {
            response.sendRedirect("AccessDenied.jsp");
            return;
        }
        try (PrintWriter out = response.getWriter()) {
            User admin = (User) session.getAttribute("acc");
            if (admin != null && admin.getRole() == 1) {
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
            String cityName = request.getParameter("cityName");
            CityDAO dao = new CityDAO();
            if (cityName == null) {
                mess = "City name can't be null!";
            } else if (cityName.isBlank()) {
                mess = "City name can't be empty!";
            } else if (!dao.editCity(cityID, cityName)) {
                mess = "Something go wrong!";
            } else {
                mess = "City edited successfully!";
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
            CityDAO dao = new CityDAO();
            if (!dao.deleteCity(cityID)) {
                mess = "Something go wrong!";
            } else {
                mess = "City added successfully!";
            }
        } catch (NumberFormatException e) {
            mess = "Id not int ?????";
        }
        request.setAttribute("mess", mess);
    }

    private void addCity(HttpServletRequest request) {
        String cityName = request.getParameter("cityName");
        String mess = "";
        if (cityName == null) {
            mess = "City name can't be null!";
        } else if (cityName.isBlank()) {
            mess = "City name can't be empty!";
        } else {
            //check name policy
            CityDAO dao = new CityDAO();
            if (!dao.addCity(cityName)) {
                mess = "Something go wrong!";
            } else {
                mess = "City added successfully!";
            }
        }
        request.setAttribute("mess", mess);
    }

    private void listCity(HttpServletRequest request) {
        CityDAO dao = new CityDAO();
        List<City> cityList = dao.listCity();
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
/*<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.City" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>City List</title>
    </head>
    <body>
        <h1>List of Cities</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>City ID</th>
                    <th>City Name</th>
                    <th>Number of Theater</th>
                    <th>Edit</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.cityList}" var="city" >
                    <tr>
                        <td>${city.getCityID()}</td>
                        <td>${city.getCityName()}</td>
                        <td>${city.getNumOfTheater()}</td>
                        <td><a href="city_control?cityID=${city.getCityID()}">Edit</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>*/
