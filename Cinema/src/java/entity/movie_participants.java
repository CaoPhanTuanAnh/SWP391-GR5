
package entity;


import java.sql.Date;
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
public class movie_participants {
    private int movie_id;
    private int participant_id;
    private String role_in_movie; // "director" hoặc "actor"
}


