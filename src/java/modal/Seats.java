/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modal;

/**
 *
 * @author bquoc
 */
public class Seats {
    private int seatID;
    private String seatNumber;
    private ScreeningTimes screeningID;

    public Seats() {
    }

    public Seats(int seatID, String seatNumber, ScreeningTimes screeningID) {
        this.seatID = seatID;
        this.seatNumber = seatNumber;
        this.screeningID = screeningID;
    }

    public int getSeatID() {
        return seatID;
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public ScreeningTimes getScreeningID() {
        return screeningID;
    }

    public void setScreeningID(ScreeningTimes screeningID) {
        this.screeningID = screeningID;
    }
    
    
    
}
