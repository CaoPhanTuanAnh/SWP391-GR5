/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author PCASUS
 */
public class Seats {
    private int seat_id;
    private int room_id;
    private int level_id;
    private String seat_row;
    private int seat_number;
    private String status;

    public Seats() {
    }

    public Seats(int seat_id, int room_id, int level_id, String seat_row, int seat_number, String status) {
        this.seat_id = seat_id;
        this.room_id = room_id;
        this.level_id = level_id;
        this.seat_row = seat_row;
        this.seat_number = seat_number;
        this.status = status;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getLevel_id() {
        return level_id;
    }

    public void setLevel_id(int level_id) {
        this.level_id = level_id;
    }

    public String getSeat_row() {
        return seat_row;
    }

    public void setSeat_row(String seat_row) {
        this.seat_row = seat_row;
    }

    public int getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(int seat_number) {
        this.seat_number = seat_number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
          
}
