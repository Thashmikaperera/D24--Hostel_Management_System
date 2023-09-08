package tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class RoomTM {
    private String roomId;
    private String roomType;
    private Double keyMoney;
    private int qty;
}
