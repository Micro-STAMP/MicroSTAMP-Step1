package microstamp.step1.repository;

import microstamp.step1.data.SystemGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemGoalRepository extends JpaRepository<SystemGoal, Long> {

    @Query(value = "SELECT * FROM system_goals WHERE project_id = ?1", nativeQuery = true)
    List<SystemGoal> findByProjectId(Long id);

}
