/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;

import dal.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import modal.MovieGenres;
import modal.Movies;

/**
 *
 * @author bquoc
 */
@WebServlet(name = "DetailMovieServlet", urlPatterns = {"/detail-movie"})
public class DetailMovieServlet extends HttpServlet {

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
            out.println("<title>Servlet DetailMovieServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailMovieServlet at " + request.getContextPath() + "</h1>");
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String movieID = request.getParameter("movieID");

        DAO d = new DAO();
        Movies m = d.getMovieByID(Integer.parseInt(movieID));
        request.setAttribute("movie", m);
        //create a boolean if m.getReleaseDate() is after now then it is false
        boolean isCommingSoon = false; // Mặc định là false
        Date today = new Date(); // Lấy ngày hiện tại

        // Kiểm tra xem bộ phim có phải là "đang sắp ra mắt" không
        if (m.getReleaseDate().after(today)) {
            isCommingSoon = true;
        }

        //response.getWriter().println(isCommingSoon);
        request.setAttribute("isCommingSoon", isCommingSoon);
        List<MovieGenres> list = d.getMovieGenres(Integer.parseInt(movieID));
        request.setAttribute("listGenres", list);
        request.getRequestDispatcher("/WEB-INF/views/detailMovie.jsp").forward(request, response);
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
