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
import java.util.List;
import modal.Movies;

/**
 *
 * @author bquoc
 */
@WebServlet(name="GenresServlet", urlPatterns={"/genres"})
public class GenresServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet GenresServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GenresServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DAO dao = new DAO();
        String genres = "";
        String genreID = request.getParameter("genreID");
        if (genreID == null || genreID.isEmpty()) {
            response.sendRedirect("home");
        }else{
            List<Movies> movies = dao.getMovieByGenreID(genreID);
            if(genreID.equals("1")){
                genres = "Hành Động";
            }else if(genreID.equals("2")){
                genres = "Phưu Lưu";
            }else if(genreID.equals("3")){
                genres = "Tâm Lý";
            }else if(genreID.equals("4")){
                genres = "Tình Cảm";
            }else if(genreID.equals("5")){
                genres = "Kinh Dị";
            }else if(genreID.equals("6")){
                genres = "Gia Đình";
            }else if(genreID.equals("7")){
                genres = "Hài";
            }else if(genreID.equals("8")){
                genres = "Hoạt Hình";
            }else if(genreID.equals("9")){
                genres = "Hồi Hộp";
            }
            request.setAttribute("genres", genres);
            request.setAttribute("movies", movies);
            request.getRequestDispatcher("/WEB-INF/views/genres.jsp").forward(request, response);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
