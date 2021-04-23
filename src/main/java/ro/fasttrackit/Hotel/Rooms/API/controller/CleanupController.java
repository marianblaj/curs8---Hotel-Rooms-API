package ro.fasttrackit.Hotel.Rooms.API.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.fasttrackit.Hotel.Rooms.API.model.entity.Cleanup;
import ro.fasttrackit.Hotel.Rooms.API.model.room.CollectionResponse;
import ro.fasttrackit.Hotel.Rooms.API.model.room.PageInfo;
import ro.fasttrackit.Hotel.Rooms.API.service.room.room.cleanup.CleanupService;

@AllArgsConstructor
@RestController
@RequestMapping("/cleanups")
public class CleanupController {

    private final CleanupService cleanupService;

    @GetMapping("/{roomId}")
    CollectionResponse<Cleanup> getCleanupsRoomId(@PathVariable long roomId, Pageable pageable) {
        Page<Cleanup> page = cleanupService.getCleanupRoomId(roomId, pageable);

        return CollectionResponse.<Cleanup>builder()
                .content(page.getContent())
                .pageInfo(PageInfo.builder()
                        .totalPages(page.getTotalPages())
                        .totalElements(page.getNumberOfElements())
                        .crtPage(page.getNumber())
                        .pageSize(page.getSize())
                        .build())
                .build();
    }
}
