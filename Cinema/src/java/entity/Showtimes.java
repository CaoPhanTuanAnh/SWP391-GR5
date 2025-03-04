/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author PCASUS
 */
public class Showtimes {
    private int showtime_id;
    private int movie_id;
    private int room_id;
    private Date date;
    private Time time;
    private String status;

    public Showtimes() {
    }

    public Showtimes(int showtime_id, int movie_id, int room_id, Date date, Time time, String status) {
        this.showtime_id = showtime_id;
        this.movie_id = movie_id;
        this.room_id = room_id;
        this.date = date;
        this.time = time;
        this.status = status;
    }
    
    

    public int getShowtime_id() {
        return showtime_id;
    }

    public void setShowtime_id(int showtime_id) {
        this.showtime_id = showtime_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   

   
    
    
}
