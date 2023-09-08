package bo.custom.impl;

import bo.custom.RoomBO;
import dao.DAOFactory;
import dao.custom.RoomDAO;
import dto.RoomDTO;
import dto.StudentDTO;
import entity.Room;
import entity.Student;
import javafx.collections.FXCollections;
import tm.RoomTM;

import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO= (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public boolean saveRoom(RoomDTO roomDTO) {
        Room room=new Room();
        room.setRoomTypeId(roomDTO.getRoomId());
        room.setType(roomDTO.getRoomType());
        room.setKeyMoney(roomDTO.getKeyMoney());
        room.setQty(roomDTO.getQty());
        return roomDAO.save(room);
    }

    @Override
    public boolean updateRooms(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoomTypeId(roomDTO.getRoomId());
        room.setType(roomDTO.getRoomType());
        room.setKeyMoney(roomDTO.getKeyMoney());
        room.setQty(roomDTO.getQty());
        return roomDAO.update(room);
    }

    @Override
    public List<RoomDTO> getAllRoom() {
        List<RoomDTO> roomDTOArrayList = new ArrayList<>();
        List<Room> roomArrayList = roomDAO.getAll();
        for (Room room:roomArrayList) {
            roomDTOArrayList.add(new RoomDTO(room.getRoomTypeId(),room.getType(),room.getKeyMoney(),room.getQty()));
        }
        return roomDTOArrayList;
    }

    @Override
    public boolean deleteRoom(String roomId) {
        Room room = new Room();
        room.setRoomTypeId(roomId);
        return roomDAO.delete(room);
    }
}
