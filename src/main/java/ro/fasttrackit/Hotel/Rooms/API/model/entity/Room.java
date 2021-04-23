package ro.fasttrackit.Hotel.Rooms.API.model.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    private String number;
    private int etaj;
    private String hotelName;

    @OneToOne(cascade = CascadeType.PERSIST)
    private RoomFacilities roomFacilities;

}
