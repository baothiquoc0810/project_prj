/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.paypal.base.rest.PayPalRESTException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import dal.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modal.OrderDetail;
import modal.OrderTest;
import modal.ScreeningTimes;
import modal.Seats;
import modal.Users;

/**
 *
 * @author bquoc
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/payment"})
public class PaymentServlet extends HttpServlet {

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
            out.println("<title>Servlet PaymentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentServlet at " + request.getContextPath() + "</h1>");
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
        DAO dao = new DAO();
        java.time.LocalDate localDate = java.time.LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
        String selectedSeats = request.getParameter("selectedSeats");
        String totalPrice = request.getParameter("totalPrice");
        String screeningID = request.getParameter("screeningID");

        if (user == null || selectedSeats == null || totalPrice == null || screeningID == null) {
            response.sendRedirect("home");
        } else {
            String[] selectedSeatsArray = selectedSeats.split(", ");
            int count = selectedSeatsArray.length;

            ScreeningTimes screeningTime = dao.getScreeningTimesByID(Integer.parseInt(screeningID));
            request.setAttribute("count", count);
            request.setAttribute("selectedSeats", selectedSeats);
            request.setAttribute("screeningTime", screeningTime);
            request.setAttribute("totalPrice", totalPrice);
            request.getRequestDispatcher("/WEB-INF/views/paymentDetail.jsp").forward(request, response);
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
        Users user = (Users) request.getSession().getAttribute("account");
        DAO dao = new DAO();
        java.time.LocalDate localDate = java.time.LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
        String selectedSeats = request.getParameter("selectedSeats");
        String totalPrice = request.getParameter("totalPrice");
        String screeningID = request.getParameter("screeningID");
        ScreeningTimes screeningTime = dao.getScreeningTimesByID(Integer.parseInt(screeningID));

        if (user == null || selectedSeats == null || totalPrice == null || screeningID == null) {
            response.sendRedirect("home");
        } else {

            //split the selectedSeats string into an array of strings
            String[] selectedSeatsArray = selectedSeats.split(", ");
            List<Seats> seats = new ArrayList<>();
            int count = 0;
            for (String seat : selectedSeatsArray) {
                //isnert each seat into the database
                count++;
                dao.insertSeats(Integer.parseInt(screeningID), seat);
                //get the seatID of the seat just inserted
                seats.add(dao.getSeatByScreeningIDAndSeatNumber(Integer.parseInt(screeningID), seat));
            }

            //get userID from account session
            HttpSession session = request.getSession();
            Users account = (Users) session.getAttribute("account");
            int userID = account.getUserID();

            //insert order
            dao.insertOrder(userID, screeningTime.getMovieID().getMovieID(), count, totalPrice);

            //get orderID newly inserted
            int orderID = dao.getOrderIDByUserID(userID);

            //insert seatID to ticketID
            for (Seats seat : seats) {
                dao.insertTicket(userID, screeningTime.getMovieID().getMovieID(), screeningTime.getTheaterID().getCinemaID().getCinemaID(), "75000", sqlDate, seat.getSeatID(), orderID);
            }

            String stringUserID = Integer.toString(user.getUserID());
            String movieID = Integer.toString(screeningTime.getMovieID().getMovieID());
            OrderTest orders = new OrderTest("ticket", stringUserID, movieID, Integer.toString(count), totalPrice);

            //create session attribute for order
            OrderDetail orderDetail = new OrderDetail(screeningTime, selectedSeats, Integer.toString(count), totalPrice);
            HttpSession sessionOrder = request.getSession();
            sessionOrder.setAttribute("orderDetail", orderDetail);

            try {
                PaymentServices paymentService = new PaymentServices();
                String approvalLink = paymentService.authorizePayment(orders);
                response.sendRedirect(approvalLink);
            } catch (PayPalRESTException ex) {
                ex.printStackTrace();
                request.setAttribute("errorMessage", "Invalid Payment Details");
                request.getRequestDispatcher("/WEB-INF/views/paymentDetail.jsp").forward(request, response);
            }
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
