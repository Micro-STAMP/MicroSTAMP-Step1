package microstamp.step1.repository;

import microstamp.step1.data.Loss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LossRepository extends JpaRepository<Loss, Long> {

    @Query(value = "SELECT * FROM losses WHERE project_id = ?1", nativeQuery = true)
    Optional<List<Loss>> findByProjectId(Long id);

}
