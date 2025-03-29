package controller.guest;

import dao.combosDAO;
import dao.roomsDAO;
import dao.theatersDAO;
import dao.moviesDAO;
import dao.seatsDAO;
import dao.showtimesDAO;
import entity.combos;
import entity.rooms;
import entity.theaters;
import entity.movies;
import entity.users;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "BillController", urlPatterns = {"/BillController"})
public class BillController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy session và kiểm tra user
        HttpSession session = request.getSession(false);
        users user = (session != null) ? (users) session.getAttribute("acc") : null;
        // Nếu chưa đăng nhập thì chặn
        if (user == null) {
            response.sendRedirect("sign_in.jsp");
            return;
        }
        String mid_raw = request.getParameter("mid");
        String branchId_raw = request.getParameter("branchId");
        String startDate_raw = request.getParameter("startDate");
        String startTime_raw = request.getParameter("startTime");
        String roomId_raw = request.getParameter("roomId");
        String[] selectedSeats = request.getParameterValues("seats");

        // Kiểm tra nếu không có ghế nào được chọn
        if (selectedSeats == null || selectedSeats.length == 0) {
            request.setAttribute("bookedError", "Vui lòng chọn ít nhất một ghế!");
            // Giữ lại các thông tin để hiển thị lại trên seat.jsp
            request.setAttribute("mid", mid_raw);
            request.setAttribute("branchId", branchId_raw);
            request.setAttribute("startDate", startDate_raw);
            request.setAttribute("startTime", startTime_raw);
            request.setAttribute("roomId", roomId_raw);

            // Tải lại danh sách ghế
            seatsDAO seatDAO = new seatsDAO();
            try {
                int mid = Integer.parseInt(mid_raw);
                int branchId = Integer.parseInt(branchId_raw);
                int roomId = Integer.parseInt(roomId_raw);

                // Chuyển đổi startDate từ String sang java.sql.Date
                Date startDate = Date.valueOf(startDate_raw);

                // Chuyển đổi startTime từ định dạng 12 giờ (AM/PM) sang 24 giờ
                SimpleDateFormat sdf12 = new SimpleDateFormat("hh:mm:ss a"); // Định dạng 12 giờ với AM/PM
                SimpleDateFormat sdf24 = new SimpleDateFormat("HH:mm:ss");   // Định dạng 24 giờ
                String formattedTime = sdf24.format(sdf12.parse(startTime_raw));
                Time startTime = Time.valueOf(formattedTime);
                showtimesDAO dao = new showtimesDAO();
                int showtimeID = dao.getShowTime(startDate, startTime, roomId);
                // Tải danh sách ghế cho từng hàng
                request.setAttribute("listA", seatDAO.getAllSeatA(mid, branchId, startDate, startTime, roomId, showtimeID));
                request.setAttribute("listB", seatDAO.getAllSeatB(mid, branchId, startDate, startTime, roomId, showtimeID));
                request.setAttribute("listC", seatDAO.getAllSeatC(mid, branchId, startDate, startTime, roomId, showtimeID));
                request.setAttribute("listD", seatDAO.getAllSeatD(mid, branchId, startDate, startTime, roomId, showtimeID));
                request.setAttribute("listE", seatDAO.getAllSeatE(mid, branchId, startDate, startTime, roomId, showtimeID));

            } catch (Exception e) {
                log("Error loading seats in BillController: " + e.toString());
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            request.getRequestDispatcher("seat.jsp").forward(request, response);
            return;
        }

        // Xử lý khi có ghế được chọn
        moviesDAO a = new moviesDAO();
        theatersDAO b = new theatersDAO();
        roomsDAO c = new roomsDAO();
        combosDAO d = new combosDAO();

        try {
            int mid = Integer.parseInt(mid_raw);
            int branchId = Integer.parseInt(branchId_raw);
            int roomId = Integer.parseInt(roomId_raw);

            movies movie = a.getMovie(mid);
            theaters brand = b.getBrand(branchId);
            rooms room = c.getRoom(roomId);
            List<combos> e = d.listCombo();

            request.setAttribute("room", room);
            request.setAttribute("movie", movie);
            request.setAttribute("brand", brand);
            request.setAttribute("startDate", startDate_raw);
            request.setAttribute("startTime", startTime_raw);
            request.setAttribute("mid", mid);
            request.setAttribute("branchId", branchId);
            request.setAttribute("roomId", roomId);
            request.setAttribute("e", e);
            request.setAttribute("selectedSeats", selectedSeats);

            request.getRequestDispatcher("bill.jsp").forward(request, response);
        } catch (Exception e) {
            log("Error at BillController: " + e.toString());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
