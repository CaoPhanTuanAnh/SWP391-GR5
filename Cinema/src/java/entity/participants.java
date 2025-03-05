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
public class participants {

    private int participant_id;
    private String participant_name;
    private String portrait_url;
    private Date birth_date;
    private String nationality;
    private String about;

    public participants(int participant_id, String participant_name, String portrait_url, Date birth_date, String nationality, String about) {
        this.participant_id = participant_id;
        this.participant_name = participant_name;
        this.portrait_url = portrait_url;
        this.birth_date = birth_date;
        this.nationality = nationality;
        this.about = about;
    }

    public participants() {
    }

    public int getParticipant_id() {
        return participant_id;
    }

    public void setParticipantid(int participant) {
        this.participant_id = participant;
    }

    public String getParticipant_name() {
        return participant_name;
    }

    public void setParticipant_name(String participant_name) {
        this.participant_name = participant_name;
    }

    public String getPortrait_url() {
        return portrait_url;
    }

    public void setPortrait_url(String portrait_url) {
        this.portrait_url = portrait_url;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

}
