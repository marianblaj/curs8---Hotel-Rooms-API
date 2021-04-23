package ro.fasttrackit.Hotel.Rooms.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.Hotel.Rooms.API.model.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
