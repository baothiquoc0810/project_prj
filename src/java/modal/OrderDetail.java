/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modal;

/**
 *
 * @author bquoc
 */
public class OrderDetail {
    private ScreeningTimes screeningTime;
    private String selectedSeats;
    private String count;
    private String totalPrice;

    public OrderDetail() {
    }

    public OrderDetail(ScreeningTimes screeningTime, String selectedSeats, String count, String totalPrice) {
        this.screeningTime = screeningTime;
        this.selectedSeats = selectedSeats;
        this.count = count;
        this.totalPrice = totalPrice;
    }

    public ScreeningTimes getScreeningTime() {
        return screeningTime;
    }

    public void setScreeningTime(ScreeningTimes screeningTime) {
        this.screeningTime = screeningTime;
    }

    public String getSelectedSeats() {
        return selectedSeats;
    }

    public void setSelectedSeats(String selectedSeats) {
        this.selectedSeats = selectedSeats;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    
    
}
