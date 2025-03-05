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
 * @author 84912
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class theaters {

    private int theater_id;
    private int city_id;
    private int director_id;
    private String theater_name;
    private String img;
    private String address;

    public theaters(int theater_id, int city_id, String theater_name, String img, String address) {
        this.theater_id = theater_id;
        this.city_id = city_id;
        this.theater_name = theater_name;
        this.img = img;
        this.address = address;
    }

    public theaters(int theater_id, int director_id, String theater_name) {
        this.theater_id = theater_id;
        this.director_id = director_id;
        this.theater_name = theater_name;
    }

    
}
