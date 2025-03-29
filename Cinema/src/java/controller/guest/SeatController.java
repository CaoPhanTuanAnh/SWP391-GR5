package controller.guest;

import dao.seatsDAO;
import entity.seats;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "SeatController", urlPatterns = {"/SeatController"})
public class SeatController extends HttpServlet {

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
        String mid_raw = request.getParameter("mid");
        String branchId_raw = request.getParameter("branchId");
        String startDate_raw = request.getParameter("startDate");
        String startTime_raw = request.getParameter("startTime");
        String roomId_raw = request.getParameter("roomId");

        try {
            int mid = Integer.parseInt(mid_raw);
            int branchId = Integer.parseInt(branchId_raw);
            int roomId = Integer.parseInt(roomId_raw);

            // Chuyển `startDate_raw` từ String -> java.sql.Date
            Date startDate = Date.valueOf(startDate_raw);

            // Chuyển `startTime_raw` từ 12 giờ AM/PM -> 24 giờ (java.sql.Time)
            SimpleDateFormat sdf12 = new SimpleDateFormat("hh:mm:ss a"); // 12 giờ có AM/PM
            SimpleDateFormat sdf24 = new SimpleDateFormat("HH:mm:ss"); // Chuyển thành 24 giờ
            String formattedTime = sdf24.format(sdf12.parse(startTime_raw));
            Time startTime = Time.valueOf(formattedTime);

            List<seats> listA = new seatsDAO().getAllSeatA(mid, branchId, startDate, startTime, roomId);
            List<seats> listB = new seatsDAO().getAllSeatB(mid, branchId, startDate, startTime, roomId);
            List<seats> listC = new seatsDAO().getAllSeatC(mid, branchId, startDate, startTime, roomId);
            List<seats> listD = new seatsDAO().getAllSeatD(mid, branchId, startDate, startTime, roomId);
            List<seats> listE = new seatsDAO().getAllSeatE(mid, branchId, startDate, startTime, roomId);
            request.setAttribute("listA", listA);
            request.setAttribute("listB", listB);
            request.setAttribute("listC", listC);
            request.setAttribute("listD", listD);
            request.setAttribute("listE", listE);
            request.setAttribute("startDate", startDate_raw);
            request.setAttribute("startTime", startTime_raw);
            request.setAttribute("mid", mid);
            request.setAttribute("branchId", branchId);
             request.setAttribute("roomId", roomId);

        } catch (Exception e) {
            log("Error at HomeController: " + e.toString());
        } finally {
            request.getRequestDispatcher("seat.jsp").forward(request, response);
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
