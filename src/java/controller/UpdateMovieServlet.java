/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import dal.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import modal.MovieGenres;
import modal.Movies;
import modal.Users;

/**
 *
 * @author bquoc
 */
@MultipartConfig
@WebServlet(name = "UpdateMovieServlet", urlPatterns = {"/updateMovie"})
public class UpdateMovieServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateMovieServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateMovieServlet at " + request.getContextPath() + "</h1>");
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
            movieID = request.getParameter("movieID");
            Movies movie = dao.getMovieByID(Integer.parseInt(movieID));

            request.setAttribute("movie", movie);

            request.getRequestDispatcher("WEB-INF/views/updateMovie.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        DAO dao = new DAO();
        String title = request.getParameter("movieTitle");
        String description = request.getParameter("description");
        Date releaseDate = Date.valueOf(request.getParameter("releaseDate"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        String genres = request.getParameter("selectedGenres");
        String oldPosterImage = request.getParameter("oldPosterImage");

        // Đường dẫn thực tế trên máy chủ để lưu trữ hình ảnh
        String realPath = "C:\\Users\\bquoc\\Documents\\NetBeansProjects\\Project\\web\\assets\\images\\posterImages";
        // Tạo đối tượng Path từ đường dẫn thực tế
        Path dirPath = Paths.get(realPath);
        try {
            //check if date is 30 days from now will return a error message
            Date currentDate = new Date(System.currentTimeMillis());
            long diff = currentDate.getTime() - releaseDate.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            if (diffDays > 30) {
                request.setAttribute("message", "Release date must be at most 30 days before now");
                request.getRequestDispatcher("WEB-INF/views/updateMovie.jsp").forward(request, response);
            } else {
                // Kiểm tra nếu thư mục không tồn tại thì tạo mới
                if (!Files.exists(dirPath)) {
                    Files.createDirectories(dirPath);
                }
                // Lấy phần của request chứa file ảnh
                Part part = request.getPart("posterImage");
                // Lấy tên file gốc
                String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                // Tạo tên file duy nhất để tránh trùng lặp
                String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
                // Tạo đường dẫn tới file ảnh trong thư mục uploads
                Path imagePath = Paths.get(realPath, uniqueFileName);
                // Ghi dữ liệu từ input stream của file ảnh vào đường dẫn vừa tạo
                try (InputStream input = part.getInputStream()) {
                    Files.copy(input, imagePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dao.updateMovieByID(title, description, releaseDate, uniqueFileName, duration, Integer.parseInt(request.getParameter("movieID")));

                Movies movie = dao.getMovieByID(Integer.parseInt(request.getParameter("movieID")));
                List<MovieGenres> movieGenres = dao.getMovieGenresIDByMovieID(movie.getMovieID());
                String[] genreList = genres.split(", ");

                for (int i = 0; i < movieGenres.size() - 1; i++) {
                    MovieGenres movieGenre = movieGenres.get(i);
                    int genreID = Integer.parseInt(genreList[i]);

                    // Update the movieGenre with the genreID
                    dao.updateMovieGenresByMovieGenresID(movieGenre.getMovieGenresID(), genreID);
                }

                // Delete the old image file
                Path oldImagePath = Paths.get(realPath, oldPosterImage);
                try {
                    Files.deleteIfExists(oldImagePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                response.sendRedirect("home");
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();
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
