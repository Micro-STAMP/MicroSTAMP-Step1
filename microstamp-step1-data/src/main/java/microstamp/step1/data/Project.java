package microstamp.step1.data;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "projects")
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String url;

    private String type;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private List<Hazard> hazardEntities;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private List<Loss> lossEntities;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private List<SystemGoal> systemGoalEntities;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private List<Assumption> assumptionEntities;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private List<SystemSafetyConstraint> systemSafetyConstraintEntities;

    public Project() {
    }

    public Project(Long id, String name, String description, String url, String type, List<Hazard> hazardEntities, List<Loss> lossEntities, List<SystemGoal> systemGoalEntities, List<Assumption> assumptionEntities, List<SystemSafetyConstraint> systemSafetyConstraintEntities) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.type = type;
        this.hazardEntities = hazardEntities;
        this.lossEntities = lossEntities;
        this.systemGoalEntities = systemGoalEntities;
        this.assumptionEntities = assumptionEntities;
        this.systemSafetyConstraintEntities = systemSafetyConstraintEntities;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Hazard> getHazardEntities() {
        return hazardEntities;
    }

    public void setHazardEntities(List<Hazard> hazardEntities) {
        this.hazardEntities = hazardEntities;
    }

    public List<Loss> getLossEntities() {
        return lossEntities;
    }

    public void setLossEntities(List<Loss> lossEntities) {
        this.lossEntities = lossEntities;
    }

    public List<SystemGoal> getSystemGoalEntities() {
        return systemGoalEntities;
    }

    public void setSystemGoalEntities(List<SystemGoal> systemGoalEntities) {
        this.systemGoalEntities = systemGoalEntities;
    }

    public List<Assumption> getAssumptionEntities() {
        return assumptionEntities;
    }

    public void setAssumptionEntities(List<Assumption> assumptionEntities) {
        this.assumptionEntities = assumptionEntities;
    }

    public List<SystemSafetyConstraint> getSystemSafetyConstraintEntities() {
        return systemSafetyConstraintEntities;
    }

    public void setSystemSafetyConstraintEntities(List<SystemSafetyConstraint> systemSafetyConstraintEntities) {
        this.systemSafetyConstraintEntities = systemSafetyConstraintEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project that = (Project) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(url, that.url) &&
                Objects.equals(type, that.type) &&
                Objects.equals(hazardEntities, that.hazardEntities) &&
                Objects.equals(lossEntities, that.lossEntities) &&
                Objects.equals(systemGoalEntities, that.systemGoalEntities) &&
                Objects.equals(assumptionEntities, that.assumptionEntities) &&
                Objects.equals(systemSafetyConstraintEntities, that.systemSafetyConstraintEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, url, type, hazardEntities, lossEntities, systemGoalEntities, assumptionEntities, systemSafetyConstraintEntities);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", hazardEntities=" + hazardEntities +
                ", lossEntities=" + lossEntities +
                ", systemGoalEntities=" + systemGoalEntities +
                ", assumptionEntities=" + assumptionEntities +
                ", systemSafetyConstraintEntities=" + systemSafetyConstraintEntities +
                '}';
    }
}
