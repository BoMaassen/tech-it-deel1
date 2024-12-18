package nl.bo.techiteasydeel1.repositories;

import nl.bo.techiteasydeel1.models.CIModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CIModulesRepository extends JpaRepository<CIModule, Long> {
}
