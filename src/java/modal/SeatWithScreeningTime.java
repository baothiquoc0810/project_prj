/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modal;

/**
 *
 * @author bquoc
 */
public class SeatWithScreeningTime {
    private Seats seatID;
    private ScreeningTimes screeningID;

    public SeatWithScreeningTime() {
    }

    public SeatWithScreeningTime(Seats seatID, ScreeningTimes screeningID) {
        this.seatID = seatID;
        this.screeningID = screeningID;
    }

    public Seats getSeatID() {
        return seatID;
    }

    public void setSeatID(Seats seatID) {
        this.seatID = seatID;
    }

    public ScreeningTimes getScreeningID() {
        return screeningID;
    }

    public void setScreeningID(ScreeningTimes screeningID) {
        this.screeningID = screeningID;
    }
    
    
}
