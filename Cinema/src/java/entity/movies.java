package entity;

import java.sql.Date;
import java.util.List;



public class movies {

    private int movie_id;
    private String title;
    private String description;
    private String trailer_url;
    private String poster_url;
    private int duration;
    private Date release_date;
    private String status;
    private List<participants> parts;
    private String genres;
    private String[] actors;
    private String[] directors;
    private String actorsString;
    private String directorsString;

    public movies() {
    }

    public movies(int movie_id, String title, String description, String trailer_url, String poster_url, int duration, Date release_date, String status, List<participants> parts, String genres, String[] actors, String[] directors, String actorsString, String directorsString) {
        this.movie_id = movie_id;
        this.title = title;
        this.description = description;
        this.trailer_url = trailer_url;
        this.poster_url = poster_url;
        this.duration = duration;
        this.release_date = release_date;
        this.status = status;
        this.parts = parts;
        this.genres = genres;
        this.actors = actors;
        this.directors = directors;
        this.actorsString = actorsString;
        this.directorsString = directorsString;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrailer_url() {
        return trailer_url;
    }

    public void setTrailer_url(String trailer_url) {
        this.trailer_url = trailer_url;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<participants> getParts() {
        return parts;
    }

    public void setParts(List<participants> parts) {
        this.parts = parts;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public String[] getDirectors() {
        return directors;
    }

    public void setDirectors(String[] directors) {
        this.directors = directors;
    }

    public String getActorsString() {
        return actorsString;
    }

    public void setActorsString(String actorsString) {
        this.actorsString = actorsString;
    }

    public String getDirectorsString() {
        return directorsString;
    }

    public void setDirectorsString(String directorsString) {
        this.directorsString = directorsString;
    }

  
    
    
    
}
