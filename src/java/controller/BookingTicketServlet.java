/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import modal.Cinemas;
import modal.ScreeningTimes;
import modal.Users;

/**
 *
 * @author bquoc
 */
@WebServlet(name = "BookingTicketServlet", urlPatterns = {"/booking"})
public class BookingTicketServlet extends HttpServlet {

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
            out.println("<title>Servlet BookingTicketServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookingTicketServlet at " + request.getContextPath() + "</h1>");
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
            //  String movieID = request.getParameter("movieid");
            //  String date = request.getParameter("date");
            //  String direction = request.getParameter("direction");
            //  request.setAttribute("direction", direction);
            //  request.setAttribute("date", date);
            //  request.setAttribute("movieID", movieID);
            //get movieID, date, direction from request if it null then send to signin
            try {
                String movieID = request.getParameter("movieid");
                String date = request.getParameter("date");
                String direction = request.getParameter("direction");
                //get current hour and minute
                Timestamp startTime = new Timestamp(System.currentTimeMillis());

                // Get the current year and month
                LocalDate now = LocalDate.now();
                int year = now.getYear();
                int month = now.getMonthValue();

                // Combine the current year and month with the given date
                String mainDate = year + "-" + String.format("%02d", month) + "-" + String.format("%02d", Integer.parseInt(date));

                if (movieID == null && date == null && direction == null) {
                    response.sendRedirect("home");
                    return;
                }
                request.setAttribute("direction", direction);
                request.setAttribute("date", date);
                request.setAttribute("movieID", movieID);

                DAO d = new DAO();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.sql.Date dateSql = null;
                try {
                    java.util.Date utilDate = sdf.parse(mainDate);
                    dateSql = new java.sql.Date(utilDate.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //lay ra cac rap chieu phim do
                List<Cinemas> listCinemas = d.getAllCinemas(Integer.parseInt(movieID), dateSql, Integer.parseInt(direction));

                // Create a map to store the list of lists of ScreeningTimes for each cinema
                Map<String, List<List<ScreeningTimes>>> cinemaScreeningTimes = new HashMap<>();

                // For each cinema, get the cinemaID and use it to get the list of ScreeningTimes
                for (Cinemas cinema : listCinemas) {
                    String cinemaName = cinema.getName();
                    List<ScreeningTimes> listScreeningTimes = d.getAllFlimList(Integer.parseInt(movieID), cinema.getCinemaID(), dateSql, startTime);
                    if (listCinemas != null && listScreeningTimes.size() == 0) {
                        request.setAttribute("isEmpty", true);
                    } else {
                        request.setAttribute("isEmpty", false);
                        request.setAttribute("listCinemas", listCinemas);
                        // Sort the listScreeningTimes in ascending order based on the start time
                        Collections.sort(listScreeningTimes, new Comparator<ScreeningTimes>() {
                            @Override
                            public int compare(ScreeningTimes st1, ScreeningTimes st2) {
                                return st1.getStartTime().compareTo(st2.getStartTime());
                            }
                        });

                        // If the cinema name is already in the map, add the listScreeningTimes to the existing list
                        if (cinemaScreeningTimes.containsKey(cinemaName)) {
                            cinemaScreeningTimes.get(cinemaName).add(listScreeningTimes);
                        } else {
                            // If the cinema name is not in the map, create a new list and add the listScreeningTimes to it
                            List<List<ScreeningTimes>> lists = new ArrayList<>();
                            lists.add(listScreeningTimes);
                            cinemaScreeningTimes.put(cinemaName, lists);
                        }
                    }
                }
                // Add the map to the request attributes
                request.setAttribute("cinemaScreeningTimes", cinemaScreeningTimes);

            } catch (NumberFormatException e) {
                request.setAttribute("isEmpty", true);
//              response.sendRedirect("home");
//              return;
            }

            request.getRequestDispatcher("/WEB-INF/views/bookingTicket.jsp").forward(request, response);
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
