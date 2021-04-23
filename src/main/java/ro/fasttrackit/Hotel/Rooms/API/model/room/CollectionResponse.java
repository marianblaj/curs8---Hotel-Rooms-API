package ro.fasttrackit.Hotel.Rooms.API.model.room;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CollectionResponse<T> {
    List<T> content;
    PageInfo pageInfo;
}
