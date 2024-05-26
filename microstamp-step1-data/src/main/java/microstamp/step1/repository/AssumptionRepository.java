package microstamp.step1.repository;

import microstamp.step1.data.Assumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssumptionRepository extends JpaRepository<Assumption, Long> {

    @Query(value = "SELECT * FROM assumptions WHERE project_id = ?1", nativeQuery = true)
    List<Assumption> findByProjectId(Long id);

}
