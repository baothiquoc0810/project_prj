/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modal;

/**
 *
 * @author bquoc
 */
public class MovieAndTotalOrders {
    private int movieID;
    private String title;
    private int totalOrders;

    public MovieAndTotalOrders() {
    }

    public MovieAndTotalOrders(int movieID, String title, int totalOrders) {
        this.movieID = movieID;
        this.title = title;
        this.totalOrders = totalOrders;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }
    
    
    
}
