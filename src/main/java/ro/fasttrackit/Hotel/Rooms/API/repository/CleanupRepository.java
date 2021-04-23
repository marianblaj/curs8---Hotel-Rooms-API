package ro.fasttrackit.Hotel.Rooms.API.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.Hotel.Rooms.API.model.entity.Cleanup;

public interface CleanupRepository extends JpaRepository<Cleanup, Long> {
    Page<Cleanup> findCleanupByRoomId(long roomId, Pageable pageable);
}
