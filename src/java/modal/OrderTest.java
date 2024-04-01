/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modal;

/**
 *
 * @author bquoc
 */
public class OrderTest {
    private String orderName;
    private int userID;
    private int movieID;
    private int quantity;
    private String allPrice;

    public OrderTest() {
    }

    public OrderTest(String orderName, String userID, String movieID, String quantity, String allPrice) {
        this.orderName = orderName;
        this.userID = Integer.parseInt(userID);
        this.movieID = Integer.parseInt(movieID);
        this.quantity = Integer.parseInt(quantity);
        this.allPrice = allPrice;
    }

    public String getOrderName() {
        return orderName;
    }


    public int getUserID() {
        return userID;
    }


    public int getMovieID() {
        return movieID;
    }

    public int getQuantity() {
        return quantity;
    }


    public String getAllPrice() {
        return allPrice;
    }

}
