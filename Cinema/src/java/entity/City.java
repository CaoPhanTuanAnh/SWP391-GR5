/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author GIGABYTE
 */
public class City {
    private int cityID;
    private String cityName;
    private int numOfTheater;

    public City() {
    }

    public City(int cityID, String cityName, int numOfTheater) {
        this.cityID = cityID;
        this.cityName = cityName;
        this.numOfTheater = numOfTheater;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int CityID) {
        this.cityID = CityID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String CityName) {
        this.cityName = CityName;
    }

    public int getNumOfTheater() {
        return numOfTheater;
    }

    public void setNumOfTheater(int numOfTheater) {
        this.numOfTheater = numOfTheater;
    }
    
    
    
}
