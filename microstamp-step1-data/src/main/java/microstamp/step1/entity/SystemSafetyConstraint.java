package microstamp.step1.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.List;
import java.util.UUID;

@Entity(name = "SystemSafetyConstraint")
@Table(name = "system_safety_constraints", uniqueConstraints = { @UniqueConstraint(columnNames = { "code", "analysis_id" }) })
@Data
@NoArgsConstructor
public class SystemSafetyConstraint {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    private String name;

    private String code;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "system_safety_constraint_hazard",
            joinColumns = @JoinColumn(name = "system_safety_constraint_id"),
            inverseJoinColumns = @JoinColumn(name = "hazard_id")
    )
    private List<Hazard> hazardEntities;

    @JdbcTypeCode(Types.VARCHAR)
    private UUID analysisId;

    public SystemSafetyConstraint(String name, String code, UUID analysisId) {
        this.name = name;
        this.code = code;
        this.analysisId = analysisId;
    }
}
