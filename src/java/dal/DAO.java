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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import modal.Cinemas;
import modal.Genres;
import modal.Location;
import modal.MovieAndTotalOrders;
import modal.MovieGenres;
import modal.Movies;
import modal.Orders;
import modal.ScreeningTimes;
import modal.Theaters;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import modal.SeatWithScreeningTime;
import modal.Seats;
import modal.Tickets;
import modal.TotalTicketOfUser;

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
        String sql = "SELECT * FROM Movies m WHERE m.releaseDate < CAST(GETDATE() AS DATE)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Movies m = new Movies(rs.getInt("movieID"), rs.getString("title"), rs.getString("description"), rs.getDate("releaseDate"), rs.getString("posterImage"), rs.getInt("duration"), rs.getInt("display"));
                list.add(m);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //void get all movie after now
    public List<Movies> getAllMovieAfterNow() {
        String sql = "SELECT * FROM Movies m WHERE m.releaseDate > CAST(GETDATE() AS DATE)";
        List<Movies> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Movies m = new Movies(rs.getInt("movieID"), rs.getString("title"), rs.getString("description"), rs.getDate("releaseDate"), rs.getString("posterImage"), rs.getInt("duration"), rs.getInt("display"));
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
                Movies m = new Movies(rs.getInt("movieID"), rs.getString("title"), rs.getString("description"), rs.getDate("releaseDate"), rs.getString("posterImage"), rs.getInt("duration"), rs.getInt("display"));
                return m;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //void get movie by genreID
    public List<Movies> getMovieByGenreID(String genreID){
        List<Movies> list = new ArrayList<>();
        String sql = "select * from Movies m join MovieGenres mg on m.movieID = mg.movieID join Genres g on mg.genreID = g.genreID where mg.genreID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, genreID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Movies m = new Movies(rs.getInt("movieID"), rs.getString("title"), rs.getString("description"), rs.getDate("releaseDate"), rs.getString("posterImage"), rs.getInt("duration"), rs.getInt("display"));
                list.add(m);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
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
                Movies m = new Movies(rs.getInt("movieID"), rs.getString("title"), rs.getString("description"), rs.getDate("releaseDate"), rs.getString("posterImage"), rs.getInt("duration"), rs.getInt("display"));
                MovieGenres mg = new MovieGenres(rs.getInt("movieGenresID"), g, m);
                list.add(mg);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //get list screening time
    public List<ScreeningTimes> getAllFlimList(int movieID, int cinemaID, Date movieDate, Timestamp startTime) {
        List<ScreeningTimes> list = new ArrayList<>();
        String sql = "select m.display, l.locationID, l.name as location, c.cinemaID, c.name as cinemasName, c.movieDate, t.theaterID, t.theaterNumber, st.screeningID, st.startTime, st.endTime, m.movieID,m.title, m.description, m.releaseDate, m.posterImage, m.duration from [Location] l join Cinemas c on l.locationID = c.locationID join Theaters t on c.cinemaID = t.cinemaID join ScreeningTimes st on t.theaterID = st.theaterID join Movies m on st.movieID = m.movieID where m.movieID = ? and c.cinemaID = ? and c.movieDate = ? and st.startTime > DATEADD(hour, 1, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, movieID);
            ps.setInt(2, cinemaID);
            ps.setDate(3, movieDate);
            ps.setTimestamp(4, startTime);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Location l = new Location(rs.getInt("locationID"), rs.getString("location"));
                Cinemas c = new Cinemas(rs.getInt("cinemaID"), rs.getString("cinemasName"), rs.getDate("movieDate"), l);
                Theaters t = new Theaters(rs.getInt("theaterID"), c, rs.getInt("theaterNumber"));
                Movies m = new Movies(rs.getInt("movieID"), rs.getString("title"), rs.getString("description"), rs.getDate("releaseDate"), rs.getString("posterImage"), rs.getInt("duration"), rs.getInt("display"));
                ScreeningTimes st = new ScreeningTimes(rs.getInt("screeningID"), t, m, rs.getTimestamp("startTime"), rs.getTimestamp("endTime"));
                list.add(st);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<SeatWithScreeningTime> getSWSByID(int screeningID) {
        String sql = "select m.display,l.locationID, l.name as location, c.cinemaID, c.name as cinemasName, c.movieDate, t.theaterID, t.theaterNumber, st.screeningID, st.startTime, st.endTime, m.movieID,m.title, m.description, m.releaseDate, m.posterImage, m.duration, s.seatID, s.seatNumber from [Location] l join Cinemas c on l.locationID = c.locationID join Theaters t on c.cinemaID = t.cinemaID join ScreeningTimes st on t.theaterID = st.theaterID join Movies m on st.movieID = m.movieID join Seats s on s.screeningID = st.screeningID where st.screeningID = ?";
        List<SeatWithScreeningTime> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, screeningID);
            ResultSet rs = ps.executeQuery();
            //if (rs.next()) {
            while (rs.next()) {
                Location l = new Location(rs.getInt("locationID"), rs.getString("location"));
                Cinemas c = new Cinemas(rs.getInt("cinemaID"), rs.getString("cinemasName"), rs.getDate("movieDate"), l);
                Theaters t = new Theaters(rs.getInt("theaterID"), c, rs.getInt("theaterNumber"));
                Movies m = new Movies(rs.getInt("movieID"), rs.getString("title"), rs.getString("description"), rs.getDate("releaseDate"), rs.getString("posterImage"), rs.getInt("duration"), rs.getInt("display"));
                ScreeningTimes st = new ScreeningTimes(rs.getInt("screeningID"), t, m, rs.getTimestamp("startTime"), rs.getTimestamp("endTime"));
                Seats s = new Seats(rs.getInt("SeatID"), rs.getString("seatNumber"), st);
                SeatWithScreeningTime SWS = new SeatWithScreeningTime(s, st);
                list.add(SWS);
                // } else {
                //     System.out.println("No rows found.");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //get list screening time by id
    public ScreeningTimes getScreeningTimesByID(int screeningID) {
        String sql = "select m.display,l.locationID, l.name as location, c.cinemaID, c.name as cinemasName, c.movieDate, t.theaterID, t.theaterNumber, st.screeningID, st.startTime, st.endTime, m.movieID,m.title, m.description, m.releaseDate, m.posterImage, m.duration from [Location] l join Cinemas c on l.locationID = c.locationID join Theaters t on c.cinemaID = t.cinemaID join ScreeningTimes st on t.theaterID = st.theaterID join Movies m on st.movieID = m.movieID where st.screeningID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, screeningID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Location l = new Location(rs.getInt("locationID"), rs.getString("location"));
                Cinemas c = new Cinemas(rs.getInt("cinemaID"), rs.getString("cinemasName"), rs.getDate("movieDate"), l);
                Theaters t = new Theaters(rs.getInt("theaterID"), c, rs.getInt("theaterNumber"));
                Movies m = new Movies(rs.getInt("movieID"), rs.getString("title"), rs.getString("description"), rs.getDate("releaseDate"), rs.getString("posterImage"), rs.getInt("duration"), rs.getInt("display"));
                ScreeningTimes st = new ScreeningTimes(rs.getInt("screeningID"), t, m, rs.getTimestamp("startTime"), rs.getTimestamp("endTime"));
                return st;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
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

    //inrset into seats
    public void insertSeats(int screeningID, String seatNumber) {
        String sql = "insert into Seats (screeningID, seatNumber)\r\n"
                + //
                "values(?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, screeningID);
            ps.setString(2, seatNumber);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //get seat by screeningID, seatNumber
    public Seats getSeatByScreeningIDAndSeatNumber(int screeningID, String seatNumber) {
        String sql = "select * from Seats where screeningID = ? and seatNumber = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, screeningID);
            ps.setString(2, seatNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Seats s = new Seats(rs.getInt("seatID"), rs.getString("seatNumber"), getScreeningTimesByID(screeningID));
                return s;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //insert into tickets
    public void insertTicket(int userID, int movieID, int cinemaID, String price, Date purchaseDate, int seatID, int orderID) {
        String sql = "INSERT INTO Tickets (userID, movieID, cinemaID, price, purchaseDate, seatID, orderID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setInt(2, movieID);
            ps.setInt(3, cinemaID);
            ps.setString(4, price);
            ps.setDate(5, purchaseDate);
            ps.setInt(6, seatID);
            ps.setInt(7, orderID);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //insert into order
    public void insertOrder(int userID, int movieID, int quantity, String allPrice) {
        String sql = "insert into Orders (userID, movieID, quantity, allPrice)\r\n"
                + //
                "values(?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setInt(2, movieID);
            ps.setInt(3, quantity);
            ps.setString(4, allPrice);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //get orderID by userID
    public int getOrderIDByUserID(int userID) {
        String sql = "select top 1 * from Orders where userID = ? order by orderID desc";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("orderID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    //get all tickets by userID
    public List<Tickets> getAllTicketsByUserID(int userID) {
        List<Tickets> list = new ArrayList<>();
        String sql = "select t.ticketID, u.userID, m.movieID,m.display ,c.cinemaID, t.price, t.purchaseDate, s.seatID, o.orderID,u.displayName, u.username, u.password, r.roleID, r.name as roleName, m.title, m.description, m.releaseDate, m.posterImage, m.duration, m.duration, c.name as cinemasName, c.movieDate, l.locationID, l.name as locationName, st.screeningID, s.seatNumber, th.theaterID, st.startTime, st.endTime, th.theaterNumber, o.quantity, o.allPrice from Tickets t \n"
                + "join Users u on t.userID = u.userID\n"
                + "join Roles r on r.roleID = u.roleID\n"
                + "join Movies m on m.movieID = t.movieID\n"
                + "join Cinemas c on c.cinemaID = t.cinemaID\n"
                + "join Location l on c.locationID = l.locationID\n"
                + "join Seats s on s.seatID = t.seatID\n"
                + "join ScreeningTimes st on s.screeningID = st.screeningID\n"
                + "join Theaters th on th.theaterID = st.theaterID\n"
                + "join Orders o on o.orderID = t.orderID\n"
                + "where u.userID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Roles r = new Roles(rs.getInt("roleID"), rs.getString("roleName"));
                Users u = new Users(rs.getInt("userID"), rs.getString("displayName"), rs.getString("username"), rs.getString("password"), r);
                Location l = new Location(rs.getInt("locationID"), rs.getString("locationName"));
                Cinemas c = new Cinemas(rs.getInt("cinemaID"), rs.getString("cinemasName"), rs.getDate("movieDate"), l);
                Movies m = new Movies(rs.getInt("movieID"), rs.getString("title"), rs.getString("description"), rs.getDate("releaseDate"), rs.getString("posterImage"), rs.getInt("duration"), rs.getInt("display"));
                Theaters th = new Theaters(rs.getInt("theaterID"), c, rs.getInt("theaterNumber"));
                ScreeningTimes st = new ScreeningTimes(rs.getInt("screeningID"), th, m, rs.getTimestamp("startTime"), rs.getTimestamp("endTime"));
                Seats s = new Seats(rs.getInt("seatID"), rs.getString("seatNumber"), st);
                Orders o = new Orders(rs.getInt("orderID"), u, m, rs.getInt("quantity"), rs.getString("allPrice"));
                Tickets t = new Tickets(rs.getInt("ticketID"), u, m, c, rs.getString("price"), rs.getDate("purchaseDate"), s, o);
                list.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //count pagination tickets by userID
    public int countPaginationTickets(int userID) {
        String sql = "select count(*) from Tickets where userID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    //pagination list tickets by userID
    public List<Tickets> paginationTickets(int userID, int index) {
        List<Tickets> list = new ArrayList<>();
        String sql = "select t.ticketID, u.userID, m.movieID,m.display, c.cinemaID, t.price, t.purchaseDate, s.seatID, o.orderID,u.displayName, u.username, u.password, r.roleID, r.name as roleName, m.title, m.description, m.releaseDate, m.posterImage, m.duration, m.duration, c.name as cinemasName, c.movieDate, l.locationID, l.name as locationName, st.screeningID, s.seatNumber, th.theaterID, st.startTime, st.endTime, th.theaterNumber, o.quantity, o.allPrice from Tickets t \r\n"
                + //
                "join Users u on t.userID = u.userID\r\n"
                + //
                "join Roles r on r.roleID = u.roleID\r\n"
                + //
                "join Movies m on m.movieID = t.movieID\r\n"
                + //
                "join Cinemas c on c.cinemaID = t.cinemaID\r\n"
                + //
                "join Location l on c.locationID = l.locationID\r\n"
                + //
                "join Seats s on s.seatID = t.seatID\r\n"
                + //
                "join ScreeningTimes st on s.screeningID = st.screeningID\r\n"
                + //
                "join Theaters th on th.theaterID = st.theaterID\r\n"
                + //
                "join Orders o on o.orderID = t.orderID\r\n"
                + //
                "where u.userID = ?\r\n"
                + //
                "order by t.ticketID\r\n"
                + //
                "offset ? rows fetch next 5 rows only";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setInt(2, (index - 1) * 5);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Roles r = new Roles(rs.getInt("roleID"), rs.getString("roleName"));
                Users u = new Users(rs.getInt("userID"), rs.getString("displayName"), rs.getString("username"), rs.getString("password"), r);
                Location l = new Location(rs.getInt("locationID"), rs.getString("locationName"));
                Cinemas c = new Cinemas(rs.getInt("cinemaID"), rs.getString("cinemasName"), rs.getDate("movieDate"), l);
                Movies m = new Movies(rs.getInt("movieID"), rs.getString("title"), rs.getString("description"), rs.getDate("releaseDate"), rs.getString("posterImage"), rs.getInt("duration"), rs.getInt("display"));
                Theaters th = new Theaters(rs.getInt("theaterID"), c, rs.getInt("theaterNumber"));
                ScreeningTimes st = new ScreeningTimes(rs.getInt("screeningID"), th, m, rs.getTimestamp("startTime"), rs.getTimestamp("endTime"));
                Seats s = new Seats(rs.getInt("seatID"), rs.getString("seatNumber"), st);
                Orders o = new Orders(rs.getInt("orderID"), u, m, rs.getInt("quantity"), rs.getString("allPrice"));
                Tickets t = new Tickets(rs.getInt("ticketID"), u, m, c, rs.getString("price"), rs.getDate("purchaseDate"), s, o);
                list.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //insert new movie
    public void insertNewMovie(String title, String description, Date releaseDate, String posterImage, int duration) {
        String sql = "insert into Movies (title, description, releaseDate, posterImage, duration, 1)\r\n"
                + //
                "values(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setDate(3, releaseDate);
            ps.setString(4, posterImage);
            ps.setInt(5, duration);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //select the movie recently added
    public Movies getMovieRecentlyAdded() {
        String sql = "select top 1 * from Movies order by movieID desc";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Movies m = new Movies(rs.getInt("movieID"), rs.getString("title"), rs.getString("description"), rs.getDate("releaseDate"), rs.getString("posterImage"), rs.getInt("duration"), rs.getInt("display"));
                return m;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //insert movie genre
    public void insertMovieGenre(int genreID, int movieID) {
        String sql = "insert into MovieGenres (genreID, movieID)\r\n"
                + //
                "values(?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, genreID);
            ps.setInt(2, movieID);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //update movie by id
    public void updateMovieByID(String title, String description, Date releaseDate, String posterImage, int duration, int movieID) {
        String sql = "update Movies set title = ?, description = ?, releaseDate = ?, posterImage = ?, duration = ? where movieID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setDate(3, releaseDate);
            ps.setString(4, posterImage);
            ps.setInt(5, duration);
            ps.setInt(6, movieID);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    //get movieGenresID by movieID
    public List<MovieGenres> getMovieGenresIDByMovieID(int movieID) {
        List<MovieGenres> list = new ArrayList<>();
        String sql = "select * from MovieGenres where movieID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, movieID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MovieGenres mg = new MovieGenres(rs.getInt("movieGenresID"), getGenresByID(rs.getInt("genreID")), getMovieByID(rs.getInt("movieID")));
                list.add(mg);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //update movie genres by movieGenresID
    public void updateMovieGenresByMovieGenresID(int movieGenresID, int genreID) {
        String sql = "update MovieGenres set genreID = ? where movieGenresID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, genreID);
            ps.setInt(2, movieGenresID);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //insert new cinemas
    public void insertNewCinemas(String name, Date movieDate, int locationID) {
        String sql = "insert into Cinemas (name, movieDate, locationID)\r\n"
                + //
                "values(?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDate(2, movieDate);
            ps.setInt(3, locationID);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //get cinemasID recently added
    public int getCinemasRecentlyAdded() {
        String sql = "select top 1 * from Cinemas order by cinemaID desc";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("cinemaID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    //insert into theaters with cinemaID, theaterNumber
    public void insertTheaters(int cinemaID, int theaterNumber) {
        String sql = "insert into Theaters (cinemaID, theaterNumber)\r\n"
                + //
                "values(?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cinemaID);
            ps.setInt(2, theaterNumber);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //get theatersID recently added
    public int getTheaterIDRecentlyAdded() {
        String sql = "select top 1 * from Theaters order by theaterID desc";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("theaterID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    //insert into screeningTimes with movieID, theaterID, startTime, endTime
    public void insertScreeningTimes(int theaterID, int movieID, Timestamp startTime, Timestamp endTime) {
        String sql = "insert into ScreeningTimes (theaterID, movieID, startTime, endTime)\r\n"
                + //
                "values(?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, theaterID);
            ps.setInt(2, movieID);
            ps.setTimestamp(3, startTime);
            ps.setTimestamp(4, endTime);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //get the latest end time of each theater number
    public Timestamp getLastestEndTimeOfTheater(String cinemasName, Date movieDate, int theaterNumber) {
        String sql = "select top 1 st.endTime from Location l join Cinemas c on l.locationID = c.locationID join Theaters t on t.cinemaID = c.cinemaID join ScreeningTimes st on st.theaterID = t.theaterID where c.name = ? and c.movieDate = ? and t.theaterNumber = ? order by endTime desc";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cinemasName);
            ps.setDate(2, movieDate);
            ps.setInt(3, theaterNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getTimestamp("endTime");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //get all tickets of all users
    public List<TotalTicketOfUser> getAllTicketsOfAllUsers(String sort) {
        String sql = "select u.userID,u.username,r.name, sum(o.quantity) as 'totalTickets' from Users u left join Orders o on u.userID = o.userID join Roles r on u.roleID = r.roleID GROUP BY u.userID, u.username, r.name " + sort ;
        List<TotalTicketOfUser> list = new ArrayList<>();
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                TotalTicketOfUser t = new TotalTicketOfUser(rs.getInt("userID"), rs.getString("username"), rs.getString("name"), rs.getInt("totalTickets"));
                list.add(t);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }

    //update display movie by movieID and display
    public void updateDisplayMovieByMovieID(int movieID, int display){
        String sql = "update Movies set display = ? where movieID = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, display);
            ps.setInt(2, movieID);
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    //get list move and totalOrders 
    public List<MovieAndTotalOrders> getListMovieAndTotalOrders(){
        String sql = "SELECT m.movieID, m.title, SUM(o.quantity) AS totalOrders FROM Movies m JOIN Orders o ON m.movieID = o.movieID GROUP BY m.movieID, m.title ORDER BY totalOrders desc";
        List<MovieAndTotalOrders> list = new ArrayList<>();
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                MovieAndTotalOrders m = new MovieAndTotalOrders(rs.getInt("movieID"), rs.getString("title"), rs.getInt("totalOrders"));
                list.add(m);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) {
        DAO dao = new DAO();
        //test getallflimlist
        List<ScreeningTimes> list = dao.getAllFlimList(1, 34, Date.valueOf("2024-03-15"), Timestamp.valueOf("2024-03-15 17:12:12"));
        if(list == null){
            System.out.println("null");
        }else if(list.size() == 0){
            System.out.println("size = 0");
        }
    }
}


//.getAllFlimList(14, 35, Date.valueOf("2024-03-16"), Timestamp.valueOf("2024-03-15 12:12:12"));