package nl.bo.techiteasydeel1.repositories;

import nl.bo.techiteasydeel1.models.RemoteController;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemoteControllerRepository extends JpaRepository<RemoteController, Long> {
}
