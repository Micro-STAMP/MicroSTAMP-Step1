package microstamp.step1.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.List;
import java.util.UUID;

@Entity(name = "Hazard")
@Table(name = "hazards")
@Data
@NoArgsConstructor
public class Hazard {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

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

    @JdbcTypeCode(Types.VARCHAR)
    private UUID analysisId;

    public Hazard(String name, UUID analysisId) {
        this.name = name;
        this.analysisId = analysisId;
    }
}
