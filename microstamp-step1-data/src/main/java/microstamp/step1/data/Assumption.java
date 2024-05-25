package microstamp.step1.data;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "assumptions")
@Table(name = "assumptions")
@Data
public class Assumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
