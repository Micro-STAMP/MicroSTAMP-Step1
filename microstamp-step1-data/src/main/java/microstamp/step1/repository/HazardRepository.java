package microstamp.step1.repository;

import microstamp.step1.data.Hazard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface HazardRepository extends JpaRepository<Hazard, Long> {

    @Query(value = "SELECT * FROM hazards WHERE project_id = ?1", nativeQuery = true)
    List<Hazard> findByProjectId(Long id);

    @Query(value = "SELECT * FROM hazards WHERE father_id = ?1", nativeQuery = true)
    List<Hazard> findHazardChildren(Long id);

    @Modifying
    @Query(value = "DELETE FROM hazard_loss WHERE hazard_id = ?1", nativeQuery = true)
    void deleteLossesAssociation(Long id);

    @Modifying
    @Query(value = "DELETE FROM system_safety_constraint_hazard WHERE hazard_id = ?1", nativeQuery = true)
    void deleteSystemSafetyConstraintsAssociation(Long id);
}
