package ro.fasttrackit.Hotel.Rooms.API.service.room.room.cleanup;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.fasttrackit.Hotel.Rooms.API.model.entity.Cleanup;
import ro.fasttrackit.Hotel.Rooms.API.repository.CleanupRepository;
import ro.fasttrackit.Hotel.Rooms.API.service.room.room.RoomValidator;

@Service
@AllArgsConstructor
public class CleanupService {

    private final CleanupRepository repo;
    private final RoomValidator validator;

    public Page<Cleanup> getCleanupRoomId(long roomId, Pageable pageable) {
        validator.validateExistsOrThrow(roomId);
        return repo.findCleanupByRoomId(roomId, pageable);
    }
}
