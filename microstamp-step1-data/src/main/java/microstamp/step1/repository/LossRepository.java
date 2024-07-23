package microstamp.step1.repository;

import microstamp.step1.data.Loss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface LossRepository extends JpaRepository<Loss, Long> {

    @Query(value = "SELECT * FROM losses WHERE project_id = ?1", nativeQuery = true)
    List<Loss> findByProjectId(Long id);

    @Modifying
    @Query(value = "DELETE FROM hazard_loss WHERE loss_id = ?1", nativeQuery = true)
    void deleteHazardsAssociation(Long id);
}
