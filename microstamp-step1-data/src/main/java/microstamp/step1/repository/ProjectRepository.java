package microstamp.step1.repository;

import microstamp.step1.data.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {

    Project findByUrl(String url);

    @Query(value = "SELECT * FROM projects p WHERE p.user_id = ?1", nativeQuery = true)
    List<Project> findProjectsByUserId(String id);

    @Query(value = "SELECT * FROM projects p WHERE p.user_id = '2e776941-de10-4aca-98bb-5dabac287229'", nativeQuery = true)
    List<Project> findProjectsForGuests();

}
