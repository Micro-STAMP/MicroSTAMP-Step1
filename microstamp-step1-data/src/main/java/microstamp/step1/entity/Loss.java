package microstamp.step1.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity(name = "Loss")
@Table(name = "losses")
@Data
@NoArgsConstructor
public class Loss {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    private String name;

    private String code;

    @JdbcTypeCode(Types.VARCHAR)
    private UUID analysisId;

    public Loss(String name, String code, UUID analysisId) {
        this.name = name;
        this.code = code;
        this.analysisId = analysisId;
    }
}
