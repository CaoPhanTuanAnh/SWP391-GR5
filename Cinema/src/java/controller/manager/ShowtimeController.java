/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dao.showtimesDAO;
import entity.extend_showtimes;
import entity.movies;
import entity.rooms;
import entity.users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 *
 * @author GIGABYTE
 */
@WebServlet(name = "ShowtimeController", urlPatterns = {"/ShowtimeURL"})
public class ShowtimeController extends HttpServlet {

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
        if (user == null || (user.getRole_id() != 2)) {
            response.sendRedirect("AccessDenied.jsp");
            return;
        }
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service.equals("listShowtimeByRoom")) {
                listShowtimeByRoom(request, response, session, user);
            } else if (service.equals("addShowtime")) {
                addShowtime(request, response, session, user);
            } else if (service.equals("deleteShowtime")) {
                deleteShowtime(request, response, session, user);
            } else if (service.equals("submitShowtime")) {
                submitShowtime(request, response, session, user);
            } else if (service.equals("editShowtime")) {
                editShowtime(request, response, session, user);
            }
        }
    }

    private void listShowtimeByRoom(HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            users user)
            throws ServletException, IOException {
        showtimesDAO dao = new showtimesDAO();
        String submit = request.getParameter("submit");
        if (submit != null) {
            int room_id = Integer.parseInt(request.getParameter("room_id"));
            int movie_id = Integer.parseInt(request.getParameter("movie_id"));
            String dateString = request.getParameter("date");
            Date date = dateString == null || dateString.isBlank() ? null : Date.valueOf(request.getParameter("date"));
            String status = request.getParameter("status");
            session.setAttribute("list_st_room_id", room_id);
            session.setAttribute("list_st_movie_id", movie_id);
            session.setAttribute("list_st_date", date);
            session.setAttribute("list_st_status", status);
            List<extend_showtimes> showtimeList = dao.listShowtimeByRoom(room_id, movie_id, date, status);
            request.setAttribute("showtimeList", showtimeList);
        }
        List<rooms> roomList = dao.listRoomByDirector(user.getUser_id());
        if (roomList.isEmpty()) {
            roomList = dao.listRoomByManager(user.getUser_id());
        }
        List<movies> movieList = dao.listMovie();
        request.setAttribute("roomList", roomList);
        request.setAttribute("movieList", movieList);
        request.getRequestDispatcher("list_showtime.jsp").forward(request, response);
    }

    private void addShowtime(HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            users user)
            throws ServletException, IOException {
        int room_id = Integer.parseInt(request.getParameter("room_id"));
        int movie_id = Integer.parseInt(request.getParameter("movie_id"));
        Date date = Date.valueOf(request.getParameter("date"));
        String temp = request.getParameter("time");
        System.out.println(temp);
        Time time = Time.valueOf(temp+":00");
        showtimesDAO dao = new showtimesDAO();
        if(!dao.addShowtime(room_id, movie_id, date, time)){
            String mess = "Something go wrong!";
            session.setAttribute("list_st_mess", mess);
        }
        String url = "ShowtimeURL?service=listShowtimeByRoom";
        Integer ss_room_id = (Integer) session.getAttribute("list_st_room_id");
        Integer ss_movie_id = (Integer) session.getAttribute("list_st_movie_id");
        Date ss_date = (Date) session.getAttribute("list_st_date");
        String ss_status = (String) session.getAttribute("list_st_status");
        if(ss_room_id!=null){
            url+="&submit=search&room_id="+ss_room_id;
        }
        if(ss_movie_id!=null){
            url+="&movie_id="+ss_movie_id;
        }
        if(ss_date!=null){
            url+="&date="+ss_date;
        }
        if(ss_status!=null){
            url+="&status="+ss_status;
        }
        response.sendRedirect(url);
    }
    
    private void deleteShowtime(HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            users user)
            throws ServletException, IOException {
        int showtime_id = Integer.parseInt(request.getParameter("showtime_id"));
        showtimesDAO dao = new showtimesDAO();
        if(!dao.deleteShowtime(showtime_id)){
            String mess = "Something go wrong!";
            session.setAttribute("list_st_mess", mess);
        }
        String url = "ShowtimeURL?service=listShowtimeByRoom";
        Integer ss_room_id = (Integer) session.getAttribute("list_st_room_id");
        Integer ss_movie_id = (Integer) session.getAttribute("list_st_movie_id");
        Date ss_date = (Date) session.getAttribute("list_st_date");
        String ss_status = (String) session.getAttribute("list_st_status");
        if(ss_room_id!=null){
            url+="&submit=search&room_id="+ss_room_id;
        }
        if(ss_movie_id!=null){
            url+="&movie_id="+ss_movie_id;
        }
        if(ss_date!=null){
            url+="&date="+ss_date;
        }
        if(ss_status!=null){
            url+="&status="+ss_status;
        }
        response.sendRedirect(url);
    }
    
    private void submitShowtime(HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            users user)
            throws ServletException, IOException {
        int showtime_id = Integer.parseInt(request.getParameter("showtime_id"));
        showtimesDAO dao = new showtimesDAO();
        if(!dao.submitShowtime(showtime_id)){
            String mess = "Something go wrong!";
            session.setAttribute("list_st_mess", mess);
        }
        String url = "ShowtimeURL?service=listShowtimeByRoom";
        Integer ss_room_id = (Integer) session.getAttribute("list_st_room_id");
        Integer ss_movie_id = (Integer) session.getAttribute("list_st_movie_id");
        Date ss_date = (Date) session.getAttribute("list_st_date");
        String ss_status = (String) session.getAttribute("list_st_status");
        if(ss_room_id!=null){
            url+="&submit=search&room_id="+ss_room_id;
        }
        if(ss_movie_id!=null){
            url+="&movie_id="+ss_movie_id;
        }
        if(ss_date!=null){
            url+="&date="+ss_date;
        }
        if(ss_status!=null){
            url+="&status="+ss_status;
        }
        response.sendRedirect(url);
    }
    
    private void editShowtime(HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            users user)
            throws ServletException, IOException {
        int room_id = Integer.parseInt(request.getParameter("room_id"));
        int movie_id = Integer.parseInt(request.getParameter("movie_id"));
        Date date = Date.valueOf(request.getParameter("date"));
        String temp = request.getParameter("time");
        System.out.println(temp);
        Time time = Time.valueOf(temp);
        int showtime_id = Integer.parseInt(request.getParameter("showtime_id"));
        showtimesDAO dao = new showtimesDAO();
        if(!dao.editShowtime(showtime_id, room_id, movie_id, date, time)){
            String mess = "Something go wrong!";
            session.setAttribute("list_st_mess", mess);
        }
        String url = "ShowtimeURL?service=listShowtimeByRoom";
        Integer ss_room_id = (Integer) session.getAttribute("list_st_room_id");
        Integer ss_movie_id = (Integer) session.getAttribute("list_st_movie_id");
        Date ss_date = (Date) session.getAttribute("list_st_date");
        String ss_status = (String) session.getAttribute("list_st_status");
        if(ss_room_id!=null){
            url+="&submit=search&room_id="+ss_room_id;
        }
        if(ss_movie_id!=null){
            url+="&movie_id="+ss_movie_id;
        }
        if(ss_date!=null){
            url+="&date="+ss_date;
        }
        if(ss_status!=null){
            url+="&status="+ss_status;
        }
        response.sendRedirect(url);
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
