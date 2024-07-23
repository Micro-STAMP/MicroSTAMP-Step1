package microstamp.step1.data;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "Role")
@Table(name = "roles")
@Data
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

}
