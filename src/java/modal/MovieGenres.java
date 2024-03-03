/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modal;

/**
 *
 * @author bquoc
 */
public class MovieGenres {
    private int movieGenresID;
    private Genres genreID;
    private Movies movieID;

    public MovieGenres() {
    }

    public MovieGenres(int movieGenresID, Genres genreID, Movies movieID) {
        this.movieGenresID = movieGenresID;
        this.genreID = genreID;
        this.movieID = movieID;
    }

    public int getMovieGenresID() {
        return movieGenresID;
    }

    public void setMovieGenresID(int movieGenresID) {
        this.movieGenresID = movieGenresID;
    }

    public Genres getGenreID() {
        return genreID;
    }

    public void setGenreID(Genres genreID) {
        this.genreID = genreID;
    }

    public Movies getMovieID() {
        return movieID;
    }

    public void setMovieID(Movies movieID) {
        this.movieID = movieID;
    }
    
    
}
