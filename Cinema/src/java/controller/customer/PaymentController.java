package controller.customer;

import dao.bookingsDAO;
import dao.seatsDAO;
import dao.showtimesDAO;
import dao.ticketDAO;
import entity.bookings;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int amount = Integer.parseInt(request.getParameter("amount"));
       int combo = (int) Double.parseDouble(request.getParameter("combo"));
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        int mid = Integer.parseInt(request.getParameter("mid"));
        int room = Integer.parseInt(request.getParameter("room"));
        int branch = Integer.parseInt(request.getParameter("branch"));
        String seatParam = request.getParameter("seat");  // Ví dụ: "4,20,41"
        String[] selectedSeats = (seatParam != null) ? seatParam.split("\\s*,\\s*") : new String[0];

        java.sql.Date startDate = java.sql.Date.valueOf(date);

        // Chuyển `time` từ định dạng 12 giờ AM/PM sang 24 giờ
        Time startTime = null;
        try {
            SimpleDateFormat sdf12 = new SimpleDateFormat("hh:mm:ss a"); // Thêm giây
            SimpleDateFormat sdf24 = new SimpleDateFormat("HH:mm:ss");

            java.util.Date parsedTime = sdf12.parse(time);
            String formattedTime = sdf24.format(parsedTime);

            startTime = Time.valueOf(formattedTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        String orderid = PaymentConfig.getRandomNumber(3);
        amount = amount * 100;
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", PaymentConfig.VERSION);
        vnp_Params.put("vnp_Command", PaymentConfig.COMMAND);
        vnp_Params.put("vnp_TmnCode", PaymentConfig.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount + combo));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", orderid);
        vnp_Params.put("vnp_OrderInfo", "GYMM - Thanh toan hoa don");
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
                hashData.append(fieldName).append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString())).append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        showtimesDAO b = new showtimesDAO();
        showtimes listDates = b.getShowTimebyDateTime(startDate, startTime, mid, room);
        HttpSession session = request.getSession();
        users acc = (users) session.getAttribute("acc");
        bookings a = new bookings();
        a = bookingsDAO.createBooking(acc.getUser_id(), date, Double.parseDouble(String.valueOf(amount+combo)), "Booked");
        for (String seat : selectedSeats) {
            try {
                int seatNumber = Integer.parseInt(seat.trim()); // Chuyển seat thành số nguyên
                System.out.println("⏳ Đang tạo ticket cho seat: " + seatNumber);

                ticketDAO.createTicket(
                        listDates.getShowtime_id(),
                        seatNumber, // Chuyển thành số nguyên
                        acc.getUser_id(),
                        a.getBooking_id(),
                        Double.parseDouble(String.valueOf(amount / 100)), // Giữ đúng đơn vị tiền
                        "Complete"
                );
                
                seatsDAO z = new seatsDAO();
                z.updateSeatStatus(seat, "Booked");
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

        response.setStatus(HttpServletResponse.SC_OK);
//        response.getWriter().write(paymentUrl);

        response.sendRedirect(paymentUrl);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
