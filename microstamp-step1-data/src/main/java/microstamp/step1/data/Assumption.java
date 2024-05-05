package microstamp.step1.data;

import jakarta.persistence.Entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity(name = "assumptions")
@Table(name = "assumptions")
public class Assumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Assumption() {
    }

    public Assumption(Long id, String name) {
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
        Assumption that = (Assumption) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Assumption{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
