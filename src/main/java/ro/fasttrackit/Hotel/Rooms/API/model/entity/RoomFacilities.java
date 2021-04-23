package ro.fasttrackit.Hotel.Rooms.API.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomFacilities {

    @Id
    @GeneratedValue
    private Long id;

    private boolean tv;
    private boolean doubleBed;
}
