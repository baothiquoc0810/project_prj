/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import modal.Roles;
import modal.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO extends DBContext {

    //add user
    public void add(Users u) {
        //1 admin, 2 user, 3 guest
        String sql = "INSERT INTO Users VALUES(?,?,?,2)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, u.getDisplayName());
            ps.setString(2, u.getUsername());
            ps.setString(3, u.getPassword());
            ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //void check user
    public boolean checkUser(String username) {
        String sql = "SELECT * FROM Users WHERE username = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    //get all users
    public List<Users> getUsers() {
        List<Users> list = new ArrayList<>();

        String sql = "SELECT * FROM Users join Roles on Users.roleID = Roles.roleID";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Roles r = new Roles(rs.getInt("roleID"), rs.getString("name"));
                Users u = new Users(rs.getInt("userID"), rs.getString("displayName"), rs.getString("username"), rs.getString("password"), r);
                list.add(u);
                return list;
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //check login
    public Users checkLogin(String username, String password) {
        String sql = "select * from Users where username = ? and password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2,password);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                 return new Users(rs.getString("displayName"),username, password);
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public static void main(String[] args) {
        DAO dao = new DAO();
        Users list = dao.checkLogin("bao", "123");
            System.out.println(list.getDisplayName());
    }
}
