package ro.fasttrackit.Hotel.Rooms.API.model.room;

import lombok.Value;
import org.springframework.data.domain.Sort;

@Value
public class RoomPage {
    int pageNumber = 0;
    int pageSize = 10;
    Sort.Direction sortDirection = Sort.Direction.ASC;
    String sortBy="number";
}

