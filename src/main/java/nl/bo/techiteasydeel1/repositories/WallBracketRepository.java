package nl.bo.techiteasydeel1.repositories;

import nl.bo.techiteasydeel1.models.WallBracket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WallBracketRepository extends JpaRepository <WallBracket, Long> {
}
