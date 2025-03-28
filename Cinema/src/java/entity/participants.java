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
public class participants {

    private int participant_id;
    private String participant_name;
    private String portrait_url;
    private Date birth_date;
    private String nationality;
    private String about;
    private String role;
    
    
    
    
    
}
