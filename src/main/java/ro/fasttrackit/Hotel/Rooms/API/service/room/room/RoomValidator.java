package ro.fasttrackit.Hotel.Rooms.API.service.room.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fasttrackit.Hotel.Rooms.API.exception.ValidationException;
import ro.fasttrackit.Hotel.Rooms.API.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

@Component
@RequiredArgsConstructor
public class RoomValidator {

    private final RoomRepository repo;

    private Optional<ValidationException> exists(long roomId) {
        return repo.existsById(roomId)
                ? empty()
                : Optional.of(new ValidationException(List.of("Room with id " + roomId + " does not exist.")));
    }

    public void validateExistsOrThrow(long roomId) {
        exists(roomId).ifPresent(ex -> {
            throw ex;
        });
    }
}
