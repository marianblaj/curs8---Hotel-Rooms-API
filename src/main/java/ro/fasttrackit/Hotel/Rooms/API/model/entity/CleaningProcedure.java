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
public class CleaningProcedure {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int outCome;

    @ManyToOne
    private Cleanup cleanup;
}
