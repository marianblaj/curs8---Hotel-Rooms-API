package ro.fasttrackit.Hotel.Rooms.API.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    private String mesaj;
    private int rating;
    private String turist;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Room room;

}
