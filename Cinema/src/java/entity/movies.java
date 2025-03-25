package entity;

import java.sql.Date;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class movies {

    private int movie_id;
    private String title;
    private String description;
    private String trailer_url;
    private String poster_url;
    private int duration;
    private Date release_date;
    private String status;
    private String[] participants;
    private String genres;
    private String[] actors;
    private String[] directors;
    private String actorsString;
    private String directorsString;
    
}
