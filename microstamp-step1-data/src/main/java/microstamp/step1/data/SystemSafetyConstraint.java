package microstamp.step1.data;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "system_safety_constraints")
@Table(name = "system_safety_constraints")
public class SystemSafetyConstraint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "system_safety_constraint_hazard",
            joinColumns = @JoinColumn(name = "system_safety_constraint_id"),
            inverseJoinColumns = @JoinColumn(name = "hazard_id")
    )
    private List<Hazard> hazardEntities;


    public SystemSafetyConstraint() {
    }

    public SystemSafetyConstraint(Long id, String name, List<Hazard> hazardEntities) {
        this.id = id;
        this.name = name;
        this.hazardEntities = hazardEntities;
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

    public List<Hazard> getHazardEntities() {
        return hazardEntities;
    }

    public void setHazardEntities(List<Hazard> hazardEntities) {
        this.hazardEntities = hazardEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemSafetyConstraint that = (SystemSafetyConstraint) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(hazardEntities, that.hazardEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hazardEntities);
    }

    @Override
    public String toString() {
        return "SystemSafetyConstraint{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hazardEntities=" + hazardEntities +
                '}';
    }
}
