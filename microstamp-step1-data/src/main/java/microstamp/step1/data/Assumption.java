package microstamp.step1.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity(name = "Assumption")
@Table(name = "assumptions")
@Data
@NoArgsConstructor
public class Assumption {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    private String name;

    @JdbcTypeCode(Types.VARCHAR)
    private UUID analysisId;

    public Assumption(String name, UUID analysisId) {
        this.name = name;
        this.analysisId = analysisId;
    }
}
