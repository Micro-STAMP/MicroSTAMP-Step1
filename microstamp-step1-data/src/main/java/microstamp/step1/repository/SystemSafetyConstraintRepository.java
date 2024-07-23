package microstamp.step1.repository;

import microstamp.step1.data.SystemSafetyConstraint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface SystemSafetyConstraintRepository extends JpaRepository<SystemSafetyConstraint, Long> {

    @Query(value = "SELECT * FROM system_safety_constraints WHERE project_id = ?1", nativeQuery = true)
    List<SystemSafetyConstraint> findByProjectId(Long id);

    @Modifying
    @Query(value = "DELETE FROM system_safety_constraint_hazard WHERE system_safety_constraint_id = ?1", nativeQuery = true)
    void deleteHazardsAssociation(Long id);

}
