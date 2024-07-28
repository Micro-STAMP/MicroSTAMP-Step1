package microstamp.step1.repository;

import microstamp.step1.data.Loss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public interface LossRepository extends JpaRepository<Loss, UUID> {

    @Query(value = "SELECT * FROM losses WHERE project_id = ?1", nativeQuery = true)
    List<Loss> findByProjectId(String id);

    @Modifying
    @Query(value = "DELETE FROM hazard_loss WHERE loss_id = ?1", nativeQuery = true)
    void deleteHazardsAssociation(String id);
}
