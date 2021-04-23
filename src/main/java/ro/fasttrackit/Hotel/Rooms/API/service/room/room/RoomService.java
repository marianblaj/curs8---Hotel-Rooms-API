package ro.fasttrackit.Hotel.Rooms.API.service.room.room;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ro.fasttrackit.Hotel.Rooms.API.exception.ResourceNotFoundException;
import ro.fasttrackit.Hotel.Rooms.API.model.entity.RoomFacilities;
import ro.fasttrackit.Hotel.Rooms.API.model.room.RoomFilters;
import ro.fasttrackit.Hotel.Rooms.API.model.entity.Room;
import ro.fasttrackit.Hotel.Rooms.API.model.room.RoomPage;
import ro.fasttrackit.Hotel.Rooms.API.repository.RoomDao;
import ro.fasttrackit.Hotel.Rooms.API.repository.RoomRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository repo;
    private final RoomDao roomDao;
    private final RoomValidator validator;
    private final ObjectMapper mapper;

    public Page<Room> getAll(RoomPage roomPage, RoomFilters roomFilters) {
        return roomDao.getAllWithFilters(roomPage, roomFilters);
    }

    public Optional<Room> getRoomId(long roomId) {
        validator.validateExistsOrThrow(roomId);
        return repo.findById(roomId);
    }

    @SneakyThrows
    public Room patchRoom(long roomId, JsonPatch patch) {
        validator.validateExistsOrThrow(roomId);
        Room dbRoom = repo.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn`t find room with id " + roomId));

        JsonNode patchedRoomJson = patch.apply(mapper.valueToTree(dbRoom));
        Room patchedRoom = mapper.treeToValue(patchedRoomJson, Room.class);
        return replaceRoom(roomId, patchedRoom);
    }

    private Room replaceRoom(long roomId, Room newRoom) {
        newRoom.setId(roomId);
        Room dbRoom = repo.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn`t find room with id " + roomId));
        copyRoom(newRoom, dbRoom);
        return repo.save(dbRoom);
    }

    private void copyRoom(Room newRoom, Room dbRoom) {
        dbRoom.setNumber(newRoom.getNumber());
        dbRoom.setEtaj(newRoom.getEtaj());
        dbRoom.setHotelName(newRoom.getHotelName());
        dbRoom.setRoomFacilities(RoomFacilities.builder()
                .tv(newRoom.getRoomFacilities().isTv())
                .doubleBed(newRoom.getRoomFacilities().isDoubleBed()).build());
    }

    public void deleteRoom(long roomId) {
        validator.validateExistsOrThrow(roomId);
        repo.deleteById(roomId);
    }
}
