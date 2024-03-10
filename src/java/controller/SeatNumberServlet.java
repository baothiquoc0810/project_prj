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

import java.util.ArrayList;
import java.util.List;

import modal.ScreeningTimes;
import modal.SeatWithScreeningTime;
import modal.Seats;
import modal.Users;

/**
 *
 * @author bquoc
 */
@WebServlet(name = "SeatNumberServlet", urlPatterns = {"/seatNumber"})
public class SeatNumberServlet extends HttpServlet {

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
            out.println("<title>Servlet SeatNumberServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SeatNumberServlet at " + request.getContextPath() + "</h1>");
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
        if (user == null || user.getRole() == null || user.getRole().getName() == null) {
            response.sendRedirect("signin");
        } else {
            try {
                String screeningID = request.getParameter("screeningID");
                if (screeningID == null) {
                    response.sendRedirect("home");
                    return;
                }
                DAO d = new DAO();
                List<SeatWithScreeningTime> SWS = d.getSWSByID(Integer.parseInt(screeningID));
                ScreeningTimes screeningTimes = d.getScreeningTimesByID(Integer.parseInt(screeningID));
                //get list seatNumber from list seats
//                List<String> listSeatNumber = new ArrayList<>();
//                for (Seats seat : seats) {
//                    if(seat.getSeatType().equals("Đã đặt")){
//
//                    //list seatID da dat    
//                    listSeatNumber.add(seat.getSeatNumber());
//                }
//                }
//
//                request.setAttribute("listSeatID", listSeatNumber);
                request.setAttribute("screeningTimes", screeningTimes);
                request.setAttribute("SWS", SWS);

                request.getRequestDispatcher("/WEB-INF/views/seatNumber.jsp").forward(request, response);

            } catch (NumberFormatException e) {

            }
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
