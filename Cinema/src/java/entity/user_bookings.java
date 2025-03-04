/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author GIGABYTE
 */
public class user_bookings extends bookings{

    private int num_of_tickets;
    private String seats_info;
    private int num_of_combos;
    private String combos_info;
    private String title;

    public user_bookings() {
    }

    public user_bookings(int num_of_tickets, String seats_info, int num_of_combos, String combos_info, String title, int booking_id, int user_id, Date booking_date, double sub_total_amount, int coupon_id, double total_amount, String status) {
        super(booking_id, user_id, booking_date, sub_total_amount, coupon_id, total_amount, status);
        this.num_of_tickets = num_of_tickets;
        this.seats_info = seats_info;
        this.num_of_combos = num_of_combos;
        this.combos_info = combos_info;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
}
