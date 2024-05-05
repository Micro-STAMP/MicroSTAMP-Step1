package microstamp.step1.repository;

import microstamp.step1.data.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByUrl(String url);

    @Query(value = "SELECT * FROM projects p WHERE p.user_id = ?1", nativeQuery = true)
    Optional<List<Project>> findProjectsByUserId(long id);

    @Query(value = "SELECT * FROM projects p WHERE p.user_id = 3", nativeQuery = true)
    Optional<List<Project>> findProjectsForGuests();

}
