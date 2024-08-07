package microstamp.step1.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity(name = "Assumption")
@Table(name = "assumptions", uniqueConstraints = { @UniqueConstraint(columnNames = { "code", "analysis_id" }) })
@Data
@NoArgsConstructor
public class Assumption {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    private String name;

    private String code;

    @JdbcTypeCode(Types.VARCHAR)
    private UUID analysisId;

    public Assumption(String name, String code, UUID analysisId) {
        this.name = name;
        this.code = code;
        this.analysisId = analysisId;
    }
}
