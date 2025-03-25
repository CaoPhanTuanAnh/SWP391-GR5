/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;


/**
 *
 * @author GIGABYTE
 */
public class user_bookings extends bookings{

    private int num_of_tickets;
    private String seats_info;
    private int num_of_combos;
    private String combos_info;
    private String coupon_code;
    private double discount_percentage;
    private String title;
    private String poster_url;
    private int showtime_id;
    private String showtime;
    private String theater_name;
    private String room_name;

    public user_bookings() {
    }

    public user_bookings(int num_of_tickets, String seats_info, int num_of_combos, String combos_info, String discount_code, double discount_percentage, String title, String poster_url, int showtime_id, String showtime, String theater_name, String room_name, int booking_id, int user_id, String booking_date, double sub_total_amount, int coupon_id, double total_amount, String status) {
        super(booking_id, user_id, booking_date, sub_total_amount, coupon_id, total_amount, status);
        this.num_of_tickets = num_of_tickets;
        this.seats_info = seats_info;
        this.num_of_combos = num_of_combos;
        this.combos_info = combos_info;
        this.coupon_code = discount_code;
        this.discount_percentage = discount_percentage;
        this.title = title;
        this.poster_url = poster_url;
        this.showtime_id = showtime_id;
        this.showtime = showtime;
        this.theater_name = theater_name;
        this.room_name = room_name;
    }

    public user_bookings(String title, String poster_url, String showtime, String theater_name, String room_name, int booking_id, int user_id, String booking_date, double sub_total_amount, int coupon_id, double total_amount, String status) {
        super(booking_id, user_id, booking_date, sub_total_amount, coupon_id, total_amount, status);
        this.title = title;
        this.poster_url = poster_url;
        this.showtime = showtime;
        this.theater_name = theater_name;
        this.room_name = room_name;
    }

    public int getNum_of_tickets() {
        return num_of_tickets;
    }

    public void setNum_of_tickets(int num_of_tickets) {
        this.num_of_tickets = num_of_tickets;
    }

    public String getSeats_info() {
        return seats_info;
    }

    public void setSeats_info(String seats_info) {
        this.seats_info = seats_info;
    }

    public int getNum_of_combos() {
        return num_of_combos;
    }

    public void setNum_of_combos(int num_of_combos) {
        this.num_of_combos = num_of_combos;
    }

    public String getCombos_info() {
        return combos_info;
    }

    public void setCombos_info(String combos_info) {
        this.combos_info = combos_info;
    }

    public double getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(double discount_percentage) {
        this.discount_percentage = discount_percentage;
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

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }

    public String getTheater_name() {
        return theater_name;
    }

    public void setTheater_name(String theater_name) {
        this.theater_name = theater_name;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public int getShowtime_id() {
        return showtime_id;
    }

    public void setShowtime_id(int showtime_id) {
        this.showtime_id = showtime_id;
    }

    
}
