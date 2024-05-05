package microstamp.step1.data;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "hazards")
@Table(name = "hazards")
public class Hazard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "hazard_loss",
            joinColumns = @JoinColumn(name = "hazard_id"),
            inverseJoinColumns = @JoinColumn(name = "loss_id")
    )
    private List<Loss> lossEntities;

    @ManyToOne
    @JoinColumn(name = "father_id")
    private Hazard father;

    public Hazard() {
    }

    public Hazard(Long id, String name, List<Loss> lossEntities, Hazard father) {
        this.id = id;
        this.name = name;
        this.lossEntities = lossEntities;
        this.father = father;
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

    public List<Loss> getLossEntities() {
        return lossEntities;
    }

    public void setLossEntities(List<Loss> lossEntities) {
        this.lossEntities = lossEntities;
    }

    public Hazard getFather() {
        return father;
    }

    public void setFather(Hazard father) {
        this.father = father;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hazard that = (Hazard) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(lossEntities, that.lossEntities) &&
                Objects.equals(father, that.father);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lossEntities, father);
    }

    @Override
    public String toString() {
        return "Hazard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lossEntities=" + lossEntities +
                ", father=" + father +
                '}';
    }
}
