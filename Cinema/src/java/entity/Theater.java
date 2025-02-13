/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author 84912
 */
public class Theater {
    private int idTheater;
    private int idCity;
    private String theaterName;
    private String theaterAddress;
    
    public Theater(){
    }

    public Theater(int idTheater, int idCity, String theaterName, String theaterAddress) {
        this.idTheater = idTheater;
        this.idCity = idCity;
        this.theaterName = theaterName;
        this.theaterAddress = theaterAddress;
    }

    public int getIdTheater() {
        return idTheater;
    }

    public void setIdTheater(int idTheater) {
        this.idTheater = idTheater;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getTheaterAddress() {
        return theaterAddress;
    }

    public void setTheaterAddress(String theaterAddress) {
        this.theaterAddress = theaterAddress;
    }

    @Override
    public String toString() {
        return "Theater{" + "idTheater=" + idTheater + ", idCity=" + idCity + ", theaterName=" + theaterName + ", theaterAddress=" + theaterAddress + '}';
    }
    
    
}
