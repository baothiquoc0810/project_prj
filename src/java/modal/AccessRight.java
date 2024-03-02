/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modal;

/**
 *
 * @author bquoc
 */
public class AccessRight {
    private int accessID;
    private String pageAccess;

    public AccessRight() {
    }

    public AccessRight(int accessID, String pageAccess) {
        this.accessID = accessID;
        this.pageAccess = pageAccess;
    }

    public int getAccessID() {
        return accessID;
    }

    public void setAccessID(int accessID) {
        this.accessID = accessID;
    }

    public String getPageAccess() {
        return pageAccess;
    }

    public void setPageAccess(String pageAccess) {
        this.pageAccess = pageAccess;
    }
    
    
}
