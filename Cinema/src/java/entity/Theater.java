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
    private int idManager;
    private String theaterName;
    private String theaterAddress;
    private String img;

    public Theater(int idTheater, int idCity, String theaterName, String theaterAddress, String img) {
        this.idTheater = idTheater;
        this.idCity = idCity;
        this.theaterName = theaterName;
        this.theaterAddress = theaterAddress;
        this.img = img;
    }

    public Theater() {
    }

    public Theater(int idTheater, int idCity, int idManager, String theaterName, String theaterAddress) {
        this.idTheater = idTheater;
        this.idCity = idCity;
        this.idManager = idManager;
        this.theaterName = theaterName;
        this.theaterAddress = theaterAddress;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
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
        return "Theater{" + "idTheater=" + idTheater + ", idCity=" + idCity + ", idManager=" + idManager + ", theaterName=" + theaterName + ", theaterAddress=" + theaterAddress + '}';
    }

}
