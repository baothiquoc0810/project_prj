/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modal;

/**
 *
 * @author bquoc
 */
public class Theaters {
    private int theaterID;
    private Cinemas cinemaID;
    private int theaterNumber;

    public Theaters() {
    }

    public Theaters(int theaterID, Cinemas cinemaID, int theaterNumber) {
        this.theaterID = theaterID;
        this.cinemaID = cinemaID;
        this.theaterNumber = theaterNumber;
    }

    public int getTheaterID() {
        return theaterID;
    }

    public void setTheaterID(int theaterID) {
        this.theaterID = theaterID;
    }

    public Cinemas getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(Cinemas cinemaID) {
        this.cinemaID = cinemaID;
    }

    public int getTheaterNumber() {
        return theaterNumber;
    }

    public void setTheaterNumber(int theaterNumber) {
        this.theaterNumber = theaterNumber;
    }

  
    
}
