/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author default
 */
public class User {
    private int user_id;
    private int role_id;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phone;
    private String birth_date;
    private int theater_id;
    private String status;
    
    public User(){
    }

    public User(int user_id, int role_id, String username, String password, String fullname, String email, String phone, String birth_date, int theater_id, String status) {
        this.user_id = user_id;
        this.role_id = role_id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.birth_date = birth_date;
        this.theater_id = theater_id;
        this.status = status;
    }

    public User(int role_id, String username, String password, String fullname, String email, String phone, String birth_date, int theater_id, String status) {
        this.role_id = role_id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.birth_date = birth_date;
        this.theater_id = theater_id;
        this.status = status;
    }

    public User(int user_id, int role_id, String username, String fullname, String email, String phone, String birth_date, int theater_id, String status) {
        this.user_id = user_id;
        this.role_id = role_id;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.birth_date = birth_date;
        this.theater_id = theater_id;
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public int getTheater_id() {
        return theater_id;
    }

    public void setTheater_id(int theater_id) {
        this.theater_id = theater_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "User{" + "ID=" + user_id + ", role=" + role_id + ", username=" + username + ", password=" + password + ", fullname=" + fullname + ", email=" + email + ", phone=" + phone + ", address=" + birth_date + '}';
    }
    
    
   
}
