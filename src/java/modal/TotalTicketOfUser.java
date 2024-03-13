/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modal;

/**
 *
 * @author bquoc
 */
public class TotalTicketOfUser {
    private int userID;
    private String username;
    private String name;
    private int totalTickets;

    public TotalTicketOfUser() {
    }

    public TotalTicketOfUser(int userID, String username, String name, int totalTickets) {
        this.userID = userID;
        this.username = username;
        this.name = name;
        this.totalTickets = totalTickets;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }
    
    
}
