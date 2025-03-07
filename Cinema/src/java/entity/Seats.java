
package entity;

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
public class seats {
    private int seat_id;
    private int room_id;
    private int level_id;
    private String seat_row;
    private int seat_number;
    private String status;

}
