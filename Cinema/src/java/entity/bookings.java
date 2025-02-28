/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author GIGABYTE
 */
public class bookings {

    private int booking_id;
    private int user_id;
    private String booking_date;
    private double sub_total_amount;
    private int coupon_id;
    private double total_amount;
    private String status;

    public bookings() {
    }

    public bookings(int booking_id, int user_id, String booking_date, double sub_total_amount, int coupon_id, double total_amount, String status) {
        this.booking_id = booking_id;
        this.user_id = user_id;
        this.booking_date = booking_date;
        this.sub_total_amount = sub_total_amount;
        this.coupon_id = coupon_id;
        this.total_amount = total_amount;
        this.status = status;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public double getSub_total_amount() {
        return sub_total_amount;
    }

    public void setSub_total_amount(double sub_total_amount) {
        this.sub_total_amount = sub_total_amount;
    }

    public int getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
