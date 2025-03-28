/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dao.NewsDAO;
import entity.news;
import entity.users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author default
 */
public class ManageNews extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManageNews</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageNews at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    private final NewsDAO newsDAO = new NewsDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // Lấy session và kiểm tra user
            HttpSession session = request.getSession(false);
            users user = (session != null) ? (users) session.getAttribute("acc") : null;

            // Nếu chưa đăng nhập hoặc không phải Manager thì chặn
            if (user == null || (user.getRole_id() != 2)) {
                response.sendRedirect("AccessDenied.jsp");
                return;
            }
            String action = request.getParameter("action");
            if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                news news = newsDAO.getNewsById(id);
                request.setAttribute("news", news);
                request.getRequestDispatcher("edit_news.jsp").forward(request, response);
            } else {
                List<news> list = newsDAO.getAllNews();
                request.setAttribute("newsList", list);
                request.getRequestDispatcher("ManageNews.jsp").forward(request, response);
            }
        } catch (Exception ex) {

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            users user = (session != null) ? (users) session.getAttribute("acc") : null;

            // Nếu chưa đăng nhập hoặc không phải Admin/Manager thì chặn
            if (user == null || (user.getRole_id() != 2)) {
                response.sendRedirect("AccessDenied.jsp");
                return;
            }
            String action = request.getParameter("action");
            String userId = String.valueOf(user.getUser_id());
            String title = request.getParameter("title").trim();
            String photoUrl = request.getParameter("photo_url").trim();
            String content = request.getParameter("content").trim();
            String contentType = request.getParameter("content_type");
            String mess = "";
            if (title == null || title.isBlank()) {
                request.setAttribute("error", "Title must not empty!");
                request.getRequestDispatcher("create_news.jsp").forward(request, response);
                return;
            }
            if (photoUrl == null || photoUrl.isBlank()) {
                request.setAttribute("error", "PhotoUrl must not empty!");
                request.getRequestDispatcher("create_news.jsp").forward(request, response);
                return;
            }
            if (content == null || content.isBlank()) {
                request.setAttribute("error", "Content must not empty!");
                request.getRequestDispatcher("create_news.jsp").forward(request, response);
                return;
            }
            if (title.length() > 100 || photoUrl.length() > 100) {
                request.setAttribute("error", "Title or imageURL is too long!");
                request.getRequestDispatcher("create_news.jsp").forward(request, response);
                return;
            }

            if ("update".equals(action)) {
                int postId = Integer.parseInt(request.getParameter("post_id"));
                news news = new news(postId, userId, title, photoUrl, content, null, contentType);
                if (!newsDAO.updateNews(news)) {
                    session.setAttribute("error", "News is already exist");
                    response.sendRedirect("ManageNews");
                } else {
                    session.setAttribute("mess", "Edit News successfully");
                    response.sendRedirect("ManageNews");
                }
            } else {
                news news = new news(0, userId, title, photoUrl, content, null, contentType);
                if (!newsDAO.insertNews(news)) {
                    session.setAttribute("error", "News is already exist");
                    response.sendRedirect("ManageNews");
                } else {
                    session.setAttribute("mess", "Add News successfully");
                    response.sendRedirect("ManageNews");
                }
            }

        } catch (Exception ex) {

        }
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
