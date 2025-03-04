/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author PCASUS
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class movies {
    private int movie_id;
    private String title;
    private int genre_id;
    private String director;
    private String actors;
    private String description;
    private String trailer_url;
    private String poster_url;
    private int duration;
    private Date release_date;
    private LocalDateTime create_at;
    private LocalDateTime updated_at;
    
    
    
    
    
    
}
