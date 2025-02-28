/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author GIGABYTE
 */
public class cities {
    private int city_id;
    private String city_name;
    private int numOfTheater;

    public cities() {
    }

    public cities(int city_id, String city_name, int numOfTheater) {
        this.city_id = city_id;
        this.city_name = city_name;
        this.numOfTheater = numOfTheater;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public int getNumOfTheater() {
        return numOfTheater;
    }

    public void setNumOfTheater(int numOfTheater) {
        this.numOfTheater = numOfTheater;
    }

    
    
    
}
