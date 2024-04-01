package controller;


import dal.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import modal.Movies;
import modal.OrderDetail;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HomeServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO d = new DAO();
        List<Movies> list = d.getMovies();
        OrderDetail order = (OrderDetail) request.getSession().getAttribute("order");
        if(order != null){
            request.getSession().removeAttribute("order");
        }
        request.setAttribute("listPoster", list);
        request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
    }
    
    
}
