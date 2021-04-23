package ro.fasttrackit.Hotel.Rooms.API.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.Hotel.Rooms.API.model.room.CollectionResponse;
import ro.fasttrackit.Hotel.Rooms.API.model.room.RoomFilters;
import ro.fasttrackit.Hotel.Rooms.API.model.room.PageInfo;
import ro.fasttrackit.Hotel.Rooms.API.model.entity.Room;
import ro.fasttrackit.Hotel.Rooms.API.model.room.RoomPage;
import ro.fasttrackit.Hotel.Rooms.API.service.room.room.RoomService;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    CollectionResponse<Room> getAll(RoomPage roomPage, RoomFilters roomfilters) {

        Page<Room> page = roomService.getAll(roomPage, roomfilters);

        return CollectionResponse.<Room>builder()
                .content(page.getContent())
                .pageInfo(PageInfo.builder()
                        .totalPages(page.getTotalPages())
                        .totalElements(page.getNumberOfElements())
                        .crtPage(page.getNumber())
                        .pageSize(page.getSize())
                        .build())
                .build();
    }

    @GetMapping("/{roomId}")
    Optional<Room> getRoomId(@PathVariable long roomId) {
        return roomService.getRoomId(roomId);
    }

    @PatchMapping("/{roomId}")
    Room patchRoom(@RequestBody JsonPatch patch, @PathVariable long roomId) {
        return roomService.patchRoom(roomId, patch);
    }

    @DeleteMapping("/{roomId}")
    void deleteRoom(@PathVariable long roomId) {
        roomService.deleteRoom(roomId);
    }
}
