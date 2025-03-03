/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author 84912
 */
public class rooms {
    private int room_id;
    private int theater_id;
    private int manager_id;
    private String room_name;
    private String img;
    private int capacity;
    private int type_id;
    
    public rooms(){
    }

    public rooms(int room_id, int theater_id, int manager_id, String room_name, String img, int capacity, int type_id) {
        this.room_id = room_id;
        this.theater_id = theater_id;
        this.manager_id = manager_id;
        this.room_name = room_name;
        this.img = img;
        this.capacity = capacity;
        this.type_id = type_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getTheater_id() {
        return theater_id;
    }

    public void setTheater_id(int theater_id) {
        this.theater_id = theater_id;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    @Override
    public String toString() {
        return "rooms{" + "room_id=" + room_id + ", theater_id=" + theater_id + ", manager_id=" + manager_id + ", room_name=" + room_name + ", img=" + img + ", capacity=" + capacity + ", type_id=" + type_id + '}';
    }
    
    
    
}
