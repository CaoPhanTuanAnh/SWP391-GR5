/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author default
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class users {

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

    public users(int role_id, String username, String password, String fullname, String email, String phone, String birth_date) {
        this.role_id = role_id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.birth_date = birth_date;
    }

    
    
}
