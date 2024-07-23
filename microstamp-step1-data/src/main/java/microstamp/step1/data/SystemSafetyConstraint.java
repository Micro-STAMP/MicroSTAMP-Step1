package microstamp.step1.data;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "SystemSafetyConstraint")
@Table(name = "system_safety_constraints")
@Data
public class SystemSafetyConstraint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "system_safety_constraint_hazard",
            joinColumns = @JoinColumn(name = "system_safety_constraint_id"),
            inverseJoinColumns = @JoinColumn(name = "hazard_id")
    )
    private List<Hazard> hazardEntities;

}
