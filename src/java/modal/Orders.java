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
public class Orders {
   private int orderID;
   private Users userID;
   private Movies movieID;
   private int quantity;
   private String allPrice;

    public Orders() {
    }

    public Orders(int orderID, Users userID, Movies movieID, int quantity, String allPrice) {
        this.orderID = orderID;
        this.userID = userID;
        this.movieID = movieID;
        this.quantity = quantity;
        this.allPrice = allPrice;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int oriderID) {
        this.orderID = oriderID;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(String allPrice) {
        this.allPrice = allPrice;
    }
   
   
    
}
