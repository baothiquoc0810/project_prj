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
import modal.Cinemas;
import modal.Genres;
import modal.Location;
import modal.MovieGenres;
import modal.Movies;
import modal.ScreeningTimes;
import modal.Theaters;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

    //void check user by UserID
    public boolean checkUserByID(int userID) {
        String sql = "SELECT * FROM Users WHERE userID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
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

    //get user by username
    public Users getUserByUsername(String username) {
        String sql = "SELECT * FROM Users join Roles on Users.roleID = Roles.roleID where username = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Roles r = new Roles(rs.getInt("roleID"), rs.getString("name"));
                Users u = new Users(rs.getInt("userID"), rs.getString("displayName"), rs.getString("username"), rs.getString("password"), r);
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //check login
    public Users checkLogin(String username, String password) {
        String sql = "select * from Users join [Roles] on Users.roleID = Roles.roleID where username = ? and password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            Roles r;
            if (rs.next()) {
                String role = "";
                if (rs.getString("name").equals("admin")) {
                    role = "admin";
                } else {
                    role = "user";
                }
                r = new Roles(rs.getInt("roleID"), role);
                return new Users(rs.getInt("userID"), rs.getString("displayName"), username, password, r);
            }
        } catch (SQLException e) {
        }
        return null;
    }

    //void update display name
    public void updateDisplayName(String username, String displayName) {
        String sql = "update Users set displayName = ? where username = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, displayName);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //void check pass
    public boolean checkPass(String password, String username) {
        String sql = "select [password] from Users where username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String acctualPass = rs.getString("Password");
                return acctualPass.equals(password);
            }
        } catch (SQLException e) {
        }
        return false;
    }

    //void update password
    public void updatePassword(String password, String username) {
        String sql = "update Users set [password] = ? where username = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //void get all movies
    public List<Movies> getMovies() {
        List<Movies> list = new ArrayList<>();
        String sql = "select * from Movies";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Movies m = new Movies(rs.getInt("movieID"), rs.getString("title"), rs.getString("description"), rs.getDate("releaseDate"), rs.getString("posterImage"), rs.getInt("duration"));
                list.add(m);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //void get movie by movieID
    public Movies getMovieByID(int movieID) {
        String sql = "select * from Movies where movieID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, movieID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Movies m = new Movies(rs.getInt("movieID"), rs.getString("title"), rs.getString("description"), rs.getDate("releaseDate"), rs.getString("posterImage"), rs.getInt("duration"));
                return m;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //void get Genres by genresID
    public Genres getGenresByID(int genresID) {
        String sql = "select * from Genres where genreID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, genresID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Genres g = new Genres(rs.getInt("genreID"), rs.getString("name"));
                return g;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //void get list<movieGenres> by movieID
    public List<MovieGenres> getMovieGenres(int movieID) {
        List<MovieGenres> list = new ArrayList<>();
        String sql = "select * from MovieGenres mg "
                + "join Movies m on mg.movieID = m.movieID "
                + "join Genres g on mg.genreID = g.genreID "
                + "where m.movieID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, movieID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Genres g = new Genres(rs.getInt("genreID"), rs.getString("name"));
                Movies m = new Movies(rs.getInt("movieID"), rs.getString("title"), rs.getString("description"), rs.getDate("releaseDate"), rs.getString("posterImage"), rs.getInt("duration"));
                MovieGenres mg = new MovieGenres(rs.getInt("movieGenresID"), g, m);
                list.add(mg);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //get list screening time
    public List<ScreeningTimes> getAllFlimList(int movieID, int cinemaID, Date movieDate) {
        List<ScreeningTimes> list = new ArrayList<>();
        String sql = "select l.locationID, l.name as location, c.cinemaID, c.name as cinemasName, c.movieDate, t.theaterID, t.theaterNumber, st.screeningID, st.startTime, st.endTime, m.movieID,m.title, m.description, m.releaseDate, m.posterImage, m.duration from [Location] l join Cinemas c on l.locationID = c.locationID join Theaters t on c.cinemaID = t.cinemaID join ScreeningTimes st on t.theaterID = st.theaterID join Movies m on st.movieID = m.movieID where m.movieID = ? and c.cinemaID = ? and c.movieDate = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, movieID);
            ps.setInt(2, cinemaID);
            ps.setDate(3, movieDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Location l = new Location(rs.getInt("locationID"), rs.getString("location"));
                Cinemas c = new Cinemas(rs.getInt("cinemaID"), rs.getString("cinemasName"), rs.getDate("movieDate"), l);
                Theaters t = new Theaters(rs.getInt("theaterID"), c, rs.getInt("theaterNumber"));
                Movies m = new Movies(rs.getInt("movieID"), rs.getString("title"), rs.getString("description"), rs.getDate("releaseDate"), rs.getString("posterImage"), rs.getInt("duration"));
                ScreeningTimes st = new ScreeningTimes(rs.getInt("screeningID"), t, m, rs.getTime("startTime"), rs.getTime("endTime"));
                list.add(st);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //get all cinemas with movieID, date, direction
    public List<Cinemas> getAllCinemas(int movieID, Date movieDate, int direction) {
        List<Cinemas> list = new ArrayList<>();
        String sql = "select distinct c.cinemaID, c.name as cinemas, c.movieDate, l.locationID, l.name as location from [Location] l join Cinemas c on l.locationID = c.locationID join Theaters t on c.cinemaID = t.cinemaID join ScreeningTimes st on t.theaterID = st.theaterID join Movies m on st.movieID = m.movieID where m.movieID = ? and c.movieDate = ? and l.locationID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, movieID);
            ps.setDate(2, movieDate);
            ps.setInt(3, direction);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cinemas c = new Cinemas(rs.getInt("cinemaID"), rs.getString("cinemas"), rs.getDate("movieDate"), new Location(rs.getInt("locationID"), rs.getString("location")));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) {
        DAO dao = new DAO();
        // Chuyển ngày từ String sang java.sql.Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date date = null;
        try {
            java.util.Date utilDate = sdf.parse("2024-03-04");
            date = new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Test hàm getAllFlimList
       List<ScreeningTimes> list = dao.getAllFlimList(2, 7, date);
        for (ScreeningTimes st : list) {
            System.out.println(st.getStartTime() + " "+ st.getTheaterID().getCinemaID().getName());
        }
    }
}
