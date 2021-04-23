package ro.fasttrackit.Hotel.Rooms.API.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cleanup {

    @Id
    @GeneratedValue
    private long id;

    LocalDate data;

    @ManyToOne
    Room room;

    @OneToOne(cascade = CascadeType.PERSIST)
    CleaningProcedure cleaningProcedure;
}
