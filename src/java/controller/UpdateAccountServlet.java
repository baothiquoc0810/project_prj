/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modal.Users;

/**
 *
 * @author bquoc
 */
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/update"})
public class UpdateAccountServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateAccountServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateAccountServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/WEB-INF/views/updateAccount.jsp").forward(request, response);
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
        String displayName = request.getParameter("displayName");
        String username = request.getParameter("username");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        DAO d = new DAO();
        response.getWriter().print(displayName);
        response.getWriter().print(username);
        boolean b = d.checkPass(oldPassword, username);
        if (oldPassword.equals("")) {
            d.updateDisplayName(username, displayName);
            Users u = d.getUserByUsername(username);
            HttpSession session = request.getSession();
            session.setAttribute("account", u);
            response.sendRedirect("home");
        } else {
            if (!confirmPassword.equals(newPassword)) {
                request.setAttribute("error", "Password does not the same");
                request.getRequestDispatcher("/WEB-INF/views/updateAccount.jsp").forward(request, response);
            } else {
                if (!b) {
                    request.setAttribute("error", "Old password is incorrect");
                    request.getRequestDispatcher("/WEB-INF/views/updateAccount.jsp").forward(request, response);
                } else {
                    d.updatePassword(newPassword, username);
                    d.updateDisplayName(username, displayName);
                    Users u = d.getUserByUsername(username);
                    HttpSession session = request.getSession();
                    session.setAttribute("account", u);
                    response.sendRedirect("home");
                }
            }
        }
    }
//void if oldPassword is null then update displayname
    //void if oldPassword is not null then check if oldPassword is correct then update password
// public void updateAccount(String displayName, String username, String oldPassword, String newPassword, String confirmPassword){
//     DAO d = new DAO();
//     if(oldPassword == null){
//         d.updateDisplayName(displayName, 1);
//     }else{

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
