package microstamp.step1.data;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "Loss")
@Table(name = "losses")
@Data
public class Loss {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
