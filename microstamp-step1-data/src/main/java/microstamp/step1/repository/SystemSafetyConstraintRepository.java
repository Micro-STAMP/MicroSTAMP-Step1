package microstamp.step1.repository;

import microstamp.step1.entity.SystemSafetyConstraint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public interface SystemSafetyConstraintRepository extends JpaRepository<SystemSafetyConstraint, UUID> {

    List<SystemSafetyConstraint> findByAnalysisId(UUID id);

    @Modifying
    @Query(value = "DELETE FROM system_safety_constraint_hazard WHERE system_safety_constraint_id = ?1", nativeQuery = true)
    void deleteHazardsAssociation(String id);

}
