package entity;

import java.sql.Date;
import java.sql.Time;
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
public class showtimes {
    private int showtime_id;
    private int movie_id;
    private int room_id;
    private Date date;
    private Time time;
    private String status;

}
