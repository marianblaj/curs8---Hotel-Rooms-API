package ro.fasttrackit.Hotel.Rooms.API.loader;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.fasttrackit.Hotel.Rooms.API.model.entity.CleaningProcedure;
import ro.fasttrackit.Hotel.Rooms.API.model.entity.Cleanup;
import ro.fasttrackit.Hotel.Rooms.API.model.entity.Room;
import ro.fasttrackit.Hotel.Rooms.API.model.entity.RoomFacilities;
import ro.fasttrackit.Hotel.Rooms.API.repository.CleanupRepository;
import ro.fasttrackit.Hotel.Rooms.API.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final RoomRepository roomRepository;
    private final CleanupRepository cleanupRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Room> rooms = roomRepository.saveAll(List.of(
                Room.builder()
                        .hotelName("Hotel1")
                        .etaj(1)
                        .number("10")
                        .roomFacilities(RoomFacilities.builder()
                                .doubleBed(true)
                                .tv(true).build())
                        .build(),
                Room.builder()
                        .hotelName("Hotel2")
                        .etaj(1)
                        .number("11")
                        .roomFacilities(RoomFacilities.builder()
                                .doubleBed(false)
                                .tv(false).build())
                        .build(),
                Room.builder()
                        .hotelName("Hotel3")
                        .etaj(2)
                        .number("12")
                        .roomFacilities(RoomFacilities.builder()
                                .doubleBed(true)
                                .tv(true).build())
                        .build(),
                Room.builder()
                        .hotelName("Hotel4")
                        .etaj(2)
                        .number("13")
                        .roomFacilities(RoomFacilities.builder()
                                .doubleBed(false)
                                .tv(false).build())
                        .build()));

        cleanupRepository.saveAll(List.of(
                Cleanup.builder()
                        .data(LocalDate.of(2021, 2, 20))
                        .cleaningProcedure(CleaningProcedure.builder()
                                .name("Everything")
                                .outCome(110)
                                .build())
                        .room(rooms.get(0))
                        .build(),
                Cleanup.builder()
                        .data(LocalDate.of(2021, 3, 21))
                        .cleaningProcedure(CleaningProcedure.builder()
                                .name("All Rooms")
                                .outCome(50)
                                .build())
                        .room(rooms.get(0))
                        .build(),
                Cleanup.builder()
                        .data(LocalDate.of(2021, 4, 15))
                        .cleaningProcedure(CleaningProcedure.builder()
                                .name("All cleanup")
                                .outCome(50)
                                .build())
                        .room(rooms.get(1))
                        .build(),
                Cleanup.builder()
                        .data(LocalDate.of(2021, 3, 11))
                        .cleaningProcedure(CleaningProcedure.builder()
                                .name("Total cleanup")
                                .outCome(40)
                                .build())
                        .room(rooms.get(1))
                        .build(),
                Cleanup.builder()
                        .data(LocalDate.of(2021, 4, 6))
                        .cleaningProcedure(CleaningProcedure.builder()
                                .name("Partial cleanup")
                                .outCome(20)
                                .build())
                        .room(rooms.get(2))
                        .build(),
                Cleanup.builder()
                        .data(LocalDate.of(2021, 4, 7))
                        .cleaningProcedure(CleaningProcedure.builder()
                                .name("General cleanup")
                                .outCome(10)
                                .build())
                        .room(rooms.get(3))
                        .build()
        ));
    }
}