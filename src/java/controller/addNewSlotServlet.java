/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Timestamp;
import java.text.SimpleDateFormat;

import dal.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modal.Movies;
import modal.Users;
import java.sql.Date;
import java.text.ParseException;

/**
 *
 * @author bquoc
 */
@WebServlet(name = "addNewSlotServlet", urlPatterns = {"/addNewSlot"})
public class addNewSlotServlet extends HttpServlet {

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
            out.println("<title>Servlet addNewSlotServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addNewSlotServlet at " + request.getContextPath() + "</h1>");
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
        Users user = (Users) request.getSession().getAttribute("account");
        String movieID = request.getParameter("movieID");
        if (user == null || user.getRole().getName().equals("user") || movieID == null) {
            response.sendRedirect("signin");
            return;
        } else {
            DAO dao = new DAO();
            Movies movie = dao.getMovieByID(Integer.parseInt(movieID));
            int duration = movie.getDuration();
            request.setAttribute("duration", duration);

            request.setAttribute("movie", movie);
            request.getRequestDispatcher("WEB-INF/views/addNewSlot.jsp").forward(request, response);
        }
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
        String movieID = request.getParameter("movieID");
        String cinema = request.getParameter("cinemaSelect");
        response.getWriter().print("cinema: " + cinema);

        String movieDateStr = request.getParameter("dateInput");
        String theaterNumber = request.getParameter("theaterNumber");
        String startTime = request.getParameter("startTimeInput");
        String endTime = request.getParameter("endTimeInput");
        int locationID = 0;
        DAO dao = new DAO();
        if (cinema.equals("CGV Hùng Vương Plaza")) {
            locationID = 2;
        } else if (cinema.equals("CGV Menas Mall")) {
            locationID = 2;
        } else if (cinema.equals("CGV Crescent Mall")) {
            locationID = 2;
        } else if (cinema.equals("CGV Vincom Center Bà Triệu")) {
            locationID = 1;
        } else if (cinema.equals("CGV Hồ Gươm Plaza")) {
            locationID = 1;
        } else if (cinema.equals("CGV Aeon Long Biên")) {
            locationID = 1;
        } else if (cinema.equals("CGV Vĩnh Trung Plaza")) {
            locationID = 3;
        } else if (cinema.equals("CGV Vincom Đà Nẵng")) {
            locationID = 3;
        }

        //response.getWriter().print("locationID: " + locationID);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date movieDateUtil;
        try {
            movieDateUtil = format.parse(movieDateStr);
        } catch (ParseException e) {
            response.getWriter().print("Error: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        java.sql.Date movieDateSql = new java.sql.Date(movieDateUtil.getTime());

        //lay ra slot chieu phim muon nhat
        java.sql.Timestamp endTimeLastestSlot = dao.getLastestEndTimeOfTheater(cinema, movieDateSql, Integer.parseInt(theaterNumber));

        // Assuming movieDateStr is a String representing a date in the format "yyyy-MM-dd"
        String startTimeStr = movieDateStr + " " + startTime + ":00";
        String endTimeStr = movieDateStr + " " + endTime + ":00";
        java.sql.Timestamp startTimeTimestamp;
        java.sql.Timestamp endTimeTimestamp;
        try {
            startTimeTimestamp = java.sql.Timestamp.valueOf(startTimeStr);
            endTimeTimestamp = java.sql.Timestamp.valueOf(endTimeStr);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            response.getWriter().print("Error: " + e.getMessage());
            // Handle the exception. For example, you might want to return an error response.
            return;
        }
        Movies movie = dao.getMovieByID(Integer.parseInt(movieID));
        // Check start time of new slot have to later than end time of lastest slot
        if (endTimeLastestSlot != null && endTimeLastestSlot.after(startTimeTimestamp)) {
            request.setAttribute("message", "Start time of new slot have to later than end time of lastest slot is " + endTimeLastestSlot);
            request.setAttribute("movie", movie);
            request.getRequestDispatcher("WEB-INF/views/addNewSlot.jsp").forward(request, response);
            return;
        } else {
            //insert new cinemas
            dao.insertNewCinemas(cinema, Date.valueOf(movieDateStr), locationID);
            //get id of cinemas recently inserted
            int cinemasID = dao.getCinemasRecentlyAdded();
            //response.getWriter().print("cinemasID: " + cinemasID);
            //response.getWriter().print("locationID: " + locationID);
            // //insert new theaters
            dao.insertTheaters(cinemasID, Integer.parseInt(theaterNumber));
            // //get id of theaters recently inserted
            int theaterID = dao.getTheaterIDRecentlyAdded();
            response.getWriter().print("theaterID: " + theaterID);

            // //insert screeningTimes
            dao.insertScreeningTimes(theaterID, Integer.parseInt(movieID), startTimeTimestamp, endTimeTimestamp);
            //response.getWriter().print("Insert new slot successfully");
            response.sendRedirect("home");
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
