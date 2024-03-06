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
public class ScreeningTimes {
    private int screeningID;
    private Theaters theaterID;
    private Movies movieID;
    private Timestamp startTime;
    private Timestamp endTime;

    public ScreeningTimes() {
    }

    public ScreeningTimes(int screeningID, Theaters theaterID, Movies movieID, Timestamp startTime, Timestamp endTime) {
        this.screeningID = screeningID;
        this.theaterID = theaterID;
        this.movieID = movieID;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getScreeningID() {
        return screeningID;
    }

    public void setScreeningID(int screeningID) {
        this.screeningID = screeningID;
    }

    public Theaters getTheaterID() {
        return theaterID;
    }

    public void setTheaterID(Theaters theaterID) {
        this.theaterID = theaterID;
    }

    public Movies getMovieID() {
        return movieID;
    }

    public void setMovieID(Movies movieID) {
        this.movieID = movieID;
    }

    

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
    
    
}
