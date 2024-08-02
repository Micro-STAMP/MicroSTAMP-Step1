package microstamp.step1.repository;

import microstamp.step1.entity.Hazard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public interface HazardRepository extends JpaRepository<Hazard, UUID> {

    List<Hazard> findByAnalysisId(UUID id);

    @Query(value = "SELECT * FROM hazards WHERE father_id = ?1", nativeQuery = true)
    List<Hazard> findHazardChildren(String id);

    @Modifying
    @Query(value = "DELETE FROM hazard_loss WHERE hazard_id = ?1", nativeQuery = true)
    void deleteLossesAssociation(String id);

    @Modifying
    @Query(value = "DELETE FROM system_safety_constraint_hazard WHERE hazard_id = ?1", nativeQuery = true)
    void deleteSystemSafetyConstraintsAssociation(String id);
}
