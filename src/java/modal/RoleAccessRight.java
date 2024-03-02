/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modal;

/**
 *
 * @author bquoc
 */
public class RoleAccessRight {
    private int roleAccessRightID;
    private Roles role;
    private AccessRight accessRight;

    public RoleAccessRight() {
    }

    public RoleAccessRight(int roleAccessRightID, Roles role, AccessRight accessRight) {
        this.roleAccessRightID = roleAccessRightID;
        this.role = role;
        this.accessRight = accessRight;
    }

    public int getRoleAccessRightID() {
        return roleAccessRightID;
    }

    public void setRoleAccessRightID(int roleAccessRightID) {
        this.roleAccessRightID = roleAccessRightID;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public AccessRight getAccessRight() {
        return accessRight;
    }

    public void setAccessRight(AccessRight accessRight) {
        this.accessRight = accessRight;
    }
    
    
}
