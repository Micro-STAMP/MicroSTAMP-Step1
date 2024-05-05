package microstamp.step1.data;

import jakarta.persistence.*;
import java.util.Objects;

@Entity(name = "system_goals")
@Table(name = "system_goals")
public class SystemGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public SystemGoal() {
    }

    public SystemGoal(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemGoal that = (SystemGoal) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "SystemGoal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
