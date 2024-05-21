package microstamp.step1.data;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "hazards")
@Table(name = "hazards")
@Data
public class Hazard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "hazard_loss",
            joinColumns = @JoinColumn(name = "hazard_id"),
            inverseJoinColumns = @JoinColumn(name = "loss_id")
    )
    private List<Loss> lossEntities;

    @ManyToOne
    @JoinColumn(name = "father_id")
    private Hazard father;

}
