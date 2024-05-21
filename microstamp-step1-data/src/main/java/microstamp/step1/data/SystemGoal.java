package microstamp.step1.data;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "system_goals")
@Table(name = "system_goals")
@Data
public class SystemGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
