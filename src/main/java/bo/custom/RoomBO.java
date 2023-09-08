package bo.custom;

import bo.SuperBO;
import dto.RoomDTO;

import java.util.List;

public interface RoomBO extends SuperBO {
    boolean saveRoom(RoomDTO roomDTO);

    boolean updateRooms(RoomDTO roomDTO);

    List<RoomDTO> getAllRoom();

    boolean deleteRoom(String roomId);
}
