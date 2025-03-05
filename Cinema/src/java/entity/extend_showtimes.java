/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author GIGABYTE
 */
public class extend_showtimes extends showtimes {

    private String room_name;
    private String title;
    private String poster_url;
    private int duration;

    public extend_showtimes() {
    }

    public extend_showtimes(String room_name, String title, String poster_url, int duration, int showtime_id, int movie_id, int room_id, Date date, Time time, String status) {
        super(showtime_id, movie_id, room_id, date, time, status);
        this.room_name = room_name;
        this.title = title;
        this.poster_url = poster_url;
        this.duration = duration;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    
}
