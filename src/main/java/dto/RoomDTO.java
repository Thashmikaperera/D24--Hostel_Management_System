package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class RoomDTO {
    private String roomId;
    private String roomType;
    private Double keyMoney;
    private int qty;
}
