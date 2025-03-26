/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author PCASUS
 */
public class coupon {
    private int coupon_id;
    private String coupon_code;
    private int user_id;
    private double discount_percentage;
    private Date expiry_date;

    public coupon() {
    }

    public coupon(int coupon_id, String coupon_code, int user_id, double discount_percentage, Date expiry_date) {
        this.coupon_id = coupon_id;
        this.coupon_code = coupon_code;
        this.user_id = user_id;
        this.discount_percentage = discount_percentage;
        this.expiry_date = expiry_date;
    }

    public int getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(double discount_percentage) {
        this.discount_percentage = discount_percentage;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }
    
    
    
    
}
