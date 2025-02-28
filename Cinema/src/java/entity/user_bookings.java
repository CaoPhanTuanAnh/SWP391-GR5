/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author GIGABYTE
 */
public class user_bookings extends bookings{
    private int num_of_tickets;
    private int num_of_combos;
    private String title;

    public user_bookings() {
    }

    public user_bookings(int num_of_tickets, int num_of_combos, String title, int booking_id, int user_id, String booking_date, double sub_total_amount, int coupon_id, double total_amount, String status) {
        super(booking_id, user_id, booking_date, sub_total_amount, coupon_id, total_amount, status);
        this.num_of_tickets = num_of_tickets;
        this.num_of_combos = num_of_combos;
        this.title = title;
    }

    public int getNum_of_tickets() {
        return num_of_tickets;
    }

    public void setNum_of_tickets(int num_of_tickets) {
        this.num_of_tickets = num_of_tickets;
    }

    public int getNum_of_combos() {
        return num_of_combos;
    }

    public void setNum_of_combos(int num_of_combos) {
        this.num_of_combos = num_of_combos;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
}
