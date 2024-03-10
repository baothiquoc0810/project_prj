/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modal;
import java.sql.Date;  

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
    private Date purchaseDate;
    private Seats seatID;
    private Orders orderID;

    public Tickets() {
    }

    public Tickets(int ticketID, Users userID, Movies movieID, Cinemas cinemaID, String price, Date purchaseDate, Seats seatID, Orders orderID) {
        this.ticketID = ticketID;
        this.userID = userID;
        this.movieID = movieID;
        this.cinemaID = cinemaID;
        this.price = price;
        this.purchaseDate = purchaseDate;
        this.seatID = seatID;
        this.orderID = orderID;
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

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Seats getSeatID() {
        return seatID;
    }

    public void setSeatID(Seats seatID) {
        this.seatID = seatID;
    }

    public Orders getOrderID() {
        return orderID;
    }

    public void setOrderID(Orders orderID) {
        this.orderID = orderID;
    }
    

}
