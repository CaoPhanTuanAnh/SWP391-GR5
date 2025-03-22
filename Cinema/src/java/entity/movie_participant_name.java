/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


public class movie_participant_name extends movie_participants {
    private String title;
    private String participant_name;

    public movie_participant_name() {
    }

    public movie_participant_name(int movie_id, String title, int participant_id, String participant_name,   String role_in_movie) {
        super(movie_id, participant_id, role_in_movie);
        this.title = title;
        this.participant_name = participant_name;
    }

    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParticipant_name() {
        return participant_name;
    }

    public void setParticipant_name(String participant_name) {
        this.participant_name = participant_name;
    }
    
    
}
