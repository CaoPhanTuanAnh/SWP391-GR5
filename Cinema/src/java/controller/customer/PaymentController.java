package controller.customer;

import dao.bookingsDAO;
import dao.combosDAO;
import dao.seatsDAO;
import dao.showtimesDAO;
import dao.ticketDAO;
import entity.bookings;
import entity.combos;
import entity.showtimes;
import entity.users;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Time;
import java.text.ParseException;

@WebServlet("/payment-VNPay")
public class PaymentController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Hàm hỗ trợ để parse int an toàn
    private int parseIntSafely(String param, String paramName) {
        try {
            return Integer.parseInt(param != null ? param.trim() : "0");
        } catch (NumberFormatException e) {
            System.out.println("❌ Lỗi: " + paramName + " không hợp lệ - " + param);
            return 0;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Kiểm tra nếu đây là phản hồi từ VNPay
        String vnp_ResponseCode = request.getParameter("vnp_ResponseCode");
        if (vnp_ResponseCode != null) {
            handlePaymentResponse(request, response, vnp_ResponseCode);
            return;
        }

        // Lấy và kiểm tra amount
        String amountParam = request.getParameter("amount");
        int amount = parseIntSafely(amountParam, "amount");

        // Lấy và kiểm tra combo
        String comboParam = request.getParameter("combo");
        int combo = parseIntSafely(comboParam, "combo");

        String date = request.getParameter("date");
        String time = request.getParameter("time");

        int mid = parseIntSafely(request.getParameter("mid"), "mid");
        int room = parseIntSafely(request.getParameter("room"), "room");
        int branch = parseIntSafely(request.getParameter("branch"), "branch");

        String seatParam = request.getParameter("seat"); // Ví dụ: "4,20,41"
        String[] selectedSeats = (seatParam != null) ? seatParam.split("\\s*,\\s*") : new String[0];

        java.sql.Date startDate = java.sql.Date.valueOf(date);

        // Chuyển `time` từ định dạng 12 giờ AM/PM sang 24 giờ
        Time startTime = null;
        try {
            SimpleDateFormat sdf12 = new SimpleDateFormat("hh:mm:ss a");
            SimpleDateFormat sdf24 = new SimpleDateFormat("HH:mm:ss");
            java.util.Date parsedTime = sdf12.parse(time);
            String formattedTime = sdf24.format(parsedTime);
            startTime = Time.valueOf(formattedTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        //HttpSession session = request.getSession();
        users acc = (users) session.getAttribute("acc");
        //bookings a = new bookings();
        //a = bookingsDAO.createBooking(acc.getUser_id(), date, Double.parseDouble(String.valueOf(amount)), "Booked");
        // Tạo booking với trạng thái "Pending"
        bookings booking = bookingsDAO.createBooking(acc.getUser_id(), date, Double.parseDouble(String.valueOf(amount)), "Pending");
        combosDAO combosdao = new combosDAO();
        List<combos> comlist = combosdao.listCombo();
        for (combos com : comlist) {
            int quantity = Integer.parseInt(request.getParameter("comboId" + com.getCombo_id()));
            if (quantity > 0) {
                amount += quantity * com.getCombo_price();
                combosdao.addBookingCombo(booking.getBooking_id(), com.getCombo_id(), quantity);
            }
        }

        String orderId = PaymentConfig.getRandomNumber(3);
        amount = amount * 100;
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", PaymentConfig.VERSION);
        vnp_Params.put("vnp_Command", PaymentConfig.COMMAND);
        vnp_Params.put("vnp_TmnCode", PaymentConfig.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", orderId);
        vnp_Params.put("vnp_OrderInfo", "MTBS - Thanh toan hoa don");
        vnp_Params.put("vnp_OrderType", PaymentConfig.ORDER_TYPE);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_BankCode", "VNBANK");
        vnp_Params.put("vnp_ReturnUrl", PaymentConfig.vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", "127.0.0.1");

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator<String> itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = itr.next();
            String fieldValue = vnp_Params.get(fieldName);
            if (fieldValue != null && !fieldValue.isEmpty()) {
                hashData.append(fieldName).append('=').append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString())).append('=').append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }

        showtimesDAO showtimeDAO = new showtimesDAO();
        showtimes showtime = showtimeDAO.getShowTimebyDateTime(startDate, startTime, mid, room);
        //users acc = (users) session.getAttribute("acc");

        

        // Tạo ticket và đặt ghế ở trạng thái "Pending"
        seatsDAO seatsDAO = new seatsDAO();
        for (String seat : selectedSeats) {
            try {
                int seatNumber = Integer.parseInt(seat.trim());
                System.out.println("⏳ Đang tạo ticket cho seat: " + seatNumber);
                ticketDAO.createTicket(
                        showtime.getShowtime_id(),
                        seatNumber,
                        acc.getUser_id(),
                        booking.getBooking_id(),
                        Double.parseDouble(String.valueOf(amount / 100)),
                        "Pending"
                );
                seatsDAO.updateSeatStatus(seat, "Pending"); // Trạng thái ghế ban đầu là "Pending"
                System.out.println("✅ Ticket created for seat: " + seatNumber);
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Seat không hợp lệ - " + seat);
                e.printStackTrace();
            }
        }

        String queryUrl = query.toString();
        String vnp_SecureHash = PaymentConfig.hmacSHA512(PaymentConfig.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = PaymentConfig.vnp_PayUrl + "?" + queryUrl;

        // Lưu bookingId vào session để xử lý sau khi thanh toán
        session.setAttribute("bookingId", booking.getBooking_id());
        response.sendRedirect(paymentUrl);
    }

    // Xử lý phản hồi từ VNPay
    private void handlePaymentResponse(HttpServletRequest request, HttpServletResponse response, String vnp_ResponseCode) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer bookingId = (Integer) session.getAttribute("bookingId");

        if (bookingId == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        bookingsDAO bookingDAO = new bookingsDAO();
        seatsDAO seatsDAO = new seatsDAO();
        ticketDAO ticketDAO = new ticketDAO();

        if ("00".equals(vnp_ResponseCode)) {
            // Thanh toán thành công
            bookingDAO.updateBookingStatus(bookingId, "Booked");
            List<String> seatIds = ticketDAO.getSeatIdsByBookingId(bookingId);
            for (String seatId : seatIds) {
                seatsDAO.updateSeatStatus(seatId, "Booked");
                ticketDAO.updateTicketStatus(bookingId, seatId, "Complete"); // Cập nhật trạng thái ticket
            }
            request.getRequestDispatcher("/transaction-success.jsp").forward(request, response);
        } else {
            // Thanh toán thất bại hoặc bị hủy
            bookingDAO.updateBookingStatus(bookingId, "Cancelled");
            List<String> seatIds = ticketDAO.getSeatIdsByBookingId(bookingId);
            for (String seatId : seatIds) {
                seatsDAO.updateSeatStatus(seatId, "Available"); // Trả ghế về trạng thái "Available"
                ticketDAO.updateTicketStatus(bookingId, seatId, "Cancelled"); // Cập nhật trạng thái ticket
            }
            request.setAttribute("errorMessage", "Thanh toán không thành công. Mã lỗi: " + vnp_ResponseCode);
            request.getRequestDispatcher("/transaction-failure.jsp").forward(request, response);
        }

        // Xóa bookingId khỏi session sau khi xử lý
        session.removeAttribute("bookingId");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}