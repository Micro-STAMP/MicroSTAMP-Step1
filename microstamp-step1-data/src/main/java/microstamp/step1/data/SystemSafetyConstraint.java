package microstamp.step1.data;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.List;
import java.util.UUID;

@Entity(name = "SystemSafetyConstraint")
@Table(name = "system_safety_constraints")
@Data
public class SystemSafetyConstraint {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "system_safety_constraint_hazard",
            joinColumns = @JoinColumn(name = "system_safety_constraint_id"),
            inverseJoinColumns = @JoinColumn(name = "hazard_id")
    )
    private List<Hazard> hazardEntities;

}
