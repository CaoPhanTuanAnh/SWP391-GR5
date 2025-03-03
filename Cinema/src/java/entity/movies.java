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
    private String[] participants;
    
    
    
    
    
    
}
