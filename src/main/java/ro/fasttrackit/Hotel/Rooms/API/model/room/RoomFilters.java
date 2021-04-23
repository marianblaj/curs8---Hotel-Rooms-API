package ro.fasttrackit.Hotel.Rooms.API.model.room;

import lombok.Value;

@Value
public class RoomFilters {

    String number;
    String etaj;
    String hotelName;
    String tv;
    String doubleBed;
}
