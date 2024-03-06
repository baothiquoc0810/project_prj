/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modal;
import java.sql.Timestamp;  

/**
 *
 * @author bquoc
 */
public class Tickets {
    private int ticketID;
    private Users userID;
    private Movies movieID;
    private Cinemas cinemaID;
    private String price;
    private Timestamp purchaseDate;

    public Tickets() {
    }

    public Tickets(int ticketID, Users userID, Movies movieID, Cinemas cinemaID, String price, Timestamp purchaseDate) {
        this.ticketID = ticketID;
        this.userID = userID;
        this.movieID = movieID;
        this.cinemaID = cinemaID;
        this.price = price;
        this.purchaseDate = purchaseDate;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public Users getUserID() {
        return userID;
    }

    public void setUserID(Users userID) {
        this.userID = userID;
    }

    public Movies getMovieID() {
        return movieID;
    }

    public void setMovieID(Movies movieID) {
        this.movieID = movieID;
    }

    public Cinemas getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(Cinemas cinemaID) {
        this.cinemaID = cinemaID;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

}
